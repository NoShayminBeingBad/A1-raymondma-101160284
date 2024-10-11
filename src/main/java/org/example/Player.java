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
        hand.remove(index);
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
        return hand.size() > 12;
    }

    public void trimHand(Scanner input, PrintWriter output){
        if (overLimit()){
            output.println(String.format("Player %d has more than 12 cards. Please discard %d cards.", number + 1, hand.size()-12));
            int handSize = hand.size();
            for (int i = 0; i < handSize-12; i++){
                printHand(output);
                output.print(String.format("Please enter the position of the card you want to discard (0 - %d): ", hand.size()-1));
                int index = input.nextInt();
                output.println("");
                discardCard(index);
            }
            printHand(output);
        }
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
