package com.isep.game.wonders;

/**
 * An abstract class representing a stage.
 */
public class Stage
{
    // Attributes
    private int victoryPoints;
    private int requiredResourcesAmount;
    private boolean resourcesNeedToBeEqual;
    private boolean hasEffect;
    private boolean constructed;
    private int level;

    // Constructor
    public Stage(int victoryPoints, int requiredResourcesAmount, boolean resourcesNeedToBeEqual, boolean hasEffect, boolean constructed, int level)
    {
        this.victoryPoints = victoryPoints;
        this.requiredResourcesAmount = requiredResourcesAmount;
        this.resourcesNeedToBeEqual = resourcesNeedToBeEqual;
        this.hasEffect = hasEffect;
        this.constructed = constructed;
        this.level = level;
    }

    // Getters & Setters
    public int getVictoryPoints()
    {
        return this.victoryPoints;
    }

    public int getRequiredResourcesAmount()
    {
        return this.requiredResourcesAmount;
    }

    public boolean getResourcesNeedToBeEqual()
    {
        return this.resourcesNeedToBeEqual;
    }

    public boolean isConstructed()
    {
        return this.constructed;
    }

    public int getLevel()
    {
        return this.level;
    }

    public void setConstructed(boolean constructed)
    {
        this.constructed = constructed;
    }

    // Methods
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(String.format("Victory points: %d%n", this.victoryPoints));
        s.append(String.format("Resources required: %d%n", this.requiredResourcesAmount));
        s.append(String.format("Resources should be equal: %s%n", this.resourcesNeedToBeEqual));
        s.append(String.format("Has effect: %s%n", this.hasEffect));
        s.append(String.format("Constructed: %s%n", this.constructed));
        s.append(String.format("Level: %d%n", this.level));

        return s.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Stage))
            return false;
        Stage stage = (Stage) o;

        return (this.victoryPoints == stage.victoryPoints
                && this.resourcesNeedToBeEqual == stage.resourcesNeedToBeEqual
                && this.hasEffect == stage.hasEffect
                && this.constructed == stage.constructed
                && this.level == stage.level);
    }
}
