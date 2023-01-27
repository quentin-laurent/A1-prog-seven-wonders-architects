
package com.isep.utils.scenescontrollers;

import com.isep.game.Player;
import com.isep.game.wonders.*;
import com.isep.utils.GUIParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

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

import java.io.IOException;
import java.time.LocalDate;

import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;


public class GameMenu {

    @FXML
    private Button soundButton, playButton;
    @FXML
    private ImageView image1,image2,image3,image4,image5,image6,image7;

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
    @FXML
    private DatePicker date1,date2,date3,date4,date5,date6,date7;

    private ArrayList<ChoiceBox> menuArray = new ArrayList<>();
    private ArrayList<AnchorPane> boxArray = new ArrayList<>();
    private ArrayList<TextField> pseudoArray = new ArrayList<>();
    private ArrayList<DatePicker> dateArray = new ArrayList<>();
    private float coeff_button = 10;
    private int button_font_size = 13;
    private ObservableList<String> wondersList = FXCollections.observableArrayList("Alexandrie","Ephese","Babylone","Rhodes","Halicarnasse","Olympie","Gizeh","None");
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

        menu1.setItems(wondersList); menu1.setValue("Alexandrie");
        menu2.setItems(wondersList); menu2.setValue("Ephese");
        menu3.setItems(wondersList); menu3.setValue("Babylone");
        menu4.setItems(wondersList); menu4.setValue("Rhodes");
        menu5.setItems(wondersList); menu5.setValue("Halicarnasse");
        menu6.setItems(wondersList); menu6.setValue("Olympie");
        menu7.setItems(wondersList); menu7.setValue("Gizeh");

        menuArray.add(menu1);boxArray.add(box1);pseudoArray.add(text1);dateArray.add(date1);
        menuArray.add(menu2);boxArray.add(box2);pseudoArray.add(text2);dateArray.add(date2);
        menuArray.add(menu3);boxArray.add(box3);pseudoArray.add(text3);dateArray.add(date3);
        menuArray.add(menu4);boxArray.add(box4);pseudoArray.add(text4);dateArray.add(date4);
        menuArray.add(menu5);boxArray.add(box5);pseudoArray.add(text5);dateArray.add(date5);
        menuArray.add(menu6);boxArray.add(box6);pseudoArray.add(text6);dateArray.add(date6);
        menuArray.add(menu7);boxArray.add(box7);pseudoArray.add(text7);dateArray.add(date7);

        box1.setVisible(false);
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
                updateImage(image1,newValue);
            }
        });

        menu2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateImage(image2,newValue);
            }
        });

        menu3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateImage(image3,newValue);
            }
        });

        menu4.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateImage(image4,newValue);
            }
        });

        menu5.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateImage(image5,newValue);
            }
        });

        menu6.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateImage(image6,newValue);

            }
        });

        menu7.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the change in the selected item here
                updateImage(image7,newValue);
            }
        });

        if(!StageLoader.sound)
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

    private void updateImage(ImageView image, String wonder)
    {
        switch(wonder)
        {
            case "Alexandrie":
                image.setImage(new Image(GUIParser.class.getResource("/imgs/wonders/alexandrie.png").toExternalForm()));
                break;
            case "Ephese":
                image.setImage(new Image(GUIParser.class.getResource("/imgs/wonders/ephese.png").toExternalForm()));
                break;
            case "Babylone":
                image.setImage(new Image(GUIParser.class.getResource("/imgs/wonders/babylone.png").toExternalForm()));
                break;
            case "Rhodes":
                image.setImage(new Image(GUIParser.class.getResource("/imgs/wonders/rhodes.png").toExternalForm()));
                break;
            case "Halicarnasse":
                image.setImage(new Image(GUIParser.class.getResource("/imgs/wonders/halicarnas.png").toExternalForm()));
                break;
            case "Olympie":
                image.setImage(new Image(GUIParser.class.getResource("/imgs/wonders/olympie.png").toExternalForm()));
                break;
            case "Gizeh":
                image.setImage(new Image(GUIParser.class.getResource("/imgs/wonders/gizeh.png").toExternalForm()));
                break;
        }
    }

    private void onSliderMoved()
    {
        int value = (int) slider.getValue();
        box1.setVisible(false);
        box2.setVisible(false);
        box3.setVisible(false);
        box4.setVisible(false);
        box5.setVisible(false);
        box6.setVisible(false);
        box7.setVisible(false);

        switch(value)
        {
            case 2:
                box2.setVisible(true);
                box3.setVisible(true);
            break;

            case 3:
                box1.setVisible(true);
                box4.setVisible(true);
                box5.setVisible(true);
            break;

            case 4:
                box2.setVisible(true);
                box3.setVisible(true);
                box4.setVisible(true);
                box5.setVisible(true);
            break;

            case 5:
                box1.setVisible(true);
                box2.setVisible(true);
                box3.setVisible(true);
                box4.setVisible(true);
                box5.setVisible(true);
            break;

            case 6:
                box2.setVisible(true);
                box3.setVisible(true);
                box4.setVisible(true);
                box5.setVisible(true);
                box6.setVisible(true);
                box7.setVisible(true);
            break;

            case 7:
                box1.setVisible(true);
                box2.setVisible(true);
                box3.setVisible(true);
                box4.setVisible(true);
                box5.setVisible(true);
                box6.setVisible(true);
                box7.setVisible(true);
            break;
        }
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
    protected void onPlayButton() throws IOException
    {
        StageLoader.numberPlayer = (int) slider.getValue();
        for(int i=0;i<7;i++)
        {
            if (boxArray.get(i).isVisible())
            {
                // there is a player here

                String name = pseudoArray.get(i).getText();
                Wonder wonder = null;
                LocalDate birthday = dateArray.get(i).getValue();
                if(birthday == null)
                    return;
                switch ((String) menuArray.get(i).getValue())
                {
                    case "Alexandrie":
                        wonder = new Alexandria();
                        break;
                    case "Ephese":
                        wonder = new Ephesus();
                        break;
                    case "Babylone":
                        wonder = new Babylon();
                        break;
                    case "Rhodes":
                        wonder = new Rhodes();
                        break;
                    case "Halicarnasse":
                        wonder = new Halicarnassus();
                        break;
                    case "Olympie":
                        wonder = new Olympia();
                        break;
                    case "Gizeh":
                        wonder = new Giza();
                        break;
                }
                StageLoader.playerList.add(new Player(name,birthday,wonder));
            }
        }
        StageLoader.isActionFinished = true;
        Collections.sort(StageLoader.playerList);
        StageLoader.loadFXMLScene("/scenes/gameScene.fxml");
    }
}
