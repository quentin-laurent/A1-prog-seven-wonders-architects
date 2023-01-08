package com.isep.game.cards;

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
        super(Color.BLUE);
        this.victoryPoints = victoryPoints;
        this.cat = cat;
    }

    // Methods
    @Override
    public String toString()
    {
        if(this.cat)
            return String.format("[%s] %d Victory points, [CAT]", this.color, this.victoryPoints);
        else
            return String.format("[%s] %d Victory points", this.color, this.victoryPoints);
    }
}
