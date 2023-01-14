package com.isep.game.cards;

import com.isep.game.wonders.Alexandria;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DeckTest
{
    @Test
    void newlyCreatedDeckShouldBeEmpty()
    {
        Deck deck = new Deck();
        assertTrue(deck.isEmpty());
    }

    @Test
    void deckWithCardsShouldNotBeEmpty()
    {
        Deck deck = new Deck();
        deck.addCard(new GreyCard(GreyCard.Material.WOOD));
        deck.addCard(new GreenCard(GreenCard.ScienceSymbol.TABLET));

        assertFalse(deck.isEmpty());
    }

    @Test
    void deckWithNoCardsLeftShouldBeEmpty()
    {
        Deck deck = new Deck();
        deck.addCard(new GreyCard(GreyCard.Material.WOOD));
        deck.pickCard();

        assertTrue(deck.isEmpty());
    }

    @Test
    void pickCardShouldRemoveTheFirstCardOfTheDeck()
    {
        Deck deck = new Alexandria().getDeck();
        List<Card> expectedCards = deck.getCards();
        expectedCards.remove(0);
        deck.pickCard();

        assertEquals(expectedCards, deck.getCards());
    }

    @Test
    void deckEqualsShouldBeReflective()
    {
        Deck deck = new Alexandria().getDeck();
        assertEquals(deck, deck);
    }

    @Test
    void deckEqualsShouldBeSymmetric()
    {
        Alexandria alexandria = new Alexandria();
        Deck deck1 = alexandria.getDeck();
        Deck deck2 = alexandria.getDeck();

        assertTrue(deck1.equals(deck2) && deck2.equals(deck1));
    }

    @RepeatedTest(100)
    void deckEqualsShouldBeConsistent()
    {
        Deck deck = new Alexandria().getDeck();
        assertEquals(deck, deck);
    }

    @Test
    void deckEqualsShouldReturnFalseOnNull()
    {
        Deck deck = new Alexandria().getDeck();
        assertNotEquals(null, deck);
    }
}