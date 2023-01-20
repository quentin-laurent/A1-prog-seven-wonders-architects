package com.isep.utils.scenescontrollers;

import com.isep.utils.GUIParser;
import javafx.fxml.FXML;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.isep.utils.StageLoader;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class WelcomeMenu {

    @FXML
    private Button soundButton, beginButton, leaveButton;
    @FXML
    private MediaView mediaView;

    private Clip clip = AudioSystem.getClip();

    String button_style = "-fx-background-color: #506E3A;";
    private float coeff_button = 10;
    private int button_font_size = 30;
    public WelcomeMenu() throws LineUnavailableException {
    }
    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        String file = "../imgs/bg-main.mp4";
        Media media =  new Media(GUIParser.class.getResource("/imgs/bg-main2.mp4").toExternalForm());//ew Media(new File(file).toURI().toString());
        MediaPlayer mediaplayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaplayer);
        mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaplayer.play();

        beginButton.setOnMouseEntered(event -> expendButton(beginButton));
        beginButton.setOnMouseExited(event -> reduceButton(beginButton));

        leaveButton.setOnMouseEntered(event -> expendButton(leaveButton));
        leaveButton.setOnMouseExited(event -> reduceButton(leaveButton));

        soundButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/MesloLGS-NF.ttf"), 51));
        leaveButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), button_font_size));
        beginButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), button_font_size));

        this.clip.open(AudioSystem.getAudioInputStream(Objects.requireNonNull(GUIParser.class.getResourceAsStream("/musics/welcome.wav"))));
        if(StageLoader.sound)
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            soundButton.setText("\uF026");
    }

    private void expendButton(Button button)
    {
        button.setLayoutX(button.getLayoutX()-coeff_button/2);
        button.setLayoutY(button.getLayoutY()-coeff_button/2);

        button.setPrefWidth(button.getPrefWidth()+coeff_button);
        button.setPrefHeight(button.getPrefHeight()+coeff_button);
        button.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), button_font_size+coeff_button/2.0));
    }

    private void reduceButton(Button button)
    {

        button.setLayoutX(button.getLayoutX()+coeff_button/2);
        button.setLayoutY(button.getLayoutY()+coeff_button/2);

        button.setPrefWidth(button.getPrefWidth()-coeff_button);
        button.setPrefHeight(button.getPrefHeight()-coeff_button);
        button.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), button_font_size));
    }

    @FXML
    protected void onSoundButton()
    {
        if(StageLoader.sound)
        {
            StageLoader.sound = false;
            soundButton.setText("\uF026");
            this.clip.stop();
        }
        else
        {
            StageLoader.sound = true;
            soundButton.setText("\uF028");
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    @FXML
    protected void onBeginButton() throws IOException
    {
        StageLoader.loadFXMLScene("/scenes/choiceMenu.fxml");
    }
    @FXML
    protected void onLeaveButton()
    {
        System.exit(0);
    }

}
