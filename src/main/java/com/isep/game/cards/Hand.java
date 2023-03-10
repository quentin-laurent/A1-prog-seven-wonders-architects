package com.isep.game.cards;

import com.isep.game.Player;
import com.isep.game.tokens.ProgressToken;
import com.isep.game.wonders.Stage;

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
     * Adds a single {@link Card} to this {@link Hand}.
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
     * Adds a one or more identical {@link Card}(s) to this {@link Hand}.
     * @param card The {@link Card}(s) to add.
     * @author Quentin LAURENT
     */
    public void addCard(Card card, int quantity)
    {
        if(quantity < 1)
            throw new RuntimeException("Quantity to add cannot be less than 1 !");

        if(this.cards.containsKey(card))
            this.cards.put(card, this.cards.get(card) + quantity);
        else
            this.cards.put(card, quantity);
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
     * <p>This method does NOT account for the ENGINEERING effect.
     * @param stages The {@link Stage}s to verify.
     * @param economyEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ECONOMY {@link ProgressToken}.
     * @return True if at least one the specified {@link Stage}s can be built using {@link Card}s from this {@link Hand}.
     * @author Quentin LAURENT
     */
    public boolean canBuildStage(List<Stage> stages, boolean economyEffect)
    {
        int bonus = (economyEffect) ? 2 : 1;

        for(Stage stage: stages)
        {
            int requiredResourcesAmount = stage.getRequiredResourcesAmount();
            boolean resourcesNeedToBeEqual = stage.getResourcesNeedToBeEqual();
            int maxAmountOfEqualResources = 0;
            int yellowCardAmount = 0;

            if (resourcesNeedToBeEqual)
            {
                for (var entry : this.cards.entrySet())
                {
                    if (entry.getKey() instanceof GreyCard && entry.getValue() >= requiredResourcesAmount)
                        return true;
                    else if(entry.getKey() instanceof GreyCard && entry.getValue() > maxAmountOfEqualResources)
                        maxAmountOfEqualResources = entry.getValue();
                    else if(entry.getKey() instanceof YellowCard)
                        yellowCardAmount = entry.getValue();
                }
                return (maxAmountOfEqualResources + (yellowCardAmount * bonus)) >= requiredResourcesAmount;
            }
            else
            {
                int differentResourcesAvailable = 0;
                for (var entry : this.cards.entrySet())
                {
                    if (entry.getKey() instanceof GreyCard)
                        differentResourcesAvailable++;
                    else if(entry.getKey() instanceof YellowCard)
                        yellowCardAmount = entry.getValue();
                }
                return (differentResourcesAvailable + (yellowCardAmount * bonus)) >= requiredResourcesAmount;
            }
        }
        return false;
    }

    /**
     * Indicates if this {@link Hand} contains the {@link Card}s required to build specific {@link Stage}s.
     * @param stages The {@link Stage}s to verify.
     * @param economyEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ECONOMY {@link ProgressToken}.
     * @param engineeringEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ENGINEERING {@link ProgressToken}.
     * @return True if at least one the specified {@link Stage}s can be built using {@link Card}s from this {@link Hand}.
     * @author Quentin LAURENT
     */
    public boolean canBuildStage(List<Stage> stages, boolean economyEffect, boolean engineeringEffect)
    {
        if(!engineeringEffect)
            return this.canBuildStage(stages, economyEffect);

        int bonus = (economyEffect) ? 2 : 1;

        // As the ENGINEERING effect ignores the requirement for resources to be identical of different, we only need
        // to count the amount of Grey and Yellow cards in the Hand.
        for(Stage stage: stages)
        {
            int requiredResourcesAmount = stage.getRequiredResourcesAmount();
            int totalResourcesAmount = 0;

            for (var entry : this.cards.entrySet())
            {
                if(entry.getKey() instanceof GreyCard)
                    totalResourcesAmount += entry.getValue();
                else if(entry.getKey() instanceof YellowCard)
                    totalResourcesAmount += (entry.getValue() * bonus);
            }

            if(totalResourcesAmount >= requiredResourcesAmount)
                return true;
        }

        return false;
    }

    /**
     * Returns a {@link List<Stage>} containing the next {@link Stage}s that can be built using {@link Card}s from this {@link Hand}.
     * <p>This method does NOT account for the ENGINEERING effect.
     * @param stages The {@link Stage}s {@link List<Stage>} to check from.
     * @param economyEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ECONOMY {@link ProgressToken}.
     * @return A {@link List<Stage>} containing the next {@link Stage}s that can be built.
     * @author Quentin LAURENT
     */
    public List<Stage> getStagesReadyToBuild(List<Stage> stages, boolean economyEffect)
    {
        int bonus = (economyEffect) ? 2 : 1;
        ArrayList<Stage> stagesReadyToBuild = new ArrayList<>();

        for(Stage stage: stages)
        {
            int requiredResourcesAmount = stage.getRequiredResourcesAmount();
            boolean resourcesNeedToBeEqual = stage.getResourcesNeedToBeEqual();
            int maxAmountOfEqualResources = 0;
            int yellowCardAmount = 0;

            if (resourcesNeedToBeEqual)
            {
                for (var entry : this.cards.entrySet())
                {
                    if (entry.getKey() instanceof GreyCard && entry.getValue() >= requiredResourcesAmount)
                    {
                        stagesReadyToBuild.add(stage);
                        continue;
                    }
                    else if(entry.getKey() instanceof GreyCard && entry.getValue() > maxAmountOfEqualResources)
                        maxAmountOfEqualResources = entry.getValue();
                    else if(entry.getKey() instanceof YellowCard)
                        yellowCardAmount = entry.getValue();

                    if((maxAmountOfEqualResources + (yellowCardAmount * bonus)) >= requiredResourcesAmount)
                    {
                        if(!stagesReadyToBuild.contains(stage))
                            stagesReadyToBuild.add(stage);
                    }
                }
            }
            else
            {
                int differentResourcesAvailable = 0;
                for (var entry : this.cards.entrySet())
                {
                    if (entry.getKey() instanceof GreyCard)
                        differentResourcesAvailable++;
                    else if(entry.getKey() instanceof YellowCard)
                        yellowCardAmount = entry.getValue();
                }
                if((differentResourcesAvailable + (yellowCardAmount * bonus)) >= requiredResourcesAmount)
                {
                    if(!stagesReadyToBuild.contains(stage))
                        stagesReadyToBuild.add(stage);
                }
            }
        }
        return stagesReadyToBuild;
    }

    /**
     * Returns a {@link List<Stage>} containing the next {@link Stage}s that can be built using {@link Card}s from this {@link Hand}.
     * @param stages The {@link Stage}s {@link List<Stage>} to check from.
     * @param economyEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ECONOMY {@link ProgressToken}.
     * @param engineeringEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ENGINEERING {@link ProgressToken}.
     * @return A {@link List<Stage>} containing the next {@link Stage}s that can be built.
     * @author Quentin LAURENT
     */
    public List<Stage> getStagesReadyToBuild(List<Stage> stages, boolean economyEffect, boolean engineeringEffect)
    {
        if(!engineeringEffect)
            return this.getStagesReadyToBuild(stages, economyEffect);

        int bonus = (economyEffect) ? 2 : 1;
        ArrayList<Stage> stagesReadyToBuild = new ArrayList<>();

        // As the ENGINEERING effect ignores the requirement for resources to be identical of different, we only need
        // to count the amount of Grey and Yellow cards in the Hand.
        for(Stage stage: stages)
        {
            int requiredResourcesAmount = stage.getRequiredResourcesAmount();
            int totalResourcesAmount = 0;

            for (var entry : this.cards.entrySet())
            {
                if(entry.getKey() instanceof GreyCard)
                    totalResourcesAmount += entry.getValue();
                else if(entry.getKey() instanceof YellowCard)
                    totalResourcesAmount += (entry.getValue() * bonus);
            }

            if(totalResourcesAmount >= requiredResourcesAmount)
                stagesReadyToBuild.add(stage);
        }

        return stagesReadyToBuild;
    }

    /**
     * Returns a {@link Map} containing the {@link Card}s required to build the provided {@link Stage}.
     * <p>This method does NOT account for the ENGINEERING effect.
     * @param stage The {@link Stage} to build.
     * @param economyEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ECONOMY {@link ProgressToken}.
     * @return A {@link Map} containing the {@link Card}s required.
     * @author Quentin LAURENT
     */
    public Map<Card, Integer> getCardsRequiredToBuildStage(Stage stage, boolean economyEffect)
    {
        float bonus = (economyEffect) ? 2f : 1f;

        HashMap<Card, Integer> requiredCards = new HashMap<Card, Integer>();
        final int requiredResourcesAmount = stage.getRequiredResourcesAmount();
        int differentResourcesRequiredAmount = requiredResourcesAmount;
        boolean resourcesNeedToBeEqual = stage.getResourcesNeedToBeEqual();
        int yellowCardAmount = 0;

        if(resourcesNeedToBeEqual)
        {
            GreyCard mostAvailableGreyCard = null;
            int maxAmountOfEqualResources = 0;

            // Fetches the GreyCard with the most quantity and the amount of YellowCard
            for(var entry: this.cards.entrySet())
            {
                // If there are directly enough GreyCards then they are immediately returned
                if(entry.getKey() instanceof GreyCard && entry.getValue() >= requiredResourcesAmount)
                {
                    requiredCards.put(entry.getKey(), requiredResourcesAmount);
                    return requiredCards;
                }
                else if(entry.getKey() instanceof GreyCard && entry.getValue() > maxAmountOfEqualResources)
                {
                    mostAvailableGreyCard = (GreyCard) entry.getKey();
                    maxAmountOfEqualResources = entry.getValue();
                }
                else if(entry.getKey() instanceof YellowCard)
                    yellowCardAmount = entry.getValue();
            }

            if(maxAmountOfEqualResources + (yellowCardAmount * bonus) < requiredResourcesAmount)
                throw new RuntimeException("The current Hand does not have the cards required to build the provided stage !");

            // Adds the GreyCards required and completes the missing resources with YellowCards
            if(mostAvailableGreyCard != null)
                requiredCards.put(mostAvailableGreyCard, maxAmountOfEqualResources);
            differentResourcesRequiredAmount -= maxAmountOfEqualResources;

            while(differentResourcesRequiredAmount > 0)
            {
                requiredCards.put(new YellowCard(), 1);
                differentResourcesRequiredAmount -= bonus;
            }

            return requiredCards;
        }
        else
        {
            int amountOfDifferentResources = 0;

            for(var entry: this.cards.entrySet())
            {
                // Fetches the amount of different GreyCards and the amount of YellowCard
                // We can also directly add the GreyCards to the returned HashMap
                if(entry.getKey() instanceof GreyCard)
                {
                    requiredCards.put(entry.getKey(), 1);
                    amountOfDifferentResources++;
                    differentResourcesRequiredAmount--;
                }

                else if(entry.getKey() instanceof YellowCard)
                    yellowCardAmount = entry.getValue();
            }

            if(amountOfDifferentResources + (yellowCardAmount * bonus) < requiredResourcesAmount)
                throw new RuntimeException("The current Hand does not have the cards required to build the provided stage !");

            // Completes the missing resources with YellowCards
            while(differentResourcesRequiredAmount > 0)
            {
                requiredCards.put(new YellowCard(), 1);
                differentResourcesRequiredAmount -= bonus;
            }

            return requiredCards;
        }
    }

    /**
     * Returns a {@link Map} containing the {@link Card}s required to build the provided {@link Stage}.
     * @param stage The {@link Stage} to build.
     * @param economyEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ECONOMY {@link ProgressToken}.
     * @param engineeringEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ENGINEERING {@link ProgressToken}.
     * @return A {@link Map} containing the {@link Card}s required.
     * @author Quentin LAURENT
     */
    public Map<Card, Integer> getCardsRequiredToBuildStage(Stage stage, boolean economyEffect, boolean engineeringEffect)
    {
        if(!engineeringEffect)
            return this.getCardsRequiredToBuildStage(stage, economyEffect);

        float bonus = (economyEffect) ? 2f : 1f;
        final int requiredResourcesAmount = stage.getRequiredResourcesAmount();
        HashMap<Card, Integer> requiredCards = new HashMap<Card, Integer>();

        // As the ENGINEERING effect ignores the requirement for resources to be identical of different, we only need
        // to return a combination of Grey and Yellow cards matching the required resources amount.
        int resourcesRequired = requiredResourcesAmount;
        for(var entry: this.cards.entrySet())
        {
            if(entry.getKey() instanceof GreyCard)
            {
                if(entry.getValue() <= resourcesRequired)
                {
                    requiredCards.put(entry.getKey(), entry.getValue());
                    resourcesRequired -= entry.getValue();
                }
                else
                {
                    requiredCards.put(entry.getKey(), resourcesRequired);
                    return requiredCards;
                }
            }
            else if(entry.getKey() instanceof YellowCard)
            {
                if((entry.getValue() * bonus) <= resourcesRequired)
                {
                    requiredCards.put(entry.getKey(), entry.getValue());
                    resourcesRequired -= (entry.getValue() * bonus);
                }
                else
                {
                    int yellowCardsToAdd = (int) Math.ceil(resourcesRequired / bonus);
                    requiredCards.put(entry.getKey(), yellowCardsToAdd);
                    return requiredCards;
                }
            }
        }

        if(resourcesRequired > 0)
            throw new RuntimeException("The current Hand does not have the cards required to build the provided stage !");

        return requiredCards;
    }

    /**
     * Returns true if this {@link Hand} contains two {@link GreenCard}s with the same scientific symbol.
     * @return True if this {@link Hand} contains two identical scientific symbols.
     * @author Quentin LAURENT
     */
    public boolean containsTwoIdenticalScienceSymbols()
    {
        for(var entry: this.cards.entrySet())
        {
            if(entry.getKey() instanceof GreenCard && entry.getValue() >= 2)
                return true;
        }

        return false;
    }

    /**
     * Returns true if this {@link Hand} contains three {@link GreenCard}s with the different scientific symbols.
     * @return True if this {@link Hand} contains three different scientific symbols.
     * @author Quentin LAURENT
     */
    public boolean containsThreeDifferentScienceSymbols()
    {
        int uniqueGreenCardsAmount = 0;
        for(var entry: this.cards.entrySet())
        {
            if(entry.getKey() instanceof GreenCard)
                uniqueGreenCardsAmount++;
        }

        return (uniqueGreenCardsAmount >= 3);
    }

    /**
     * Returns the number of shields ({@link RedCard}s) of this {@link Hand}.
     * @return The number of shields contained in this {@link Hand}.
     */
    public int getNumberOfShields()
    {
        int numberOfShields = 0;

        for(var entry: this.cards.entrySet())
        {
            if(entry.getKey() instanceof RedCard)
                numberOfShields += entry.getValue();
        }

        return numberOfShields;
    }

    /**
     * Discards all the {@link RedCard}s of this {@link Hand} with 1 or more horns.
     */
    public void discardRedCardsWithHorns(Deck discard)
    {
        // Adding the cards to the Game's discard
        for(var entry: this.cards.entrySet())
        {
            if(entry.getKey() instanceof RedCard && ((RedCard) entry.getKey()).getHorns() > 0)
                discard.addCard(entry.getKey(), entry.getValue());
        }

        // Removing the cards from the Hand
        this.cards.entrySet().removeIf(entry -> entry.getKey() instanceof RedCard && ((RedCard) entry.getKey()).getHorns() > 0);
    }

    /**
     * Returns the number of cats ({@link BlueCard}s with the Cat symbol) of this {@link Hand}.
     * @return The number of cats contained in this {@link Hand}.
     */
    public int getNumberOfCats()
    {
        int numberOfCats = 0;

        for(var entry: this.cards.entrySet())
        {
            if(entry.getKey() instanceof BlueCard && ((BlueCard) entry.getKey()).getCat())
                numberOfCats += entry.getValue();
        }

        return numberOfCats;
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
        if(o == null)
            return false;
        if(!(o instanceof Hand))
            return false;

        Hand hand = (Hand) o;

        return(this.cards.equals(hand.getCards()));
    }

}
