package com.isep.game.tokens;

import com.isep.game.cards.BlueCard;
import com.isep.game.cards.YellowCard;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgressTokenTest
{
    @Test
    void testToString()
    {
        String expectedString1 = "[PROGRESS TOKEN] ENGINEERING";
        ProgressToken token1 = new ProgressToken(ProgressToken.Effect.ENGINEERING);

        assertEquals(expectedString1, token1.toString());

        String expectedString2 = "[PROGRESS TOKEN] TACTICS";
        ProgressToken token2 = new ProgressToken(ProgressToken.Effect.TACTICS);

        assertEquals(expectedString2, token2.toString());
    }

    @Test
    void equalsShouldBeReflective()
    {
        ProgressToken token = new ProgressToken(ProgressToken.Effect.ENGINEERING);

        assertEquals(token, token);
    }

    @Test
    void equalsShouldBeSymmetric()
    {
        ProgressToken token1 = new ProgressToken(ProgressToken.Effect.ENGINEERING);
        ProgressToken token2 = new ProgressToken(ProgressToken.Effect.ENGINEERING);

        assertTrue(token1.equals(token2) && token2.equals(token1));
    }

    @RepeatedTest(100)
    void equalsShouldBeConsistent()
    {
        ProgressToken token = new ProgressToken(ProgressToken.Effect.ENGINEERING);

        assertEquals(token, token);
    }

    @Test
    void equalsShouldReturnFalseOnNull()
    {
        ProgressToken token = new ProgressToken(ProgressToken.Effect.ENGINEERING);

        assertFalse(token.equals(null));
    }

    @Test
    void equalsShoudReturnFalseOnDifferentClass()
    {
        ProgressToken token = new ProgressToken(ProgressToken.Effect.ENGINEERING);
        BlueCard blueCard = new BlueCard(4, false);

        assertNotEquals(token, blueCard);
    }
}