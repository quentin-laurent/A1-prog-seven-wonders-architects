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

    // Constructor
    public Wonder(String name, List<Stage> stages)
    {
        this.name = name;
        this.stages = stages;
    }

    // Getters & Setters
    public List<Stage> getStages()
    {
        return this.stages;
    }
}
