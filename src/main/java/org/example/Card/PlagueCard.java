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
        game.getOutput().println(String.format("The Plague Card has been drawn! Player %d loses 2 shields!", game.getTurnCount()));
        Player player = game.getPlayer(game.getTurnCount());
        player.giveShield(-2);
        game.getOutput().flush();
    }
}
