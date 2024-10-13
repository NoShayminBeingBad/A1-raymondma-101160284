package org.example.Card;

import org.example.Game;
import org.example.Player;

import java.util.ArrayList;

public class QueensFavorCard extends EventCard{

    public QueensFavorCard() {
        super("Queen's Favor");
    }

    @Override
    public void Event(Game game) {
        game.getOutput().println(String.format("Player %d draws 2 cards!", game.getTurnCount()+1));
        game.dealCardToPlayer(game.getTurnCount());
        game.dealCardToPlayer(game.getTurnCount());
        game.getPlayer(game.getTurnCount()).printHand(game.getOutput());
        game.getPlayer(game.getTurnCount()).trimHand(game.getInput(), game.getOutput());
        game.getOutput().flush();
    }

}
