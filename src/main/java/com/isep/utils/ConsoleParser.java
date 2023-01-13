package com.isep.utils;

import com.isep.game.cards.Card;
import com.isep.game.cards.Deck;

import javax.sound.midi.Soundbank;
import java.net.DatagramSocket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
    public int fetchPlayerCount()
    {
        System.out.print("Amount of players: ");
        int playerCount = this.getInt("Amount of players: ");

        while(playerCount < 2 || playerCount > 7)
        {
            if(playerCount < 2)
                System.out.println("At least 2 players are required !");
            if(playerCount > 7)
                System.out.println("There cannot be more than 7 players !");
            System.out.print("Amount of players: ");
            playerCount = this.getInt("Amount of players: ");
        }

        return playerCount;
    }

    public String fetchPlayerName()
    {
        System.out.print("Player name (15 char max): ");
        String name = this.getString();

        while(name.length() > 15)
        {
            System.out.println("Name must not exceed 15 chars !");
            System.out.print("Player name: ");
            name = this.getString();
        }

        return name;
    }

    public LocalDate fetchPlayerBirthday()
    {
        System.out.print("Player birthday (dd/mm/yyyy): ");
        LocalDate birthday = this.getLocalDate("Player birthday (dd/mm/yyyy): ");

        return birthday;
    }

    public Card fetchCardFromDeck(Deck leftDeck, Deck rightDeck, Deck middleDeck)
    {
        ArrayList<Deck> deckList = new ArrayList<>();
        deckList.add(leftDeck);
        deckList.add(rightDeck);
        deckList.add(middleDeck);

        StringBuilder s = new StringBuilder("Decks available:\n");
        s.append(String.format("[0] Left Deck: %s%n", leftDeck.getTopCard()));
        s.append(String.format("[1] Right Deck: %s%n", rightDeck.getTopCard()));
        s.append(String.format("[2] Middle Deck: ?%n"));
        s.append("Choose a deck to pick a card from: ");

        System.out.print(s);
        int index = this.getInt("Choose a deck to pick a card from: ");

        while(index < 0 || index > 2)
        {
            System.out.println("You must select a valid deck (use the number between the brackets) !");
            System.out.print(s);
            index = this.getInt("Choose a deck to pick a card from: ");
        }

        return deckList.get(index).pickCard();
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
                value = this.scanner.nextInt();
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
                s = this.scanner.nextLine();
                validInput = true;
            }
            catch (InputMismatchException e)
            {
                System.out.print("/!\\ Invalid value (not a string).\nPlease provide a string: ");
                scanner.nextLine();
            }
        } while(!validInput);

        return s;
    }

    /**
     * Asks the user to provide a {@link LocalDate} though the standard input.
     * @return The {@link LocalDate} provided by the user.
     * @author Quentin LAURENT
     */
    private LocalDate getLocalDate(String messageOnError)
    {
        boolean validInput = false;
        String input;
        LocalDate date = null;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            try
            {
                input = this.scanner.nextLine();
                date = LocalDate.parse(input, dtf);
                validInput = true;
            }
            catch (DateTimeParseException e)
            {
                System.out.print(String.format("/!\\ Invalid date format !\n%s\n%s", e.getMessage(), messageOnError));
            }
        } while (!validInput);

        return date;
    }
}
