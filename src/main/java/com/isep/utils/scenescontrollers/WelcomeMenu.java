package com.isep.utils.scenescontrollers;

import com.isep.utils.GUIParser;
import javafx.fxml.FXML;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.isep.utils.StageLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Objects;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WelcomeMenu {

    @FXML
    private Button sound, beginButton, leaveButton;

    private Clip clip = AudioSystem.getClip();

    public WelcomeMenu() throws LineUnavailableException {
    }
    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        sound.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/MesloLGS-NF.ttf"), 51));
        leaveButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 26));
        beginButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 26));

        this.clip.open(AudioSystem.getAudioInputStream(Objects.requireNonNull(GUIParser.class.getResourceAsStream("/musics/welcome.wav"))));
        if(StageLoader.sound)
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            sound.setText("\uF026");
    }
    @FXML
    protected void onSoundButton(){
        if(StageLoader.sound)
        {
            StageLoader.sound = false;
            sound.setText("\uF026");
            this.clip.stop();
        }
        else
        {
            StageLoader.sound = true;
            sound.setText("\uF028");
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
