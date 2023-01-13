package com.isep.utils;

import com.isep.game.Player;

/**
 * An interface used to display information about the actions happening in the Game.
 * @author Quentin LAURENT
 */
public interface OutputManager
{
    void displayMessage(String message);

    void displayPlayerTurn(Player player);

    void displayPlayerHand(Player player);
}
