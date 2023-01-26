package com.isep.game.tokens;

import com.isep.game.cards.Card;
import com.isep.game.cards.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class representing a stack of {@link ProgressToken}s.
 * @author Quentin LAURENT
 */
public class ProgressTokenStack
{
    // Attributes
    private List<ProgressToken> tokens;
    private List<ProgressToken> revealedTokens;

    // Constructor
    public ProgressTokenStack()
    {
        this.tokens = new ArrayList<>();
        this.revealedTokens = new ArrayList<>();
    }

    // Getters & Setters
    public List<ProgressToken> getTokens()
    {
        return this.tokens;
    }

    public List<ProgressToken> getRevealedTokens()
    {
        return this.revealedTokens;
    }

    // Methods
    /**
     * Adds a single {@link ProgressToken} to this {@link ProgressTokenStack}.
     * @param progressToken The {@link ProgressToken} to add.
     * @author Quentin LAURENT
     */
    public void addProgressToken(ProgressToken progressToken)
    {
        if(this.revealedTokens.size() < 3)
            this.revealedTokens.add(progressToken);
        else
            this.tokens.add(progressToken);
    }

    /**
     * Adds multiple (identical) {@link ProgressToken}s to this {@link ProgressTokenStack}.
     * @param progressToken The {@link ProgressToken} to add.
     * @param quantity The amount of the specified {@link ProgressToken} to add.
     * @author Quentin LAURENT
     */
    public void addProgressToken(ProgressToken progressToken, int quantity)
    {
        if(quantity < 1)
            throw new RuntimeException("Quantity of tokens to add must be greater than 0 !");

        for(int i = 0; i < quantity; i++)
            this.tokens.add(progressToken);
    }

    /**
     * Picks a {@link ProgressToken} from the three revealed {@link ProgressToken} or the {@link ProgressToken} at the top of this {@link ProgressTokenStack}.
     * @param index The index representing the {@link ProgressToken} to pick.
     *              <p>An index from 1 to 3 represents a revealed {@link ProgressToken}.
     *              <p>An index equal to 4 represents the {@link ProgressToken} at the top of this {@link ProgressTokenStack}.
     * @return The picked {@link ProgressToken}..
     * @author Quentin LAURENT
     */
    public ProgressToken pickProgressToken(int index)
    {
        if(index < 0 || index > 3)
            throw new RuntimeException("Index out of range [0-3] !");

        if(index == 3)
            return this.tokens.remove(0);
        else
        {
            ProgressToken pickedToken = this.revealedTokens.remove(index);
            this.revealedTokens.add(this.tokens.remove(0));
            return pickedToken;
        }
    }

    /**
     * Shuffles this {@link ProgressTokenStack}.
     * @author Quentin LAURENT
     */
    public void shuffle()
    {
        Collections.shuffle(this.tokens);
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder(String.format("%d tokens:\n",this.revealedTokens.size() + this.tokens.size()));
        for(ProgressToken token: this.revealedTokens)
        {
            s.append(token.toString());
            s.append("\n");
        }

        // Removing the last \n char
        if(this.tokens.size() == 0)
        {
            int length = s.length();
            s.replace(length - 1, length, "");
            return s.toString();
        }

        for(ProgressToken token: this.tokens)
        {
            s.append(token.toString());
            s.append("\n");
        }

        // Removing the last \n char
        int length = s.length();
        s.replace(length - 1, length, "");

        return s.toString();
    }
}
