package com.isep.game.wonders;

import com.isep.game.Player;
import com.isep.game.cards.Card;
import com.isep.game.cards.Deck;
import com.isep.game.Game;
import com.isep.game.cards.Hand;
import com.isep.game.tokens.ProgressToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * An abstract class representing a wonder.
 */
public abstract class Wonder
{
    // Attributes
    protected String name;
    protected List<Stage> stages;
    protected Deck deck;

    // Constructor
    public Wonder(String name, List<Stage> stages, Deck deck)
    {
        this.name = name;
        this.stages = stages;
        this.deck = deck;
    }

    // Getters & Setters
    public String getName()
    {
        return this.name;
    }

    public List<Stage> getStages()
    {
        return this.stages;
    }

    public Deck getDeck()
    {
        return this.deck;
    }

    // Methods

    /**
     * Returns true if this {@link Wonder} is fully built.
     * @return True if every {@link Stage} of this {@link Wonder} has been constructed.
     */
    public boolean isConstructed()
    {
        for(Stage stage: this.stages)
        {
            if(!stage.isConstructed())
                return false;
        }

        return true;
    }

    /**
     * Returns a {@link List<Stage>} containing the next {@link Stage}s that need to be built.
     * For instance, on a {@link Wonder} with 5 {@link Stage}s, if Stage 1 has been built, this method returns Stage 2.
     * @return A {@link List<Stage>} containing the next {@link Stage}s that need to be built.
     */
    public List<Stage> getNextStagesToBuild()
    {
        ArrayList<Stage> nextStagesToBuild = new ArrayList<Stage>();
        int level = 0;

        for(Stage stage: this.stages)
        {
            if(!stage.isConstructed())
            {
                // Gets the first Stage that has not been built yet
                if(level == 0 && nextStagesToBuild.size() == 0)
                {
                    nextStagesToBuild.add(stage);
                    level = stage.getLevel();
                }
                // Gets the Stages at the same level that the first one that got picked
                else if(stage.getLevel() == level)
                    nextStagesToBuild.add(stage);
            }
        }
        return nextStagesToBuild;
    }

    /**
     * Builds the specified {@link Stage} with {@link Card}s from the provided {@link  Hand}.
     * @param stage The {@link Stage} to build.
     * @param hand The {@link Hand} to use the {@link Card}s from.
     * @param discard The {@link Game}'s discard.
     * @param economyEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ECONOMY {@link ProgressToken}.
     * @param engineeringEffect A boolean indicating if the {@link Player} owning this {@link Hand} has the ENGINEERING {@link ProgressToken}.
     * @author Quentin LAURENT
     */
    public void buildStage(Stage stage, Hand hand, Deck discard, boolean economyEffect, boolean engineeringEffect)
    {
        Map<Card, Integer> cardsRequired = hand.getCardsRequiredToBuildStage(stage, economyEffect, engineeringEffect);

        // Removing the Cards from the provided Hand
        for(var entry: cardsRequired.entrySet())
        {
            for(int i = 0; i < entry.getValue(); i++)
            {
                discard.addCard(entry.getKey());
                hand.removeCard(entry.getKey());
            }
        }

        // Updating the 'constructed' attribute of the Stage
        stage.setConstructed(true);
    }

    /**
     * Returns the number of {@link Stage}s that have been built.
     * @return The number of {@link Stage}s that have been built.
     * @author Quentin LAURENT
     */
    public int getNumberOfStagesBuilt()
    {
        int numberOfStagesBuilt = 0;
        for(Stage stage: this.stages)
            if(stage.isConstructed())
                numberOfStagesBuilt++;

        return numberOfStagesBuilt;
    }

    /**
     * Returns the total number of {@link Stage}s to build.
     * @return The total number of {@link Stage}s to build.
     * @author Quentin LAURENT
     */
    public int getNumberOfStages()
    {
        return this.stages.size();
    }
}
