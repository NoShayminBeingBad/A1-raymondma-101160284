package org.example;

import jdk.jfr.Event;
import org.example.Card.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {

    private Scanner input;
    private PrintWriter output;

    private int turnCount;
    private ArrayList<Player> players;

    private ArrayList<AdventureCard> adventureDeck;
    private ArrayList<EventCard> eventDeck;

    public Game(int playersNumber) {
       this(playersNumber, new Scanner(System.in), new PrintWriter(System.out));
    }

    public Game(int playersNumber, Scanner input, PrintWriter output) {
        this.input = input;
        this.output = output;

        this.players = new ArrayList<>();
        for (int i = 0; i < playersNumber; i++){
            this.players.add(new Player(i));
        }
        this.turnCount = 0;
    }

    public void setUpGame(){
        setUpDecks();
        Collections.shuffle(adventureDeck);
        Collections.shuffle(eventDeck);
        dealHands();
    }

    public void dealHands(){
        for (int i = 0; i < 12; i++){
            for (int j = 0; j < players.size(); j++){
                players.get(j).addToHand(adventureDeck.removeFirst());
            }
        }
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

    public void flushTurn(){
        output.println(String.format("It is now Player %d turn (press <return> to clear screen)", turnCount+1));
        input.nextLine();
        output.flush();
        output.println(String.format("It is now Player %d turn", turnCount+1));
    }

    public void nextTurn(){
        turnCount++;

        if (turnCount > players.size()){
            turnCount = 0;
        }
    }

    public boolean outputWinner(){
        boolean returnVal = false;

        for (int i = 0; i < players.size(); i++){
            if(players.get(i).getShieldNum() >= 7){
                output.println(String.format("Player %d has reached 7 shields!", i + 1));
                returnVal = true;
            }
        }

        return returnVal;
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

    public Player getPlayer(int p){
        return players.get(p);
    }

}
