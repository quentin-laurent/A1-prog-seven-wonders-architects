package com.isep.game.wonders;

import com.isep.game.cards.BlueCard;
import com.isep.game.cards.YellowCard;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StageTest
{
    @Test
    void testToString()
    {
        Stage stage1 = new Ephesus().getStages().get(0);

        StringBuilder expectedString = new StringBuilder();
        expectedString.append(String.format("Victory points: 3%n"));
        expectedString.append(String.format("Resources required: 2%n"));
        expectedString.append(String.format("Resources should be equal: false%n"));
        expectedString.append(String.format("Has effect: false%n"));
        expectedString.append(String.format("Constructed: false%n"));
        expectedString.append(String.format("Level: 1%n"));

        assertEquals(expectedString.toString(), stage1.toString());
    }

    @Test
    void equalsShouldBeReflective()
    {
        Stage stage = new Stage(3, 2, false, false, false, 1);

        assertEquals(stage, stage);
    }

    @Test
    void equalsShouldBeSymmetric()
    {
        Stage stage1 = new Stage(3, 2, false, false, false, 1);
        Stage stage2 = new Stage(3, 2, false, false, false, 1);

        assertTrue(stage1.equals(stage2) && stage1.equals(stage2));
    }

    @RepeatedTest(100)
    void equalsShouldBeConsistent()
    {
        Stage stage = new Stage(3, 2, false, false, false, 1);

        assertEquals(stage, stage);
    }

    @Test
    void equalsShouldReturnFalseOnNull()
    {
        Stage stage = new Stage(3, 2, false, false, false, 1);

        assertFalse(stage.equals(null));
    }

    @Test
    void equalsShoudReturnFalseOnDifferentClass()
    {
        Stage stage = new Stage(3, 2, false, false, false, 1);
        Wonder wonder = new Alexandria();

        assertNotEquals(stage, wonder);
    }
}