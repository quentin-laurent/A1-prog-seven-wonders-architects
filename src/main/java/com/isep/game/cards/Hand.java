package com.isep.game.cards;

import com.isep.game.wonders.Stage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * A class representing a player's hand.
 * @author Quentin LAURENT
 */
public class Hand
{
    // Attributes
    private List<Card> cards;

    // Constructor
    /**
     * Creates a new empty {@link Deck}
     * @author Quentin LAURENT
     */
    public Hand()
    {
        this.cards = new ArrayList<Card>();
    }

    // Methods

    /**
     * Adds a new {@link Card} to this {@link Hand}.
     * @param card The {@link Card} to add.
     * @author Quentin LAURENT
     */
    public void addCard(Card card)
    {
        this.cards.add(card);
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
       // TODO canBuildStage()
        if(resourcesNeedToBeEqual)
        {
            HashMap<GreyCard, Integer> greyCards = new HashMap<GreyCard, Integer>();
            for(Card card: this.cards)
            {
                if(card instanceof GreyCard)
                {
                    int amount = greyCards.get((GreyCard) card);
                    if(amount == 0)
                        greyCards.put((GreyCard) card, 1);
                    else
                        greyCards.put((GreyCard) card, amount + 1);
                }

                //TODO
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder(String.format("%d cards:\n", this.cards.size()));
        for(Card card: this.cards)
        {
            s.append(card.toString());
            s.append("\n");
        }

        // Removing the last \n char
        int length = s.length();
        s.replace(length - 1, length, "");

        return s.toString();
    }

}
