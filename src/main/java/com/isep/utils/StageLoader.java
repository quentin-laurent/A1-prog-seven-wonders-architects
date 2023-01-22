package com.isep.utils;

import com.isep.game.Main;
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
}