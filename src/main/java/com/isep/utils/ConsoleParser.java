package com.isep.utils;

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

}
