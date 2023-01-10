package com.isep.game;

import com.isep.game.cards.Deck;
import com.isep.game.cards.Hand;
import com.isep.game.wonders.Wonder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing a player.
 */
public class Player
{
    // Attributes
    private String name;
    private LocalDate birthday;
    private Wonder wonder;
    private Deck deck;
    private Hand hand;

    // Constructor
    public Player(String name, LocalDate birthday, Wonder wonder)
    {
        this.name = name;
        this.birthday = birthday;
        this.wonder = wonder;
        this.deck = this.wonder.getDeck();
        this.hand = new Hand();
    }

    // Methods
    @Override
    public String toString()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("[PLAYER] %s, %s", this.name, this.birthday.format(dtf));
    }
}
