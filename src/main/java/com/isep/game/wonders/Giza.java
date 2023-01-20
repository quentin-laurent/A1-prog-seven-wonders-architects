package com.isep.game.wonders;

import com.isep.game.cards.*;

import java.util.ArrayList;

public class Giza extends Wonder
{
    // Constructor
    public Giza()
    {
        super("Giza", new ArrayList<Stage>() {{
            add(new Stage(4, 2, false, false, false, 1));
            add(new Stage(5, 2, true, false, false, 2));
            add(new Stage(6, 3, false, false, false, 3));
            add(new Stage(7, 3, true, false, false, 4));
            add(new Stage(8, 4, false, false, false, 5));
        }}, new Deck());
        this.buildDeck();
        this.deck.shuffle();
    }

    /**
     * Creates the {@link Deck} associated to the {@link Giza} wonder.
     * @author Minaé RAFFIN
     */
    private void buildDeck()
    {
        // Yellow cards
        this.deck.addCard(new YellowCard(), 3);
        // Grey cards
        this.deck.addCard(new GreyCard(GreyCard.Material.STONE), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.BRICK));
        this.deck.addCard(new GreyCard(GreyCard.Material.WOOD), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.GLASS), 2);
        this.deck.addCard(new GreyCard(GreyCard.Material.PAPYRUS), 2);
        // Green cards
        this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.GEAR));
        this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.COMPASS), 2);
        this.deck.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        // Blue cards
        this.deck.addCard(new BlueCard(3, false), 2);
        this.deck.addCard(new BlueCard(2, true), 3);
        // Red cards
        this.deck.addCard(new RedCard(0), 2);
        this.deck.addCard(new RedCard(2));
    }
}
