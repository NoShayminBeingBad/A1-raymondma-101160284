package org.example.Card;

import org.example.Game;
import org.example.Player;

import java.util.ArrayList;

public abstract class EventCard extends Card{

    public EventCard(String title) {
        super(title);
    }

    public abstract void Event(Game game);

}
