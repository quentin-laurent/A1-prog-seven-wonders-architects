package com.isep.game.wonders;

import com.isep.game.cards.Deck;

import java.util.List;

/**
 * An abstract class representing a wonder.
 */
public abstract class Wonder
{
    // Attributes
    protected String name;
    protected List<Stage> stages;
    protected Deck deck;

    // Constructor
    public Wonder(String name, List<Stage> stages, Deck deck)
    {
        this.name = name;
        this.stages = stages;
        this.deck = deck;
    }

    // Getters & Setters
    public String getName()
    {
        return this.name;
    }

    public List<Stage> getStages()
    {
        return this.stages;
    }

    public Deck getDeck()
    {
        return this.deck;
    }
}
