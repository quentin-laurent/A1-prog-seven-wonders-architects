package com.isep;

/**
 * A class representing a grey card.
 */
public class GreyCard extends Card
{
    enum Material
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
}
