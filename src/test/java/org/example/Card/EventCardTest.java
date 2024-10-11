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

        int shieldsBefore = game.getPlayer(game.getTurnCount()).getShieldNum();

        game.getEventDeck().getCards().set(0, new PlagueCard());
        game.drawEventCard();
        game.playEventCard();

        Assertions.assertEquals(shieldsBefore - 2, game.getPlayer(game.getTurnCount()).getShieldNum());
    }

    @Test
    @DisplayName("Player draws Queen's Favor card")
    public void RESP_6_TEST_2(){
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
    @DisplayName("Player draws Prosperity card")
    public void RESP_6_TEST_3(){
        Scanner input = new Scanner("\n\n\n\n");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));
        game.setUpGame();

        game.getPlayer(0).discardCards(3);
        game.getPlayer(1).discardCards(2);
        game.getPlayer(2).discardCards(4);
        game.getPlayer(3).discardCards(12);

        int[] cardsBefore = {game.getPlayer(0).getHand().size(),
                game.getPlayer(1).getHand().size(),
                game.getPlayer(2).getHand().size(),
                game.getPlayer(3).getHand().size()};

        game.getEventDeck().getCards().set(0, new ProsperityCard());
        game.drawEventCard();
        game.playEventCard();

        for (int i = 0; i < cardsBefore.length; i++) {
            Assertions.assertEquals(cardsBefore[i] + 2, game.getPlayer(i).getHand().size());
        }
    }

    @Test
    @DisplayName("Player draws Queen's Favor card and needs to discard 1 cards")
    public void RESP_6_TEST_4(){
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
    public void RESP_6_TEST_5(){
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

    @Test
    @DisplayName("Player draws Prosperity card and players 1 and 3 need to discard cards")
    public void RESP_6_TEST_6(){
        Scanner input = new Scanner("3\n\n\n8\n10\n\n\n");
        StringWriter output = new StringWriter();

        Game game = new Game(4, input, new PrintWriter(output));
        game.setUpGame();

        //remove cards from player 2 and 4
        game.getPlayer(0).discardCard(1);
        game.getPlayer(1).discardCards(2);
        game.getPlayer(3).discardCards(12);

        int[] cardsBefore = {game.getPlayer(0).getHand().size(),
                game.getPlayer(1).getHand().size(),
                game.getPlayer(2).getHand().size(),
                game.getPlayer(3).getHand().size()};

        game.getEventDeck().getCards().set(0, new ProsperityCard());
        game.drawEventCard();
        game.playEventCard();

        for (int i = 0; i < cardsBefore.length; i++) {
            Assertions.assertEquals(Math.min(cardsBefore[i] + 2, 12), game.getPlayer(i).getHand().size());
        }
    }

}