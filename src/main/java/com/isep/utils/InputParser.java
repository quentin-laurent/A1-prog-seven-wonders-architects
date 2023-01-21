package com.isep.utils;

import com.isep.game.cards.Card;
import com.isep.game.cards.Deck;
import com.isep.game.tokens.ProgressToken;
import com.isep.game.tokens.ProgressTokenStack;

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

    Card fetchCardFromDeck(Deck leftDeck, Deck rightDeck, Deck centralDeck);

    ProgressToken fetchProgressTokenFromStack(ProgressTokenStack stack);
}
