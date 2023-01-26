package com.isep.game.wonders;

import com.isep.game.Game;
import com.isep.game.cards.Deck;
import com.isep.game.cards.GreyCard;
import com.isep.game.cards.Hand;
import com.isep.utils.ConsoleOutput;
import com.isep.utils.ConsoleParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WonderTest
{
    @Test
    void isConstructedShouldReturnTrueIfWonderIsConstructed()
    {
        Wonder wonder = new Giza();
        wonder.getStages().get(0).setConstructed(true);
        wonder.getStages().get(1).setConstructed(true);
        wonder.getStages().get(2).setConstructed(true);
        wonder.getStages().get(3).setConstructed(true);
        wonder.getStages().get(4).setConstructed(true);

        assertTrue(wonder.isConstructed());
    }

    @Test
    void isConstructedShouldReturnFalseIfWonderIsNotConstructed()
    {
        Wonder wonder = new Halicarnassus();
        wonder.getStages().get(0).setConstructed(true);
        wonder.getStages().get(1).setConstructed(true);
        wonder.getStages().get(2).setConstructed(true);
        wonder.getStages().get(3).setConstructed(true);

        assertFalse(wonder.isConstructed());
    }

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

    @Test
    void buildStageShouldUpdateStageConstructedAttribute()
    {
        Babylon babylon = new Babylon();
        Stage stage1 = babylon.getStages().get(0);

        Hand hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.STONE));

        babylon.buildStage(stage1, hand, new Deck(), false, false);

        assertTrue(stage1.isConstructed());
    }

    @Test
    void buildStageShouldRemoveDiscardedCardsFromHand()
    {
        Game game = new Game(new ConsoleParser(), new ConsoleOutput());

        Babylon babylon = new Babylon();
        Stage stage1 = babylon.getStages().get(0);

        Hand hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.STONE));

        Deck expectedDiscard = new Deck();
        expectedDiscard.addCard(new GreyCard(GreyCard.Material.WOOD));
        expectedDiscard.addCard(new GreyCard(GreyCard.Material.STONE));

        babylon.buildStage(stage1, hand, game.getDiscard(), false, false);

        assertEquals(new Hand(), hand);
    }

    @Test
    void buildStageShouldAddDiscardedCardsToGameDiscard()
    {
        Game game = new Game(new ConsoleParser(), new ConsoleOutput());

        Babylon babylon = new Babylon();
        Stage stage1 = babylon.getStages().get(0);

        Hand hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.STONE));

        Deck expectedDiscard = new Deck();
        expectedDiscard.addCard(new GreyCard(GreyCard.Material.WOOD));
        expectedDiscard.addCard(new GreyCard(GreyCard.Material.STONE));

        babylon.buildStage(stage1, hand, game.getDiscard(), false, false);

        assertTrue(game.getDiscard().getCards().containsAll(expectedDiscard.getCards()));
    }

    @Test
    void getNumberOfStagesBuiltShouldReturnTheNumberOfBuiltStages()
    {
        Olympia olympia = new Olympia();
        olympia.getStages().get(0).setConstructed(true);
        olympia.getStages().get(1).setConstructed(true);
        olympia.getStages().get(2).setConstructed(true);

        assertEquals(3, olympia.getNumberOfStagesBuilt());

        Rhodes rhodes = new Rhodes();

        assertEquals(0, rhodes.getNumberOfStagesBuilt());
    }

    @Test
    void getNumberOfStagesShouldReturnTheNumberOfStages()
    {
        Alexandria alexandria = new Alexandria();

        assertEquals(5, alexandria.getNumberOfStages());
    }
}