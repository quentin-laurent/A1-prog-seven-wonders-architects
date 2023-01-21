package com.isep.game.cards;

import com.isep.game.Game;
import com.isep.game.wonders.Alexandria;
import com.isep.game.wonders.Babylon;
import com.isep.game.wonders.Stage;
import com.isep.utils.ConsoleOutput;
import com.isep.utils.ConsoleParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HandTest
{
    @Test
    void newlyCreatedHandShouldBeEmpty()
    {
        Hand hand = new Hand();
        assertTrue(hand.isEmpty());
    }

    @Test
    void handWithCardsShouldNotBeEmpty()
    {
        Hand hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));

        assertFalse(hand.isEmpty());
    }

    @Test
    void handWithNoCardsLeftShouldBeEmpty()
    {
        Hand hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.removeCard(new GreyCard(GreyCard.Material.WOOD));

        assertTrue(hand.isEmpty());
    }

    @Test
    void addCardShouldAddANewCardToTheHand()
    {
        Hand hand = new Hand();
        Card card = new GreyCard(GreyCard.Material.WOOD);
        hand.addCard(card);

        assertEquals(1, (int) hand.getCards().get(card));
    }

    @Test
    void existingCardShouldStackIfAlreadyInTheHand()
    {
        Hand hand = new Hand();
        Card card = new GreyCard(GreyCard.Material.WOOD);
        hand.addCard(card, 2);

        assertEquals(2, (int) hand.getCards().get(card));
    }

    @Test
    void removingCardAvailableInMultipleCopiesShouldDecreaseItsQuantity()
    {
        Hand hand = new Hand();
        Card card = new GreyCard(GreyCard.Material.WOOD);
        hand.addCard(card, 2);
        hand.removeCard(card);

        assertEquals(1, hand.getCards().get(card));
    }

    @Test
    void removeCardAvailableInOneCopyShouldRemoveItFromTheHand()
    {
        Hand hand = new Hand();
        Card card = new GreyCard(GreyCard.Material.WOOD);
        hand.addCard(card);
        hand.removeCard(card);

        assertNull(hand.getCards().get(card));
    }

    @Test
    void removeCardNotAvailableInHandShouldThrowRuntimeException()
    {
        Hand hand = new Hand();
        Card card = new GreyCard(GreyCard.Material.WOOD);

        assertThrows(RuntimeException.class, () -> hand.removeCard(card));
    }

    @Test
    void canBuildStageShouldReturnTrueWhenHandContainsCardsRequiredToBuildStage()
    {
        // Stage 1 of Alexandria requires 2 different resources
        Stage stage1 = new Alexandria().getStages().get(0);
        Hand hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS));

        ArrayList<Stage> stages = new ArrayList<>();
        stages.add(stage1);
        assertTrue(hand.canBuildStage(stages, false));

        // Stage 4 of Alexandria requires 3 identical resources
        Stage stage4 = new Alexandria().getStages().get(3);
        hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));

        stages.clear();
        stages.add(stage4);
        assertTrue(hand.canBuildStage(stages, false));
    }

    @Test
    void canBuildStageShouldReturnTrueWhenHandContainsCardsRequiredToBuildStageWithEngineeringEffect()
    {
        // Stage 1 of Alexandria requires 2 different resources
        Stage stage1 = new Alexandria().getStages().get(0);
        Hand hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));

        ArrayList<Stage> stages = new ArrayList<>();
        stages.add(stage1);
        assertTrue(hand.canBuildStage(stages, false, true));

        // Stage 4 of Alexandria requires 3 identical resources
        Stage stage4 = new Alexandria().getStages().get(3);
        hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new GreyCard(GreyCard.Material.PAPYRUS));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS));

        stages.clear();
        stages.add(stage4);
        assertTrue(hand.canBuildStage(stages, false, true));
    }

    @Test
    void canBuildStageShouldReturnTrueWhenHandContainsCardsRequiredToBuildStageWithYellowCards()
    {
        // Stage 1 of Alexandria requires 2 different resources
        Stage stage1 = new Alexandria().getStages().get(0);
        Hand hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new YellowCard());

        ArrayList<Stage> stages = new ArrayList<>();
        stages.add(stage1);
        assertTrue(hand.canBuildStage(stages, false));

        // Stage 4 of Alexandria requires 3 identical resources
        Stage stage4 = new Alexandria().getStages().get(3);
        hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS));
        hand.addCard(new YellowCard());

        stages.clear();
        stages.add(stage4);
        assertTrue(hand.canBuildStage(stages, false));
    }

    @Test
    void canBuildStageShouldReturnTrueWhenHandContainsCardsRequiredToBuildStageWithYellowCardsWithEconomyEffect()
    {
        // Stage 1 of Alexandria requires 2 different resources
        Stage stage1 = new Alexandria().getStages().get(0);
        Hand hand = new Hand();
        hand.addCard(new YellowCard());

        ArrayList<Stage> stages = new ArrayList<>();
        stages.add(stage1);
        assertTrue(hand.canBuildStage(stages, true));

        // Stage 4 of Alexandria requires 3 identical resources
        Stage stage4 = new Alexandria().getStages().get(3);
        hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS));
        hand.addCard(new YellowCard());

        stages.clear();
        stages.add(stage4);
        assertTrue(hand.canBuildStage(stages, true));
    }

    @Test
    void canBuildStageShouldReturnTrueWhenHandContainsCardsRequiredToBuildStageWithYellowCardsWithEconomyWithEngineeringEffects()
    {
        // Stage 1 of Alexandria requires 2 different resources
        Stage stage1 = new Alexandria().getStages().get(0);
        Hand hand = new Hand();
        hand.addCard(new YellowCard());

        ArrayList<Stage> stages = new ArrayList<>();
        stages.add(stage1);
        assertTrue(hand.canBuildStage(stages, true));

        // Stage 4 of Alexandria requires 3 identical resources
        Stage stage4 = new Alexandria().getStages().get(3);
        hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new YellowCard());

        stages.clear();
        stages.add(stage4);
        assertTrue(hand.canBuildStage(stages, true, true));
    }

    @Test
    void canBuildStageShouldReturnFalseWhenHandDoesntContainsCardsRequiredToBuildStage()
    {
        // Stage 1 of Alexandria requires 2 different resources
        Stage stage1 = new Alexandria().getStages().get(0);
        Hand hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));

        ArrayList<Stage> stages = new ArrayList<>();
        stages.add(stage1);
        assertFalse(hand.canBuildStage(stages, false));

        // Stage 4 of Alexandria requires 3 identical resources
        Stage stage4 = new Alexandria().getStages().get(3);
        hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS), 2);

        stages.clear();
        stages.add(stage4);
        assertFalse(hand.canBuildStage(stages, false));
    }

    @Test
    void testToString()
    {
        Hand hand = new Hand();
        hand.addCard(new YellowCard());
        hand.addCard(new GreyCard(GreyCard.Material.WOOD), 2);
        hand.addCard(new RedCard(0));
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));

        StringBuilder expectedString = new StringBuilder("5 cards:\n");
        for(var entry: hand.getCards().entrySet())
        {
            for(int i = 0; i < entry.getValue(); i++)
                expectedString.append(entry.getKey().toString()).append('\n');
        }
        int length = expectedString.length();
        expectedString.replace(length - 1, length, "");

        assertEquals(expectedString.toString(), hand.toString());
    }

    @Test
    void getStagesReadyToBeBuiltShouldReturnAllTheStagesReadyToBeBuilt()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);

        // This hand can build Stage 4 of Babylon (but not Stage 5, which is at the same level (4) )
        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD), 3);
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stagesReadyToBuild = new ArrayList<Stage>();
        stagesReadyToBuild.add(babylon.getStages().get(3));

        assertEquals(stagesReadyToBuild, hand.getStagesReadyToBuild(babylon.getNextStagesToBuild(), false));
    }

    @Test
    void getStagesReadyToBeBuiltShouldReturnAllTheStagesReadyToBeBuiltWithEngineeringEffect()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);

        // This hand can build Stage 4 of Babylon (but not Stage 5, which is at the same level (4) )
        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS));
        hand.addCard(new GreyCard(GreyCard.Material.STONE));
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stagesReadyToBuild = new ArrayList<Stage>();
        stagesReadyToBuild.add(babylon.getStages().get(3));

        assertEquals(stagesReadyToBuild, hand.getStagesReadyToBuild(babylon.getNextStagesToBuild(), false, true));
    }

    @Test
    void getStagesReadyToBeBuiltShouldReturnAllTheStagesReadyToBeBuiltWhenMultipleAreAvailable()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);

        // This hand can build Stages 4 and 5 of Babylon
        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD), 3);
        hand.addCard(new GreyCard(GreyCard.Material.BRICK));
        hand.addCard(new GreyCard(GreyCard.Material.PAPYRUS));
        hand.addCard(new GreyCard(GreyCard.Material.STONE));
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stagesReadyToBuild = new ArrayList<Stage>();
        stagesReadyToBuild.add(babylon.getStages().get(3));
        stagesReadyToBuild.add(babylon.getStages().get(4));

        assertEquals(stagesReadyToBuild, hand.getStagesReadyToBuild(babylon.getNextStagesToBuild(), false));
    }

    @Test
    void getStagesReadyToBeBuiltShouldReturnAllTheStagesReadyToBeBuiltWhenMultipleAreAvailableWithEngineeringEffect()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);

        // This hand can build Stages 4 and 5 of Babylon
        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.BRICK));
        hand.addCard(new GreyCard(GreyCard.Material.PAPYRUS));
        hand.addCard(new GreyCard(GreyCard.Material.STONE));
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stagesReadyToBuild = new ArrayList<Stage>();
        stagesReadyToBuild.add(babylon.getStages().get(3));
        stagesReadyToBuild.add(babylon.getStages().get(4));

        assertEquals(stagesReadyToBuild, hand.getStagesReadyToBuild(babylon.getNextStagesToBuild(), false, true));
    }

    @Test
    void getStagesReadyToBeBuiltShouldReturnAllTheStagesReadyToBeBuiltWithYellowCards()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);

        // This hand can build Stage 4 of Babylon (but not Stage 5, which is at the same level (4) )
        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD), 2);
        hand.addCard(new YellowCard());
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stagesReadyToBuild = new ArrayList<Stage>();
        stagesReadyToBuild.add(babylon.getStages().get(3));

        assertEquals(stagesReadyToBuild, hand.getStagesReadyToBuild(babylon.getNextStagesToBuild(), false));
    }

    @Test
    void getStagesReadyToBeBuiltShouldReturnAllTheStagesReadyToBeBuiltWithYellowCardsWithEconomyEffect()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);

        // This hand can build Stage 4 of Babylon (but not Stage 5, which is at the same level (4) )
        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new YellowCard());
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stagesReadyToBuild = new ArrayList<Stage>();
        stagesReadyToBuild.add(babylon.getStages().get(3));

        assertEquals(stagesReadyToBuild, hand.getStagesReadyToBuild(babylon.getNextStagesToBuild(), true));
    }

    @Test
    void getStagesReadyToBeBuiltShouldReturnAllTheStagesReadyToBeBuiltWhenMultipleAreAvailableWithYellowCards()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);

        // This hand can build Stages 4 and 5 of Babylon
        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new YellowCard(), 3);
        hand.addCard(new GreyCard(GreyCard.Material.BRICK));
        hand.addCard(new GreyCard(GreyCard.Material.PAPYRUS));
        hand.addCard(new GreyCard(GreyCard.Material.STONE));
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stagesReadyToBuild = new ArrayList<Stage>();
        stagesReadyToBuild.add(babylon.getStages().get(3));
        stagesReadyToBuild.add(babylon.getStages().get(4));

        assertEquals(stagesReadyToBuild, hand.getStagesReadyToBuild(babylon.getNextStagesToBuild(), false));
    }

    @Test
    void getStagesReadyToBeBuiltShouldReturnAllTheStagesReadyToBeBuiltWhenMultipleAreAvailableWithYellowCardsWithEconomyEffect()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);

        // This hand can build Stages 4 and 5 of Babylon
        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new YellowCard(), 2);
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stagesReadyToBuild = new ArrayList<Stage>();
        stagesReadyToBuild.add(babylon.getStages().get(3));
        stagesReadyToBuild.add(babylon.getStages().get(4));

        assertEquals(stagesReadyToBuild, hand.getStagesReadyToBuild(babylon.getNextStagesToBuild(), true));
    }

    @Test
    void getStagesReadyToBeBuiltShouldReturnAllTheStagesReadyToBeBuiltWhenMultipleAreAvailableWithYellowCardsWithEconomyWithEngineeringEffects()
    {
        Babylon babylon = new Babylon();
        babylon.getStages().get(0).setConstructed(true);
        babylon.getStages().get(1).setConstructed(true);
        babylon.getStages().get(2).setConstructed(true);

        // This hand can build Stages 4 and 5 of Babylon
        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new YellowCard(), 2);
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stagesReadyToBuild = new ArrayList<Stage>();
        stagesReadyToBuild.add(babylon.getStages().get(3));
        stagesReadyToBuild.add(babylon.getStages().get(4));

        assertEquals(stagesReadyToBuild, hand.getStagesReadyToBuild(babylon.getNextStagesToBuild(), true, true));
    }

    @Test
    void getCardsRequiredToBuildStageShouldReturnCardsRequiredToBuildStage()
    {
        // Stage 1 of Alexandria requires two different resources
        Stage stage1 = new Alexandria().getStages().get(0);

        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD), 3);
        hand.addCard(new GreyCard(GreyCard.Material.STONE));
        hand.addCard(new RedCard(0));

        HashMap<Card, Integer> cardsRequired = new HashMap<Card, Integer>();
        cardsRequired.put(new GreyCard(GreyCard.Material.WOOD), 1);
        cardsRequired.put(new GreyCard(GreyCard.Material.STONE), 1);

        assertEquals(cardsRequired, hand.getCardsRequiredToBuildStage(stage1, false));

        // Stage 4 of Alexandria requires three identical resources
        Stage stage4 = new Alexandria().getStages().get(3);

        hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD), 2);
        hand.addCard(new GreyCard(GreyCard.Material.STONE));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD), 2);
        hand.addCard(new RedCard(0));

        cardsRequired.clear();
        cardsRequired.put(new GreyCard(GreyCard.Material.WOOD), 3);

        assertEquals(cardsRequired, hand.getCardsRequiredToBuildStage(stage4, false));
    }

    @Test
    void getCardsRequiredToBuildStageShouldReturnCardsRequiredToBuildStageWithEngineeringEffect()
    {
        // Stage 1 of Alexandria requires two different resources
        Stage stage1 = new Alexandria().getStages().get(0);

        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD), 3);
        hand.addCard(new RedCard(0));

        HashMap<Card, Integer> cardsRequired = new HashMap<Card, Integer>();
        cardsRequired.put(new GreyCard(GreyCard.Material.WOOD), 2);

        assertEquals(cardsRequired, hand.getCardsRequiredToBuildStage(stage1, false, true));

        // Stage 4 of Alexandria requires three identical resources
        Stage stage4 = new Alexandria().getStages().get(3);

        hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS));
        hand.addCard(new GreyCard(GreyCard.Material.STONE));
        hand.addCard(new RedCard(0));

        cardsRequired.clear();
        cardsRequired.put(new GreyCard(GreyCard.Material.WOOD), 1);
        cardsRequired.put(new GreyCard(GreyCard.Material.GLASS), 1);
        cardsRequired.put(new GreyCard(GreyCard.Material.STONE), 1);

        assertEquals(cardsRequired, hand.getCardsRequiredToBuildStage(stage4, false, true));
    }

    @Test
    void getCardsRequiredToBuildStageShouldReturnCardsRequiredToBuildStageWithYellowCards()
    {
        // Stage 1 of Alexandria requires two different resources
        Stage stage1 = new Alexandria().getStages().get(0);

        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD), 3);
        hand.addCard(new YellowCard());
        hand.addCard(new RedCard(0));

        HashMap<Card, Integer> cardsRequired = new HashMap<Card, Integer>();
        cardsRequired.put(new GreyCard(GreyCard.Material.WOOD), 1);
        cardsRequired.put(new YellowCard(), 1);

        assertEquals(cardsRequired, hand.getCardsRequiredToBuildStage(stage1, false));

        // Stage 4 of Alexandria requires three identical resources
        Stage stage4 = new Alexandria().getStages().get(3);

        hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD), 2);
        hand.addCard(new GreyCard(GreyCard.Material.STONE));
        hand.addCard(new YellowCard());
        hand.addCard(new RedCard(0));

        cardsRequired.clear();
        cardsRequired.put(new GreyCard(GreyCard.Material.WOOD), 2);
        cardsRequired.put(new YellowCard(), 1);

        assertEquals(cardsRequired, hand.getCardsRequiredToBuildStage(stage4, false));
    }

    @Test
    void getCardsRequiredToBuildStageShouldReturnCardsRequiredToBuildStageWithYellowCardsWithEconomyEffect()
    {
        // Stage 1 of Alexandria requires two different resources
        Stage stage1 = new Alexandria().getStages().get(0);

        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new YellowCard());
        hand.addCard(new RedCard(0));

        HashMap<Card, Integer> cardsRequired = new HashMap<Card, Integer>();
        cardsRequired.put(new YellowCard(), 1);

        assertEquals(cardsRequired, hand.getCardsRequiredToBuildStage(stage1, true));

        // Stage 4 of Alexandria requires three identical resources
        Stage stage4 = new Alexandria().getStages().get(3);

        hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.STONE));
        hand.addCard(new YellowCard());
        hand.addCard(new RedCard(0));

        cardsRequired.clear();
        cardsRequired.put(new GreyCard(GreyCard.Material.STONE), 1);
        cardsRequired.put(new YellowCard(), 1);

        assertEquals(cardsRequired, hand.getCardsRequiredToBuildStage(stage4, true));
    }

    @Test
    void getCardsRequiredToBuildStageShouldReturnCardsRequiredToBuildStageWithYellowCardsWithEconomyAndEngineeringEffects()
    {
        // Stage 1 of Alexandria requires two different resources
        Stage stage1 = new Alexandria().getStages().get(0);

        Hand hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new YellowCard());
        hand.addCard(new RedCard(0));

        HashMap<Card, Integer> cardsRequired = new HashMap<Card, Integer>();
        cardsRequired.put(new YellowCard(), 1);

        assertEquals(cardsRequired, hand.getCardsRequiredToBuildStage(stage1, true, true));

        // Stage 4 of Alexandria requires three identical resources
        Stage stage4 = new Alexandria().getStages().get(3);

        hand = new Hand();
        hand.addCard(new BlueCard(3, false));
        hand.addCard(new YellowCard(), 2);
        hand.addCard(new RedCard(0));

        cardsRequired.clear();
        cardsRequired.put(new YellowCard(), 2);

        assertEquals(cardsRequired, hand.getCardsRequiredToBuildStage(stage4, true, true));
    }

    @Test
    void getCardsRequiredToBuildStageShouldThrowRuntimeExceptionIfStageCannotBeBuilt()
    {
        // Stage 1 of Alexandria requires two different resources
        Stage stage1 = new Alexandria().getStages().get(0);

        final Hand hand1 = new Hand();
        hand1.addCard(new BlueCard(3, false));
        hand1.addCard(new GreyCard(GreyCard.Material.WOOD), 3);
        hand1.addCard(new RedCard(0));

        assertThrows(RuntimeException.class, () -> hand1.getCardsRequiredToBuildStage(stage1, false));

        // Stage 4 of Alexandria requires three identical resources
        Stage stage4 = new Alexandria().getStages().get(3);

        final Hand hand2 = new Hand();
        hand2.addCard(new BlueCard(3, false));
        hand2.addCard(new GreyCard(GreyCard.Material.WOOD), 2);
        hand2.addCard(new GreyCard(GreyCard.Material.STONE));
        hand2.addCard(new GreyCard(GreyCard.Material.GLASS));
        hand2.addCard(new RedCard(0));

        assertThrows(RuntimeException.class, () -> hand2.getCardsRequiredToBuildStage(stage4, false));
    }

    @Test
    void getCardsRequiredToBuildStageShouldThrowRuntimeExceptionIfStageCannotBeBuiltWithYellowCards()
    {
        // Stage 1 of Alexandria requires two different resources
        Stage stage1 = new Alexandria().getStages().get(0);

        final Hand hand1 = new Hand();
        hand1.addCard(new BlueCard(3, false));
        hand1.addCard(new YellowCard(), 1);
        hand1.addCard(new RedCard(0));

        assertThrows(RuntimeException.class, () -> hand1.getCardsRequiredToBuildStage(stage1, false));

        // Stage 4 of Alexandria requires three identical resources
        Stage stage4 = new Alexandria().getStages().get(3);

        final Hand hand2 = new Hand();
        hand2.addCard(new BlueCard(3, false));
        hand2.addCard(new YellowCard(), 2);
        hand2.addCard(new RedCard(0));

        assertThrows(RuntimeException.class, () -> hand2.getCardsRequiredToBuildStage(stage4, false));
    }

    @Test
    void getCardsRequiredToBuildStageShouldThrowRuntimeExceptionIfStageCannotBeBuiltWithYellowCardsWithEconomyEffect()
    {
        // Stage 1 of Alexandria requires three different resources
        Stage stage3 = new Alexandria().getStages().get(2);

        final Hand hand1 = new Hand();
        hand1.addCard(new BlueCard(3, false));
        hand1.addCard(new YellowCard());
        hand1.addCard(new RedCard(0));

        assertThrows(RuntimeException.class, () -> hand1.getCardsRequiredToBuildStage(stage3, true));

        // Stage 4 of Alexandria requires three identical resources
        Stage stage4 = new Alexandria().getStages().get(3);

        final Hand hand2 = new Hand();
        hand2.addCard(new BlueCard(3, false));
        hand2.addCard(new YellowCard());
        hand2.addCard(new RedCard(0));

        assertThrows(RuntimeException.class, () -> hand2.getCardsRequiredToBuildStage(stage4, true));
    }

    @Test
    void getCardsRequiredToBuildStageShouldThrowRuntimeExceptionIfStageCannotBeBuiltWithYellowCardsWithEconomyAndEngineeringEffects()
    {
        // Stage 1 of Alexandria requires three different resources
        Stage stage3 = new Alexandria().getStages().get(2);

        final Hand hand1 = new Hand();
        hand1.addCard(new BlueCard(3, false));
        hand1.addCard(new YellowCard());
        hand1.addCard(new RedCard(0));

        assertThrows(RuntimeException.class, () -> hand1.getCardsRequiredToBuildStage(stage3, true, true));

        // Stage 4 of Alexandria requires three identical resources
        Stage stage4 = new Alexandria().getStages().get(3);

        final Hand hand2 = new Hand();
        hand2.addCard(new BlueCard(3, false));
        hand2.addCard(new YellowCard());
        hand2.addCard(new RedCard(0));

        assertThrows(RuntimeException.class, () -> hand2.getCardsRequiredToBuildStage(stage4, true, true));
    }

    @Test
    void getNumberOfShieldsShouldReturnTheNumberOfShields()
    {
        Hand hand1 = new Hand();
        hand1.addCard(new RedCard(0));
        hand1.addCard(new RedCard(1), 2);
        hand1.addCard(new RedCard(2));

        assertEquals(4, hand1.getNumberOfShields());

        Hand hand2 = new Hand();

        assertEquals(0, hand2.getNumberOfShields());
    }

    @Test
    void discardRedCardsWithHornsShouldRemoveRedCardsWithHornsFromHand()
    {
        Game game = new Game(new ConsoleParser(), new ConsoleOutput());

        Hand hand1 = new Hand();
        hand1.addCard(new RedCard(0), 2);
        hand1.addCard(new RedCard(1), 2);
        hand1.addCard(new RedCard(2));

        HashMap<Card, Integer> expectedCards = new HashMap<>();
        expectedCards.put(new RedCard(0), 2);

        hand1.discardRedCardsWithHorns(game.getDiscard());
        assertEquals(expectedCards, hand1.getCards());

        Hand hand2 = new Hand();
        hand2.addCard(new RedCard(0), 3);

        expectedCards.clear();
        expectedCards.put(new RedCard(0), 3);

        hand2.discardRedCardsWithHorns(game.getDiscard());
        assertEquals(expectedCards, hand2.getCards());
    }

    @Test
    void discardRedCardsWithHornsShouldAddDiscardedCardsToGameDiscard()
    {
        Game game1 = new Game(new ConsoleParser(), new ConsoleOutput());

        Hand hand1 = new Hand();
        hand1.addCard(new RedCard(0), 2);
        hand1.addCard(new RedCard(1), 2);
        hand1.addCard(new RedCard(2));

        Deck deck1 = new Deck();
        deck1.addCard(new RedCard(1), 2);
        deck1.addCard(new RedCard(2));

        hand1.discardRedCardsWithHorns(game1.getDiscard());

        assertTrue(deck1.getCards().containsAll(game1.getDiscard().getCards()));

        Game game2 = new Game(new ConsoleParser(), new ConsoleOutput());

        Hand hand2 = new Hand();
        hand2.addCard(new RedCard(0), 3);

        Deck deck2 = new Deck();

        hand2.discardRedCardsWithHorns(game2.getDiscard());

        assertTrue(deck2.getCards().containsAll(game2.getDiscard().getCards()));
    }
}