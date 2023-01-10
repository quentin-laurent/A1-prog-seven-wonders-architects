package com.isep.utils;

import java.time.LocalDate;

/**
 * An interface used to parse the player's inputs.
 * @author Quentin LAURENT
 */
public interface InputParser
{
    int fetchPlayerCount();

    String fetchPlayerName();

    LocalDate fetchPlayerBirthday();
}
