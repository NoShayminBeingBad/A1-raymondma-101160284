package org.example.Card;

import org.example.Game;
import org.example.Player;

import java.util.ArrayList;

public class ProsperityCard extends EventCard{

    public ProsperityCard() {
        super("Prosperity");
    }

    @Override
    public void Event(Game game) {
        for (int i = 0; i < game.playerAmount(); i++){
            game.dealCardToPlayer(i);
            game.dealCardToPlayer(i);
            game.getPlayer(i).printHand(game.getOutput());
            game.getPlayer(i).trimHand(game.getInput(), game.getOutput());
            game.getOutput().println("It is now the next player's turn (press <return> to clear screen)");
            game.getInput().nextLine();
            game.getOutput().flush();
        }
    }

}
