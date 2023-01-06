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
        super(Color.GREEN);
        this.scienceSymbol = scienceSymbol;
    }
}
