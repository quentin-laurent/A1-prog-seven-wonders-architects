package com.isep.game.cards;

import com.isep.game.wonders.Stage;

import java.util.HashMap;

/**
 * A class representing a player's hand.
 * @author Quentin LAURENT
 */
public class Hand
{
    // Attributes
    private HashMap<Card, Integer> cards;

    // Constructor
    /**
     * Creates a new empty {@link Deck}
     * @author Quentin LAURENT
     */
    public Hand()
    {
        this.cards = new HashMap<Card, Integer>();
    }

    // Methods

    /**
     * Adds a new {@link Card} to this {@link Hand}.
     * @param card The {@link Card} to add.
     * @author Quentin LAURENT
     */
    public void addCard(Card card)
    {
        this.cards.put(card, 1);
    }

    /**
     * Indicates if this {@link Hand} contains the {@link Card}s required to build a specific {@link Stage}.
     * @param stage The {@link Stage} to verify.
     * @return True if the specified {@link Stage} can be built using {@link Card}s from this {@link Hand}.
     * @author Quentin LAURENT
     */
    public boolean canBuildStage(Stage stage)
    {
        int requiredResourcesAmount = stage.getRequiredResourcesAmount();
        boolean resourcesNeedToBeEqual = stage.getResourcesNeedToBeEqual();

        if(resourcesNeedToBeEqual)
        {
            for(var entry: this.cards.entrySet())
            {
                if(entry.getKey() instanceof GreyCard && entry.getValue() >= requiredResourcesAmount)
                    return true;
            }
            return false;
        }
        else
        {
            int differentResourcesAvailable = 0;
            for(var entry: this.cards.entrySet())
            {
                if(entry.getKey() instanceof GreyCard)
                    differentResourcesAvailable++;
            }
            return differentResourcesAvailable >= requiredResourcesAmount;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder(String.format("%d cards:\n", this.cards.size()));
        for(var entry: this.cards.entrySet())
        {
            s.append(entry.getKey().toString());
            s.append("\n");
        }

        // Removing the last \n char
        int length = s.length();
        s.replace(length - 1, length, "");

        return s.toString();
    }

}
