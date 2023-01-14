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

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if (!(o instanceof RedCard))
            return false;

        RedCard card = (RedCard) o;
        return (this.color == card.color && this.horns == card.horns);
    }

    @Override
    public int hashCode()
    {
        return this.color.hashCode() + this.horns;
    }
}
