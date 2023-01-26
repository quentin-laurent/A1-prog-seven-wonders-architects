package com.isep.game.cards;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedCardTest
{
    @Test
    void testToString()
    {
        RedCard card1 = new RedCard(0);
        String expectedString1 = "[RED] 0 Horns";

        assertEquals(expectedString1, card1.toString());

        RedCard card2 = new RedCard(2);
        String expectedString2 = "[RED] 2 Horns";

        assertEquals(expectedString2, card2.toString());
    }

    @Test
    void equalsShouldBeReflective()
    {
        RedCard card = new RedCard(2);

        assertEquals(card, card);
    }

    @Test
    void equalsShouldBeSymmetric()
    {
        RedCard card1 = new RedCard(2);
        RedCard card2 = new RedCard(2);

        assertTrue(card1.equals(card2) && card2.equals(card1));
    }

    @RepeatedTest(100)
    void equalsShouldBeConsistent()
    {
        RedCard card = new RedCard(1);

        assertEquals(card, card);
    }

    @Test
    void equalsShouldReturnFalseOnNull()
    {
        RedCard card = new RedCard(0);

        assertFalse(card.equals(null));
    }

    @Test
    void equalsShoudReturnFalseOnDifferentClass()
    {
        RedCard card = new RedCard(0);
        YellowCard yellowCard = new YellowCard();

        assertNotEquals(card, yellowCard);
    }
}