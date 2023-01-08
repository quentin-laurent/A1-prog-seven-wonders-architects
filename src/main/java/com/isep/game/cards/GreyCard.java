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

    // Methods
    @Override
    public String toString()
    {
        return String.format("[%s] %s", this.color, this.material);
    }
}
