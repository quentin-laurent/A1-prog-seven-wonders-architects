package com.isep.game.cards;

/**
 * A class representing a red card.
 */
public class RedCard extends Card
{
    // Attributes
    private int horns;

    // Constructor
    public RedCard(int horns)
    {
        super(Color.RED);
        this.horns = horns;
    }

    // Methods
    @Override
    public String toString()
    {
        return String.format("[%s] %d Horns", this.color, this.horns);
    }
}
