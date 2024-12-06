package org.example.Card;

public class FoeCard extends AdventureCard{
    public FoeCard(int bp) {
        super(String.format("Foe %d", bp), bp);
    }
}
