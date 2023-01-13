package com.isep.game;

import com.isep.game.cards.Deck;
import com.isep.game.cards.Hand;
import com.isep.game.wonders.Wonder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing a player.
 */
public class Player implements Comparable<Player>
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

    // Getters & Setters
    public String getName()
    {
        return this.name;
    }

    public LocalDate getBirthday()
    {
        return this.birthday;
    }

    public Deck getDeck()
    {
        return this.deck;
    }

    public Hand getHand()
    {
        return this.hand;
    }

    // Methods
    @Override
    public String toString()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("[PLAYER] %s, %s, %s", this.name, this.birthday.format(dtf), this.wonder.getName());
    }

    @Override
    public int compareTo(Player player)
    {
        if(this.birthday.isBefore(player.getBirthday()))
            return -1;
        if(this.birthday.isEqual(player.getBirthday()))
            return 0;
        if(this.birthday.isAfter(player.getBirthday()))
            return 1;
        throw new RuntimeException(String.format("Failed to compare birthday dates between players [%s] and [%s].", this, player));
    }
}
