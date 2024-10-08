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

    private Deck<AdventureCard> adventureDeck;
    private Deck<EventCard> eventDeck;

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
        adventureDeck.shuffle();
        eventDeck.shuffle();
        dealHands();
    }

    public void dealHands(){
        for (int i = 0; i < 12; i++){
            for (Player player : players) {
                player.addToHand(adventureDeck.draw());
            }
        }
    }

    public void setUpDecks(){
        this.adventureDeck = new Deck<>();
        adventureDeck.addCards(new FoeCard(5), 8);
        adventureDeck.addCards(new FoeCard(10), 7);
        adventureDeck.addCards(new FoeCard(15), 8);
        adventureDeck.addCards(new FoeCard(20), 7);
        adventureDeck.addCards(new FoeCard(25), 7);
        adventureDeck.addCards(new FoeCard(30), 4);
        adventureDeck.addCards(new FoeCard(35), 4);
        adventureDeck.addCards(new FoeCard(40), 2);
        adventureDeck.addCards(new FoeCard(50), 2);
        adventureDeck.addCards(new FoeCard(70), 1);

        adventureDeck.addCards(new WeaponCard("Dagger", 5), 6);
        adventureDeck.addCards(new WeaponCard("Horse", 10), 12);
        adventureDeck.addCards(new WeaponCard("Sword", 10), 16);
        adventureDeck.addCards(new WeaponCard("Battle-Axe", 15), 8);
        adventureDeck.addCards(new WeaponCard("Lance", 20), 6);
        adventureDeck.addCards(new WeaponCard("Excalibur", 30), 2);

        this.eventDeck = new Deck<>();
        eventDeck.addCards(new QuestCard(2), 3);
        eventDeck.addCards(new QuestCard(3), 4);
        eventDeck.addCards(new QuestCard(4), 3);
        eventDeck.addCards(new QuestCard(5), 2);

        eventDeck.addCards(new EventCard("Plague"), 1);
        eventDeck.addCards(new EventCard("Queen's Favor"), 2);
        eventDeck.addCards(new EventCard("Prosperity"), 2);
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

    public Deck<AdventureCard> getAdventureDeck(){
        return adventureDeck;
    }

    public Deck<EventCard> getEventDeck(){
        return eventDeck;
    }

    public Player getPlayer(int p){
        return players.get(p);
    }

}
