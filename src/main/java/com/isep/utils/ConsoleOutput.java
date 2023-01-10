package com.isep.utils;

/**
 * An implementation of the {@link OutputManager} interface that uses the standard output (a.k.a console or terminal)
 * to display information about the actions happening in the Game.
 * @author Quentin LAURENT
 */
public class ConsoleOutput implements OutputManager
{
    public void displayMessage(String message)
    {
        System.out.println(message);
    }
}
