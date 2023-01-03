package com.isep;

/**
 * A class representing a green card.
 */
public class GreenCard extends Card
{
    // Attributes
    private String scienceSymbol;

    // Constructor
    public GreenCard(String scienceSymbol)
    {
        super("green");
        this.scienceSymbol = scienceSymbol;
    }
}
