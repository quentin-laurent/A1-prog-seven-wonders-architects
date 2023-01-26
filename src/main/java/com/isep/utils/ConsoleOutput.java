package com.isep.utils;

import com.isep.game.Player;
import com.isep.game.wonders.Stage;
import com.isep.game.wonders.Wonder;

import java.util.List;

/**
 * An implementation of the {@link OutputManager} interface that uses the standard output (a.k.a console or terminal)
 * to display information about the actions happening in the Game.
 * @author Quentin LAURENT
 */
public class ConsoleOutput implements OutputManager
{
    public void displayMessage(String message)
    {
        System.out.println(message);
    }

    public void displayPlayerTurn(Player player)
    {
        System.out.printf("==== NOW PLAYING: %s (%s) ====%n", player.getName(), player.getWonder().getName());
    }

    public void displayPlayerHand(Player player)
    {
        int sLength = 4 + 1 + player.getName().length() + 2 + 1 + 4;
        System.out.printf("==== %s's hand ====%n", player.getName());
        System.out.println(player.getHand());
        StringBuilder s = new StringBuilder();
        s.append("=".repeat(sLength));
        System.out.println(s);
        System.out.println();
    }

    public void displayStageBuilt(Player player, Stage stage, Wonder wonder)
    {
        System.out.printf("%s built a part of %s ! [%d/%d]%n%n", player.getName(), wonder.getName(), wonder.getNumberOfStagesBuilt(), wonder.getNumberOfStages());
    }

    public void displayResults(List<Player> players)
    {
        if(players.isEmpty())
            return;

        System.out.println("===== RESULTS =====");
        Player winner = players.get(0);

        for(Player player: players)
        {
            System.out.printf("%s: %d Victory points%n", player.getName(), player.getVictoryPoints());
            winner = (player.getVictoryPoints() > winner.getVictoryPoints()) ? player : winner;
        }

        System.out.printf("%n %s wins !%n", winner.getName());
    }
}
