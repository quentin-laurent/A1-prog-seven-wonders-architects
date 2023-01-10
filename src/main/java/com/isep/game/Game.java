package com.isep.game;

import com.isep.game.cards.*;
import com.isep.game.wonders.*;
import com.isep.utils.InputParser;
import com.isep.utils.OutputManager;
import jdk.jshell.spi.ExecutionControl;

import java.time.LocalDate;
import java.util.ArrayList;
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
     * The {@link ProgressToken}s of this {@link Game}.
     */
    private List<ProgressToken> progressTokens;
    /**
     * The total amount of conflict tokens of this {@link Game}.
     */
    private int conflictTokensAmount;
    /**
     * The amount of conflict tokens that have their battle-side up.
     */
    private int conflictTokensBattleSide;

    // Constructor
    public Game(InputParser inputParser, OutputManager outputManager)
    {
        this.inputParser = inputParser;
        this.outputManager = outputManager;
        this.players = new ArrayList<Player>();
        this.centralDeck = new Deck();
        this.buildDeck();
        this.discard = new Deck();
        this.progressTokens = new ArrayList<ProgressToken>();
        // TODO: set this value depending on the amount of players
        this.conflictTokensAmount = 0;
        this.conflictTokensBattleSide = 0;
    }

    public void play() throws ExecutionControl.NotImplementedException
    {
        this.initialize();

        for(Player player: this.players)
            this.playTurn(player);
    }

    private void initialize()
    {
        int playerCount = this.inputParser.fetchPlayerCount();
        this.initializePlayers(playerCount);
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
}
