package org.example;

import jakarta.annotation.PreDestroy;
import org.example.Card.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class Controller {

    private Game game;
    private ServerOutput so;
    private ServerInput si;
    Thread thread;

    @RequestMapping(value = "/start")
    @ResponseBody
    public GameState start(){

        System.out.println("Start!");
        this.so = new ServerOutput();
        this.si = new ServerInput(this.so);
        regular_game();

        this.so.locked = true;

        this.thread = new Thread(this.game);

        thread.start();

        String out = so.awaitOutput();

        //System.out.println("Output: " + out);

        return new GameState(game, out);
    }

    @RequestMapping(value = "/a1")
    @ResponseBody
    public GameState a1(){
        System.out.println("Start!");
        this.so = new ServerOutput();
        this.si = new ServerInput(this.so);
        a1_scenario();
        this.so.println("A1 scenario");
        this.so.locked = true;

        this.thread = new Thread(this.game);

        thread.start();

        String out = so.awaitOutput();

        //System.out.println("Output: " + out);

        return new GameState(game, out);
    }

    @RequestMapping(value = "/2w2g")
    @ResponseBody
    public GameState scenario_2(){
        System.out.println("Start!");
        this.so = new ServerOutput();
        this.si = new ServerInput(this.so);
        scenario_2_setup();
        this.so.locked = true;

        this.thread = new Thread(this.game);

        thread.start();

        String out = so.awaitOutput();

        //System.out.println("Output: " + out);

        return new GameState(game, out);
    }

    @RequestMapping(value = "/1we")
    @ResponseBody
    public GameState scenario_3(){
        System.out.println("Start!");
        this.so = new ServerOutput();
        this.si = new ServerInput(this.so);
        scenario_3_setup();
        this.so.locked = true;

        this.thread = new Thread(this.game);

        thread.start();

        String out = so.awaitOutput();

        //System.out.println("Output: " + out);

        return new GameState(game, out);
    }

    @RequestMapping(value = "/0wq")
    @ResponseBody
    public GameState scenario_4(){
        System.out.println("Start!");
        this.so = new ServerOutput();
        this.si = new ServerInput(this.so);
        scenario_4_setup();
        this.so.locked = true;

        this.thread = new Thread(this.game);

        thread.start();

        String out = so.awaitOutput();

        //System.out.println("Output: " + out);

        return new GameState(game, out);
    }

    @PostMapping("/output/{input}")
    @ResponseBody
    public GameState sendMessage(@PathVariable("input") String input){
        si.setInput(input);

        String out = so.awaitOutput();

        //System.out.println("Output: " + out);

        return new GameState(game, out);
    }

    @PostMapping("/empty")
    @ResponseBody
    public GameState emptyResponse(){
        si.setInput(" ");

        String out = so.awaitOutput();

        //System.out.println("Output: " + out);

        return new GameState(game, out);
    }

    @PostMapping("/input")
    public String getInput(){
        return si.getInput();
    }

    @PostMapping("/input2")
    public String getInput2(){
        return so.buffer;
    }

    @PreDestroy
    public void onExit(){
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void regular_game(){
        this.game = new Game(4, si, so);
        this.game.setUpGame();
    }

    public void a1_scenario(){
        this.game = new Game(4, si, so);
        game.setUpDecks();
        game.getAdventureDeck().shuffle();
        game.getEventDeck().shuffle();

        Player p1 = game.getPlayer(0);
        Player p2 = game.getPlayer(1);
        Player p3 = game.getPlayer(2);
        Player p4 = game.getPlayer(3);

        p1.addToHand(new FoeCard(5));
        p1.addToHand(new FoeCard(5));
        p1.addToHand(new FoeCard(15));
        p1.addToHand(new FoeCard(15));
        p1.addToHand(new WeaponCard("Dagger", 5));
        p1.addToHand(new WeaponCard("Sword", 10));
        p1.addToHand(new WeaponCard("Sword", 10));
        p1.addToHand(new WeaponCard("Horse", 10));
        p1.addToHand(new WeaponCard("Horse", 10));
        p1.addToHand(new WeaponCard("Battle-Axe", 15));
        p1.addToHand(new WeaponCard("Battle-Axe", 15));
        p1.addToHand(new WeaponCard("Lance", 20));

        p2.addToHand(new FoeCard(5));
        p2.addToHand(new FoeCard(5));
        p2.addToHand(new FoeCard(15));
        p2.addToHand(new FoeCard(15));
        p2.addToHand(new FoeCard(40));
        p2.addToHand(new WeaponCard("Dagger", 5));
        p2.addToHand(new WeaponCard("Sword", 10));
        p2.addToHand(new WeaponCard("Horse", 10));
        p2.addToHand(new WeaponCard("Horse", 10));
        p2.addToHand(new WeaponCard("Battle-Axe", 15));
        p2.addToHand(new WeaponCard("Battle-Axe", 15));
        p2.addToHand(new WeaponCard("Excalibur", 30));

        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(15));
        p3.addToHand(new WeaponCard("Dagger", 5));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Battle-Axe", 15));
        p3.addToHand(new WeaponCard("Lance", 20));

        p4.addToHand(new FoeCard(5));
        p4.addToHand(new FoeCard(15));
        p4.addToHand(new FoeCard(15));
        p4.addToHand(new FoeCard(40));
        p4.addToHand(new WeaponCard("Dagger", 5));
        p4.addToHand(new WeaponCard("Dagger", 5));
        p4.addToHand(new WeaponCard("Sword", 10));
        p4.addToHand(new WeaponCard("Horse", 10));
        p4.addToHand(new WeaponCard("Horse", 10));
        p4.addToHand(new WeaponCard("Battle-Axe", 15));
        p4.addToHand(new WeaponCard("Lance", 20));
        p4.addToHand(new WeaponCard("Excalibur", 30));

        game.getEventDeck().getCards().set(0, new QuestCard(4));

        //stage 1
        game.getAdventureDeck().getCards().set(0, new FoeCard(30));
        game.getAdventureDeck().getCards().set(1, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(2, new WeaponCard("Battle-Axe", 15));
        //stage 2
        game.getAdventureDeck().getCards().set(3, new FoeCard(10));
        game.getAdventureDeck().getCards().set(4, new WeaponCard("Lance", 20));
        game.getAdventureDeck().getCards().set(5, new WeaponCard("Lance", 20));
        //stage 3
        game.getAdventureDeck().getCards().set(6, new WeaponCard("Battle-Axe", 15));
        game.getAdventureDeck().getCards().set(7, new WeaponCard("Sword", 10));
        //stage 4
        game.getAdventureDeck().getCards().set(8, new FoeCard(30));
        game.getAdventureDeck().getCards().set(9, new WeaponCard("Lance", 20));

    }

    public void scenario_2_setup(){
        this.game = new Game(4, si, so);
        game.setUpDecks();
        game.getAdventureDeck().shuffle();
        game.getEventDeck().shuffle();

        Player p1 = game.getPlayer(0);
        Player p2 = game.getPlayer(1);
        Player p3 = game.getPlayer(2);
        Player p4 = game.getPlayer(3);

        p1.addToHand(new FoeCard(5));
        p1.addToHand(new FoeCard(5));
        p1.addToHand(new FoeCard(10));
        p1.addToHand(new FoeCard(10));
        p1.addToHand(new FoeCard(15));
        p1.addToHand(new FoeCard(15));
        p1.addToHand(new WeaponCard("Dagger", 5));
        p1.addToHand(new WeaponCard("Horse", 10));
        p1.addToHand(new WeaponCard("Horse", 10));
        p1.addToHand(new WeaponCard("Battle-Axe", 15));
        p1.addToHand(new WeaponCard("Battle-Axe", 15));
        p1.addToHand(new WeaponCard("Lance", 20));

        p2.addToHand(new FoeCard(40));
        p2.addToHand(new FoeCard(50));
        p2.addToHand(new WeaponCard("Horse", 10));
        p2.addToHand(new WeaponCard("Horse", 10));
        p2.addToHand(new WeaponCard("Sword", 10));
        p2.addToHand(new WeaponCard("Sword", 10));
        p2.addToHand(new WeaponCard("Sword", 10));
        p2.addToHand(new WeaponCard("Battle-Axe", 15));
        p2.addToHand(new WeaponCard("Battle-Axe", 15));
        p2.addToHand(new WeaponCard("Lance", 20));
        p2.addToHand(new WeaponCard("Lance", 20));
        p2.addToHand(new WeaponCard("Excalibur", 30));

        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(5));
        p3.addToHand(new WeaponCard("Dagger", 5));
        p3.addToHand(new WeaponCard("Dagger", 5));
        p3.addToHand(new WeaponCard("Dagger", 5));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Horse", 10));

        p4.addToHand(new FoeCard(50));
        p4.addToHand(new FoeCard(70));
        p4.addToHand(new WeaponCard("Horse", 10));
        p4.addToHand(new WeaponCard("Horse", 10));
        p4.addToHand(new WeaponCard("Sword", 10));
        p4.addToHand(new WeaponCard("Sword", 10));
        p4.addToHand(new WeaponCard("Sword", 10));
        p4.addToHand(new WeaponCard("Battle-Axe", 15));
        p4.addToHand(new WeaponCard("Battle-Axe", 15));
        p4.addToHand(new WeaponCard("Lance", 20));
        p4.addToHand(new WeaponCard("Lance", 20));
        p4.addToHand(new WeaponCard("Excalibur", 30));

        game.getEventDeck().getCards().set(0, new QuestCard(4));
        game.getEventDeck().getCards().set(1, new QuestCard(3));

        //quest 1
        //stage 1
        game.getAdventureDeck().getCards().set(0, new FoeCard(5));
        game.getAdventureDeck().getCards().set(1, new FoeCard(40));
        game.getAdventureDeck().getCards().set(2, new FoeCard(10));
        //stage 2
        game.getAdventureDeck().getCards().set(3, new FoeCard(10));
        game.getAdventureDeck().getCards().set(4, new FoeCard(30));
        //stage 3
        game.getAdventureDeck().getCards().set(5, new FoeCard(30));
        game.getAdventureDeck().getCards().set(6, new FoeCard(15));
        //stage 4
        game.getAdventureDeck().getCards().set(7, new FoeCard(15));
        game.getAdventureDeck().getCards().set(8, new FoeCard(20));
        //sponsor draw
        game.getAdventureDeck().getCards().set(9, new FoeCard(5));
        game.getAdventureDeck().getCards().set(10, new FoeCard(10));
        game.getAdventureDeck().getCards().set(11, new FoeCard(15));
        game.getAdventureDeck().getCards().set(12, new FoeCard(15));
        game.getAdventureDeck().getCards().set(13, new FoeCard(20));
        game.getAdventureDeck().getCards().set(14, new FoeCard(20));
        game.getAdventureDeck().getCards().set(15, new FoeCard(20));
        game.getAdventureDeck().getCards().set(16, new FoeCard(20));
        game.getAdventureDeck().getCards().set(17, new FoeCard(25));
        game.getAdventureDeck().getCards().set(18, new FoeCard(25));
        game.getAdventureDeck().getCards().set(19, new FoeCard(30));

        //quest 2
        //stage 1
        game.getAdventureDeck().getCards().set(20, new WeaponCard("Dagger", 5));
        game.getAdventureDeck().getCards().set(21, new WeaponCard("Dagger", 5));
        //stage 2
        game.getAdventureDeck().getCards().set(22, new FoeCard(15));
        game.getAdventureDeck().getCards().set(23, new FoeCard(15));
        //stage 3
        game.getAdventureDeck().getCards().set(24, new FoeCard(25));
        game.getAdventureDeck().getCards().set(25, new FoeCard(25));
        //sponsor draw
        game.getAdventureDeck().getCards().set(26, new FoeCard(20));
        game.getAdventureDeck().getCards().set(27, new FoeCard(20));
        game.getAdventureDeck().getCards().set(28, new FoeCard(25));
        game.getAdventureDeck().getCards().set(29, new FoeCard(30));
        game.getAdventureDeck().getCards().set(30, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(31, new WeaponCard("Battle-Axe", 15));
        game.getAdventureDeck().getCards().set(32, new WeaponCard("Battle-Axe", 15));
        game.getAdventureDeck().getCards().set(33, new WeaponCard("Lance", 20));

    }

    public void scenario_3_setup(){
        this.game = new Game(4, si, so);
        game.setUpDecks();
        game.getAdventureDeck().shuffle();
        game.getEventDeck().shuffle();

        Player p1 = game.getPlayer(0);
        Player p2 = game.getPlayer(1);
        Player p3 = game.getPlayer(2);
        Player p4 = game.getPlayer(3);

        p1.addToHand(new FoeCard(5));
        p1.addToHand(new FoeCard(5));
        p1.addToHand(new FoeCard(10));
        p1.addToHand(new FoeCard(10));
        p1.addToHand(new FoeCard(15));
        p1.addToHand(new FoeCard(15));
        p1.addToHand(new FoeCard(20));
        p1.addToHand(new FoeCard(20));
        p1.addToHand(new WeaponCard("Dagger", 5));
        p1.addToHand(new WeaponCard("Dagger", 5));
        p1.addToHand(new WeaponCard("Dagger", 5));
        p1.addToHand(new WeaponCard("Dagger", 5));

        p2.addToHand(new FoeCard(25));
        p2.addToHand(new FoeCard(30));
        p2.addToHand(new WeaponCard("Horse", 10));
        p2.addToHand(new WeaponCard("Horse", 10));
        p2.addToHand(new WeaponCard("Sword", 10));
        p2.addToHand(new WeaponCard("Sword", 10));
        p2.addToHand(new WeaponCard("Sword", 10));
        p2.addToHand(new WeaponCard("Battle-Axe", 15));
        p2.addToHand(new WeaponCard("Battle-Axe", 15));
        p2.addToHand(new WeaponCard("Lance", 20));
        p2.addToHand(new WeaponCard("Lance", 20));
        p2.addToHand(new WeaponCard("Excalibur", 30));

        p3.addToHand(new FoeCard(25));
        p3.addToHand(new FoeCard(30));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Battle-Axe", 15));
        p3.addToHand(new WeaponCard("Battle-Axe", 15));
        p3.addToHand(new WeaponCard("Lance", 20));
        p3.addToHand(new WeaponCard("Lance", 20));
        p3.addToHand(new WeaponCard("Excalibur", 30));

        p4.addToHand(new FoeCard(25));
        p4.addToHand(new FoeCard(30));
        p4.addToHand(new FoeCard(70));
        p4.addToHand(new WeaponCard("Horse", 10));
        p4.addToHand(new WeaponCard("Horse", 10));
        p4.addToHand(new WeaponCard("Sword", 10));
        p4.addToHand(new WeaponCard("Sword", 10));
        p4.addToHand(new WeaponCard("Sword", 10));
        p4.addToHand(new WeaponCard("Battle-Axe", 15));
        p4.addToHand(new WeaponCard("Battle-Axe", 15));
        p4.addToHand(new WeaponCard("Lance", 20));
        p4.addToHand(new WeaponCard("Lance", 20));

        game.getEventDeck().getCards().set(0, new QuestCard(4));
        game.getEventDeck().getCards().set(1, new PlagueCard());
        game.getEventDeck().getCards().set(2, new ProsperityCard());
        game.getEventDeck().getCards().set(3, new QueensFavorCard());
        game.getEventDeck().getCards().set(4, new QuestCard(3));

        //quest 1
        //stage 1
        game.getAdventureDeck().getCards().set(0, new FoeCard(5));
        game.getAdventureDeck().getCards().set(1, new FoeCard(10));
        game.getAdventureDeck().getCards().set(2, new FoeCard(20));
        //stage 2
        game.getAdventureDeck().getCards().set(3, new FoeCard(15));
        game.getAdventureDeck().getCards().set(4, new FoeCard(5));
        game.getAdventureDeck().getCards().set(5, new FoeCard(25));
        //stage 3
        game.getAdventureDeck().getCards().set(6, new FoeCard(5));
        game.getAdventureDeck().getCards().set(7, new FoeCard(10));
        game.getAdventureDeck().getCards().set(8, new FoeCard(20));
        //stage 4
        game.getAdventureDeck().getCards().set(9, new FoeCard(5));
        game.getAdventureDeck().getCards().set(10, new FoeCard(10));
        game.getAdventureDeck().getCards().set(11, new FoeCard(20));
        //sponsor draw
        game.getAdventureDeck().getCards().set(12, new FoeCard(5));
        game.getAdventureDeck().getCards().set(13, new FoeCard(5));
        game.getAdventureDeck().getCards().set(14, new FoeCard(10));
        game.getAdventureDeck().getCards().set(15, new FoeCard(10));
        game.getAdventureDeck().getCards().set(16, new FoeCard(15));
        game.getAdventureDeck().getCards().set(17, new FoeCard(15));
        game.getAdventureDeck().getCards().set(18, new FoeCard(15));
        game.getAdventureDeck().getCards().set(19, new FoeCard(15));

        //prosperity
        game.getAdventureDeck().getCards().set(20, new WeaponCard("Battle-Axe", 15));
        game.getAdventureDeck().getCards().set(21, new FoeCard(40));
        game.getAdventureDeck().getCards().set(22, new WeaponCard("Dagger", 5));
        game.getAdventureDeck().getCards().set(23, new WeaponCard("Dagger", 5));
        game.getAdventureDeck().getCards().set(24, new FoeCard(25));
        game.getAdventureDeck().getCards().set(25, new FoeCard(25));
        game.getAdventureDeck().getCards().set(26, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(27, new WeaponCard("Sword", 10));

        //queen's favour
        game.getAdventureDeck().getCards().set(28, new FoeCard(30));
        game.getAdventureDeck().getCards().set(29, new FoeCard(25));

        //quest 2
        //stage 1
        game.getAdventureDeck().getCards().set(30,new WeaponCard("Battle-Axe", 15));
        game.getAdventureDeck().getCards().set(31, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(32, new FoeCard(50));
        //stage 2
        game.getAdventureDeck().getCards().set(33, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(34, new WeaponCard("Sword", 10));
        //stage 3
        game.getAdventureDeck().getCards().set(35, new FoeCard(40));
        game.getAdventureDeck().getCards().set(36, new FoeCard(50));
        //sponsor draw
        game.getAdventureDeck().getCards().set(37, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(38, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(39, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(40, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(41, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(42, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(43, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(44, new FoeCard(35));

    }

    public void scenario_4_setup(){
        this.game = new Game(4, si, so);
        game.setUpDecks();
        game.getAdventureDeck().shuffle();
        game.getEventDeck().shuffle();

        Player p1 = game.getPlayer(0);
        Player p2 = game.getPlayer(1);
        Player p3 = game.getPlayer(2);
        Player p4 = game.getPlayer(3);

        p1.addToHand(new FoeCard(50));
        p1.addToHand(new FoeCard(70));
        p1.addToHand(new WeaponCard("Dagger", 5));
        p1.addToHand(new WeaponCard("Dagger", 5));
        p1.addToHand(new WeaponCard("Horse", 10));
        p1.addToHand(new WeaponCard("Horse", 10));
        p1.addToHand(new WeaponCard("Sword", 10));
        p1.addToHand(new WeaponCard("Sword", 10));
        p1.addToHand(new WeaponCard("Battle-Axe", 15));
        p1.addToHand(new WeaponCard("Battle-Axe", 15));
        p1.addToHand(new WeaponCard("Lance", 20));
        p1.addToHand(new WeaponCard("Lance", 20));

        p2.addToHand(new FoeCard(5));
        p2.addToHand(new FoeCard(5));
        p2.addToHand(new FoeCard(10));
        p2.addToHand(new FoeCard(15));
        p2.addToHand(new FoeCard(15));
        p2.addToHand(new FoeCard(20));
        p2.addToHand(new FoeCard(20));
        p2.addToHand(new FoeCard(25));
        p2.addToHand(new FoeCard(30));
        p2.addToHand(new FoeCard(30));
        p2.addToHand(new FoeCard(40));
        p2.addToHand(new WeaponCard("Excalibur", 30));

        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(10));
        p3.addToHand(new FoeCard(15));
        p3.addToHand(new FoeCard(15));
        p3.addToHand(new FoeCard(20));
        p3.addToHand(new FoeCard(20));
        p3.addToHand(new FoeCard(25));
        p3.addToHand(new FoeCard(25));
        p3.addToHand(new FoeCard(30));
        p3.addToHand(new FoeCard(40));
        p3.addToHand(new WeaponCard("Lance", 20));

        p4.addToHand(new FoeCard(5));
        p4.addToHand(new FoeCard(5));
        p4.addToHand(new FoeCard(10));
        p4.addToHand(new FoeCard(15));
        p4.addToHand(new FoeCard(15));
        p4.addToHand(new FoeCard(20));
        p4.addToHand(new FoeCard(20));
        p4.addToHand(new FoeCard(25));
        p4.addToHand(new FoeCard(25));
        p4.addToHand(new FoeCard(30));
        p4.addToHand(new FoeCard(50));
        p4.addToHand(new WeaponCard("Excalibur", 30));

        game.getEventDeck().getCards().set(0, new QuestCard(2));

        //quest 1
        //stage 1
        game.getAdventureDeck().getCards().set(0, new FoeCard(5));
        game.getAdventureDeck().getCards().set(1, new FoeCard(15));
        game.getAdventureDeck().getCards().set(2, new FoeCard(10));
        //sponsor draw
        game.getAdventureDeck().getCards().set(3, new FoeCard(5));
        game.getAdventureDeck().getCards().set(4, new FoeCard(10));
        game.getAdventureDeck().getCards().set(5, new FoeCard(15));
        game.getAdventureDeck().getCards().set(6, new WeaponCard("Dagger", 5));
        game.getAdventureDeck().getCards().set(7, new WeaponCard("Dagger", 5));
        game.getAdventureDeck().getCards().set(8, new WeaponCard("Dagger", 5));
        game.getAdventureDeck().getCards().set(9, new WeaponCard("Dagger", 5));
        game.getAdventureDeck().getCards().set(10, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(11, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(12, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(13, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(14, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(15, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(16, new WeaponCard("Sword", 10));

    }

}
