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
    private int victoryPoints;
    private boolean hasCat;

    // Constructor
    public Player(String name, LocalDate birthday, Wonder wonder)
    {
        this.name = name;
        this.birthday = birthday;
        this.wonder = wonder;
        this.deck = this.wonder.getDeck();
        this.hand = new Hand();
        this.victoryPoints = 0;
        this.hasCat = false;
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

    public Wonder getWonder()
    {
        return this.wonder;
    }

    public Deck getDeck()
    {
        return this.deck;
    }

    public Hand getHand()
    {
        return this.hand;
    }

    public boolean hasCat()
    {
        return this.hasCat;
    }

    public void setHasCat(boolean hasCat)
    {
        this.hasCat = hasCat;
    }

    // Methods
    public void addVictoryPoint(int victoryPoints)
    {
        this.victoryPoints += victoryPoints;
    }

    @Override
    public String toString()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("[PLAYER] %s, %s, %s", this.name, this.birthday.format(dtf), this.wonder.getName());
    }

    @Override
    public int compareTo(Player player)
    {
        // player1 < player2 if player 1 is younger than player2
        if(this.birthday.isAfter(player.getBirthday()))
            return -1;
        if(this.birthday.isEqual(player.getBirthday()))
            return 0;
        // player1 > player2 if player 1 is older than player2
        if(this.birthday.isBefore(player.getBirthday()))
            return 1;
        throw new RuntimeException(String.format("Failed to compare birthday dates between players [%s] and [%s].", this, player));
    }
}
