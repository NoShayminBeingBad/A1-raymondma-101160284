package org.example;

import org.example.Card.AdventureCard;

import java.util.ArrayList;

public class Player {

    private int number;
    private ArrayList<AdventureCard> hand;

    public Player(int number){
        this.number = number;
        hand = new ArrayList<>();
    }

    public void addToHand(AdventureCard card){
        hand.add(card);
    }

    public ArrayList<AdventureCard> getHand(){
        return hand;
    }

}
