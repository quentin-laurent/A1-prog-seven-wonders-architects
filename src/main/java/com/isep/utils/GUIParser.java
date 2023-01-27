package com.isep.utils;

import com.isep.game.cards.Card;
import com.isep.game.cards.Deck;
import com.isep.game.tokens.ProgressToken;
import com.isep.game.tokens.ProgressTokenStack;
import com.isep.game.wonders.Wonder;
import javafx.application.Application;
import javafx.stage.Stage;

import com.isep.utils.StageLoader;
import javafx.application.Platform;
import javafx.event.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import java.io.IOException;

public class GUIParser extends Application implements InputParser{
    @Override
    public void start(Stage stage) throws IOException
    {
        // -_-_-_-_-_-_-_-_-_- load the fxml scene -_-_-_-_-_-_-_-_-_-
        StageLoader.setStage(stage);
        StageLoader.loadFXMLScene("/scenes/choiceMenu.fxml");
    }

    public int fetchPlayerCount()
    {
        boolean end = false;
        while(!end)
        {
            StageLoader.sleep(500);
            end = StageLoader.isActionFinished;
        }
        StageLoader.isActionFinished = false;
        return StageLoader.numberPlayer;
    }

    public String fetchPlayerName()    {
        return StageLoader.playerList.get(StageLoader.playerCount).getName();
    }

    public LocalDate fetchPlayerBirthday(){
        return StageLoader.playerList.get(StageLoader.playerCount).getBirthday();
    }

    public Wonder fetchPlayerWonder(List<Wonder> wonders){
        Wonder wonder = StageLoader.playerList.get(StageLoader.playerCount).getWonder();
        StageLoader.playerCount++;
        return wonder;
    }

    public Card fetchCardFromDeck(Deck leftDeck, Deck rightDeck, Deck centralDeck){
        System.out.println("Waiting for card to be picked...");
        if(!leftDeck.isEmpty())
            StageLoader.leftCard = leftDeck.getTopCard();
        else
            StageLoader.isLeftEmpty = true;
        if(!rightDeck.isEmpty())
            StageLoader.rightCard = rightDeck.getTopCard();
        else
            StageLoader.isRightEmpty = true;
        boolean end = false;
        while(!end)
        {
            StageLoader.sleep(500);
            end = StageLoader.isActionFinished;
        }
        StageLoader.isActionFinished = false;
        Card choosedCard = null;
        switch(StageLoader.choosedCard)
        {
            case 0:
                choosedCard = leftDeck.pickCard();
                break;
            case 1:
                choosedCard = centralDeck.pickCard();
                break;
            case 2:
                choosedCard = rightDeck.pickCard();
                break;
        }
        return choosedCard;
    }

    public ProgressToken fetchProgressTokenFromStack(ProgressTokenStack stack){
        List<ProgressToken> revealedTokens = stack.getRevealedTokens();
        List<ProgressToken> hiddensToken = stack.getTokens();
        for(int i=0;i<3;i++)
        {
            switch(revealedTokens.get(i).getEffect())
            {
                case URBANISM:
                    StageLoader.tokens[i] = "urbanism";
                    break;
                case CRAFTS:
                    StageLoader.tokens[i] = "artsAndCrafts";
                    break;
                case JEWELLERY:
                    StageLoader.tokens[i] = "jewelry";
                    break;
                case SCIENCE:
                    StageLoader.tokens[i] = "science";
                    break;
                case PROPAGANDA:
                    StageLoader.tokens[i] = "propaganda";
                    break;
                case ARCHITECTURE:
                    StageLoader.tokens[i] = "architecture";
                    break;
                case ECONOMY:
                    StageLoader.tokens[i] = "economy";
                    break;
                case ENGINEERING:
                    StageLoader.tokens[i] = "engineering";
                    break;
                case TACTICS:
                    StageLoader.tokens[i] = "tactic";
                    break;
                case DECOR:
                    StageLoader.tokens[i] = "decoration";
                    break;
                case POLITICS:
                    StageLoader.tokens[i] = "politic";
                    break;
                case STRATEGY:
                    StageLoader.tokens[i] = "strategy";
                    break;
                case EDUCATION:
                    StageLoader.tokens[i] = "education";
                    break;
                case CULTURE:
                    StageLoader.tokens[i] = "culture";
                    break;
            }
        }
        boolean end = false;
        while(!end)
        {
            StageLoader.sleep(500);
            end = StageLoader.isActionFinished;
        }
        StageLoader.isActionFinished = false;
        StageLoader.askToken = false;
        return stack.pickProgressToken(StageLoader.choosedToken-1);
    }

    public void launchInterface()
    {
        launch();
    }
}
