package com.isep.game;

import com.isep.game.cards.Deck;
import com.isep.game.cards.Hand;
import com.isep.game.wonders.Wonder;

import java.time.LocalTime;

/**
 * A class representing a player.
 */
public class Player
{
    // Attributes
    private String name;
    private LocalTime birthday;
    private Wonder wonder;
    private Deck deck;
    private Hand hand;

    // Constructor
    public Player(String name, LocalTime birthday, Wonder wonder)
    {
        this.name = name;
        this.birthday = birthday;
        this.wonder = wonder;
        this.deck = this.wonder.getDeck();
        this.hand = new Hand();
    }

}
