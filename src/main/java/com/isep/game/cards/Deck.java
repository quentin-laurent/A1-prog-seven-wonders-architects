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

    // Getters & Setters
    public List<Card> getCards()
    {
        return this.cards;
    }

    // Methods
    /**
     * Adds a single {@link Card} to this {@link Deck}.
     * @param card The {@link Card} to add.
     * @author Quentin LAURENT
     */
    public void addCard(Card card)
    {
        this.cards.add(card);
    }

    /**
     * Adds one or more identical {@link Card}(s) to this {@link Deck}.
     * @param card The {@link Card}(s) to add.
     * @author Quentin LAURENT
     */
    public void addCard(Card card, int quantity)
    {
        if(quantity < 1)
            throw new RuntimeException("Quantity to add cannot be less than 1 !");

        for(int i = 0; i < quantity; i++)
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
     * Returns the {@link Card} on top of this {@link Deck}.
     * @return The first {@link Card} of this {@link Deck}.
     * @author Quentin LAURENT
     */
    public Card getTopCard()
    {
        return this.cards.get(0);
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

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;

        if(!(o instanceof Deck))
            return false;

        Deck deck = (Deck) o;

        return this.cards.equals(deck.getCards());
    }
}
