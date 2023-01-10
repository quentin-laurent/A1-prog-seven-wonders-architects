package com.isep.game.cards;

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
     * Creates a new empty {@link Deck}.
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

    /**
     * Picks the {@link Card} on top of this {@link Deck}
     * @return The picked {@link Card}.
     * @author Quentin LAURENT
     */
    public Card pickCard()
    {
        Card card = this.cards.get(0);
        this.cards.remove(0);

        return card;
    }

    /**
     * Indicates if this {@link Deck} doesn't contain any {@link Card}.
     * @return true if this {@link Deck} is empty.
     * @author Quentin LAURENT
     */
    public boolean isEmpty()
    {
        return this.cards.isEmpty();
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder(String.format("%d cards:\n", this.cards.size()));
        for(Card card: this.cards)
        {
            s.append(card.toString());
            s.append("\n");
        }

        // Removing the last \n char
        int length = s.length();
        s.replace(length - 1, length, "");

        return s.toString();
    }
}
