package com.isep.game.wonders;

import com.isep.game.cards.*;

import java.util.ArrayList;

public class Halicarnassus extends Wonder
{
    // Constructor
    public Halicarnassus()
    {
        super("Halicarnassus", new ArrayList<Stage>() {{
            add(new Stage(3, 2, false, false, false, 1));
            add(new Stage(3, 2, true, true, false, 2));
            add(new Stage(6, 3, false, false, false, 3));
            add(new Stage(5, 3, true, true, false, 3));
            add(new Stage(7, 4, false, false, false, 4));
        }}, new Deck());
        this.buildDeck();
        this.deck.shuffle();
    }

    /**
     * Creates the {@link Deck} associated to the {@link Halicarnassus} wonder.
     * @author Minaé RAFFIN
     */
    private void buildDeck()
    {
        // Yellow cards
        this.deck.addCard(new YellowCard(), 3);
        // Grey cards
        this.deck.addCard(new GreyCard(GreyCard.Material.STONE), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.BRICK), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.WOOD), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.GLASS), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.PAPYRUS));
        // Green cards
        this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.GEAR));
        this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.COMPASS));
        this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET), 2);
        // Blue cards
        this.deck.addCard(new BlueCard(3, false), 2);
        this.deck.addCard(new BlueCard(2, true), 2);
        // Red cards
        this.deck.addCard(new RedCard(0), 2);
        this.deck.addCard(new RedCard(1), 2);
        this.deck.addCard(new RedCard(2));
    }
}
