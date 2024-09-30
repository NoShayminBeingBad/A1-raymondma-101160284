package org.example;

import org.example.Card.Card;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Deck {

    ArrayList<Card> cards = new ArrayList<>();

    //Draws top card, if deck is empty, it will return null
    public Card draw(){
        if (cards.isEmpty()){
            return null;
        }
        return cards.removeFirst();
    }

    //Return the top x amount of cards
    //If the deck has less cards tha requested, it will return null
    public Card[] draw(int amount){
        if (cards.size() < amount){
            return null;
        }
        Card[] c = new Card[amount];
        for (int i = 0; i < amount; i++){
            c[i] = cards.removeFirst();
        }
        return c;
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

}
