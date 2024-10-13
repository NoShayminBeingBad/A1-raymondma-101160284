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

    private EventCard eventCard;

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

    public void dealCardToPlayer(int player){
        players.get(player).addToHand(adventureDeck.draw());
    }

    public void dealCardsToPlayer(int player, int amount){
        for (int i = 0; i < amount; i++){
            dealCardToPlayer(player);
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

        eventDeck.addCards(new PlagueCard(), 1);
        eventDeck.addCards(new QueensFavorCard(), 2);
        eventDeck.addCards(new ProsperityCard(), 2);
    }

    public void gameplay(){
        boolean winner = false;
        turnCount = -1;
        output.println("Let the games begin!"); output.flush();

        while (!winner){

            flushTurn();

            drawEventCard();

            playEventCard();

            winner = outputWinner();
        }

    }

    public void flushScreen(){
        output.print("\n\n\n\n\n\n\n\n\n\n");
        output.flush();
    }

    public void flushTurn(){
        nextTurn();
        output.println(String.format("It is now Player %d turn (press <return> to clear screen)", turnCount+1)); output.flush();
        input.nextLine();
        flushScreen();
        output.println(String.format("It is now Player %d turn", turnCount+1)); output.flush();
    }

    public void nextTurn(){
        turnCount++;

        if (turnCount >= players.size()){
            turnCount = 0;
        }
    }

    public void drawEventCard() {
        eventCard = eventDeck.draw();
    }

    public void playEventCard(){
        eventCard.Event(this);
    }

    public void printShields(){
        output.println("Player Shield Amount");
        for(int i = 0; i < playerAmount(); i++){
            output.println(String.format("Player %d: %d Shields", i+1, players.get(i).getShieldNum()));
        }
        output.flush();
    }

    public boolean outputWinner(){
        boolean returnVal = false;

        for (int i = 0; i < players.size(); i++){
            if(players.get(i).getShieldNum() >= 7){
                output.println(String.format("Player %d has reached 7 shields!", i + 1));
                returnVal = true;
            }
        }

        output.flush();

        return returnVal;
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

    public ArrayList<Player> getAllPlayers(){
        return players;
    }

    public int getTurnCount(){
        return turnCount;
    }

    public int playerAmount(){
        return players.size();
    }

    public Scanner getInput() {
        return input;
    }

    public PrintWriter getOutput() {
        return output;
    }
}
