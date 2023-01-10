package com.isep.game.cards;

import com.isep.game.wonders.Alexandria;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
    @Disabled
    void pickCardShouldRemoveTheFirstCardOfTheDeck()
    {
        //TODO pickCardShouldRemoveTheFirstCardOfTheDeck
    }

    @Test
    void deckEqualsShouldBeReflective()
    {
        Deck deck = new Alexandria().getDeck();
        assertEquals(deck, deck);
    }

    @Test
    @Disabled
    void deckEqualsShouldBeSymmetric()
    {
        //TODO: deckEqualsShouldBeSymmetric
    }

    @Test
    @Disabled
    void deckEqualsShouldBeConsistent()
    {
        //TODO: deckEqualsShouldBeConsistent
    }

    @Test
    void deckEqualsShouldReturnFalseOnNull()
    {
        Deck deck = new Alexandria().getDeck();
        assertNotEquals(null, deck);
    }
}