package com.isep.utils;

import com.isep.game.Player;

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
        System.out.printf("==== NOW PLAYING: %s ====%n", player.getName());
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
}
