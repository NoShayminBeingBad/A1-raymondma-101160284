package org.example.Card;

import org.example.Game;
import org.example.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class QuestCardTest {

    @Test
    @DisplayName("Quest is declined be all players")
    public void RESP_8_TEST_1(){
        Scanner input = new Scanner("n\nn\nn\nn\n");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));
        game.setUpGame();

        game.getEventDeck().getCards().set(0, new QuestCard(2));
        game.drawEventCard();
        game.playEventCard();

        Assertions.assertTrue(output.toString().contains("Quest Card has been drawn!"));
        Assertions.assertTrue(output.toString().contains("This Quest has 2 stages"));
        Assertions.assertTrue(output.toString().contains("No sponsor, quest has been canceled"));
    }

    @Test
    @DisplayName("Player sets up a quest for 2 stages")
    public void RESP_9_TEST_1(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "0\n6\nquit\n2\n7\nQuit\n\n";
        StringWriter output = new StringWriter();

        Player player = new Player(0);

        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(70));
        player.addToHand(new FoeCard(10));
        player.addToHand(new WeaponCard("Dagger", 5));
        player.addToHand(new WeaponCard("Excalibur", 30));
        player.addToHand(new FoeCard(10));
        player.addToHand(new FoeCard(20));
        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(15));
        player.addToHand(new WeaponCard("Sword", 10));
        player.addToHand(new WeaponCard("Horse", 10));
        player.addToHand(new WeaponCard("Sword", 10));

        QuestCard qc = new QuestCard(2);
        qc.setSponsor(player);

        qc.setStages(new Scanner(input), new PrintWriter(output));

        Assertions.assertEquals("Stage 1: F5 D5 ", qc.getStage(0));
        Assertions.assertEquals("Stage 2: F10 H10 ", qc.getStage(1));
    }

    @Test
    @DisplayName("Player sets up a quest for 4 stages")
    public void RESP_9_TEST_2(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "0\n6\nquit\n2\n\n7\nQuit\n2\n4\n4\nquit\n3\nquit\n\n";
        StringWriter output = new StringWriter();

        Player player = new Player(0);

        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(70));
        player.addToHand(new FoeCard(10));
        player.addToHand(new WeaponCard("Dagger", 5));
        player.addToHand(new WeaponCard("Excalibur", 30));
        player.addToHand(new FoeCard(10));
        player.addToHand(new FoeCard(20));
        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(15));
        player.addToHand(new WeaponCard("Sword", 10));
        player.addToHand(new WeaponCard("Horse", 10));
        player.addToHand(new WeaponCard("Sword", 10));

        QuestCard qc = new QuestCard(4);
        qc.setSponsor(player);

        qc.setStages(new Scanner(input), new PrintWriter(output));

        Assertions.assertEquals("Stage 1: F5 D5 ", qc.getStage(0));
        Assertions.assertEquals("Stage 2: F10 H10 ", qc.getStage(1));
        Assertions.assertEquals("Stage 3: F15 S10 S10 ", qc.getStage(2));
        Assertions.assertEquals("Stage 4: F70 ", qc.getStage(3));
    }

    @Test
    @DisplayName("Player sets up a quest for 2 stages, checks for errors")
    public void RESP_9_TEST_3(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "quit\n7\n5\n6\nquit\n2\n0\ntest\nquit\n8\nQuit\n\n";
        StringWriter output = new StringWriter();

        Player player = new Player(0);

        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(70));
        player.addToHand(new FoeCard(10));
        player.addToHand(new WeaponCard("Dagger", 5));
        player.addToHand(new WeaponCard("Excalibur", 30));
        player.addToHand(new FoeCard(10));
        player.addToHand(new FoeCard(20));
        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(15));
        player.addToHand(new WeaponCard("Sword", 10));
        player.addToHand(new WeaponCard("Horse", 10));
        player.addToHand(new WeaponCard("Sword", 10));

        QuestCard qc = new QuestCard(2);
        qc.setSponsor(player);

        qc.setStages(new Scanner(input), new PrintWriter(output));

        Assertions.assertEquals("Stage 1: F20 D5 ", qc.getStage(0));
        Assertions.assertEquals("Stage 2: F10 E30 ", qc.getStage(1));

        Assertions.assertTrue(output.toString().contains("A stage cannot be empty"));
        Assertions.assertTrue(output.toString().contains("Insufficient value for this stage"));
        Assertions.assertTrue(output.toString().contains("Card selected is not an Foe Card"));
        Assertions.assertTrue(output.toString().contains("Card selected is not an Weapon Card"));
        Assertions.assertTrue(output.toString().contains("That is not a valid input. Please enter an index or 'Quit'"));
    }

}