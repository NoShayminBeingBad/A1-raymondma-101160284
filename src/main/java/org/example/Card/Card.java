package org.example.Card;

public abstract class Card {

    protected String title;

    public Card(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public String toString(){
        return title;
    }

}
