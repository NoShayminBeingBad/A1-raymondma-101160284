package org.example.Card;

import org.example.Game;
import org.example.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
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

        Assertions.assertTrue(output.toString().contains("This Quest has 2 stages"));
        Assertions.assertTrue(output.toString().contains("No sponsor, quest has been canceled"));
    }

    @Test
    @DisplayName("Player sets up a quest for 2 stages")
    public void RESP_9_TEST_1(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "0\n6\nquit\n2\n7\nQuit\n\n";
        StringWriter output = new StringWriter();

        Player player = getTestPlayer();

        QuestCard qc = new QuestCard(2);
        qc.setSponsor(player);

        qc.setStages(new Scanner(input), new PrintWriter(output));

        Assertions.assertEquals("Stage 1 (10 BP): F5 D5 ", qc.getStage(0));
        Assertions.assertEquals("Stage 2 (20 BP): F10 H10 ", qc.getStage(1));
    }

    @Test
    @DisplayName("Player sets up a quest for 4 stages")
    public void RESP_9_TEST_2(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "0\n6\nquit\n2\n\n7\nQuit\n2\n4\n4\nquit\n3\nquit\n\n";
        StringWriter output = new StringWriter();

        Player player = getTestPlayer();

        QuestCard qc = new QuestCard(4);
        qc.setSponsor(player);

        qc.setStages(new Scanner(input), new PrintWriter(output));

        Assertions.assertEquals("Stage 1 (10 BP): F5 D5 ", qc.getStage(0));
        Assertions.assertEquals("Stage 2 (20 BP): F10 H10 ", qc.getStage(1));
        Assertions.assertEquals("Stage 3 (25 BP): F15 S10 ", qc.getStage(2));
        Assertions.assertEquals("Stage 4 (70 BP): F70 ", qc.getStage(3));
    }

    @Test
    @DisplayName("Player sets up a quest for 2 stages, checks for errors")
    public void RESP_9_TEST_3(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "quit\n7\n5\n6\nquit\n2\n0\ntest\nquit\n5\n5\n7\nQuit\n\n";
        StringWriter output = new StringWriter();

        Player player = getTestPlayer();

        QuestCard qc = new QuestCard(2);
        qc.setSponsor(player);

        qc.setStages(new Scanner(input), new PrintWriter(output));

        Assertions.assertEquals("Stage 1 (25 BP): F20 D5 ", qc.getStage(0));
        Assertions.assertEquals("Stage 2 (50 BP): F10 S10 E30 ", qc.getStage(1));

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

        Player player = getTestPlayer(2);

        QuestCard qc = new QuestCard(2);

        qc.setAttack(new Scanner(input), new PrintWriter(output), player);

        Assertions.assertEquals("Attack from Player 3 (25 BP): D5 S10 H10 ", qc.getAttack(player.getNumber()));

    }

    @Test
    @DisplayName("Player gets ready for quest, checks for error")
    public void RESP_10_TEST_2(){
        //"Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"
        String input = "0\n8\n8\ntest\n10\nquit\n";
        StringWriter output = new StringWriter();

        Player player = getTestPlayer(1);

        QuestCard qc = new QuestCard(2);

        qc.setAttack(new Scanner(input), new PrintWriter(output), player);

        Assertions.assertEquals("Attack from Player 2 (40 BP): S10 E30 ", qc.getAttack(player.getNumber()));

        Assertions.assertTrue(output.toString().contains("Card selected is not an Weapon Card"));
        Assertions.assertTrue(output.toString().contains("That Weapon is already used"));
        Assertions.assertTrue(output.toString().contains("That is not a valid input. Please enter an index or 'Quit'"));
    }

    @Test
    @DisplayName("All players decline a quest after sponsor sets up a quest")
    public void RESP_11_TEST_1(){
        String input = "y\n0\n6\nquit\n2\n7\nQuit\n\n" + // sponsor sets quest
                "\nn\nn\nn\n" +
                "0\n0\n";;
        StringWriter output = new StringWriter();

        Game game = new Game(4, new Scanner(input), new PrintWriter(output));

        game.setUpGame();

        setPlayer(game.getPlayer(0));

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

        setPlayer(game.getPlayer(3));

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

        Player player = setPlayer(game.getPlayer(0));

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

        Player player = setPlayer(game.getPlayer(0));

        QuestCard qc = new QuestCard(4);
        qc.setSponsor(player);

        qc.setStages(game.getInput(), game.getOutput());

        qc.sponsorWins(game);

        Assertions.assertEquals(12, player.getHand().size());
    }

    @Test
    @DisplayName("Properly process eligible winners of a stage")
    public void RESP_15_TEST_1(){
        //Stage 1 (10 BP): F15 D5
        String input = "4\n6\nquit\n\n";
        StringWriter output = new StringWriter();

        Player player = getTestPlayer();

        QuestCard qc = new QuestCard(1);
        qc.setSponsor(player);

        qc.setStages(new Scanner(input), new PrintWriter(output));

        //Attack from Player 2 (25 BP): D5 S10 H10
        String input2 = "8\n7\n8\nquit\n";
        StringWriter output2 = new StringWriter();

        Player attacker1 = getTestPlayer(1);

        qc.setAttack(new Scanner(input2), new PrintWriter(output2), attacker1);

        //Attack from Player 3 (25 BP): D5 S10 H10
        String input3 = "8\n7\n8\nquit\n";
        StringWriter output3 = new StringWriter();

        Player attacker2 = getTestPlayer(2);

        qc.setAttack(new Scanner(input3), new PrintWriter(output3), attacker2);

        ArrayList<Player> eligible = new ArrayList<>();
        eligible.add(attacker1);
        eligible.add(attacker2);

        eligible = qc.getPassed(eligible, 0);

        Assertions.assertEquals(2, eligible.size());
    }

    @Test
    @DisplayName("Properly process eligible winners of a stage, Player 3 does not pass")
    public void RESP_15_TEST_2(){
        //Stage 1 (10 BP): F15 D5
        String input = "4\n6\nquit\n\n";
        StringWriter output = new StringWriter();

        Player player = getTestPlayer();

        QuestCard qc = new QuestCard(1);
        qc.setSponsor(player);

        qc.setStages(new Scanner(input), new PrintWriter(output));

        //Attack from Player 2 (25 BP): D5 S10 H10
        String input2 = "8\n7\n8\nquit\n";
        StringWriter output2 = new StringWriter();

        Player attacker1 = getTestPlayer(1);

        qc.setAttack(new Scanner(input2), new PrintWriter(output2), attacker1);

        //Attack from Player 3 (25 BP): D5
        String input3 = "8\nquit\n";
        StringWriter output3 = new StringWriter();

        Player attacker2 = getTestPlayer(2);

        qc.setAttack(new Scanner(input3), new PrintWriter(output3), attacker2);

        ArrayList<Player> eligible = new ArrayList<>();
        eligible.add(attacker1);
        eligible.add(attacker2);

        eligible = qc.getPassed(eligible, 0);

        Assertions.assertEquals(1, eligible.size());
    }

    @Test
    @DisplayName("Winners of the quest are awarded 2 shields")
    public void RESP_16_TEST_1(){
        Game game = new Game(4);
        QuestCard qc = new QuestCard(2);

        ArrayList<Player> players = game.getAllPlayers();

        qc.playersWin(players);

        for (Player player : players){
            Assertions.assertEquals(2, player.getShieldNum());
        }
    }

    @Test
    @DisplayName("Winners of the quest are awarded 3 shields")
    public void RESP_16_TEST_2(){
        Game game = new Game(4);
        QuestCard qc = new QuestCard(3);

        ArrayList<Player> players = game.getAllPlayers();

        qc.playersWin(players);

        for (Player player : players){
            Assertions.assertEquals(3, player.getShieldNum());
        }
    }

    @Test
    @DisplayName("Winners of the quest are awarded 2 shields")
    public void RESP_16_TEST_3(){
        Game game = new Game(4);
        QuestCard qc = new QuestCard(2);

        ArrayList<Player> players = game.getAllPlayers();
        players.get(1).giveShield(2);
        players.get(2).giveShield(4);

        qc.playersWin(players);

        Assertions.assertEquals(2, players.get(0).getShieldNum());
        Assertions.assertEquals(4, players.get(1).getShieldNum());
        Assertions.assertEquals(6, players.get(2).getShieldNum());
        Assertions.assertEquals(2, players.get(3).getShieldNum());
    }

    public Player getTestPlayer(){
        return getTestPlayer(0);
    }

    public Player getTestPlayer(int num){
        Player p = new Player(num);
        setPlayer(p);
        return p;
    }

    public Player setPlayer(Player player){
        player.discardAllCards();

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

        return player;
    }

}