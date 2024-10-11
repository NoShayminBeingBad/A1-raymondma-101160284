package org.example.Card;

import org.example.Game;

import java.io.PrintWriter;
import java.util.Scanner;

public class QuestCard extends EventCard{

    private int stages;

    public QuestCard(int stages) {
        super("Quest");
        this.stages = stages;
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
        int sponsor = -1;

        for (int i = 0; i < game.playerAmount(); i++){
            game.getPlayer(sponsorCheck).printHand(output);
            output.print(String.format("Player %d, sponsor this quest? (y/n) ", sponsorCheck + 1));
            String decision = input.nextLine();
            output.println("");
            if (decision.charAt(0) == 'y'){
                sponsor = sponsorCheck;
                break;
            }else {
                sponsorCheck = nextPlayer(sponsorCheck);
                continue;
            }
        }

        if (sponsor == -1){
            output.println("No sponsor, quest has been canceled");
            return;
        }

    }

    public int nextPlayer(int i){
        if (i+1 > 3){
            return 0;
        }
        return i+1;
    }
}
