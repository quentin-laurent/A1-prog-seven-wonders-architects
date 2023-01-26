package com.isep.game.cards;

/**
 * A class representing a grey card.
 */
public class GreyCard extends Card
{
    public enum Material
    {
        WOOD,
        STONE,
        BRICK,
        PAPYRUS,
        GLASS
    }
    // Attributes
    private Material material;

    // Constructor
    public GreyCard(Material material)
    {
        super(Color.GREY);
        this.material = material;
    }

    // Getters & Setters
    public Material getMaterial()
    {
        return this.material;
    }

    // Methods
    @Override
    public String toString()
    {
        return String.format("[%s] %s", this.color, this.material);
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if (!(o instanceof GreyCard))
            return false;

        GreyCard card = (GreyCard) o;
        return (this.color == card.color && this.material == card.material);
    }

    @Override
    public int hashCode()
    {
        return this.color.hashCode() + this.material.hashCode();
    }
}
