package com.isep.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An implementation of the {@link InputParser} interface that uses the standard input (a.k.a console or terminal)
 * to parse the inputs of the player.
 */
public class ConsoleParser implements InputParser
{
    // Attributes
    private Scanner scanner;

    // Constructor
    public ConsoleParser()
    {
        this.scanner = new Scanner(System.in);
    }

    // Methods
    public int askPlayerCount()
    {
        System.out.print("Amount of players: ");
        int playerCount = this.getInt("Amount of players: ");

        while(playerCount < 2)
        {
            System.out.println("At least 2 players are required !");
            System.out.print("Amount of players: ");
            playerCount = this.getInt("Amount of players: ");
        }

        return playerCount;
    }

    /**
     * Asks the user to provide an integer though the standard input.
     * This checks for {@link InputMismatchException}.
     * @return The integer provided by the user.
     */
    private int getInt(String messageWhenMismatch)
    {
        boolean validInput = false;
        int value = 0;

        // Looping until an integer is provided
        do {
            try
            {
                value = this.sc.nextInt();
                // Consuming the \n char so it doesn't break the next call for nextLine()
                this.scanner.nextLine();
                validInput = true;
            }
            catch (InputMismatchException e)
            {
                //System.out.print("/!\\ Invalid value (not a number).\nPlease provide a number: ");
                System.out.printf("/!\\ Invalid value (not a number)%n%s: ", messageWhenMismatch);
                scanner.nextLine();
            }
        } while(!validInput);

        return value;
    }

    /**
     * Asks the user to provide a {@link String} though the standard input.
     * This checks for {@link InputMismatchException}.
     * @return The String provided by the user.
     */
    private String getString()
    {
        boolean validInput = false;
        String s = null;

        // Looping until a string is provided
        do {
            try
            {
                s = this.sc.nextLine();
                validInput = true;
            }
            catch (InputMismatchException e)
            {
                System.out.println("/!\\ Invalid value (not a string).\nPlease provide a string:");
                scanner.nextLine();
            }
        } while(!validInput);

        return s;
    }
}
