package org.example;

import jdk.jfr.Event;
import org.example.Card.*;

import java.util.ArrayList;

public class Game {

    private ArrayList<AdventureCard> adventureDeck;
    private ArrayList<EventCard> eventDeck;

    public Game() {
        setUpDecks();
    }

    public void setUpDecks(){
        this.adventureDeck = new ArrayList<>();
        addAdventureCards(adventureDeck, new FoeCard(5), 8);
        addAdventureCards(adventureDeck, new FoeCard(10), 7);
        addAdventureCards(adventureDeck, new FoeCard(15), 8);
        addAdventureCards(adventureDeck, new FoeCard(20), 7);
        addAdventureCards(adventureDeck, new FoeCard(25), 7);
        addAdventureCards(adventureDeck, new FoeCard(30), 4);
        addAdventureCards(adventureDeck, new FoeCard(35), 4);
        addAdventureCards(adventureDeck, new FoeCard(40), 2);
        addAdventureCards(adventureDeck, new FoeCard(50), 2);
        addAdventureCards(adventureDeck, new FoeCard(70), 1);

        addAdventureCards(adventureDeck, new WeaponCard("Dagger", 5), 6);
        addAdventureCards(adventureDeck, new WeaponCard("Horse", 10), 12);
        addAdventureCards(adventureDeck, new WeaponCard("Sword", 10), 16);
        addAdventureCards(adventureDeck, new WeaponCard("Battle-Axe", 15), 8);
        addAdventureCards(adventureDeck, new WeaponCard("Lance", 20), 6);
        addAdventureCards(adventureDeck, new WeaponCard("Excalibur", 30), 2);

        this.eventDeck = new ArrayList<>();
        addEventCards(eventDeck, new QuestCard(2), 3);
        addEventCards(eventDeck, new QuestCard(3), 4);
        addEventCards(eventDeck, new QuestCard(4), 3);
        addEventCards(eventDeck, new QuestCard(5), 2);

        addEventCards(eventDeck, new EventCard("Plague"), 1);
        addEventCards(eventDeck, new EventCard("Queen's Favor"), 2);
        addEventCards(eventDeck, new EventCard("Prosperity"), 2);
    }

    public void addAdventureCards(ArrayList<AdventureCard> deck, AdventureCard card, int amount){
        for (int i = 0; i < amount; i++){
            deck.add(card);
        }
    }

    public void addEventCards(ArrayList<EventCard> deck, EventCard card, int amount){
        for (int i = 0; i < amount; i++){
            deck.add(card);
        }
    }

    public ArrayList<AdventureCard> getAdventureDeck(){
        return adventureDeck;
    }

    public ArrayList<EventCard> getEventDeck(){
        return eventDeck;
    }

}
