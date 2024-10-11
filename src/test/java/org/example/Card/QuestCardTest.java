package org.example.Card;

import org.example.Game;
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

}