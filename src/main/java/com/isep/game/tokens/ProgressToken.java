package com.isep.game.tokens;

/**
 * A class representing a progress token.
 */
public class ProgressToken
{
    public enum Effect
    {
        URBANISM,
        CRAFTS,
        JEWELLERY,
        SCIENCE,
        PROPAGANDA,
        ARCHITECTURE,
        ECONOMY,
        ENGINEERING,
        TACTICS,
        DECOR,
        POLITICS,
        STRATEGY,
        EDUCATION,
        CULTURE
    }

    // Attributes
    private Effect effect;

    // Constructor
    public ProgressToken(Effect effect)
    {
        this.effect = effect;
    }

    // Getters & Setters
    public Effect getEffect()
    {
        return this.effect;
    }

    // Methods
    @Override
    public String toString()
    {
        return String.format("[PROGRESS TOKEN] %s", this.effect);
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if(!(o instanceof ProgressToken))
            return false;

        return (this.effect == ((ProgressToken) o).getEffect());
    }
}
