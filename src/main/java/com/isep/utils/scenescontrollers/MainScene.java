
package com.isep.utils.scenescontrollers;

import com.isep.utils.GUIParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.animation.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.isep.utils.StageLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MainScene {

    @FXML
    private Button soundButton;
    @FXML
    private ImageView alexandria1,alexandria2,alexandria3,alexandria4,alexandria5;
    @FXML
    private ImageView ephese1,ephese2,ephese3,ephese4,ephese5;
    @FXML
    private ImageView babylon1,babylon2,babylon3,babylon4,babylon5;
    @FXML
    private ImageView gizeh1,gizeh2,gizeh3,gizeh4,gizeh5;
    @FXML
    private ImageView halicarnas1,halicarnas2,halicarnas3,halicarnas4,halicarnas5;
    @FXML
    private ImageView rhodes1,rhodes2,rhodes3,rhodes4,rhodes5;
    @FXML
    private ImageView olympie1,olympie2,olympie3,olympie4,olympie5;
    @FXML
    private AnchorPane boxTable;
    private float coeff_button = 10;
    private int button_font_size = 13;
    public MainScene() throws LineUnavailableException {
    }
    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        soundButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/MesloLGS-NF.ttf"), 34));
        if(!StageLoader.sound)
            soundButton.setText("\uF026");
    }

    boolean switcher = true;

    @FXML
    protected void onButton()
    {
        String end = "";
        switcher = !switcher;
        if(switcher)
            end = ".png";
        else
            end = "f.png";

        alexandria1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_alexandria_1" + end).toExternalForm()));
        alexandria2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_alexandria_2" + end).toExternalForm()));
        alexandria3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_alexandria_3" + end).toExternalForm()));
        alexandria4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_alexandria_4" + end).toExternalForm()));
        alexandria5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_alexandria_5" + end).toExternalForm()));

        ephese1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/ephese/wonder_ephese_1" + end).toExternalForm()));
        ephese2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/ephese/wonder_ephese_2" + end).toExternalForm()));
        ephese3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/ephese/wonder_ephese_3" + end).toExternalForm()));
        ephese4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/ephese/wonder_ephese_4" + end).toExternalForm()));
        ephese5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/ephese/wonder_ephese_5" + end).toExternalForm()));

        babylon1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/babylon/wonder_babylon_1" + end).toExternalForm()));
        babylon2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/babylon/wonder_babylon_2" + end).toExternalForm()));
        babylon3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/babylon/wonder_babylon_3" + end).toExternalForm()));
        babylon4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/babylon/wonder_babylon_4" + end).toExternalForm()));
        babylon5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/babylon/wonder_babylon_5" + end).toExternalForm()));

        gizeh1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/gizeh/wonder_gizeh_1" + end).toExternalForm()));
        gizeh2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/gizeh/wonder_gizeh_2" + end).toExternalForm()));
        gizeh3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/gizeh/wonder_gizeh_3" + end).toExternalForm()));
        gizeh4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/gizeh/wonder_gizeh_4" + end).toExternalForm()));
        gizeh5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/gizeh/wonder_gizeh_5" + end).toExternalForm()));

        halicarnas1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/halicarnas/wonder_halicarnas_1" + end).toExternalForm()));
        halicarnas2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/halicarnas/wonder_halicarnas_2" + end).toExternalForm()));
        halicarnas3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/halicarnas/wonder_halicarnas_3" + end).toExternalForm()));
        halicarnas4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/halicarnas/wonder_halicarnas_4" + end).toExternalForm()));
        halicarnas5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/halicarnas/wonder_halicarnas_5" + end).toExternalForm()));

        rhodes1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/rhodes/wonder_rhodes_1" + end).toExternalForm()));
        rhodes2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/rhodes/wonder_rhodes_2" + end).toExternalForm()));
        rhodes3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/rhodes/wonder_rhodes_3" + end).toExternalForm()));
        rhodes4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/rhodes/wonder_rhodes_4" + end).toExternalForm()));
        rhodes5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/rhodes/wonder_rhodes_5" + end).toExternalForm()));

        olympie1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/olympie/wonder_olympie_1" + end).toExternalForm()));
        olympie2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/olympie/wonder_olympie_2" + end).toExternalForm()));
        olympie3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/olympie/wonder_olympie_3" + end).toExternalForm()));
        olympie4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/olympie/wonder_olympie_4" + end).toExternalForm()));
        olympie5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/olympie/wonder_olympie_5" + end).toExternalForm()));
    }

    int rotate = 0,i;

    @FXML
    protected void onButton2()
    {

        RotateTransition rt = new RotateTransition(Duration.millis(1000), boxTable);
        i++;
        if(i==7)
        {
            i = 0;
            rotate = 54;
        }
        else
            rotate = 51;
        System.out.println("-_-_-_-_-");
        System.out.println(rotate);
        System.out.println(i);
        rt.setByAngle(rotate);
        rt.play();
        System.out.println(boxTable.getRotate());
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
            StageLoader.clip.stop();
        }
        else
        {
            StageLoader.sound = true;
            soundButton.setText("\uF028");
            StageLoader.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
