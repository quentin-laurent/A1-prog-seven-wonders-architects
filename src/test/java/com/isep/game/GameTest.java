package com.isep.game;

import com.isep.game.cards.*;
import com.isep.game.wonders.Alexandria;
import com.isep.game.wonders.Stage;
import com.isep.utils.ConsoleOutput;
import com.isep.utils.ConsoleParser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest
{
    @Test
    void ifAPlayerCanBuildAStageItShouldBeBuilt()
    {
        // Checking if the player can build the Stage
        Alexandria alexandria = new Alexandria();

        Player player = new Player("bob", LocalDate.of(1999, 1, 1), alexandria);
        Hand hand = player.getHand();

        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.GLASS));
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stages = new ArrayList<>();
        stages.add(alexandria.getStages().get(0));

        assertTrue(player.getHand().canBuildStage(stages));

        // Checking if the Stage has been built
        alexandria.buildStage(alexandria.getStages().get(0), player.getHand());

        assertTrue(alexandria.getStages().get(0).isConstructed());

        // Checking if the cards have been removed from the player's hand after building the Stage
        Hand handAfterBuildingStage = new Hand();
        handAfterBuildingStage.addCard(new BlueCard(3, false));
        handAfterBuildingStage.addCard(new GreyCard(GreyCard.Material.WOOD));
        handAfterBuildingStage.addCard(new RedCard(0));

        assertEquals(handAfterBuildingStage, player.getHand());
    }

    @Test
    void ifAPlayerCanBuildAStageUsingYellowCardsItShouldBeBuilt()
    {
        // Checking if the player can build the Stage
        Alexandria alexandria = new Alexandria();

        Player player = new Player("bob", LocalDate.of(1999, 1, 1), alexandria);
        Hand hand = player.getHand();

        hand.addCard(new BlueCard(3, false));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new GreyCard(GreyCard.Material.WOOD));
        hand.addCard(new YellowCard());
        hand.addCard(new RedCard(0));

        ArrayList<Stage> stages = new ArrayList<>();
        stages.add(alexandria.getStages().get(0));

        assertTrue(player.getHand().canBuildStage(stages));

        // Checking if the Stage has been built
        alexandria.buildStage(alexandria.getStages().get(0), player.getHand());

        assertTrue(alexandria.getStages().get(0).isConstructed());

        // Checking if the cards have been removed from the player's hand after building the Stage
        Hand handAfterBuildingStage = new Hand();
        handAfterBuildingStage.addCard(new BlueCard(3, false));
        handAfterBuildingStage.addCard(new GreyCard(GreyCard.Material.WOOD));
        handAfterBuildingStage.addCard(new RedCard(0));

        assertEquals(handAfterBuildingStage, player.getHand());
    }

    @Test
    @Disabled
    void resolveBattleShouldFlipAllConflictTokensOnTheirPeaceSide()
    {
        //Can't call because method is private
        // game.resolveBattle();
    }

    @Test
    @Disabled
    void resolveBattleShouldDistributePointsAccordingly()
    {
        Game game = new Game(new ConsoleParser(), new ConsoleOutput());

        Player p1 = new Player("alice", LocalDate.of(1999, 1, 1), new Alexandria());
        Player p2 = new Player("bob", LocalDate.of(2000, 1, 1), new Alexandria());
        Player p3 = new Player("charlie", LocalDate.of(2001, 1, 1), new Alexandria());

        p1.getHand().addCard(new RedCard(0));
        p1.getHand().addCard(new RedCard(1));
        p1.getHand().addCard(new RedCard(2));

        p2.getHand().addCard(new RedCard(0));
        p2.getHand().addCard(new RedCard(2));

        p3.getHand().addCard(new RedCard(1));

        //Can't call because method is private
        // game.resolveBattle();

    }
}