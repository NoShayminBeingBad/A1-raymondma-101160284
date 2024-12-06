package org.example.Card;

import org.example.Game;

public class ProsperityCard extends EventCard{

    public ProsperityCard() {
        super("Prosperity");
    }

    @Override
    public void Event(Game game) {
        game.getOutput().println("Everyone draws 2 cards!"); game.getOutput().flush();
        int p = game.getTurnCount();

        game.getInput().nextLine();

        for (int i = 0; i < game.playerAmount(); i++){
            game.dealCardToPlayer(p);
            game.dealCardToPlayer(p);
            game.getPlayer(p).printHand(game.getOutput());
            game.getPlayer(p).trimHand(game.getInput(), game.getOutput());
            game.getOutput().println("It is now the next player's turn to draw cards (press <return> to clear screen)"); game.getOutput().flush();
            game.getInput().nextLine();
            p = nextPlayer(p);
            game.flushScreen();
        }
    }

    public int nextPlayer(int i){
        if (i+1 > 3){
            return 0;
        }
        return i+1;
    }

}
