package com.isep.game.cards;

import com.isep.game.wonders.Stage;
import com.isep.game.wonders.Wonder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Getters & Setters
    public HashMap<Card, Integer> getCards()
    {
        return this.cards;
    }

    // Methods
    public boolean isEmpty()
    {
        return this.cards.isEmpty();
    }

    /**
     * Adds a new {@link Card} to this {@link Hand}.
     * @param card The {@link Card} to add.
     * @author Quentin LAURENT
     */
    public void addCard(Card card)
    {
        if(this.cards.containsKey(card))
            this.cards.put(card, this.cards.get(card) + 1);
        else
            this.cards.put(card, 1);
    }

    /**
     * Removes a {@link Card} from this {@link Hand}.
     * @param card The {@link Card} to remove.
     * @throws RuntimeException If the provided {@link Card} is not in this {@link Hand}.
     * @author Quentin LAURENT
     */
    public void removeCard(Card card)
    {
        if(!(this.cards.containsKey(card)))
            throw new RuntimeException(String.format("Failed to remove Card: [%s] from Hand: The Card provided is not in this Hand.", card));

        if(this.cards.get(card) == 1)
            this.cards.remove(card);
        else
            this.cards.put(card, this.cards.get(card) - 1);
    }

    /**
     * Indicates if this {@link Hand} contains the {@link Card}s required to build specific {@link Stage}s.
     * @param stages The {@link Stage}s to verify.
     * @return True if at least one the specified{@link Stage}s can be built using {@link Card}s from this {@link Hand}.
     * @author Quentin LAURENT
     */
    public boolean canBuildStage(List<Stage> stages)
    {
        for(Stage stage: stages)
        {
            int requiredResourcesAmount = stage.getRequiredResourcesAmount();
            boolean resourcesNeedToBeEqual = stage.getResourcesNeedToBeEqual();

            if (resourcesNeedToBeEqual)
            {
                for (var entry : this.cards.entrySet())
                {
                    if (entry.getKey() instanceof GreyCard && entry.getValue() >= requiredResourcesAmount)
                        return true;
                }
                return false;
            }
            else
            {
                int differentResourcesAvailable = 0;
                for (var entry : this.cards.entrySet())
                {
                    if (entry.getKey() instanceof GreyCard)
                        differentResourcesAvailable++;
                }
                return differentResourcesAvailable >= requiredResourcesAmount;
            }
        }
        return false;
    }

    /**
     * Returns a {@link List<Stage>} containing the next {@link Stage}s that can be built using {@link Card}s from this {@link Hand}.
     * @return A {@link List<Stage>} containing the next {@link Stage}s that can be built.
     * @author Quentin LAURENT
     */
    public List<Stage> getStagesReadyToBuild(List<Stage> stages)
    {
        ArrayList<Stage> stagesReadyToBuild = new ArrayList<>();

        for(Stage stage: stages)
        {
            int requiredResourcesAmount = stage.getRequiredResourcesAmount();
            boolean resourcesNeedToBeEqual = stage.getResourcesNeedToBeEqual();

            if (resourcesNeedToBeEqual)
            {
                for (var entry : this.cards.entrySet())
                {
                    if (entry.getKey() instanceof GreyCard && entry.getValue() >= requiredResourcesAmount)
                        stagesReadyToBuild.add(stage);
                }
            }
            else
            {
                int differentResourcesAvailable = 0;
                for (var entry : this.cards.entrySet())
                {
                    if (entry.getKey() instanceof GreyCard)
                        differentResourcesAvailable++;
                }
                if(differentResourcesAvailable >= requiredResourcesAmount)
                    stagesReadyToBuild.add(stage);
            }
        }
        return stagesReadyToBuild;
    }

    /**
     * Returns a {@link Map} containing the {@link Card}s required to build the provided {@link Stage}.
     * @param stage The {@link Stage} to build.
     * @return A {@link Map} containing the {@link Card}s required.
     * @author Quentin LAURENT
     */
    public Map<Card, Integer> getCardsRequiredToBuildStage(Stage stage)
    {
        HashMap<Card, Integer> requiredCards = new HashMap<Card, Integer>();
        int requiredResourcesAmount = stage.getRequiredResourcesAmount();
        boolean resourcesNeedToBeEqual = stage.getResourcesNeedToBeEqual();

        if (resourcesNeedToBeEqual)
        {
            for (var entry : this.cards.entrySet())
            {
                if (entry.getKey() instanceof GreyCard && entry.getValue() >= requiredResourcesAmount)
                    requiredCards.put(entry.getKey(), requiredResourcesAmount);
            }
            if(requiredCards.isEmpty())
                throw new RuntimeException("The current does not have the cards required to build the provided stage !");
        }
        else
        {
            HashMap<Card, Integer> differentResources = new HashMap<Card, Integer>();
            int differentResourcesAvailable = 0;
            for (var entry : this.cards.entrySet())
            {
                if (entry.getKey() instanceof GreyCard)
                {
                    differentResources.put(entry.getKey(), 1);
                    differentResourcesAvailable++;
                }
            }
            if(differentResourcesAvailable >= requiredResourcesAmount)
                requiredCards.putAll(differentResources);
            else
                throw new RuntimeException("The current does not have the cards required to build the provided stage !");
        }
        return requiredCards;
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder(String.format("%d cards:\n", this.cards.size()));
        for(var entry: this.cards.entrySet())
        {
            for(int i = 0; i < entry.getValue(); i++)
                s.append(entry.getKey().toString()).append('\n');
        }

        // Removing the last \n char
        int length = s.length();
        s.replace(length - 1, length, "");

        return s.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Hand))
            return false;

        Hand hand = (Hand) o;

        return(this.cards.equals(hand.getCards()));
    }

}
