package com.isep;

/**
 * A class representing a blue card.
 */
public class BlueCard extends Card
{
    // Attributes
    private int victoryPoints;
    private boolean cat;

    // Constructor
    public BlueCard(int victoryPoints, boolean cat)
    {
        super("blue");
        this.victoryPoints = victoryPoints;
        this.cat = cat;
    }
}
