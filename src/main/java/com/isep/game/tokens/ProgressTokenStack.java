package com.isep.game.tokens;

import com.isep.game.cards.Card;
import com.isep.game.cards.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a stack of {@link ProgressToken}s.
 * @author Quentin LAURENT
 */
public class ProgressTokenStack
{
    // Attributes
    private List<ProgressToken> tokens;

    // Constructor
    public ProgressTokenStack()
    {
        this.tokens = new ArrayList<>();
    }

    // Methods
    /**
     * Adds a single {@link ProgressToken} to this {@link ProgressTokenStack}.
     * @param progressToken The {@link ProgressToken} to add.
     * @author Quentin LAURENT
     */
    public void addProgressToken(ProgressToken progressToken)
    {
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

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder(String.format("%d tokens:\n", this.tokens.size()));
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
