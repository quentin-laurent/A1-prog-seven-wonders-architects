package com.isep.game.tokens;

import com.isep.game.cards.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgressTokenStackTest
{
    @Test
    void addProgressTokenShouldAddProgressTokenToStackIfThereAreThreeRevealedToken()
    {
        ProgressTokenStack stack = new ProgressTokenStack();
        ProgressToken token = new ProgressToken(ProgressToken.Effect.ECONOMY);
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.TACTICS));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.JEWELLERY));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.CRAFTS));
        stack.addProgressToken(token);

        assertTrue(stack.getTokens().contains(token));
    }

    @Test
    void addProgressTokenShouldAddMultipleProgressTokenToStack()
    {
        ProgressTokenStack stack = new ProgressTokenStack();
        ProgressToken token = new ProgressToken(ProgressToken.Effect.ECONOMY);
        stack.addProgressToken(token, 2);

        assertTrue(stack.getTokens().get(0).equals(token) && stack.getTokens().get(1).equals(token) && stack.getTokens().size() == 2);
    }

    @Test
    void addProgressTokenShouldThrowRuntimeExceptionWhenAddingLessThanOneToken()
    {
        ProgressTokenStack stack = new ProgressTokenStack();
        ProgressToken token = new ProgressToken(ProgressToken.Effect.ECONOMY);

        assertThrows(RuntimeException.class, () -> stack.addProgressToken(token, 0));
        assertThrows(RuntimeException.class, () -> stack.addProgressToken(token, -1));
        assertThrows(RuntimeException.class, () -> stack.addProgressToken(token, -999));
    }

    @Test
    void pickProgressTokenShouldReturnTheTokenAtTheCorrespondingIndex()
    {
        ProgressTokenStack stack = new ProgressTokenStack();
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.ECONOMY));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.CRAFTS));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.CULTURE));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.DECOR));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.TACTICS));

        assertEquals(new ProgressToken(ProgressToken.Effect.CRAFTS), stack.pickProgressToken(1));
        assertEquals(new ProgressToken(ProgressToken.Effect.TACTICS), stack.pickProgressToken(3));
    }

    @Test
    void pickProgressTokenShouldThrowRuntimeExceptionWhenIndexIsOutOfBounds()
    {
        ProgressTokenStack stack = new ProgressTokenStack();
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.ECONOMY));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.CRAFTS));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.CULTURE));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.DECOR));

        assertThrows(RuntimeException.class, () -> stack.pickProgressToken(4));
        assertThrows(RuntimeException.class, () -> stack.pickProgressToken(-1));
        assertThrows(RuntimeException.class, () -> stack.pickProgressToken(999));
        assertThrows(RuntimeException.class, () -> stack.pickProgressToken(-999));
    }

    @Test
    void testToString()
    {
        StringBuilder expectedString = new StringBuilder("4 tokens:\n");
        expectedString.append("[PROGRESS TOKEN] ECONOMY\n");
        expectedString.append("[PROGRESS TOKEN] CRAFTS\n");
        expectedString.append("[PROGRESS TOKEN] CULTURE\n");
        expectedString.append("[PROGRESS TOKEN] DECOR");

        ProgressTokenStack stack = new ProgressTokenStack();
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.ECONOMY));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.CRAFTS));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.CULTURE));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.DECOR));

        assertEquals(expectedString.toString(), stack.toString());
    }

    @Test
    void testToStringWithThreeTokensOrLess()
    {
        StringBuilder expectedString = new StringBuilder("3 tokens:\n");
        expectedString.append("[PROGRESS TOKEN] ECONOMY\n");
        expectedString.append("[PROGRESS TOKEN] CRAFTS\n");
        expectedString.append("[PROGRESS TOKEN] CULTURE");

        ProgressTokenStack stack = new ProgressTokenStack();
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.ECONOMY));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.CRAFTS));
        stack.addProgressToken(new ProgressToken(ProgressToken.Effect.CULTURE));

        assertEquals(expectedString.toString(), stack.toString());
    }
}