package org.example;

import org.example.Card.FoeCard;
import org.example.Card.PlagueCard;
import org.example.Card.QuestCard;
import org.example.Card.WeaponCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    String input = "\n" +
            "n\ny\n" + //p1 rejects sponsor, p2 accepts sponsor
            "0\n6\nquit\n1\n4\nquit\n1\n2\n3\nquit\n1\n2\nquit\n\n" + //p2 sets up quest
            "\ny\ny\ny\n\n" + //everyone quests
            "0\n0\n0\n" + //trim
            "4\n4\nquit\n" + // p1 stage 1
            "4\n3\nquit\n" + // p3 stage 1
            "3\n5\nquit\n" + // p4 stage 1
            "\n\n" +
            "y\ny\ny\n\n\n" + // stage 2 everyone quests
            "6\n5\nquit\n" + // p1 stage 2
            "8\n3\nquit\n" + // p3 stage 2
            "5\n5\nquit\n" + // p4 stage 2
            "\n\n" +
            "y\ny\n\n\n" + // stage 3 every quests
            "8\n5\n3\nquit\n" + // p3 stage 3
            "6\n4\n5\nquit\n" +
            "\n\n" +
            "y\ny\n\n\n" + // stage 4 every quests
            "6\n5\n5\nquit\n" + // p3 stage 4
            "3\n3\n3\n4\nquit\n" + // p4 stage 4
            "\n\n" +
            "0\n0\n0\n0\n"; // p2 trims hand

    StringWriter output = new StringWriter();

    public Game game;
    public Player p1;
    public Player p2;
    public Player p3;
    public Player p4;

    @BeforeEach
    public void init(){
        game = new Game(4, new Scanner(input), new PrintWriter(output));
        game.setUpDecks();
        game.getAdventureDeck().shuffle();
        game.getEventDeck().shuffle();

        p1 = game.getPlayer(0);
        p2 = game.getPlayer(1);
        p3 = game.getPlayer(2);
        p4 = game.getPlayer(3);

        p1.addToHand(new FoeCard(5));
        p1.addToHand(new FoeCard(5));
        p1.addToHand(new FoeCard(15));
        p1.addToHand(new FoeCard(15));
        p1.addToHand(new WeaponCard("Dagger", 5));
        p1.addToHand(new WeaponCard("Sword", 10));
        p1.addToHand(new WeaponCard("Sword", 10));
        p1.addToHand(new WeaponCard("Horse", 10));
        p1.addToHand(new WeaponCard("Horse", 10));
        p1.addToHand(new WeaponCard("Battle-Axe", 15));
        p1.addToHand(new WeaponCard("Battle-Axe", 15));
        p1.addToHand(new WeaponCard("Lance", 20));

        p2.addToHand(new FoeCard(5));
        p2.addToHand(new FoeCard(5));
        p2.addToHand(new FoeCard(15));
        p2.addToHand(new FoeCard(15));
        p2.addToHand(new FoeCard(40));
        p2.addToHand(new WeaponCard("Dagger", 5));
        p2.addToHand(new WeaponCard("Sword", 10));
        p2.addToHand(new WeaponCard("Horse", 10));
        p2.addToHand(new WeaponCard("Horse", 10));
        p2.addToHand(new WeaponCard("Battle-Axe", 15));
        p2.addToHand(new WeaponCard("Battle-Axe", 15));
        p2.addToHand(new WeaponCard("Excalibur", 30));

        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(15));
        p3.addToHand(new WeaponCard("Dagger", 5));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Battle-Axe", 15));
        p3.addToHand(new WeaponCard("Lance", 20));

        p4.addToHand(new FoeCard(5));
        p4.addToHand(new FoeCard(15));
        p4.addToHand(new FoeCard(15));
        p4.addToHand(new FoeCard(40));
        p4.addToHand(new WeaponCard("Dagger", 5));
        p4.addToHand(new WeaponCard("Dagger", 5));
        p4.addToHand(new WeaponCard("Sword", 10));
        p4.addToHand(new WeaponCard("Horse", 10));
        p4.addToHand(new WeaponCard("Horse", 10));
        p4.addToHand(new WeaponCard("Battle-Axe", 15));
        p4.addToHand(new WeaponCard("Lance", 20));
        p4.addToHand(new WeaponCard("Excalibur", 30));

        game.getEventDeck().getCards().set(0, new QuestCard(4));

        //stage 1
        game.getAdventureDeck().getCards().set(0, new FoeCard(30));
        game.getAdventureDeck().getCards().set(1, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(2, new WeaponCard("Battle-Axe", 15));
        //stage 2
        game.getAdventureDeck().getCards().set(3, new FoeCard(10));
        game.getAdventureDeck().getCards().set(4, new WeaponCard("Lance", 20));
        game.getAdventureDeck().getCards().set(5, new WeaponCard("Lance", 20));
        //stage 3
        game.getAdventureDeck().getCards().set(6, new WeaponCard("Battle-Axe", 15));
        game.getAdventureDeck().getCards().set(7, new WeaponCard("Sword", 10));
        //stage 4
        game.getAdventureDeck().getCards().set(8, new FoeCard(30));
        game.getAdventureDeck().getCards().set(9, new WeaponCard("Lance", 20));

    }

    @Test
    @DisplayName("A-TEST JP-Scenario")
    public void A_TEST(){
        // game loop
        game.setTurnCount(-1);
        game.flushTurn();
        game.drawEventCard();
        game.playEventCard();

        QuestCard qc = (QuestCard) game.getEventCard();

        Assertions.assertEquals("Stage 1 (15 BP): F5 H10 ", qc.getStage(0));
        Assertions.assertEquals("Stage 2 (25 BP): F15 S10 ", qc.getStage(1));
        Assertions.assertEquals("Stage 3 (35 BP): F15 D5 B15 ", qc.getStage(2));
        Assertions.assertEquals("Stage 4 (55 BP): F40 B15 ", qc.getStage(3));

        Assertions.assertEquals("Player 1's Hand: F5 F10 F15 F15 F30 H10 B15 B15 L20 ", game.getPlayer(0).toString());
        Assertions.assertEquals(0, game.getPlayer(0).getShieldNum());

        Assertions.assertEquals("Player 3's Hand: F5 F5 F15 F30 S10 ", game.getPlayer(2).toString());
        Assertions.assertEquals(0, game.getPlayer(2).getShieldNum());

        Assertions.assertEquals("Player 4's Hand: F15 F15 F40 L20 ", game.getPlayer(3).toString());
        Assertions.assertEquals(4, game.getPlayer(3).getShieldNum());

        Assertions.assertEquals(12, game.getPlayer(1).getHand().size());
        Assertions.assertEquals(0, game.getPlayer(1).getShieldNum());

    }

}