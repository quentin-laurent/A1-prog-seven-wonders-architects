package com.isep;

/**
 * An abstract class representing a game card.
 */
public abstract class Card
{
    enum Color
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
}
