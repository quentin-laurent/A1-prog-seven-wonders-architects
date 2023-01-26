package com.isep.game.cards;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YellowCardTest
{
    @Test
    void testToString()
    {
        YellowCard card = new YellowCard();
        String expectedString = "[YELLOW] Gold";

        assertEquals(expectedString, card.toString());
    }

    @Test
    void equalsShouldBeReflective()
    {
        YellowCard card = new YellowCard();

        assertEquals(card, card);
    }

    @Test
    void equalsShouldBeSymmetric()
    {
        YellowCard card1 = new YellowCard();
        YellowCard card2 = new YellowCard();

        assertTrue(card1.equals(card2) && card2.equals(card1));
    }

    @RepeatedTest(100)
    void equalsShouldBeConsistent()
    {
        YellowCard card = new YellowCard();

        assertEquals(card, card);
    }

    @Test
    void equalsShouldReturnFalseOnNull()
    {
        YellowCard card = new YellowCard();

        assertFalse(card.equals(null));
    }

    @Test
    void equalsShoudReturnFalseOnDifferentClass()
    {
        YellowCard card = new YellowCard();
        BlueCard blueCard = new BlueCard(4, false);

        assertNotEquals(card, blueCard);
    }
}