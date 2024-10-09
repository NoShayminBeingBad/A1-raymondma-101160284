package org.example.Card;

import org.example.Game;

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

    }
}
