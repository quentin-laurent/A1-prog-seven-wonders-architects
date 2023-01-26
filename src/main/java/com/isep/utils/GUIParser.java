package com.isep.utils;

import javafx.application.Application;
import javafx.stage.Stage;

import com.isep.utils.StageLoader;

import java.io.IOException;

public class GUIParser extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        // -_-_-_-_-_-_-_-_-_- load the fxml scene -_-_-_-_-_-_-_-_-_-
        StageLoader.setStage(stage);
        StageLoader.loadFXMLScene("/scenes/gameScene.fxml");
    }

    public void launchInterface()
    {
        launch();
    }
}
