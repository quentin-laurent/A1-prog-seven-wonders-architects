package com.isep.game.cards;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreyCardTest
{
    @Test
    void testToString()
    {
        GreyCard card1 = new GreyCard(GreyCard.Material.STONE);
        String expectedString1 = "[GREY] STONE";

        assertEquals(expectedString1, card1.toString());

        GreyCard card2 = new GreyCard(GreyCard.Material.BRICK);
        String expectedString2 = "[GREY] BRICK";

        assertEquals(expectedString2, card2.toString());

        GreyCard card3 = new GreyCard(GreyCard.Material.PAPYRUS);
        String expectedString3 = "[GREY] PAPYRUS";

        assertEquals(expectedString3, card3.toString());

        GreyCard card4 = new GreyCard(GreyCard.Material.WOOD);
        String expectedString4 = "[GREY] WOOD";

        assertEquals(expectedString4, card4.toString());

        GreyCard card5 = new GreyCard(GreyCard.Material.GLASS);
        String expectedString5 = "[GREY] GLASS";

        assertEquals(expectedString5, card5.toString());
    }

    @Test
    void equalsShouldBeReflective()
    {
        GreyCard card = new GreyCard(GreyCard.Material.WOOD);

        assertEquals(card, card);
    }

    @Test
    void equalsShouldBeSymmetric()
    {
        GreyCard card1 = new GreyCard(GreyCard.Material.GLASS);
        GreyCard card2 = new GreyCard(GreyCard.Material.GLASS);

        assertTrue(card1.equals(card2) && card2.equals(card1));
    }

    @RepeatedTest(100)
    void equalsShouldBeConsistent()
    {
        GreyCard card = new GreyCard(GreyCard.Material.BRICK);

        assertEquals(card, card);
    }

    @Test
    void equalsShouldReturnFalseOnNull()
    {
        GreyCard card = new GreyCard(GreyCard.Material.STONE);

        assertFalse(card.equals(null));
    }

    @Test
    void equalsShoudReturnFalseOnDifferentClass()
    {
        GreyCard card = new GreyCard(GreyCard.Material.WOOD);
        YellowCard yellowCard = new YellowCard();

        assertNotEquals(card, yellowCard);
    }
}