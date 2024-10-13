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
        Assertions.assertEquals("Stage 3: F15 S10 ", qc.getStage(2));
        Assertions.assertEquals("Stage 4: F70 ", qc.getStage(3));
    }

    @Test
    @DisplayName("Player sets up a quest for 2 stages, checks for errors")
    public void RESP_9_TEST_3(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "quit\n7\n5\n6\nquit\n2\n0\ntest\nquit\n5\n5\n7\nQuit\n\n";
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
        Assertions.assertEquals("Stage 2: F10 S10 E30 ", qc.getStage(1));

        Assertions.assertTrue(output.toString().contains("A stage cannot be empty"));
        Assertions.assertTrue(output.toString().contains("Insufficient value for this stage"));
        Assertions.assertTrue(output.toString().contains("That Weapon is already used"));
        Assertions.assertTrue(output.toString().contains("Card selected is not an Foe Card"));
        Assertions.assertTrue(output.toString().contains("Card selected is not an Weapon Card"));
        Assertions.assertTrue(output.toString().contains("That is not a valid input. Please enter an index or 'Quit'"));
    }

    @Test
    @DisplayName("Player 3 gets ready for quest")
    public void RESP_10_TEST_1(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "8\n7\n8\nquit\n";
        StringWriter output = new StringWriter();

        Player player = new Player(2);

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

        qc.setAttack(new Scanner(input), new PrintWriter(output), player);

        Assertions.assertEquals("Attack from Player 3: D5 S10 H10 ", qc.getAttack(player.getNumber()));

    }

    @Test
    @DisplayName("Player gets ready for quest, checks for error")
    public void RESP_10_TEST_2(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "quit\n0\n8\n8\ntest\n11\nquit\n";
        StringWriter output = new StringWriter();

        Player player = new Player(1);

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
        player.addToHand(new WeaponCard("Horse", 10));
        player.addToHand(new WeaponCard("Sword", 10));

        QuestCard qc = new QuestCard(2);

        qc.setAttack(new Scanner(input), new PrintWriter(output), player);

        Assertions.assertEquals("Attack from Player 2: S10 E30 ", qc.getAttack(player.getNumber()));

        Assertions.assertTrue(output.toString().contains("Your attack cannot be empty"));
        Assertions.assertTrue(output.toString().contains("Card selected is not an Weapon Card"));
        Assertions.assertTrue(output.toString().contains("That Weapon is already used"));
        Assertions.assertTrue(output.toString().contains("That is not a valid input. Please enter an index or 'Quit'"));
    }

    @Test
    @DisplayName("All players decline a quest after sponsor sets up a quest")
    public void RESP_11_TEST_1(){
        String input = "y\n0\n6\nquit\n2\n7\nQuit\n\n" + // sponsor sets quest
                "\nn\nn\nn\n";
        StringWriter output = new StringWriter();

        Game game = new Game(4, new Scanner(input), new PrintWriter(output));

        game.setUpGame();

        Player player = game.getPlayer(0);
        player.discardCards(12);

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

        game.getEventDeck().getCards().set(0, new QuestCard(2));
        game.drawEventCard();
        game.playEventCard();

        Assertions.assertTrue(output.toString().contains("No eligible players can take this quest"));
    }

    @Test
    @DisplayName("All players decline a quest after player 4 sets up a quest")
    public void RESP_11_TEST_2(){
        String input = "n\nn\nn\ny\n0\n6\nquit\n2\n7\nQuit\n\n" + // sponsor sets quest
                "\nn\nn\nn\n" +
                "0\n0\n";
        StringWriter output = new StringWriter();

        Game game = new Game(4, new Scanner(input), new PrintWriter(output));

        game.setUpGame();

        Player player = game.getPlayer(3);
        player.discardCards(12);

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

        game.getEventDeck().getCards().set(0, new QuestCard(2));
        game.drawEventCard();
        game.playEventCard();

        Assertions.assertTrue(output.toString().contains("No eligible players can take this quest"));
    }

    @Test
    @DisplayName("Sponsor wins and and gets the correct amount of cards")
    public void RESP_12_TEST_1(){
        String input = "0\n6\nquit\n2\n7\nQuit\n\n";
        StringWriter output = new StringWriter();

        Game game = new Game(4);
        game.setUpDecks();

        Player player = game.getPlayer(0);

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

        player.discardCards(4);

        int beforeWin = player.getHand().size();

        qc.sponsorWins(game);

        Assertions.assertEquals(beforeWin + 6, player.getHand().size());
    }

    @Test
    @DisplayName("Sponsor wins and trims hand")
    public void RESP_12_TEST_2(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "0\n6\nquit\n2\n\n7\nQuit\n2\n4\n4\nquit\n3\nquit\n\n0\n0\n0\n0\n0\n";
        StringWriter output = new StringWriter();

        Game game = new Game(4, new Scanner(input), new PrintWriter(output));
        game.setUpDecks();

        Player player = game.getPlayer(0);

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

        qc.setStages(game.getInput(), game.getOutput());

        qc.sponsorWins(game);

        Assertions.assertEquals(12, player.getHand().size());
    }

}