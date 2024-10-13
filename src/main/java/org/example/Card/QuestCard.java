package org.example.Card;

import org.example.Game;
import org.example.Player;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuestCard extends EventCard{

    private int stages;
    private Player sponsor;
    private ArrayList<AdventureCard>[] cards;

    private ArrayList<AdventureCard>[] attackers;

    public QuestCard(int stages) {
        super("Quest");
        this.stages = stages;
        this.cards = new ArrayList[stages];
        this.attackers = new ArrayList[4];
        this.sponsor = null;
    }

    public int getStageAmount(){
        return stages;
    }

    public String toString(){
        return "Q".concat(Integer.toString(stages));
    }

    @Override
    public void Event(Game game) {
        Scanner input = game.getInput();
        PrintWriter output = game.getOutput();

        output.println(String.format("Quest Card has been drawn!\nThis Quest has %d stages", stages));


        int sponsorCheck = game.getTurnCount();

        for (int i = 0; i < game.playerAmount(); i++){
            game.getPlayer(sponsorCheck).printHand(output);
            output.print(String.format("Player %d, sponsor this quest? (y/n) ", sponsorCheck + 1)); output.flush();
            String decision = input.nextLine();
            output.println("");
            if (decision.charAt(0) == 'y'){
                sponsor = game.getPlayer(sponsorCheck);
                break;
            }else {
                sponsorCheck = nextPlayer(sponsorCheck);
                continue;
            }
        }

        if (sponsor == null){
            output.println("No sponsor, quest has been canceled"); output.flush();
            return;
        }

    }

    public void setStages(Scanner input, PrintWriter output) {

        output.println(String.format("Player %d, please set the stages for this quest", sponsor.getNumber()+1));

        for (int i = 0; i < stages; i++){
            cards[i] = new ArrayList<>();
            String foe = "";
            int in = -1;

            output.println(String.format("Stage %d", i + 1));

            while (true){
                output.println("Please select 1 Foe:");
                sponsor.printHand(output); output.flush();
                foe = input.nextLine();

                try {
                    in = Integer.parseInt(foe);
                    if (sponsor.getCard(in) instanceof FoeCard){
                        break;
                    }else {
                        output.println("Card selected is not an Foe Card");
                    }
                } catch (NumberFormatException e) {
                    if (foe.equalsIgnoreCase("quit")){
                        output.println("A stage cannot be empty");
                    }else {
                        output.println("That is not a valid input. Please enter an index or 'Quit'");
                    }
                }
            }
            cards[i].add(sponsor.discardCard(in));

            String weapon = "";
            in = -1;

            while(true){
                output.println("Please select a Weapon to boost your Foe Card (enter 'Quit' to exit):");
                sponsor.printHand(output); output.flush();
                weapon = input.nextLine();

                try {
                    in = Integer.parseInt(weapon);
                    AdventureCard ac = sponsor.getCard(in);
                    if (ac instanceof WeaponCard){
                        if(!repeatWeapon(cards[i], ac)){
                            cards[i].add(sponsor.discardCard(in));
                        }else {
                            output.println("That Weapon is already used");
                        }
                    }else {
                        output.println("Card selected is not an Weapon Card");
                    }
                } catch (NumberFormatException e) {
                    if (weapon.equalsIgnoreCase("quit")){
                        if (getStageValue(i) <= getStageValue(i-1)){
                            output.println("Insufficient value for this stage");
                            continue;
                        }
                        break;
                    }else {
                        output.println("That is not a valid input. Please enter an index or 'Quit'");
                    }
                }
            }
            output.println(getStage(i));
        }

        output.println("The Quest has been set. (press <return> to clear screen)"); output.flush();
        input.nextLine();
    }

    public void setAttack(Scanner input, PrintWriter output, Player player){
        String weapon = "";
        int in = -1;
        int playerNum = player.getNumber();
        attackers[playerNum] = new ArrayList<>();

        output.println(String.format("Player %d, please set up your forces for the quest", playerNum + 1));

        while(true) {
            output.println("Please select a Weapon to boost your forces (enter 'Quit' to exit):");
            player.printHand(output);
            output.flush();
            weapon = input.nextLine();

            try {
                in = Integer.parseInt(weapon);
                AdventureCard ac = player.getCard(in);
                if (ac instanceof WeaponCard) {
                    if (!repeatWeapon(attackers[playerNum], ac)) {
                        attackers[playerNum].add(player.discardCard(in));
                    }else {
                        output.println("That Weapon is already used");
                    }
                } else {
                    output.println("Card selected is not an Weapon Card");
                }
            } catch (NumberFormatException e) {
                if (weapon.equalsIgnoreCase("quit")) {
                    if (attackers[playerNum].isEmpty()) {
                        output.println("Your attack cannot be empty");
                        continue;
                    }
                    break;
                } else {
                    output.println("That is not a valid input. Please enter an index or 'Quit'");
                }
            }
            output.println(getAttack(playerNum));
        }
        output.println(getAttack(playerNum)); output.flush();
    }

    public boolean repeatWeapon(ArrayList<AdventureCard> set, AdventureCard card){

        for (AdventureCard ac : set){
            if (ac.toString().equals(card.toString())){
                return true;
            }
        }

        return false;
    }

    public int getStageValue(int stageNum){
        if (stageNum < 0){
            return 0;
        }

        int v = 0;

        for (int i = 0; i < cards[stageNum].size(); i++){
            v += cards[stageNum].get(i).getBP();
        }

        return v;
    }

    public String getStage(int stageNum){
        String s = String.format("Stage %d: ", stageNum + 1);
        Collections.sort(cards[stageNum]);

        for (AdventureCard c : cards[stageNum]){
            s = s.concat(String.format("%s ", c.toString()));
        }

        return s;
    }

    public int getAttackValue(int player){
        int v = 0;

        for (int i = 0; i < attackers[player].size(); i++){
            v += attackers[player].get(i).getBP();
        }

        return v;
    }

    public String getAttack(int player){
        String s = String.format("Attack from Player %d: ", player + 1);
        Collections.sort(attackers[player]);

        for (AdventureCard c : attackers[player]){
            s = s.concat(String.format("%s ", c.toString()));
        }

        return s;
    }

    public int nextPlayer(int i){
        if (i+1 > 3){
            return 0;
        }
        return i+1;
    }

    public void setSponsor(Player p){
        this.sponsor = p;
    }
}
