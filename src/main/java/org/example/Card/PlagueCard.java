package org.example.Card;

import org.example.Game;
import org.example.Player;

import java.util.ArrayList;

public class PlagueCard extends EventCard {

    public PlagueCard() {
        super("Plague");
    }

    @Override
    public void Event(Game game) {
        Player player = game.getPlayer(game.getTurnCount());
        player.giveShield(-2);
    }
}
