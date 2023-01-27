package com.isep.game;

import com.isep.game.cards.Deck;
import com.isep.game.cards.Hand;
import com.isep.game.tokens.ProgressToken;
import com.isep.game.tokens.ProgressToken.Effect;
import com.isep.game.wonders.Wonder;
import com.isep.utils.StageLoader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    private List<ProgressToken> progressTokens;
    private int militaryVictoryTokens;
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
        this.progressTokens = new ArrayList<ProgressToken>();
        this.militaryVictoryTokens = 0;
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

    public List<ProgressToken> getProgressTokens()
    {
        return this.progressTokens;
    }

    public int getMilitaryVictoryTokens()
    {
        return this.militaryVictoryTokens;
    }

    public int getVictoryPoints()
    {
        return this.victoryPoints;
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
    /**
     * Increments this {@link Player}'s amount of military victory tokens.
     * @param militaryVictoryTokens The amount of military victory tokens points to add.
     * @author Quentin LAURENT
     */
    public void addMilitaryVictoryTokens(int militaryVictoryTokens)
    {
        if(militaryVictoryTokens < 1)
            throw new RuntimeException("The amount of military victory tokens to add needs to be greater than 0 !");
        this.militaryVictoryTokens += militaryVictoryTokens;
    }

    /**
     * Increments this {@link Player}'s amount of victory points.
     * @param victoryPoints The amount of victory points to add.
     * @author Quentin LAURENT
     */
    public void addVictoryPoints(int victoryPoints)
    {
        if(victoryPoints < 1)
            throw new RuntimeException("The amount of victory points to add needs to be greater than 0 !");
        this.victoryPoints += victoryPoints;
    }

    /**
     * Adds a single {@link ProgressToken} to this {@link Player}'s list of progress tokens.
     * @param token The {@link ProgressToken} to add.
     * @author Quentin LAURENT
     */
    public void addProgressToken(ProgressToken token)
    {
        this.progressTokens.add(token);
    }

    /**
     * Returns true if this {@link Player} has a {@link ProgressToken} with the provided {@link Effect}.
     * @param effect The {@link Effect} to check for.
     * @return True if this {@link Player} has the provided {@link Effect}.
     */
    public boolean hasProgressTokenEffect(ProgressToken.Effect effect)
    {
        for(ProgressToken token: this.progressTokens)
        {
            if(token.getEffect() == effect)
                return true;
        }

        return false;
    }

    /**
     * Returns true if this {@link Player} has the exact quantity of {@link ProgressToken} with the provided {@link Effect}.
     * @param effect The {@link Effect} to check for.
     * @param exactQuantity The exact quantity of the provided {@link Effect}.
     * @return True if this {@link Player} has the exact quantity of the {@link Effect}.
     */
    public boolean hasProgressTokenEffect(ProgressToken.Effect effect, int exactQuantity)
    {
        int quantity = 0;

        for(ProgressToken token: this.progressTokens)
        {
            if(token.getEffect() == effect)
                quantity++;
        }

        return (quantity == exactQuantity);
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
