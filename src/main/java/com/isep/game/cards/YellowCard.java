package com.isep.game.cards;

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

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if (!(o instanceof YellowCard))
            return false;

        YellowCard card = (YellowCard) o;
        return (this.color == card.color);
    }

    @Override
    public int hashCode()
    {
        return this.color.hashCode();
    }
}
