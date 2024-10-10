package org.example;

import org.example.Card.AdventureCard;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

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

    //For testing purposes
    public void discardCards(int amount){
        for (int i = 0; i < amount; i++){
            hand.removeFirst();
        }
    }

    public void discardCard(int index){
        
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

    public boolean overLimit(){
    }

    public void trimHand(Scanner input, PrintWriter output){

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
