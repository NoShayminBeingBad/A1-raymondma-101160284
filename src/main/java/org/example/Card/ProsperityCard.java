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
        }
    }

}
