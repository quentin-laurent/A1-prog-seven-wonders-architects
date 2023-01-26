
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

    // -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- WONDERS IMAGES -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
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

    // -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- WONDERS TABLES -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    @FXML
    private AnchorPane boxTable,boxAlexandria,boxOlympie,boxBabylon,boxHalicarnas,boxGizeh,boxRhodes,boxEphese;

    // -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- CARDS LABELS AND IMAGES -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-

    @FXML
    private ImageView deckGauche,deckDroite,deckCentral;

    @FXML
    private Label labelStone,labelWood,labelClay,labelScroll,labelWater,labelGold,labelWar1,labelWar2,labelWar3,labelBlue1,labelBlue2,labelCompass,labelWheel,labelTablet;

    @FXML
    private ImageView imageStone,imageWood,imageClay,imageScroll,imageWater,imageGold,imageWar1,imageWar2,imageWar3,imageBlue1,imageBlue2,imageCompass,imageWheel,imageTablet;

    // -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- GLOBAL VARIABLES -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
    private float coeff_button = 10;
    private int button_font_size = 13;

    private int rotationValue;

    private ArrayList<AnchorPane> players = new ArrayList<>();

    public MainScene() throws LineUnavailableException {
    }
    public void initialize() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        boxAlexandria.setVisible(false);
        boxEphese.setVisible(false);
        boxGizeh.setVisible(false);
        boxOlympie.setVisible(false);
        boxRhodes.setVisible(false);
        boxHalicarnas.setVisible(false);
        boxBabylon.setVisible(false);

        if(StageLoader.alexandria)
            players.add(boxAlexandria);
        if(StageLoader.ephese)
            players.add(boxEphese);
        if(StageLoader.gizeh)
            players.add(boxGizeh);
        if(StageLoader.olympie)
            players.add(boxOlympie);
        if(StageLoader.rhodes)
            players.add(boxRhodes);
        if(StageLoader.halicarnas)
            players.add(boxHalicarnas);
        if(StageLoader.babylon)
            players.add(boxBabylon);


        setupWonders();

        // -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- EVENT FOR MOUSE HOVER -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
        deckCentral.setOnMouseEntered(event -> expendImage(deckCentral));
        deckCentral.setOnMouseExited(event -> reduceImage(deckCentral));
        deckGauche.setOnMouseEntered(event -> expendImage(deckGauche));
        deckGauche.setOnMouseExited(event -> reduceImage(deckGauche));
        deckDroite.setOnMouseEntered(event -> expendImage(deckDroite));
        deckDroite.setOnMouseExited(event -> reduceImage(deckDroite));
        imageStone.setOnMouseEntered(event -> expendImage(imageStone));
        imageStone.setOnMouseExited(event -> reduceImage(imageStone));
        imageWood.setOnMouseEntered(event -> expendImage(imageWood));
        imageWood.setOnMouseExited(event -> reduceImage(imageWood));
        imageClay.setOnMouseEntered(event -> expendImage(imageClay));
        imageClay.setOnMouseExited(event -> reduceImage(imageClay));
        imageScroll.setOnMouseEntered(event -> expendImage(imageScroll));
        imageScroll.setOnMouseExited(event -> reduceImage(imageScroll));
        imageWater.setOnMouseEntered(event -> expendImage(imageWater));
        imageWater.setOnMouseExited(event -> reduceImage(imageWater));
        imageGold.setOnMouseEntered(event -> expendImage(imageGold));
        imageGold.setOnMouseExited(event -> reduceImage(imageGold));
        imageWar1.setOnMouseEntered(event -> expendImage(imageWar1));
        imageWar1.setOnMouseExited(event -> reduceImage(imageWar1));
        imageWar2.setOnMouseEntered(event -> expendImage(imageWar2));
        imageWar2.setOnMouseExited(event -> reduceImage(imageWar2));
        imageWar3.setOnMouseEntered(event -> expendImage(imageWar3));
        imageWar3.setOnMouseExited(event -> reduceImage(imageWar3));
        imageBlue1.setOnMouseEntered(event -> expendImage(imageBlue1));
        imageBlue1.setOnMouseExited(event -> reduceImage(imageBlue1));
        imageBlue2.setOnMouseEntered(event -> expendImage(imageBlue2));
        imageBlue2.setOnMouseExited(event -> reduceImage(imageBlue2));
        imageCompass.setOnMouseEntered(event -> expendImage(imageCompass));
        imageCompass.setOnMouseExited(event -> reduceImage(imageCompass));
        imageWheel.setOnMouseEntered(event -> expendImage(imageWheel));
        imageWheel.setOnMouseExited(event -> reduceImage(imageWheel));
        imageTablet.setOnMouseEntered(event -> expendImage(imageTablet));
        imageTablet.setOnMouseExited(event -> reduceImage(imageTablet));

        soundButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/MesloLGS-NF.ttf"), 34));
        if(!StageLoader.sound)
            soundButton.setText("\uF026");
    }

    boolean switcher = true;

    private void setupWonders()
    {
        switch(StageLoader.numberPlayer)
        {
            case 2:
                rotationValue = 180;
                players.get(0).setLayoutX(256);
                players.get(0).setLayoutY(494);
                players.get(0).setRotate(0);
                players.get(0).setVisible(true);
                players.get(1).setLayoutX(276);
                players.get(1).setLayoutY(0);
                players.get(1).setRotate(180);
                players.get(1).setVisible(true);
                break;
            case 3:
                rotationValue = 120;
                players.get(0).setLayoutX(256);
                players.get(0).setLayoutY(494);
                players.get(0).setRotate(0);
                players.get(0).setVisible(true);
                players.get(1).setLayoutX(447);
                players.get(1).setLayoutY(130);
                players.get(1).setRotate(-120);
                players.get(1).setVisible(true);
                players.get(2).setLayoutX(46);
                players.get(2).setLayoutY(135);
                players.get(2).setRotate(-240);
                players.get(2).setVisible(true);
                break;
            case 4:
                rotationValue = 90;
                players.get(0).setLayoutX(256);
                players.get(0).setLayoutY(494);
                players.get(0).setRotate(0);
                players.get(0).setVisible(true);
                players.get(1).setLayoutX(501);
                players.get(1).setLayoutY(248);
                players.get(1).setRotate(-90);
                players.get(1).setVisible(true);
                players.get(2).setLayoutX(276);
                players.get(2).setLayoutY(28);
                players.get(2).setRotate(-180);
                players.get(2).setVisible(true);
                players.get(3).setLayoutX(37);
                players.get(3).setLayoutY(252);
                players.get(3).setRotate(-270);
                players.get(3).setVisible(true);
                break;
            case 5:
                rotationValue = 72;
                players.get(0).setLayoutX(256);
                players.get(0).setLayoutY(494);
                players.get(0).setRotate(0);
                players.get(0).setVisible(true);
                players.get(1).setLayoutX(502);
                players.get(1).setLayoutY(323);
                players.get(1).setRotate(-72);
                players.get(1).setVisible(true);
                players.get(2).setLayoutX(397);
                players.get(2).setLayoutY(69);
                players.get(2).setRotate(-144);
                players.get(2).setVisible(true);
                players.get(3).setLayoutX(111);
                players.get(3).setLayoutY(55);
                players.get(3).setRotate(-216);
                players.get(3).setVisible(true);
                players.get(4).setLayoutX(35);
                players.get(4).setLayoutY(322);
                players.get(4).setRotate(-288);
                players.get(4).setVisible(true);
                break;
            case 6:
                rotationValue = 60;
                players.get(0).setLayoutX(256);
                players.get(0).setLayoutY(494);
                players.get(0).setRotate(0);
                players.get(0).setVisible(true);
                players.get(1).setLayoutX(476);
                players.get(1).setLayoutY(375);
                players.get(1).setRotate(-60);
                players.get(1).setVisible(true);
                players.get(2).setLayoutX(447);
                players.get(2).setLayoutY(130);
                players.get(2).setRotate(-120);
                players.get(2).setVisible(true);
                players.get(3).setLayoutX(245);
                players.get(3).setLayoutY(10);
                players.get(3).setRotate(-180);
                players.get(3).setVisible(true);
                players.get(4).setLayoutX(46);
                players.get(4).setLayoutY(135);
                players.get(4).setRotate(-240);
                players.get(4).setVisible(true);
                players.get(5).setLayoutX(18);
                players.get(5).setLayoutY(384);
                players.get(5).setRotate(-300);
                players.get(5).setVisible(true);
                break;
            case 7:
                rotationValue = 51;
                players.get(0).setLayoutX(256);
                players.get(0).setLayoutY(494);
                players.get(0).setRotate(0);
                players.get(0).setVisible(true);
                players.get(1).setLayoutX(465);
                players.get(1).setLayoutY(411);
                players.get(1).setRotate(-51);
                players.get(1).setVisible(true);
                players.get(2).setLayoutX(500);
                players.get(2).setLayoutY(177);
                players.get(2).setRotate(-102);
                players.get(2).setVisible(true);
                players.get(3).setLayoutX(331);
                players.get(3).setLayoutY(35);
                players.get(3).setRotate(-153);
                players.get(3).setVisible(true);
                players.get(4).setLayoutX(136);
                players.get(4).setLayoutY(41);
                players.get(4).setRotate(-204);
                players.get(4).setVisible(true);
                players.get(5).setLayoutX(33);
                players.get(5).setLayoutY(194);
                players.get(5).setRotate(-255);
                players.get(5).setVisible(true);
                players.get(6).setLayoutX(81);
                players.get(6).setLayoutY(418);
                players.get(6).setRotate(-306);
                players.get(6).setVisible(true);
                break;
        }
    }

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

    int i;

    @FXML
    protected void onButton2()
    {
        i++;
        System.out.println(boxTable.getRotate());
        RotateTransition rt = new RotateTransition(Duration.millis(1000), boxTable);
        if(StageLoader.numberPlayer!= 7  || i<StageLoader.numberPlayer)
            rt.setByAngle(rotationValue);
        else
        {
            rt.setByAngle(54.0);
            i= 0;
        }

        if((int)boxTable.getRotate() == 360)
        {
            boxTable.setRotate(0.0);
            System.out.println(boxTable.getRotate());
        }
        rt.play();
    }

    @FXML
    protected void onButton3() throws IOException {
        StageLoader.loadFXMLScene("/scenes/choiceMenu.fxml");
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

    private void expendImage(ImageView image)
    {
        image.setLayoutX(image.getLayoutX()-coeff_button/2);
        image.setLayoutY(image.getLayoutY()-coeff_button/2);

        image.setFitWidth(image.getFitWidth()+coeff_button);
        image.setFitHeight(image.getFitHeight()+coeff_button);
    }
    private void reduceImage(ImageView image)
    {
        image.setLayoutX(image.getLayoutX()+coeff_button/2);
        image.setLayoutY(image.getLayoutY()+coeff_button/2);

        image.setFitWidth(image.getFitWidth()-coeff_button);
        image.setFitHeight(image.getFitHeight()-coeff_button);
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
