package org.example.Card;

import org.example.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

class EventCardTest {

    @Test
    @DisplayName("Player draws Plague card")
    public void RESP_6_TEST_1(){
        Scanner input = new Scanner("");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));

        game.setUpGame();

        game.getPlayer(game.getTurnCount()).giveShield(3);
        int shieldsBefore = game.getPlayer(game.getTurnCount()).getShieldNum();

        game.getEventDeck().getCards().set(0, new PlagueCard());
        game.drawEventCard();
        game.playEventCard();

        Assertions.assertEquals(shieldsBefore - 2, game.getPlayer(game.getTurnCount()).getShieldNum());
    }

    @Test
    @DisplayName("Player draws Plague card and has less than 2 shields")
    public void RESP_6_TEST_2(){
        Scanner input = new Scanner("");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));

        game.setUpGame();

        game.getPlayer(game.getTurnCount()).giveShield(1);

        game.getEventDeck().getCards().set(0, new PlagueCard());
        game.drawEventCard();
        game.playEventCard();

        Assertions.assertEquals(0, game.getPlayer(game.getTurnCount()).getShieldNum());
    }

    @Test
    @DisplayName("Player draws Queen's Favor card")
    public void RESP_13_TEST_1(){
        Scanner input = new Scanner("");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));
        game.setUpGame();

        game.getPlayer(game.getTurnCount()).getHand().removeFirst();
        game.getPlayer(game.getTurnCount()).getHand().removeFirst();
        game.getPlayer(game.getTurnCount()).getHand().removeFirst();

        int cardsBefore = game.getPlayer(game.getTurnCount()).getHand().size();

        game.getEventDeck().getCards().set(0, new QueensFavorCard());
        game.drawEventCard();
        game.playEventCard();

        Assertions.assertEquals(cardsBefore + 2, game.getPlayer(game.getTurnCount()).getHand().size());
    }

    @Test
    @DisplayName("Player draws Queen's Favor card and needs to discard 1 cards")
    public void RESP_13_TEST_2(){
        Scanner input = new Scanner("3\n");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));
        game.setUpGame();

        game.getPlayer(game.getTurnCount()).getHand().removeFirst();

        int cardsBefore = game.getPlayer(game.getTurnCount()).getHand().size();

        game.getEventDeck().getCards().set(0, new QueensFavorCard());
        game.drawEventCard();
        game.playEventCard();

        Assertions.assertEquals(12, game.getPlayer(game.getTurnCount()).getHand().size());
    }

    @Test
    @DisplayName("Player draws Queen's Favor card and needs to discard 2 cards")
    public void RESP_13_TEST_3(){
        Scanner input = new Scanner("13\n1\n");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));
        game.setUpGame();

        int cardsBefore = game.getPlayer(game.getTurnCount()).getHand().size();

        game.getEventDeck().getCards().set(0, new QueensFavorCard());
        game.drawEventCard();
        game.playEventCard();

        Assertions.assertEquals(12, game.getPlayer(game.getTurnCount()).getHand().size());
    }

}