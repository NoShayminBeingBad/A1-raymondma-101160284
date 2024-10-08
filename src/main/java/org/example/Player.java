package org.example;

import org.example.Card.AdventureCard;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public void printHand(PrintWriter output){
        Collections.sort(hand);
        String h = String.format("Player %d's Hand: ", number + 1);

        for (AdventureCard c : hand){
            h = h.concat(String.format("%s ", c.toString()));
        }

        output.println(h);
    }

}
