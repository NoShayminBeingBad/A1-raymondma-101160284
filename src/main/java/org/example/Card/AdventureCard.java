package org.example.Card;

public abstract class AdventureCard extends Card{

    protected int bp;

    public AdventureCard(String title, int bp) {
        super(title);
        this.bp = bp;
    }

    public int getBP(){
        return bp;
    }

    public String toString(){
        return this.title.toUpperCase().substring(0,1).concat(Integer.toString(bp)) ;
    }

}
