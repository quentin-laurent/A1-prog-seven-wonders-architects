package com.isep;

/**
 * A class representing a yellow card.
 */
public class YellowCard extends Card
{
    // Constructor
    public YellowCard()
    {
        super(Color.YELLOW);
    }

    // Methods
    @Override
    public String toString()
    {
        return String.format("[%s] Gold", this.color);
    }
}
