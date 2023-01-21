package com.isep.game;

import com.isep.game.cards.*;
import com.isep.game.tokens.ProgressToken;
import com.isep.game.tokens.ProgressTokenStack;
import com.isep.game.wonders.*;
import com.isep.utils.InputParser;
import com.isep.utils.OutputManager;
import jdk.jshell.spi.ExecutionControl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game
{
    // Attributes
    private InputParser inputParser;
    private OutputManager outputManager;
    private List<Player> players;
    /**
     * The central {@link Deck} in which every {@link Player} can pick a {@link Card}.
     */
    private Deck centralDeck;
    /**
     * The {@link Deck} containing the discarded {@link Card}s.
     */
    private Deck discard;
    /**
     * The {@link ProgressTokenStack} in which every {@link Player} can pick a {@link ProgressToken} when possible.
     */
    private ProgressTokenStack progressTokenStack;
    /**
     * The total amount of conflict tokens of this {@link Game}.
     */
    private int conflictTokensAmount;
    /**
     * The amount of conflict tokens that have their battle-side up.
     */
    private int conflictTokensBattleSide;
    private boolean onePlayerBuiltItsWonder;

    // Constructor
    public Game(InputParser inputParser, OutputManager outputManager)
    {
        this.inputParser = inputParser;
        this.outputManager = outputManager;
        this.players = new ArrayList<Player>();
        this.centralDeck = new Deck();
        this.buildDeck();
        this.centralDeck.shuffle();
        this.discard = new Deck();
        this.progressTokenStack = new ProgressTokenStack();
        this.buildProgressTokenStack();
        this.progressTokenStack.shuffle();
        this.conflictTokensBattleSide = 0;
        this.onePlayerBuiltItsWonder = false;
    }

    // Getters & Setters
    public Deck getDiscard()
    {
        return this.discard;
    }

    // Methods
    /**
     * Starts the {@link Game} and stops automatically when a {@link Player}'s {@link Wonder} has been entirely constructed.
     * @throws ExecutionControl.NotImplementedException
     * @author Quentin LAURENT
     */
    public void play() throws ExecutionControl.NotImplementedException
    {
        this.initialize();

        while(!this.onePlayerBuiltItsWonder)
        {
            for(Player player: this.players)
            {
                this.outputManager.displayPlayerTurn(player);

                int cardsToPick = 1;
                while(cardsToPick > 0)
                {
                    cardsToPick--;

                    // Pick a card from one of the three available decks
                    // (the Player's deck (aka the left deck), the next player's deck (aka the right deck) or the central deck
                    int nextPlayerIndex = this.players.indexOf(player) + 1;
                    if(nextPlayerIndex >= this.players.size())
                        nextPlayerIndex = 0;
                    Card pickedCard = this.inputParser.fetchCardFromDeck(player.getDeck(), this.players.get(nextPlayerIndex).getDeck(), this.centralDeck);
                    player.getHand().addCard(pickedCard);

                    this.outputManager.displayPlayerHand(player);

                    // Checks if the player can build a Stage of its Wonder
                    // If possible, it builds the first available Stage that can be built
                    if(player.getHand().canBuildStage(player.getWonder().getNextStagesToBuild(), player.hasProgressTokenEffect(ProgressToken.Effect.ECONOMY)))
                    {
                        // Bonuses granted by progress tokens
                        if(player.hasProgressTokenEffect(ProgressToken.Effect.ARCHITECTURE))
                            cardsToPick++;

                        List<Stage> stagesReadyToBuild = player.getHand().getStagesReadyToBuild(player.getWonder().getNextStagesToBuild(), player.hasProgressTokenEffect(ProgressToken.Effect.ECONOMY));
                        // TODO: add the ability to let the Player chose the Stage to build if multiple are available
                        player.getWonder().buildStage(stagesReadyToBuild.get(0), player.getHand(), this.discard, player.hasProgressTokenEffect(ProgressToken.Effect.ECONOMY));
                        this.outputManager.displayStageBuilt(player, stagesReadyToBuild.get(0), player.getWonder());
                    }

                    // Parsing actions depending on the color of the picked card
                    if(pickedCard instanceof GreyCard)
                    {
                        // Bonuses granted by progress tokens
                        // TODO: display message to player indicating why they got to pick another card
                        if(((GreyCard) pickedCard).getMaterial() == GreyCard.Material.WOOD || ((GreyCard) pickedCard).getMaterial() == GreyCard.Material.BRICK)
                        {
                            if(player.hasProgressTokenEffect(ProgressToken.Effect.URBANISM))
                                cardsToPick++;
                        }
                        if(((GreyCard) pickedCard).getMaterial() == GreyCard.Material.PAPYRUS || ((GreyCard) pickedCard).getMaterial() == GreyCard.Material.GLASS)
                        {
                            if(player.hasProgressTokenEffect(ProgressToken.Effect.CRAFTS))
                                cardsToPick++;
                        }
                        if(((GreyCard) pickedCard).getMaterial() == GreyCard.Material.STONE)
                        {
                            if(player.hasProgressTokenEffect(ProgressToken.Effect.JEWELLERY))
                                cardsToPick++;
                        }
                    }
                    if(pickedCard instanceof YellowCard)
                    {
                        // Bonuses granted by progress tokens
                        if(player.hasProgressTokenEffect(ProgressToken.Effect.JEWELLERY))
                            cardsToPick++;
                    }
                    if(pickedCard instanceof BlueCard)
                    {
                        if(((BlueCard) pickedCard).getCat())
                        {
                            for(Player p: this.players)
                                p.setHasCat(false);
                            player.setHasCat(true);
                            player.addVictoryPoints(((BlueCard) pickedCard).getVictoryPoints());
                        }
                    }
                    else if(pickedCard instanceof GreenCard)
                    {
                        // Bonuses granted by progress tokens
                        if(player.hasProgressTokenEffect(ProgressToken.Effect.SCIENCE))
                            cardsToPick++;

                        if(player.getHand().containsTwoIdenticalScienceSymbols() || player.getHand().containsThreeDifferentScienceSymbols())
                            player.addProgressToken(this.inputParser.fetchProgressTokenFromStack(this.progressTokenStack));
                    }
                    else if(pickedCard instanceof RedCard)
                    {
                        // Bonuses granted by progress tokens
                        if(((RedCard) pickedCard).getHorns() > 0)
                        {
                            if(player.hasProgressTokenEffect(ProgressToken.Effect.PROPAGANDA))
                                cardsToPick++;
                        }

                        this.conflictTokensBattleSide += ((RedCard) pickedCard).getHorns();
                        // TODO: battle should be started at the end of the Player's turn
                        if(this.conflictTokensBattleSide >= this.conflictTokensAmount)
                            this.resolveBattle();
                    }
                }
            }
        }

        //TODO: calculate points
    }

    /**
     * Initializes the {@link Game}. This creates every {@link Player}, assigns a random {@link Wonder} to all {@link Player}s
     * and sorts the players list base on their birthday attribute (from the youngest to the oldest).
     * @author Quentin LAURENT
     */
    private void initialize()
    {
        int playerCount = this.inputParser.fetchPlayerCount();
        this.initializePlayers(playerCount);
        this.initializeConflictTokens();

        // Sorts the player list based on the birthday attribute (the youngest player is at the beginning of the list)
        Collections.sort(this.players);
    }

    /**
     * Asks the information required for each {@link Player} (name and birthday).
     * This also assigns a random {@link Wonder} to each {@link Player}.
     * @param playerCount The amount of players to initialize.
     * @author Quentin LAURENT
     */
    private void initializePlayers(int playerCount)
    {
        ArrayList<Wonder> wonders = new ArrayList<Wonder>();
        wonders.add(new Alexandria());
        wonders.add(new Babylon());
        wonders.add(new Ephesus());
        wonders.add(new Giza());
        wonders.add(new Halicarnassus());
        wonders.add(new Olympia());
        wonders.add(new Rhodes());

        Random random = new Random();
        int randomIndex;

        String playerName;
        LocalDate playerBirthday;
        for(int i = 1; i <= playerCount; i++)
        {
            this.outputManager.displayMessage(String.format("==== Player n°%d ====", i));
            playerName = this.inputParser.fetchPlayerName();
            playerBirthday = this.inputParser.fetchPlayerBirthday();
            // TODO: let players choose their Wonder

            // Assigning a random wonder to the player
            randomIndex = random.nextInt(wonders.size());
            Wonder wonder = wonders.get(randomIndex);
            wonders.remove(randomIndex);

            this.players.add(new Player(playerName, playerBirthday, wonder));
        }
    }

    /**
     * Starts a battle as described in the game's rules.
     * <p> Each player compares his amount of {@link RedCard}s with both neighbors. The one with the most shields (red cards)
     * wins the battle and earns 1 military victory token (1 victory point) per beaten neighbor. All {@link RedCard} with 1 or 2
     * horns must be discarded at the end of the battle.
     */
    private void resolveBattle()
    {
        int leftPlayerIndex;
        int rightPlayerIndex;
        Player leftPlayer;
        Player player;
        Player rightPlayer;

        for(int i = 0; i < this.players.size(); i++)
        {
            leftPlayerIndex = i - 1;
            rightPlayerIndex = i + 1;

            if (leftPlayerIndex < 0)
                leftPlayerIndex = this.players.size() - 1;
            if(rightPlayerIndex >= this.players.size())
                rightPlayerIndex = 0;

            leftPlayer = this.players.get(leftPlayerIndex);
            player = this.players.get(i);
            rightPlayer = this.players.get(rightPlayerIndex);

            int additionalShields = 0;
            if(player.hasProgressTokenEffect(ProgressToken.Effect.TACTICS))
                additionalShields = 2;

            // If a draw occurs, no points are awarded.
            if((player.getHand().getNumberOfShields() + additionalShields) > leftPlayer.getHand().getNumberOfShields())
                player.addVictoryPoints(1);
            if((player.getHand().getNumberOfShields() + additionalShields) > rightPlayer.getHand().getNumberOfShields())
                player.addVictoryPoints(1);

            this.conflictTokensBattleSide = 0;
        }

        // Discarding all Red cards with 1 or more horns
        for(Player p: this.players)
            p.getHand().discardRedCardsWithHorns(this.discard);
    }

    //TODO: to delete if not needed
    @Deprecated
    private void playTurn(Player player) throws ExecutionControl.NotImplementedException
    {
        throw new ExecutionControl.NotImplementedException("TODO");
    }

    // Methods
    /**
     * Creates the central {@link Deck} associated to this {@link Game}.
     * @author Minaé RAFFIN
     */
    private void buildDeck()
    {
        // Yellow cards
        this.centralDeck.addCard(new YellowCard(), 6);
        // Grey cards
        this.centralDeck.addCard(new GreyCard(GreyCard.Material.STONE), 4);
        this.centralDeck.addCard(new GreyCard(GreyCard.Material.BRICK), 4);
        this.centralDeck.addCard(new GreyCard(GreyCard.Material.WOOD), 4);
        this.centralDeck.addCard(new GreyCard(GreyCard.Material.GLASS), 4);
        this.centralDeck.addCard(new GreyCard(GreyCard.Material.PAPYRUS), 4);
        // Green cards
        this.centralDeck.addCard(new GreenCard(GreenCard.ScienceSymbol.GEAR), 4);
        this.centralDeck.addCard(new GreenCard(GreenCard.ScienceSymbol.COMPASS),4 );
        this.centralDeck.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET), 4);
        // Blue cards
        this.centralDeck.addCard(new BlueCard(3, false),4 );
        this.centralDeck.addCard(new BlueCard(2, true), 8);
        // Red cards
        this.centralDeck.addCard(new RedCard(0), 4);
        this.centralDeck.addCard(new RedCard(1), 4);
        this.centralDeck.addCard(new RedCard(2), 2);
    }

    /**
     * Initializes the amount of conflict tokens depending on the amount of {@link Player}s.
     * @author Quentin LAURENT
     */
    private void initializeConflictTokens()
    {
        int playerCount = this.players.size();

        if(playerCount >= 6)
            this.conflictTokensAmount = 6;
        else if(playerCount >= 5)
            this.conflictTokensAmount = 5;
        else if(playerCount >= 4)
            this.conflictTokensAmount = 4;
        else if(playerCount >= 2)
            this.conflictTokensAmount = 3;
        else
            throw new RuntimeException("Could not initialize conflict tokens amount: playerCount out of range [2-7].");
    }

    /**
     * Creates the {@link ProgressTokenStack} associated to this {@link Game}.
     * @author Quentin LAURENT
     */
    private void buildProgressTokenStack()
    {
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.URBANISM));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.CRAFTS));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.JEWELLERY));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.SCIENCE));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.PROPAGANDA));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.ARCHITECTURE));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.ECONOMY));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.ENGINEERING));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.TACTICS));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.DECOR));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.POLITICS));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.STRATEGY));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.EDUCATION));
        this.progressTokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.CULTURE), 2);
    }
}
