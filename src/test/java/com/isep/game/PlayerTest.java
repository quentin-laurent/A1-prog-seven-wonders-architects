package com.isep.game;

import com.isep.game.tokens.ProgressToken;
import com.isep.game.tokens.ProgressTokenStack;
import com.isep.game.wonders.Alexandria;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest
{
    @Test
    void addMilitaryVictoryTokensShouldAddMilitaryVictoryTokens()
    {
        Player player = new Player("alice", LocalDate.of(2000, 1, 1), new Alexandria());
        player.addMilitaryVictoryTokens(5);

        assertEquals(5, player.getMilitaryVictoryTokens());
    }

    @Test
    void addMilitaryVictoryTokensShouldThrowRuntimeExceptionIfQuantityIsLessThanOne()
    {
        Player player = new Player("alice", LocalDate.of(2000, 1, 1), new Alexandria());

        assertThrows(RuntimeException.class, () -> player.addMilitaryVictoryTokens(0));
        assertThrows(RuntimeException.class, () -> player.addMilitaryVictoryTokens(-1));
        assertThrows(RuntimeException.class, () -> player.addMilitaryVictoryTokens(-999));
    }

    @Test
    void addVictoryPointsShouldAddVictoryPoints()
    {
        Player player = new Player("alice", LocalDate.of(2000, 1, 1), new Alexandria());
        player.addVictoryPoints(12);

        assertEquals(12, player.getVictoryPoints());
    }

    @Test
    void addVictoryPointsShouldThrowRuntimeExceptionIfAmountIsLessThanOne()
    {
        Player player = new Player("alice", LocalDate.of(2000, 1, 1), new Alexandria());

        assertThrows(RuntimeException.class, () -> player.addVictoryPoints(0));
        assertThrows(RuntimeException.class, () -> player.addVictoryPoints(-1));
        assertThrows(RuntimeException.class, () -> player.addVictoryPoints(-999));
    }

    @Test
    void addProgressTokenShouldAddProgressTokenToPlayerTokens()
    {
        Player player = new Player("alice", LocalDate.of(2000, 1, 1), new Alexandria());
        ProgressToken token = new ProgressToken(ProgressToken.Effect.ECONOMY);
        player.addProgressToken(token);

        assertEquals(token, player.getProgressTokens().get(0));
    }

    @Test
    void hasProgressTokenEffectShouldReturnTrueIfPlayerHasCorrespondingProgressToken()
    {
        Player player = new Player("alice", LocalDate.of(2000, 1, 1), new Alexandria());
        ProgressToken token = new ProgressToken(ProgressToken.Effect.ECONOMY);
        player.addProgressToken(new ProgressToken(ProgressToken.Effect.ENGINEERING));
        player.addProgressToken(token);
        player.addProgressToken(new ProgressToken(ProgressToken.Effect.TACTICS));

        assertTrue(player.hasProgressTokenEffect(ProgressToken.Effect.ECONOMY));
    }

    @Test
    void hasProgressTokenEffectShouldReturnFalseIfPlayerDoesNotHaveCorrespondingProgressToken()
    {
        Player player = new Player("alice", LocalDate.of(2000, 1, 1), new Alexandria());
        ProgressToken token = new ProgressToken(ProgressToken.Effect.ECONOMY);
        player.addProgressToken(new ProgressToken(ProgressToken.Effect.ENGINEERING));
        player.addProgressToken(token);
        player.addProgressToken(new ProgressToken(ProgressToken.Effect.TACTICS));

        assertFalse(player.hasProgressTokenEffect(ProgressToken.Effect.JEWELLERY));
    }

    @Test
    void hasProgressTokenEffectShouldReturnTrueIfPlayerHasExactAmountOfCorrespondingProgressToken()
    {
        Player player = new Player("alice", LocalDate.of(2000, 1, 1), new Alexandria());
        ProgressToken token = new ProgressToken(ProgressToken.Effect.CULTURE);
        player.addProgressToken(new ProgressToken(ProgressToken.Effect.ENGINEERING));
        player.addProgressToken(token);
        player.addProgressToken(token);
        player.addProgressToken(new ProgressToken(ProgressToken.Effect.TACTICS));

        assertTrue(player.hasProgressTokenEffect(ProgressToken.Effect.CULTURE, 2));
    }

    @Test
    void hasProgressTokenEffectShouldReturnFalseIfPlayerDoesNotHaveExactAmountOfCorrespondingProgressToken()
    {
        Player player = new Player("alice", LocalDate.of(2000, 1, 1), new Alexandria());
        ProgressToken token = new ProgressToken(ProgressToken.Effect.CULTURE);
        player.addProgressToken(new ProgressToken(ProgressToken.Effect.ENGINEERING));
        player.addProgressToken(token);
        player.addProgressToken(new ProgressToken(ProgressToken.Effect.TACTICS));

        assertFalse(player.hasProgressTokenEffect(ProgressToken.Effect.CULTURE, 2));
    }

    @Test
    void testToString()
    {
        Player player = new Player("alice", LocalDate.of(2000, 1, 1), new Alexandria());
        String expectedString = "[PLAYER] alice, 01/01/2000, Alexandria";

        assertEquals(expectedString, player.toString());
    }

    @Test
    void testCompareTo()
    {
        Player alice = new Player("alice", LocalDate.of(2000, 7, 12), new Alexandria());
        Player bob = new Player("bob", LocalDate.of(2000, 10, 5), new Alexandria());
        Player charlie = new Player("charlie", LocalDate.of(1999, 2, 28), new Alexandria());
        Player david = new Player("david", LocalDate.of(2000, 7, 12), new Alexandria());

        assertEquals(-1, alice.compareTo(charlie));
        assertEquals(0, alice.compareTo(david));
        assertEquals(1, alice.compareTo(bob));
    }

    @Test
    void compareToShouldThrowRuntimeExceptionIfPlayersCannotBeCompared()
    {
        Player alice = new Player("alice", LocalDate.of(2000, 7, 12), new Alexandria());
        Player bob = new Player("bob", null, new Alexandria());

        assertThrows(RuntimeException.class, () -> alice.compareTo(bob));
    }
}
