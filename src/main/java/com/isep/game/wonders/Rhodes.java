package com.isep.game.wonders;

import com.isep.game.cards.*;

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
        this.deck.addCard(new YellowCard(), 2);
        // Grey cards
        this.deck.addCard(new GreyCard(GreyCard.Material.STONE), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.BRICK), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.WOOD), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.GLASS), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.PAPYRUS), 2);
        // Green cards
        this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.GEAR), 2);
        this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.COMPASS));
        this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        // Blue cards
        this.deck.addCard(new BlueCard(3, false), 2);
        this.deck.addCard(new BlueCard(2, true), 2);
        // Red cards
        this.deck.addCard(new RedCard(0),2 );
        this.deck.addCard(new RedCard(1), 2);
        this.deck.addCard(new RedCard(2));
    }
}
