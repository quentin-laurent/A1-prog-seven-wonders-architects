package com.isep.utils;

import com.isep.game.Main;
//import com.isep.game.Player;
import com.isep.game.Player;
import com.isep.game.cards.Card;
import com.isep.game.cards.Hand;
import com.isep.game.tokens.ProgressToken;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class StageLoader
{
    private static Stage primaryStage;
    private static FXMLLoader loader;

    public static void setStage(Stage stage)
    {
        primaryStage = stage;
    }
    public static void loadFXMLScene(String path) throws IOException
    {
        // Create a new FXMLLoader object
        loader = new FXMLLoader(Main.class.getResource(path));
        // Load the FXML file
        Parent root = loader.load();
        // Set the root node of the scene to the newly loaded FXML file
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("7 wonders Architects");
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // End the JavaFX thread when the window is closed
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static boolean sound = false;
    public static boolean isActionFinished = false;
    public static int playerCount= 0;
    public static Card centralCard, leftCard, rightCard;
    public static boolean rotate = false, askToken = false, isRightEmpty = false, isLeftEmpty = false;
    public static String[] tokens = new String[3];
    public static int choosedCard,choosedToken;


    public static Hand hand;
    public static ArrayList<Player> playerList = new ArrayList<>();

    public static int alexandria=0,babylon=0,ephese=0,gizeh=0,halicarnas=0,rhodes=0,olympie=0;

    public static int numberPlayer = 0;
    public static Clip clip;

    public static void sleep(int n)
    {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            // Handle the exception
        }
    }


    public static void updateWonder(String name)
    {
        switch(name)
        {
            case "Alexandria":
                alexandria++;
                break;
            case "Ephesus":
                ephese++;
                break;
            case "Babylon":
                babylon++;
                break;
            case "Rhodes":
                rhodes++;
                break;
            case "Olympia":
                olympie++;
                break;
            case "Giza":
                gizeh++;
                break;
            case "Halicarnassus":
                halicarnas++;
                break;
        }
    }
}