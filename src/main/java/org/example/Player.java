package org.example;

import org.example.Card.AdventureCard;

import java.util.ArrayList;

public class Player {

    private int number;
    private ArrayList<AdventureCard> hand;

    private int shieldNum;

    public Player(int number){
        this.number = number;
        hand = new ArrayList<>();

        this.shieldNum = 0;
    }

    public void addToHand(AdventureCard card){
        hand.add(card);
    }

    public ArrayList<AdventureCard> getHand(){
        return hand;
    }

    public int getShieldNum(){
        return shieldNum;
    }

    public void giveShield(int x){
        shieldNum += x;
    }

}
