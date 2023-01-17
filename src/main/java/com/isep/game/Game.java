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
     * The {@link ProgressTokenStack} in which every {@link Player} can pick a {@link ProgressToken} when possible..
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
        this.conflictTokensBattleSide = 0;
        this.onePlayerBuiltItsWonder = false;
    }

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
                if(player.getHand().canBuildStage(player.getWonder().getNextStagesToBuild()))
                {
                    List<Stage> stagesReadyToBuild = player.getHand().getStagesReadyToBuild(player.getWonder().getNextStagesToBuild());
                    // TODO: add the ability to let the Player chose the Stage to build if multiple are available
                    player.getWonder().buildStage(stagesReadyToBuild.get(0), player.getHand());
                    this.outputManager.displayStageBuilt(player, stagesReadyToBuild.get(0), player.getWonder());
                }

                if(pickedCard instanceof BlueCard)
                {
                    if(((BlueCard) pickedCard).getCat())
                    {
                        for(Player p: this.players)
                            p.setHasCat(false);
                        player.setHasCat(true);
                        player.addVictoryPoint(((BlueCard) pickedCard).getVictoryPoints());
                    }
                }
            }
        }
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

            // Assigning a random wonder to the player
            randomIndex = random.nextInt(wonders.size());
            Wonder wonder = wonders.get(randomIndex);
            wonders.remove(randomIndex);

            this.players.add(new Player(playerName, playerBirthday, wonder));
        }
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
        for(int i = 1 ; i <= 6; i++)
            this.centralDeck.addCard(new YellowCard());
        // Grey cards
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new GreyCard(GreyCard.Material.STONE));
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new GreyCard(GreyCard.Material.BRICK));
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new GreyCard(GreyCard.Material.WOOD));
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new GreyCard(GreyCard.Material.GLASS));
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new GreyCard(GreyCard.Material.PAPYRUS));
        // Green cards
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new GreenCard(GreenCard.ScienceSymbol.GEAR));
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new GreenCard(GreenCard.ScienceSymbol.COMPASS));
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        // Blue cards
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new BlueCard(3, false));
        for(int i = 1 ; i <= 8; i++)
            this.centralDeck.addCard(new BlueCard(2, true));
        // Red cards
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new RedCard(0));
        for(int i = 1 ; i <= 2; i++)
            this.centralDeck.addCard(new RedCard(1));
        for(int i = 1 ; i <= 4; i++)
            this.centralDeck.addCard(new RedCard(2));
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
