
package com.isep.utils.scenescontrollers;

import com.isep.game.Player;
import com.isep.game.cards.*;
import com.isep.game.wonders.Stage;
import com.isep.utils.GUIParser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.animation.*;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.isep.utils.StageLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

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
    private Thread thread;
    private int rotationValue;
    boolean gameStart = true;

    private ArrayList<AnchorPane> playersBox = new ArrayList<>();
    private ArrayList<Label> labelList = new ArrayList<>();

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

        for(int i=0;i<StageLoader.numberPlayer;i++)
        {
            switch(StageLoader.playerList.get(i).getWonder().getName())
            {
                case "Alexandria":
                    playersBox.add(boxAlexandria);
                    break;
                case "Ephesus":
                    playersBox.add(boxEphese);
                    break;
                case "Babylon":
                    playersBox.add(boxBabylon);
                    break;
                case "Rhodes":
                    playersBox.add(boxRhodes);
                    break;
                case "Olympia":
                    playersBox.add(boxOlympie);
                    break;
                case "Giza":
                    playersBox.add(boxGizeh);
                    break;
                case "Halicarnassus":
                    playersBox.add(boxHalicarnas);
                    break;
            }
        }
        setupWonders();
        setupEvents();
        setupArrays();

        soundButton.setFont(Font.loadFont(GUIParser.class.getResourceAsStream("/fonts/MesloLGS-NF.ttf"), 34));
        if(!StageLoader.sound)
            soundButton.setText("\uF026");

        update();
    }

    boolean switcher = true;

    public void update()
    {
        this.thread = new Thread(() -> update());
        StageLoader.sleep(1000);
        Platform.runLater(() -> {
            if(gameStart)
                gameStart = false;
            else
                rotateTable();

            updateInventory();

            String leftCard = getCardType(StageLoader.leftCard);
            String rightCard = getCardType(StageLoader.rightCard);

            deckGauche.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/cards/"+leftCard).toExternalForm()));
            deckDroite.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/cards/"+rightCard).toExternalForm()));

            this.thread.interrupt();
        });
    }

    public void updateWonder()
    {
        setupWonderBuild();
        for(int i=0;i<StageLoader.numberPlayer;i++)
        {
            String wonder = StageLoader.playerList.get(i).getWonder().getName();
            int c = 0;
            switch (wonder)
            {
                case "Alexandria":
                    c = StageLoader.alexandria;
                    if(c>=1)
                        alexandria1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_alexandria_1f.png").toExternalForm()));
                    if(c>=2)
                        alexandria2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_alexandria_2f.png").toExternalForm()));
                    if(c>=3)
                        alexandria3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_alexandria_3f.png").toExternalForm()));
                    if(c>=4)
                        alexandria4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_alexandria_4f.png").toExternalForm()));
                    if(c>=5)
                        alexandria5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_alexandria_5f.png").toExternalForm()));
                    break;

                case "Ephesus":
                    c = StageLoader.ephese;
                    if(c>=1)
                        ephese1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_ephese_1f.png").toExternalForm()));
                    if(c>=2)
                        ephese2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_ephese_2f.png").toExternalForm()));
                    if(c>=3)
                        ephese3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_ephese_3f.png").toExternalForm()));
                    if(c>=4)
                        ephese4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_ephese_4f.png").toExternalForm()));
                    if(c>=5)
                        ephese5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_ephese_5f.png").toExternalForm()));
                    break;
                case "Babylon":
                    c = StageLoader.babylon;
                    if(c>=1)
                        babylon1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_babylon_1f.png").toExternalForm()));
                    if(c>=2)
                        babylon2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_babylon_2f.png").toExternalForm()));
                    if(c>=3)
                        babylon3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_babylon_3f.png").toExternalForm()));
                    if(c>=4)
                        babylon4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_babylon_4f.png").toExternalForm()));
                    if(c>=5)
                        babylon5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_babylon_5f.png").toExternalForm()));
                    break;
                case "Rhodes":
                    c = StageLoader.rhodes;
                    if(c>=1)
                        alexandria1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_rhodes_1f.png").toExternalForm()));
                    if(c>=2)
                        alexandria2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_rhodes_2f.png").toExternalForm()));
                    if(c>=3)
                        alexandria3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_rhodes_3f.png").toExternalForm()));
                    if(c>=4)
                        alexandria4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_rhodes_4f.png").toExternalForm()));
                    if(c>=5)
                        alexandria5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_rhodes_5f.png").toExternalForm()));
                    break;
                case "Olympia":
                    c = StageLoader.olympie;
                    if(c>=1)
                        olympie1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_olympie_1f.png").toExternalForm()));
                    if(c>=2)
                        olympie2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_olympie_2f.png").toExternalForm()));
                    if(c>=3)
                        olympie3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_olympie_3f.png").toExternalForm()));
                    if(c>=4)
                        olympie4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_olympie_4f.png").toExternalForm()));
                    if(c>=5)
                        olympie5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_olympie_5f.png").toExternalForm()));
                    break;
                case "Giza":
                    c = StageLoader.gizeh;
                    if(c>=1)
                        gizeh1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_gizeh_1f.png").toExternalForm()));
                    if(c>=2)
                        gizeh2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_gizeh_2f.png").toExternalForm()));
                    if(c>=3)
                        gizeh3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_gizeh_3f.png").toExternalForm()));
                    if(c>=4)
                        gizeh4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_gizeh_4f.png").toExternalForm()));
                    if(c>=5)
                        gizeh5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_gizeh_5f.png").toExternalForm()));
                    break;
                case "Halicarnassus":
                    c = StageLoader.halicarnas;
                    if(c>=1)
                        halicarnas1.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_halicarnas_1f.png").toExternalForm()));
                    if(c>=2)
                        halicarnas2.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_halicarnas_2f.png").toExternalForm()));
                    if(c>=3)
                        halicarnas3.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_halicarnas_3f.png").toExternalForm()));
                    if(c>=4)
                        halicarnas4.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_halicarnas_4f.png").toExternalForm()));
                    if(c>=5)
                        halicarnas5.setImage(new Image(GUIParser.class.getResource("/imgs/game-elements/alexandria/wonder_halicarnas_5f.png").toExternalForm()));
                    break;
            }
        }
    }

    public void updateInventory()
    {
        Hand hand = StageLoader.hand;
        for(int i=0;i<labelList.size();i++)
        {
            labelList.get(i).setText("0");
        }
        for(var entry: hand.getCards().entrySet())
        {
            Card card = entry.getKey();
            Label label = null;
            switch(card.getColor())
            {
                case GREY:
                    switch(((GreyCard) card).getMaterial())
                    {
                        case WOOD:
                            label = labelWood;
                            break;
                        case STONE:
                            label = labelStone;
                            break;
                        case BRICK:
                            label = labelClay;
                            break;
                        case PAPYRUS:
                            label = labelScroll;
                            break;
                        case GLASS:
                            label = labelWater;
                            break;
                    }
                    break;

                case GREEN:
                    switch(((GreenCard) card).getScienceSymbol())
                    {
                        case COMPASS:
                            label = labelCompass;
                            break;
                        case GEAR:
                            label = labelWheel;
                            break;
                        case TABLET:
                            label = labelTablet;
                            break;
                    }
                    break;

                case BLUE:
                    switch(((BlueCard) card).getVictoryPoints())
                    {
                        case 2:
                            label = labelBlue1;
                            break;
                        case 3:
                            label = labelBlue2;
                            break;
                    }
                    break;

                case RED:
                    switch(((RedCard) card).getHorns())
                    {
                        case 0:
                            label = labelWar1;
                            break;
                        case 1:
                            label = labelWar2;
                            break;
                        case 2:
                            label = labelWar3;
                            break;
                    }
                    break;

                case YELLOW:
                    label = labelGold;
                    break;

            }
            label.setText(Integer.toString(entry.getValue()));
        }
    }

    private String getCardType(Card card)
    {
        switch(card.getColor())
        {
            case GREY:
                switch(((GreyCard) card).getMaterial())
                {
                    case WOOD:
                        return "card_grey2.png";
                    case STONE:
                        return "card_grey1.png";
                    case BRICK:
                        return "card_grey3.png";
                    case PAPYRUS:
                        return "card_grey4.png";
                    case GLASS:
                        return "card_grey5.png";
                }
                break;

            case GREEN:
                switch(((GreenCard) card).getScienceSymbol())
                {
                    case COMPASS:
                        return "card_green1.png";
                    case GEAR:
                        return "card_green2.png";
                    case TABLET:
                        return "card_green3.png";
                }
                break;

            case BLUE:
                switch(((BlueCard) card).getVictoryPoints())
                {
                    case 2:
                        return "card_blue1.png";
                    case 3:
                        return "card_blue2.png";
                }
                break;

            case RED:
                switch(((RedCard) card).getHorns())
                {
                    case 0:
                        return "card_red1.png";
                    case 1:
                        return "card_red2.png";
                    case 2:
                        return "card_red3.png";
                }
                break;

            case YELLOW:
                return "card_yellow.png";

        }
        return null;
    }

    private void setupArrays()
    {
        labelList.add(labelWood);
        labelList.add(labelStone);
        labelList.add(labelClay);
        labelList.add(labelWater);
        labelList.add(labelScroll);
        labelList.add(labelCompass);
        labelList.add(labelWheel);
        labelList.add(labelTablet);
        labelList.add(labelWar1);
        labelList.add(labelWar2);
        labelList.add(labelWar3);
        labelList.add(labelBlue1);
        labelList.add(labelBlue2);
        labelList.add(labelGold);
    }
    private void setupEvents()
    {
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
    }

    private void setupWonders()
    {
        switch(StageLoader.playerList.size())
        {
            case 2:
                rotationValue = 180;
                playersBox.get(0).setLayoutX(256);
                playersBox.get(0).setLayoutY(494);
                playersBox.get(0).setRotate(0);
                playersBox.get(0).setVisible(true);
                playersBox.get(1).setLayoutX(276);
                playersBox.get(1).setLayoutY(0);
                playersBox.get(1).setRotate(180);
                playersBox.get(1).setVisible(true);
                break;
            case 3:
                rotationValue = 120;
                playersBox.get(0).setLayoutX(256);
                playersBox.get(0).setLayoutY(494);
                playersBox.get(0).setRotate(0);
                playersBox.get(0).setVisible(true);
                playersBox.get(1).setLayoutX(447);
                playersBox.get(1).setLayoutY(130);
                playersBox.get(1).setRotate(-120);
                playersBox.get(1).setVisible(true);
                playersBox.get(2).setLayoutX(46);
                playersBox.get(2).setLayoutY(135);
                playersBox.get(2).setRotate(-240);
                playersBox.get(2).setVisible(true);
                break;
            case 4:
                rotationValue = 90;
                playersBox.get(0).setLayoutX(256);
                playersBox.get(0).setLayoutY(494);
                playersBox.get(0).setRotate(0);
                playersBox.get(0).setVisible(true);
                playersBox.get(1).setLayoutX(501);
                playersBox.get(1).setLayoutY(248);
                playersBox.get(1).setRotate(-90);
                playersBox.get(1).setVisible(true);
                playersBox.get(2).setLayoutX(276);
                playersBox.get(2).setLayoutY(28);
                playersBox.get(2).setRotate(-180);
                playersBox.get(2).setVisible(true);
                playersBox.get(3).setLayoutX(37);
                playersBox.get(3).setLayoutY(252);
                playersBox.get(3).setRotate(-270);
                playersBox.get(3).setVisible(true);
                break;
            case 5:
                rotationValue = 72;
                playersBox.get(0).setLayoutX(256);
                playersBox.get(0).setLayoutY(494);
                playersBox.get(0).setRotate(0);
                playersBox.get(0).setVisible(true);
                playersBox.get(1).setLayoutX(502);
                playersBox.get(1).setLayoutY(323);
                playersBox.get(1).setRotate(-72);
                playersBox.get(1).setVisible(true);
                playersBox.get(2).setLayoutX(397);
                playersBox.get(2).setLayoutY(69);
                playersBox.get(2).setRotate(-144);
                playersBox.get(2).setVisible(true);
                playersBox.get(3).setLayoutX(111);
                playersBox.get(3).setLayoutY(55);
                playersBox.get(3).setRotate(-216);
                playersBox.get(3).setVisible(true);
                playersBox.get(4).setLayoutX(35);
                playersBox.get(4).setLayoutY(322);
                playersBox.get(4).setRotate(-288);
                playersBox.get(4).setVisible(true);
                break;
            case 6:
                rotationValue = 60;
                playersBox.get(0).setLayoutX(256);
                playersBox.get(0).setLayoutY(494);
                playersBox.get(0).setRotate(0);
                playersBox.get(0).setVisible(true);
                playersBox.get(1).setLayoutX(476);
                playersBox.get(1).setLayoutY(375);
                playersBox.get(1).setRotate(-60);
                playersBox.get(1).setVisible(true);
                playersBox.get(2).setLayoutX(447);
                playersBox.get(2).setLayoutY(130);
                playersBox.get(2).setRotate(-120);
                playersBox.get(2).setVisible(true);
                playersBox.get(3).setLayoutX(245);
                playersBox.get(3).setLayoutY(10);
                playersBox.get(3).setRotate(-180);
                playersBox.get(3).setVisible(true);
                playersBox.get(4).setLayoutX(46);
                playersBox.get(4).setLayoutY(135);
                playersBox.get(4).setRotate(-240);
                playersBox.get(4).setVisible(true);
                playersBox.get(5).setLayoutX(18);
                playersBox.get(5).setLayoutY(384);
                playersBox.get(5).setRotate(-300);
                playersBox.get(5).setVisible(true);
                break;
            case 7:
                rotationValue = 51;
                playersBox.get(0).setLayoutX(256);
                playersBox.get(0).setLayoutY(494);
                playersBox.get(0).setRotate(0);
                playersBox.get(0).setVisible(true);
                playersBox.get(1).setLayoutX(465);
                playersBox.get(1).setLayoutY(411);
                playersBox.get(1).setRotate(-51);
                playersBox.get(1).setVisible(true);
                playersBox.get(2).setLayoutX(500);
                playersBox.get(2).setLayoutY(177);
                playersBox.get(2).setRotate(-102);
                playersBox.get(2).setVisible(true);
                playersBox.get(3).setLayoutX(331);
                playersBox.get(3).setLayoutY(35);
                playersBox.get(3).setRotate(-153);
                playersBox.get(3).setVisible(true);
                playersBox.get(4).setLayoutX(136);
                playersBox.get(4).setLayoutY(41);
                playersBox.get(4).setRotate(-204);
                playersBox.get(4).setVisible(true);
                playersBox.get(5).setLayoutX(33);
                playersBox.get(5).setLayoutY(194);
                playersBox.get(5).setRotate(-255);
                playersBox.get(5).setVisible(true);
                playersBox.get(6).setLayoutX(81);
                playersBox.get(6).setLayoutY(418);
                playersBox.get(6).setRotate(-306);
                playersBox.get(6).setVisible(true);
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

    private void setupWonderBuild()
    {
        String end = ".png";
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

    int rotationCounter;

    private void rotateTable()
    {
        rotationCounter++;
        RotateTransition rt = new RotateTransition(Duration.millis(1000), boxTable);
        if(StageLoader.numberPlayer!= 7  || rotationCounter<StageLoader.numberPlayer)
            rt.setByAngle(rotationValue);
        else
        {
            rt.setByAngle(54.0);
            rotationCounter = 0;
        }

        if((int)boxTable.getRotate() == 360)
        {
            boxTable.setRotate(0.0);
        }
        rt.play();
    }

    @FXML
    protected void onButton2()
    {
        rotationCounter++;
        RotateTransition rt = new RotateTransition(Duration.millis(1000), boxTable);
        if(StageLoader.numberPlayer!= 7  || rotationCounter<StageLoader.numberPlayer)
            rt.setByAngle(rotationValue);
        else
        {
            rt.setByAngle(54.0);
            rotationCounter= 0;
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

    @FXML
    protected void onLeftDeck()
    {
        StageLoader.choosedCard = 0;
        StageLoader.isActionFinished = true;
        update();
    }
    @FXML
    protected void onCentralDeck()
    {
        StageLoader.choosedCard = 1;
        StageLoader.isActionFinished = true;
        update();
    }
    @FXML
    protected void onRightDeck()
    {
        StageLoader.choosedCard = 2;
        StageLoader.isActionFinished = true;
        update();
    }
}
