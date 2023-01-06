package com.isep;

import java.util.ArrayList;

public class Rhodes extends Wonder
{
    // Constructor
    public Rhodes()
    {
        super("Rhodes", new ArrayList<Stage>() {{
            add(new Stage(4, 2, false, false, false, 1));
            add(new Stage(4, 2, true, true, false, 1));
            add(new Stage(5, 3, false, false, false, 2));
            add(new Stage(6, 3, true, true, false, 3));
            add(new Stage(7, 4, false, false, false, 4));
        }}, new Deck());
        this.buildDeck();
        this.deck.shuffle();
    }

    /**
     * Creates the {@link Deck} associated to the {@link Rhodes} wonder.
     * @author Minaé RAFFIN
     */
    private void buildDeck()
    {
        // Yellow cards
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new YellowCard());
        // Grey cards
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new GreyCard(GreyCard.Material.STONE));
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new GreyCard(GreyCard.Material.BRICK));
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new GreyCard(GreyCard.Material.WOOD));
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new GreyCard(GreyCard.Material.GLASS));
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new GreyCard(GreyCard.Material.PAPYRUS));
        // Green cards
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.GEAR));
        for(int i = 1 ; i <= 1; i++)
            this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.COMPASS));
        for(int i = 1 ; i <= 1; i++)
            this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        // Blue cards
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new BlueCard(3, false));
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new BlueCard(2, true));
        // Red cards
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new RedCard(0));
        for(int i = 1 ; i <= 1; i++)
            this.deck.addCard(new RedCard(1));
        for(int i = 1 ; i <= 2; i++)
            this.deck.addCard(new RedCard(2));
    }
}
