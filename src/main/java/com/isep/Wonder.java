package com.isep;

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
    public List<Stage> getStages()
    {
        return this.stages;
    }
}
