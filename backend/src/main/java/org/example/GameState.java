package org.example;

import lombok.Getter;
import lombok.Setter;

public class GameState {

    public String getPlayer1Hand() {
        return player1Hand;
    }

    public String getPlayer2Hand() {
        return player2Hand;
    }

    public String getPlayer3Hand() {
        return player3Hand;
    }

    public String getPlayer4Hand() {
        return player4Hand;
    }

    public int getPlayer1Shield() {
        return player1Shield;
    }

    public int getPlayer2Shield() {
        return player2Shield;
    }

    public int getPlayer3Shield() {
        return player3Shield;
    }

    public int getPlayer4Shield() {
        return player4Shield;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public String getText() {
        return text;
    }

    String player1Hand;
    String player2Hand;
    String player3Hand;
    String player4Hand;
    int player1Shield;
    int player2Shield;
    int player3Shield;
    int player4Shield;

    boolean gameFinished;

    String text;

    public GameState(Game game, String message){
        this.player1Hand = game.getPlayer(0).toString();
        this.player2Hand = game.getPlayer(1).toString();
        this.player3Hand = game.getPlayer(2).toString();
        this.player4Hand = game.getPlayer(3).toString();

        this.player1Shield = game.getPlayer(0).getShieldNum();
        this.player2Shield = game.getPlayer(1).getShieldNum();
        this.player3Shield = game.getPlayer(2).getShieldNum();
        this.player4Shield = game.getPlayer(3).getShieldNum();

        this.gameFinished = game.getWinner();

        this.text = message;
    }

}
