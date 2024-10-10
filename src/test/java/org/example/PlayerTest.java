package org.example;

import org.example.Card.AdventureCard;
import org.example.Card.FoeCard;
import org.example.Card.WeaponCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("Prints player hand in proper order")
    public void RESP_5_TEST1(){
        StringWriter output = new StringWriter();

        Player player = new Player(0);

        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(70));
        player.addToHand(new FoeCard(10));
        player.addToHand(new WeaponCard("Dagger", 5));
        player.addToHand(new WeaponCard("Excalibur", 30));
        player.addToHand(new FoeCard(10));
        player.addToHand(new FoeCard(20));
        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(15));
        player.addToHand(new WeaponCard("Sword", 10));
        player.addToHand(new WeaponCard("Horse", 10));
        player.addToHand(new WeaponCard("Sword", 10));

        player.printHand(new PrintWriter(output));

        Assertions.assertTrue(output.toString().contains("Player 1's Hand: F5 F5 F10 F10 F15 F20 F70 D5 S10 S10 H10 E30"));

    }

    @Test
    @DisplayName("Player has 13 cards and needs to discard 1")
    public void RESP_7_TEST1(){
        String input = "3\n";
        StringWriter output = new StringWriter();

        Player player = new Player(0);

        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(70));
        player.addToHand(new FoeCard(10));
        player.addToHand(new FoeCard(10));
        player.addToHand(new FoeCard(20));
        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(15));
        player.addToHand(new WeaponCard("Dagger", 5));
        player.addToHand(new WeaponCard("Excalibur", 30));
        player.addToHand(new WeaponCard("Sword", 10));
        player.addToHand(new WeaponCard("Horse", 10));
        player.addToHand(new WeaponCard("Sword", 10));
        player.addToHand(new WeaponCard("Sword", 10));

        player.trimHand(new Scanner(input), new PrintWriter(output));

        Assertions.assertTrue(output.toString().contains("Player 1's Hand: F5 F5 F10 F15 F20 F70 D5 S10 S10 S10 H10 E30"));
    }

    @Test
    @DisplayName("Player has 14 cards and needs to discard 1")
    public void RESP_7_TEST2(){
        String input = "3\n10\n";
        StringWriter output = new StringWriter();

        Player player = new Player(0);

        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(70));
        player.addToHand(new FoeCard(10));
        player.addToHand(new FoeCard(10));
        player.addToHand(new FoeCard(20));
        player.addToHand(new FoeCard(5));
        player.addToHand(new FoeCard(15));
        player.addToHand(new WeaponCard("Dagger", 5));
        player.addToHand(new WeaponCard("Excalibur", 30));
        player.addToHand(new WeaponCard("Sword", 10));
        player.addToHand(new WeaponCard("Horse", 10));
        player.addToHand(new FoeCard(20));
        player.addToHand(new WeaponCard("Sword", 10));
        player.addToHand(new WeaponCard("Sword", 10));

        player.trimHand(new Scanner(input), new PrintWriter(output));

        Assertions.assertTrue(output.toString().contains("Player 1's Hand: F5 F5 F10 F15 F20 F70 D5 S10 S10 S10 F20 E30"));
    }

}