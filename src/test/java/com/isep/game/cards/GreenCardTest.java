package com.isep.game.cards;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreenCardTest
{
    @Test
    void testToString()
    {
        GreenCard card1 = new GreenCard(GreenCard.ScienceSymbol.GEAR);
        String expectedString1 = "[GREEN] GEAR";

        assertEquals(expectedString1, card1.toString());

        GreenCard card2 = new GreenCard(GreenCard.ScienceSymbol.TABLET);
        String expectedString2 = "[GREEN] TABLET";

        assertEquals(expectedString2, card2.toString());

        GreenCard card3 = new GreenCard(GreenCard.ScienceSymbol.COMPASS);
        String expectedString3 = "[GREEN] COMPASS";

        assertEquals(expectedString3, card3.toString());
    }

    @Test
    void equalsShouldBeReflective()
    {
        GreenCard card = new GreenCard(GreenCard.ScienceSymbol.GEAR);

        assertEquals(card, card);
    }

    @Test
    void equalsShouldBeSymmetric()
    {
        GreenCard card1 = new GreenCard(GreenCard.ScienceSymbol.TABLET);
        GreenCard card2 = new GreenCard(GreenCard.ScienceSymbol.TABLET);

        assertTrue(card1.equals(card2) && card2.equals(card1));
    }

    @RepeatedTest(100)
    void equalsShouldBeConsistent()
    {
        GreenCard card = new GreenCard(GreenCard.ScienceSymbol.COMPASS);

        assertEquals(card, card);
    }

    @Test
    void equalsShouldReturnFalseOnNull()
    {
        GreenCard card = new GreenCard(GreenCard.ScienceSymbol.GEAR);

        assertFalse(card.equals(null));
    }

    @Test
    void equalsShoudReturnFalseOnDifferentClass()
    {
        GreenCard card = new GreenCard(GreenCard.ScienceSymbol.TABLET);
        YellowCard yellowCard = new YellowCard();

        assertNotEquals(card, yellowCard);
    }
}