
package com.isep.utils.scenescontrollers;

import com.isep.utils.GUIParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.isep.utils.StageLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Objects;

public class GameMenu {

    @FXML
    private Button soundButton;

    private Clip clip = AudioSystem.getClip();

    @FXML
    private Label labelSlider, labelTitle;
    @FXML
    private Slider slider;
    @FXML
    private TextField text1;
    @FXML
    private AnchorPane box1;
    @FXML
    private ChoiceBox menu1;
    private float coeff_button = 10;
    private int button_font_size = 26;
    public GameMenu() throws LineUnavailableException {
    }
    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {

        soundButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/MesloLGS-NF.ttf"), 34));
        labelSlider.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 13));
        labelTitle.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 39));

        text1.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 12));

        ObservableList<String> list = FXCollections.observableArrayList("Alexandrie","Ephese","Babylone","Rhodes","Halicarnasse","Olympie","Gizeh");

        menu1.setItems(list);


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
        StageLoader.loadFXMLScene("test");
    }
    @FXML
    protected void onLeaveButton()
    {
        System.exit(0);
    }

}
