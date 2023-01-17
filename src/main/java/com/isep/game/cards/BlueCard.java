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

    // Getters & Setters
    public int getVictoryPoints()
    {
        return this.victoryPoints;
    }

    public boolean getCat()
    {
        return this.cat;
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

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if (!(o instanceof BlueCard))
            return false;

        BlueCard card = (BlueCard) o;
        return (this.color == card.color && this.victoryPoints == card.victoryPoints && this.cat == card.cat);
    }

    @Override
    public int hashCode()
    {
        return this.color.hashCode() + this.victoryPoints + ((Boolean) cat).hashCode();
    }
}
