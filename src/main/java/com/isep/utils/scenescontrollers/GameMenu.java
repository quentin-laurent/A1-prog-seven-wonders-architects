
package com.isep.utils.scenescontrollers;

import com.isep.utils.GUIParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.isep.utils.StageLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Objects;

public class GameMenu {

    @FXML
    private Button soundButton, playButton;

    private Clip clip = AudioSystem.getClip();

    @FXML
    private Label labelSlider, labelTitle;
    @FXML
    private Slider slider;
    @FXML
    private TextField text1,text2,text3,text4,text5,text6,text7;
    @FXML
    private AnchorPane box1,box2,box3,box4,box5,box6,box7;
    @FXML
    private ChoiceBox menu1,menu2,menu3,menu4,menu5,menu6,menu7;
    private float coeff_button = 10;
    private int button_font_size = 13;
    private ObservableList<String> list = FXCollections.observableArrayList("Alexandrie","Ephese","Babylone","Rhodes","Halicarnasse","Olympie","Gizeh","None");
    public GameMenu() throws LineUnavailableException {
    }
    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        playButton.setOnMouseEntered(event -> expendButton(playButton));
        playButton.setOnMouseExited(event -> reduceButton(playButton));
        soundButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/MesloLGS-NF.ttf"), 34));
        playButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 13));
        labelSlider.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 13));
        labelTitle.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 39));

        text1.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 12));
        text2.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 12));
        text3.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 12));
        text4.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 12));
        text5.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 12));
        text6.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 12));
        text7.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/Helenium-bold.ttf"), 12));

        menu1.setItems(list);
        menu2.setItems(list);
        menu3.setItems(list);
        menu4.setItems(list);
        menu5.setItems(list);
        menu6.setItems(list);
        menu7.setItems(list);

        box3.setVisible(false);
        box4.setVisible(false);
        box5.setVisible(false);
        box6.setVisible(false);
        box7.setVisible(false);

        slider.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                onSliderMoved();
            }
        });
        slider.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                onSliderMoved();
            }
        });
        menu1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateBox(oldValue,newValue);
            }
        });

        menu2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateBox(oldValue,newValue);
            }
        });

        menu3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateBox(oldValue,newValue);
            }
        });

        menu4.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateBox(oldValue,newValue);
            }
        });

        menu5.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateBox(oldValue,newValue);
            }
        });

        menu6.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateBox(oldValue,newValue);
            }
        });

        menu7.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateBox(oldValue,newValue);
            }
        });

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

    private void updateBox(String oldValue, String newValue)
    {
        list.remove(newValue);
        if(oldValue != null)
            list.add(oldValue);
    }

    private void onSliderMoved()
    {
        int value = (int) slider.getValue();
        box3.setVisible(false);
        box4.setVisible(false);
        box5.setVisible(false);
        box6.setVisible(false);
        box7.setVisible(false);

        if(value>2)
            box3.setVisible(true);
        if(value>3)
            box4.setVisible(true);
        if(value>4)
            box5.setVisible(true);
        if(value>5)
            box6.setVisible(true);
        if(value>6)
            box7.setVisible(true);
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
}
