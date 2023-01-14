package com.isep.game.wonders;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WonderTest {

    @Test
    void getNextStagesToBuildShouldReturnTheNextStageToBuild()
    {
        Alexandria alexandria = new Alexandria();
        alexandria.getStages().get(0).setConstructed(true);
        alexandria.getStages().get(1).setConstructed(true);

        ArrayList<Stage> nextStagesToBuild = new ArrayList<>();
        nextStagesToBuild.add(alexandria.getStages().get(2));

        assertEquals(nextStagesToBuild, alexandria.getNextStagesToBuild());
    }

    @Test
    void getNextStagesToBuildShouldReturnTheNextStageToBuildWithTheSameLevel()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);

        ArrayList<Stage> nextStagesToBuild = new ArrayList<>();
        nextStagesToBuild.add(babylon.getStages().get(3));
        nextStagesToBuild.add(babylon.getStages().get(4));

        assertEquals(nextStagesToBuild, babylon.getNextStagesToBuild());
    }

    @Test
    void getNextStagesToBuildShouldReturnTheNextStageToBuildWithTheSameLevelOnlyIfNotBuilt()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);
        babylon.getStages().get(4).setConstructed(true);

        ArrayList<Stage> nextStagesToBuild = new ArrayList<>();
        nextStagesToBuild.add(babylon.getStages().get(3));

        assertEquals(nextStagesToBuild, babylon.getNextStagesToBuild());
    }

    @Test
    void getNextStagesToBuildShouldReturnEmptyListIfAllStagesAreBuilt()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);
        babylon.getStages().get(3).setConstructed(true);
        babylon.getStages().get(4).setConstructed(true);

        ArrayList<Stage> nextStagesToBuild = new ArrayList<>();

        assertEquals(nextStagesToBuild, babylon.getNextStagesToBuild());
    }
}