package com.isep.game.cards;

/**
 * A class representing a green card.
 */
public class GreenCard extends Card
{
    public enum ScienceSymbol
    {
        COMPASS,
        GEAR,
        TABLET
    }
    // Attributes
    private ScienceSymbol scienceSymbol;

    // Constructor
    public GreenCard(ScienceSymbol scienceSymbol)
    {
        super(Color.GREEN);
        this.scienceSymbol = scienceSymbol;
    }

    // Methods
    @Override
    public String toString()
    {
        return String.format("[%s] %s", this.color, this.scienceSymbol);
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if (!(o instanceof GreenCard))
            return false;

        GreenCard card = (GreenCard) o;
        return (this.color == card.color && this.scienceSymbol == card.scienceSymbol);
    }

    @Override
    public int hashCode()
    {
        return this.color.hashCode() + this.scienceSymbol.hashCode();
    }
}
