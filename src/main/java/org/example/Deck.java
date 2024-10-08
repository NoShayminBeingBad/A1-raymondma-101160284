package org.example;

import org.example.Card.Card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck<T> {

    ArrayList<T> cards;

    public Deck(){
        this.cards = new ArrayList<T>();
    }

    //Draws top card, if deck is empty, it will return null
    public T draw(){
        if (cards.isEmpty()){
            return null;
        }
        return cards.removeFirst();
    }

    //Return the top x amount of cards
    //If the deck has less cards tha requested, it will return null
    public ArrayList<T> draw(int amount){
        if (cards.size() < amount){
            return null;
        }
        ArrayList<T> c = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            c.add(cards.removeFirst());
        }
        return c;
    }

    public void addCards(T c, int amount){
        for (int i = 0; i < amount; i++){
            cards.add(c);
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public ArrayList<T> getCards(){
        return cards;
    }

}
