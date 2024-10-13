package org.example;

import org.example.Card.FoeCard;
import org.example.Card.QuestCard;
import org.example.Card.WeaponCard;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(4);
        game.setUpGame();
        //game.getPlayer(0).giveShield(6);
        game.gameplay();
    }
}