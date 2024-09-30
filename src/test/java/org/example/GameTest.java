package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class GameTest {
    @Test
    public void RESP_1_TEST_1(){
        Game game = new Game();

        ArrayList<AdventureCard> adventureDeck = game.getAdventureDeck();

        //Check Foe cards
        Assertions.assertTrue(checkFoeCards(adventureDeck, 5, 8));
        Assertions.assertTrue(checkFoeCards(adventureDeck, 10, 7));
        Assertions.assertTrue(checkFoeCards(adventureDeck, 15, 8));
        Assertions.assertTrue(checkFoeCards(adventureDeck, 20, 7));
        Assertions.assertTrue(checkFoeCards(adventureDeck, 25, 7));
        Assertions.assertTrue(checkFoeCards(adventureDeck, 30, 4));
        Assertions.assertTrue(checkFoeCards(adventureDeck, 35, 4));
        Assertions.assertTrue(checkFoeCards(adventureDeck, 40, 2));
        Assertions.assertTrue(checkFoeCards(adventureDeck, 50, 2));
        Assertions.assertTrue(checkFoeCards(adventureDeck, 70, 1));

        //Check Weapon cards
        Assertions.assertTrue(checkAdventureCards(adventureDeck, "Dagger", 6));
        Assertions.assertTrue(checkAdventureCards(adventureDeck, "Horse", 12));
        Assertions.assertTrue(checkAdventureCards(adventureDeck, "Sword", 16));
        Assertions.assertTrue(checkAdventureCards(adventureDeck, "Battle-Axe", 8));
        Assertions.assertTrue(checkAdventureCards(adventureDeck, "Lance", 6));
        Assertions.assertTrue(checkAdventureCards(adventureDeck, "Excalibur", 2));

        //Check Event cards
        ArrayList<EventCard> eventDeck = game.getEventDeck();
        Assertions.assertTrue(checkEventCards(eventDeck, 2, 3));
        Assertions.assertTrue(checkEventCards(eventDeck, 3, 4));
        Assertions.assertTrue(checkEventCards(eventDeck, 4, 3));
        Assertions.assertTrue(checkEventCards(eventDeck, 5, 2));
    }

    private boolean checkEventCards(ArrayList<EventCard> deck, int quest, int amount){
        int count = 0;
        for (int i = 0; i < deck.size(); i++) {
            if (quest == deck.get(i).getStageAmount()) {
                count++;
            }
        }

        return count == amount;
    }

    private boolean checkAdventureCards(ArrayList<AdventureCard> deck, String title, int amount){
        int count = 0;
        for (int i = 0; i < deck.size(); i++) {
            if (title.equals(deck.get(i).toString())) {
                count++;
            }
        }

        return count == amount;
    }

    private boolean checkFoeCards(ArrayList<AdventureCard> deck, int foe, int amount){
        int count = 0;
        for (int i = 0; i < deck.size(); i++) {
            if (foe == deck.get(i).getBP()) {
                count++;
            }
        }

        return count == amount;
    }
}