package org.example;

import org.example.Card.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


class GameTest {

    static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    @Test
    public void RESP_1_TEST_1(){
        Game game = new Game(0);

        game.setUpDecks();

        ArrayList<AdventureCard> adventureDeck = game.getAdventureDeck();

        //Check Foe cards
        //TODO: Fix order :(
        Assertions.assertEquals(countFoeCards(adventureDeck, 5), 8);
        Assertions.assertEquals(countFoeCards(adventureDeck, 10), 7);
        Assertions.assertEquals(countFoeCards(adventureDeck, 15), 8);
        Assertions.assertEquals(countFoeCards(adventureDeck, 20), 7);
        Assertions.assertEquals(countFoeCards(adventureDeck, 25), 7);
        Assertions.assertEquals(countFoeCards(adventureDeck, 30), 4);
        Assertions.assertEquals(countFoeCards(adventureDeck, 35), 4);
        Assertions.assertEquals(countFoeCards(adventureDeck, 40), 2);
        Assertions.assertEquals(countFoeCards(adventureDeck, 50), 2);
        Assertions.assertEquals(countFoeCards(adventureDeck, 70), 1);

        //Check Weapon cards
        Assertions.assertEquals(countAdventureCards(adventureDeck, "Dagger"), 6);
        Assertions.assertEquals(countAdventureCards(adventureDeck, "Horse"), 12);
        Assertions.assertEquals(countAdventureCards(adventureDeck, "Sword"), 16);
        Assertions.assertEquals(countAdventureCards(adventureDeck, "Battle-Axe"), 8);
        Assertions.assertEquals(countAdventureCards(adventureDeck, "Lance"), 6);
        Assertions.assertEquals(countAdventureCards(adventureDeck, "Excalibur"), 2);

        //Check Event cards
        ArrayList<EventCard> eventDeck = game.getEventDeck();
        Assertions.assertEquals(countEventCards(eventDeck, "Q2"), 3);
        Assertions.assertEquals(countEventCards(eventDeck, "Q3"), 4);
        Assertions.assertEquals(countEventCards(eventDeck, "Q4"), 3);
        Assertions.assertEquals(countEventCards(eventDeck, "Q5"), 2);

        Assertions.assertEquals(countEventCards(eventDeck, "Plague"), 1);
        Assertions.assertEquals(countEventCards(eventDeck, "Queen's Favor"), 2);
        Assertions.assertEquals(countEventCards(eventDeck, "Prosperity"), 2);
    }

    private int countEventCards(ArrayList<EventCard> deck, String event){
        int count = 0;
        for (int i = 0; i < deck.size(); i++) {
            if (event.equals(deck.get(i).toString())) {
                count++;
            }
        }

        return count;
    }

    private int countAdventureCards(ArrayList<AdventureCard> deck, String title){
        int count = 0;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) instanceof WeaponCard && title.equals(deck.get(i).getTitle())) {
                count++;
            }
        }

        return count;
    }

    private int countFoeCards(ArrayList<AdventureCard> deck, int foe){
        int count = 0;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) instanceof FoeCard && foe == deck.get(i).getBP()) {
                count++;
            }
        }

        return count;
    }

    @Test
    public void RESP_2_TEST_1(){
        Game game = new Game(4);

        game.setUpDecks();

        int adventureDeckStart = game.getAdventureDeck().size();

        game.dealHands();

        for (int i = 0; i < 4; i++){
            Player player = game.getPlayer(i);
            Assertions.assertEquals(12, player.getHand().size());
        }

        Assertions.assertTrue(game.getAdventureDeck().size() < adventureDeckStart);

    }

    @Test
    @DisplayName("Test Win condition: 1 winner")
    public void RESP_3_TEST_1(){

        Scanner input = new Scanner("");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));

        game.getPlayer(0).giveShield(3);
        game.getPlayer(1).giveShield(1);
        game.getPlayer(2).giveShield(6);
        game.getPlayer(3).giveShield(7);

        Assertions.assertTrue(game.outputWinner());
        Assertions.assertTrue(output.toString().contains("Player 4 has reached 7 shields!"));

    }

    @Test
    @DisplayName("Test Win condition: Multiple winner")
    public void RESP_3_TEST_2(){

        Scanner input = new Scanner("");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));

        game.getPlayer(0).giveShield(0);
        game.getPlayer(1).giveShield(8);
        game.getPlayer(2).giveShield(7);
        game.getPlayer(3).giveShield(6);

        Assertions.assertTrue(game.outputWinner());
        Assertions.assertTrue(output.toString().contains("Player 2 has reached 7 shields!"));
        Assertions.assertTrue(output.toString().contains("Player 3 has reached 7 shields!"));
    }

    @Test
    @DisplayName("Test Win condition: No winner")
    public void RESP_3_TEST_3(){

        Scanner input = new Scanner("");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));

        game.getPlayer(0).giveShield(3);
        game.getPlayer(1).giveShield(1);
        game.getPlayer(2).giveShield(6);
        game.getPlayer(3).giveShield(1);

        Assertions.assertFalse(game.outputWinner());
        Assertions.assertEquals("", output.toString());

    }
}