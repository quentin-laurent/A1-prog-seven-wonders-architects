package com.isep.utils;

import com.isep.game.Player;
import com.isep.game.wonders.Stage;
import com.isep.game.wonders.Wonder;

import java.util.List;

/**
 * An interface used to display information about the actions happening in the Game.
 * @author Quentin LAURENT
 */
public interface OutputManager
{
    void displayMessage(String message);

    void displayPlayerTurn(Player player);

    void displayPlayerHand(Player player);

    void displayResults(List<Player> players);

    void displayStageBuilt(Player player, Stage stage, Wonder wonder);
}
