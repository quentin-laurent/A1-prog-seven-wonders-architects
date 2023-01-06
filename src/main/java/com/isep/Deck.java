package com.isep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class representing a deck containing cards.
 * @author Quentin LAURENT
 */
public class Deck
{
    // Attributes
    private List<Card> cards;

    // Constructor
    /**
     * Creates a new empty {@link Deck}
     * @author Quentin LAURENT
     */
    public Deck()
    {
        this.cards = new ArrayList<Card>();
    }

    // Methods
    /**
     * Adds a new {@link Card} to this {@link Deck}.
     * @param card The {@link Card} to add.
     * @author Quentin LAURENT
     */
    public void addCard(Card card)
    {
        this.cards.add(card);
    }

    public void shuffle()
    {
        Collections.shuffle(this.cards);
    }
}
