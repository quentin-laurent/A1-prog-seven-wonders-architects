package com.isep.game.wonders;

import com.isep.game.cards.Deck;

import java.util.ArrayList;
import java.util.List;

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
}
