package com.isep.game.cards;

import com.isep.game.tokens.ProgressToken;
import com.isep.game.tokens.ProgressTokenStack;
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
    void addCardShouldAddCardToDeck()
    {
        Deck deck = new Deck();
        GreyCard card = new GreyCard(GreyCard.Material.WOOD);
        deck.addCard(card);

        assertTrue(deck.getCards().contains(card));
    }

    @Test
    void addCardShouldAddMultipleCardsToDeck()
    {
        Deck deck = new Deck();
        GreyCard card = new GreyCard(GreyCard.Material.WOOD);
        deck.addCard(card, 2);

        assertTrue(deck.getCards().get(0).equals(card) && deck.getCards().get(1).equals(card) && deck.getCards().size() == 2);
    }

    @Test
    void addCardShouldThrowRuntimeExceptionWhenAddingLessThanOneCard()
    {
        Deck deck = new Deck();
        GreyCard card = new GreyCard(GreyCard.Material.WOOD);

        assertThrows(RuntimeException.class, () -> deck.addCard(card, 0));
        assertThrows(RuntimeException.class, () -> deck.addCard(card, -1));
        assertThrows(RuntimeException.class, () -> deck.addCard(card, -999));
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
    void getTopCardShouldReturnFirstCardOfTheDeck()
    {
        Deck deck = new Alexandria().getDeck();
        Card firstCard = deck.getCards().get(0);

        assertEquals(firstCard, deck.getTopCard());
    }

    @Test
    void testToString()
    {
        StringBuilder expectedString = new StringBuilder("4 cards:\n");
        expectedString.append("[BLUE] 3 Victory points\n");
        expectedString.append("[YELLOW] Gold\n");
        expectedString.append("[RED] 2 Horns\n");
        expectedString.append("[GREY] WOOD");

        Deck deck = new Deck();
        deck.addCard(new BlueCard(3, false));
        deck.addCard(new YellowCard());
        deck.addCard(new RedCard(2));
        deck.addCard(new GreyCard(GreyCard.Material.WOOD));

        assertEquals(expectedString.toString(), deck.toString());
    }

    @Test
    void equalsShouldBeReflective()
    {
        Deck deck = new Alexandria().getDeck();
        assertEquals(deck, deck);
    }

    @Test
    void equalsShouldBeSymmetric()
    {
        Alexandria alexandria = new Alexandria();
        Deck deck1 = alexandria.getDeck();
        Deck deck2 = alexandria.getDeck();

        assertTrue(deck1.equals(deck2) && deck2.equals(deck1));
    }

    @RepeatedTest(100)
    void equalsShouldBeConsistent()
    {
        Deck deck = new Alexandria().getDeck();
        assertEquals(deck, deck);
    }

    @Test
    void equalsShouldReturnFalseOnNull()
    {
        Deck deck = new Alexandria().getDeck();
        assertFalse(deck.equals(null));
    }

    @Test
    void equalsShoudReturnFalseOnDifferentClass()
    {
        Deck deck = new Alexandria().getDeck();
        ProgressTokenStack tokenStack = new ProgressTokenStack();
        tokenStack.addProgressToken(new ProgressToken(ProgressToken.Effect.TACTICS));

        assertNotEquals(deck, tokenStack);
    }
}