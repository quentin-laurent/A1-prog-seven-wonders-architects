package com.isep.game.cards;

import com.isep.game.wonders.Alexandria;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlueCardTest
{
    @Test
    void testToString()
    {
        BlueCard card1 = new BlueCard(4, true);
        String expectedString1 = "[BLUE] 4 Victory points, [CAT]";

        assertEquals(expectedString1, card1.toString());

        BlueCard card2 = new BlueCard(3, false);
        String expectedString2 = "[BLUE] 3 Victory points";

        assertEquals(expectedString2, card2.toString());
    }

    @Test
    void equalsShouldBeReflective()
    {
        BlueCard card = new BlueCard(3 , false);

        assertEquals(card, card);
    }

    @Test
    void equalsShouldBeSymmetric()
    {
        BlueCard card1 = new BlueCard(4, false);
        BlueCard card2 = new BlueCard(4, false);

        assertTrue(card1.equals(card2) && card2.equals(card1));
    }

    @RepeatedTest(100)
    void equalsShouldBeConsistent()
    {
        BlueCard card = new BlueCard(3, true);

        assertEquals(card, card);
    }

    @Test
    void equalsShouldReturnFalseOnNull()
    {
        BlueCard card = new BlueCard(4, true);

        assertFalse(card.equals(null));
    }

    @Test
    void equalsShoudReturnFalseOnDifferentClass()
    {
        BlueCard card = new BlueCard(4, false);
        YellowCard yellowCard = new YellowCard();

        assertNotEquals(card, yellowCard);
    }
}