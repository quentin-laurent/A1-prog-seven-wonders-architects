package com.isep.game.cards;

/**
 * An abstract class representing a game card.
 */
public abstract class Card
{
    public enum Color
    {
        GREY,
        YELLOW,
        BLUE,
        GREEN,
        RED
    }

    // Attributes
    protected Color color;

    // Constructor
    public Card(Color color)
    {
        this.color = color;
    }

    // Methods
    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if (this.getClass() != o.getClass())
            return false;

        return (this.color == ((Card) o).color);
    }

    @Override
    public int hashCode()
    {
        return this.color.hashCode();
    }
}
