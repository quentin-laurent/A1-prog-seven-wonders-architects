package com.isep.game.cards;

import com.isep.game.wonders.Alexandria;
import com.isep.game.wonders.Stage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        hand.addCard(card);
        hand.addCard(card);

        assertEquals(2, (int) hand.getCards().get(card));
    }

    @Test
    void removingCardAvailableInMultipleCopiesShouldDecreaseItsQuantity()
    {
        Hand hand = new Hand();
        Card card = new GreyCard(GreyCard.Material.WOOD);
        hand.addCard(card);
        hand.addCard(card);
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
        assertTrue(hand.canBuildStage(stages));

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
        assertTrue(hand.canBuildStage(stages));
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
        assertTrue(hand.canBuildStage(stages));

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
        assertTrue(hand.canBuildStage(stages));
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
        assertFalse(hand.canBuildStage(stages));

        // Stage 4 of Alexandria requires 3 identical resources
        Stage stage4 = new Alexandria().getStages().get(3);
        hand = new Hand();
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS));

        stages.clear();
        stages.add(stage4);
        assertFalse(hand.canBuildStage(stages));
    }

    @Test
    void testToString()
    {
        Hand hand = new Hand();
        hand.addCard(new YellowCard());
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
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
}