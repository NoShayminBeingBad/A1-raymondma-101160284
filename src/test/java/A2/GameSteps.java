package A2;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Card.*;
import org.example.Game;
import org.example.Player;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GameSteps {

    private Game game;

    @Given("a rigged game for A1_scenario")
    public void A1_scenario(){
            game = new Game(4);
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

    @Given("a rigged game")
    public void rigged_game(){
        game = new Game(4);
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
        p1.addToHand(new FoeCard(25));
        p1.addToHand(new WeaponCard("Dagger", 5));
        p1.addToHand(new WeaponCard("Sword", 10));
        p1.addToHand(new WeaponCard("Sword", 10));
        p1.addToHand(new WeaponCard("Horse", 10));
        p1.addToHand(new WeaponCard("Battle-Axe", 15));
        p1.addToHand(new WeaponCard("Battle-Axe", 15));
        p1.addToHand(new WeaponCard("Lance", 20));

        p2.addToHand(new FoeCard(5));
        p2.addToHand(new FoeCard(5));
        p2.addToHand(new FoeCard(15));
        p2.addToHand(new FoeCard(15));
        p2.addToHand(new FoeCard(25));
        p2.addToHand(new WeaponCard("Dagger", 5));
        p2.addToHand(new WeaponCard("Sword", 10));
        p2.addToHand(new WeaponCard("Sword", 10));
        p2.addToHand(new WeaponCard("Horse", 10));
        p2.addToHand(new WeaponCard("Battle-Axe", 15));
        p2.addToHand(new WeaponCard("Battle-Axe", 15));
        p2.addToHand(new WeaponCard("Lance", 20));

        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(5));
        p3.addToHand(new FoeCard(15));
        p3.addToHand(new FoeCard(15));
        p3.addToHand(new FoeCard(25));
        p3.addToHand(new WeaponCard("Dagger", 5));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Sword", 10));
        p3.addToHand(new WeaponCard("Horse", 10));
        p3.addToHand(new WeaponCard("Battle-Axe", 15));
        p3.addToHand(new WeaponCard("Battle-Axe", 15));
        p3.addToHand(new WeaponCard("Lance", 20));

        p4.addToHand(new FoeCard(5));
        p4.addToHand(new FoeCard(5));
        p4.addToHand(new FoeCard(15));
        p4.addToHand(new FoeCard(15));
        p4.addToHand(new FoeCard(25));
        p4.addToHand(new WeaponCard("Dagger", 5));
        p4.addToHand(new WeaponCard("Sword", 10));
        p4.addToHand(new WeaponCard("Sword", 10));
        p4.addToHand(new WeaponCard("Horse", 10));
        p4.addToHand(new WeaponCard("Battle-Axe", 15));
        p4.addToHand(new WeaponCard("Battle-Axe", 15));
        p4.addToHand(new WeaponCard("Lance", 20));

        //stage 1
        game.getAdventureDeck().getCards().set(0, new FoeCard(30));
        game.getAdventureDeck().getCards().set(1, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(2, new WeaponCard("Battle-Axe", 15));
        game.getAdventureDeck().getCards().set(3, new FoeCard(10));
        game.getAdventureDeck().getCards().set(4, new WeaponCard("Lance", 20));
        game.getAdventureDeck().getCards().set(5, new WeaponCard("Lance", 20));
        game.getAdventureDeck().getCards().set(6, new WeaponCard("Battle-Axe", 15));
        game.getAdventureDeck().getCards().set(7, new WeaponCard("Sword", 10));
        game.getAdventureDeck().getCards().set(8, new FoeCard(30));
        game.getAdventureDeck().getCards().set(9, new WeaponCard("Lance", 20));
        game.getAdventureDeck().getCards().set(10, new FoeCard(5));
        game.getAdventureDeck().getCards().set(11, new FoeCard(15));
        game.getAdventureDeck().getCards().set(12, new FoeCard(5));
        game.getAdventureDeck().getCards().set(13, new FoeCard(15));
        game.getAdventureDeck().getCards().set(14, new FoeCard(5));
        game.getAdventureDeck().getCards().set(15, new WeaponCard("Lance", 20));
        game.getAdventureDeck().getCards().set(16, new FoeCard(5));
        game.getAdventureDeck().getCards().set(17, new FoeCard(5));
        game.getAdventureDeck().getCards().set(18, new FoeCard(5));
        game.getAdventureDeck().getCards().set(19, new FoeCard(5));
        game.getAdventureDeck().getCards().set(20, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(21, new FoeCard(5));
        game.getAdventureDeck().getCards().set(22, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(23, new FoeCard(5));
        game.getAdventureDeck().getCards().set(24, new FoeCard(20));
        game.getAdventureDeck().getCards().set(25, new WeaponCard("Horse", 10));
        game.getAdventureDeck().getCards().set(26, new FoeCard(15));
        game.getAdventureDeck().getCards().set(27, new FoeCard(5));
        game.getAdventureDeck().getCards().set(28, new WeaponCard("Lance", 20));
    }

    @Given("a new game")
    public void a_new_game(){
        game = new Game(4);
        game.setUpGame();
    }

    @When("Player {int} draws a Plague Card")
    public void player_draws_plague_card(int player){
        player--;
        game.setTurnCount(player);
        game.getEventDeck().getCards().set(0, new PlagueCard());
        game.drawEventCard();
        game.playEventCard();
    }

    @When("Player {int} draws a Prosperity Card")
    public void player_draws_prosperity_card(int player){
        player--;
        game.setTurnCount(player);
        game.getEventDeck().getCards().set(0, new ProsperityCard());

        String input = "\n";
        int turn = game.getTurnCount();
        for (int i = 0; i < game.getAllPlayers().size(); i++){
            if (game.getPlayer(turn).getHand().size()+2 > 12){
                for (int j = 0; j < (game.getPlayer(turn).getHand().size()+2) - 12; j++){
                    input += String.format("0\n");
                }
            }
            input += "\n";
            turn = game.nextPlayer(turn);
        }

        game.setInput(new Scanner(input));

        game.drawEventCard();
        game.playEventCard();
    }

    @When("Player {int} draws a Queen's favor Card")
    public void player_draws_queens_favor_card(int player){
        player--;
        game.setTurnCount(player);
        game.getEventDeck().getCards().set(0, new QueensFavorCard());

        String input = "";
        if (game.getPlayer(player).getHand().size()+2 > 12){
            for (int j = 0; j < (game.getPlayer(player).getHand().size()+2) - 12; j++){
                input += "0\n";
            }
        }

        game.setInput(new Scanner(input));

        game.drawEventCard();
        game.playEventCard();
    }

    @When("Player {int} draws a {int} stage quest")
    public void player_draws_quest_card(int player, int stages){
        player--; //account for the player 1 is index 0
        game.setTurnCount(player);
        game.getEventDeck().getCards().set(0, new QuestCard(stages));
        game.drawEventCard();
    }

    @Then("Player {int} sponsors the quest")
    public void player_sponsors_quest_card(int sponsor){
        sponsor--; //account for the player 1 is index 0
        QuestCard qc = (QuestCard) game.getEventCard();
        String input = "";
        int currentPlayer = game.getTurnCount();

        for (int i = currentPlayer; true; i = qc.nextPlayer(i)){
            if (i == sponsor){
                input += "y\n";
                break;
            }else {
                input += "n\n";
            }
        }

        game.setInput(new Scanner(input));
        qc.setSponsor(qc.getSponsor(game));
    }

    @Then("Sponsor sets {string} for stage {int}")
    public void set_sponsor_for_stage(String cardsString, int stage){
        stage--;
        String[] cards = cardsString.split(", ");
        QuestCard qc = (QuestCard) game.getEventCard();
        Player sponsor = qc.getSponsorPlayer();
        ArrayList<AdventureCard> handCopy = (ArrayList<AdventureCard>) sponsor.getHand().clone();
        String input = "";

        for (int i = 0; i < cards.length; i++){
            int index = searchHand(handCopy, cards[i]);
            input += String.format("%d\n", index);
            handCopy.remove(index);
        }

        input += "quit\n";

        qc.setStage(new Scanner(input), game.getOutput(), stage);

        if (stage+1 == qc.getStageAmount()){
            qc.setEligible(game);
        }
    }

    @Then("Player {int} sets {string} for attack")
    public void player_set_attack(int player, String cardsString){
        player--;
        String[] cards = cardsString.split(", ");
        QuestCard qc = (QuestCard) game.getEventCard();
        Player sponsor = qc.getSponsorPlayer();
        ArrayList<AdventureCard> handCopy = (ArrayList<AdventureCard>) game.getPlayer(player).getHand().clone();
        String input = "";

        for (int i = 0; i < cards.length; i++){
            int index = searchHand(handCopy, cards[i]);
            input += String.format("%d\n", index);
            handCopy.remove(index);
        }

        input += "quit\n";

        qc.setAttack(new Scanner(input), game.getOutput(), game.getPlayer(player));
    }

    @Then("Player {int} trims hand and discards {string}")
    public void player_trims_hand(int player, String cardsString){
        player--;
        String[] cards = cardsString.split(", ");
        assertEquals(12+cards.length, game.getPlayer(player).getHand().size());

        QuestCard qc = (QuestCard) game.getEventCard();
        ArrayList<AdventureCard> handCopy = (ArrayList<AdventureCard>) game.getPlayer(player).getHand().clone();
        String input = "";

        for (int i = 0; i < cards.length; i++){
            int index = searchHand(handCopy, cards[i]);
            input += String.format("%d\n", index);
            handCopy.remove(index);
        }

        game.getPlayer(player).trimHand(new Scanner(input), game.getOutput());
    }

    public int searchHand(ArrayList<AdventureCard>hand, String title){
        for (int i = 0; i < hand.size(); i++){
            if (hand.get(i).getTitle().equals(title)){
                return i;
            }
        }

        return 0;
    }

    @Then("Sponsor sets stage {int}")
    public void set_sponsor_stage(int stage){
        stage--;
        QuestCard qc = (QuestCard) game.getEventCard();
        String input = "0\nquit\n";

        qc.setStage(new Scanner(input), game.getOutput(), stage);

        if (stage+1 == qc.getStageAmount()){
            qc.setEligible(game);
        }
    }

    @Then("Player {int} does participate in the stage")
    public void player_participates(int player){
        player--;
        QuestCard qc = (QuestCard) game.getEventCard();

        assertTrue(qc.getEligible().contains(game.getPlayer(player)));
        game.dealCardToPlayer(player);
    }

    @Then("Player {int} does not participate in the stage")
    public void player_not_participates(int player){
        player--;
        QuestCard qc = (QuestCard) game.getEventCard();

        qc.getEligible().remove(game.getPlayer(player));
    }


    @Then("all eligible players participate")
    public void all_eligible_players_play(){
        QuestCard qc = (QuestCard) game.getEventCard();
        String input = "";

        for (int i = 0; i < qc.getEligible().size(); i++){
           input += "y\n";
        }

        game.setInput(new Scanner(input));
        qc.setEligible(qc.getParticipating(game, qc.getCurrStage(), qc.getEligible()));
    }

    @Then("Player {int} plays to lose")
    public void player_loses_current_stage(int player){
        player--;
        QuestCard qc = (QuestCard) game.getEventCard();

        //player is a eligible and participating
        assertTrue(qc.getEligible().contains(game.getPlayer(player)));

        String input = "quit\n";
        game.setInput(new Scanner(input));
        qc.setAttack(game.getInput(), game.getOutput(), game.getPlayer(player));
    }

    @Then("Player {int} attack has a value of {int}")
    public void player_attack_value(int player, int value){
        player--;
        QuestCard qc = (QuestCard) game.getEventCard();

        assertEquals(value, qc.getAttackValue(player));
    }

    @Then("Player {int} has {int} cards")
    public void player_has_cards(int player, int amount){
        player--;
        assertEquals(amount, game.getPlayer(player).getHand().size());
    }

    @Then("all players lose the stage")
    public void all_players_lose(){
        QuestCard qc = (QuestCard) game.getEventCard();
        String input = "";

        for (int i = 0; i < qc.getEligible().size(); i++){
            input += "quit\n";
        }

        game.setInput(new Scanner(input));

        for (Player p : qc.getEligible()){
            qc.setAttack(game.getInput(), game.getOutput(), p);
        }
    }

    @Then("the stage resolves")
    public void curr_stage_resolves(){
        QuestCard qc = (QuestCard) game.getEventCard();
        qc.setEligible(qc.getPassed(qc.getEligible(), qc.getCurrStage()));
    }

    @Then("Player {int} passed the stage")
    public void player_passed(int player){
        player--;
        QuestCard qc = (QuestCard) game.getEventCard();
        assertTrue(qc.getEligible().contains(game.getPlayer(player)));
    }

    @Then("Player {int} lost the stage")
    public void player_lost_stage(int player){
        player--;
        QuestCard qc = (QuestCard) game.getEventCard();
        assertFalse(qc.getEligible().contains(game.getPlayer(player)));
    }


    @Then("the quest resolves")
    public void quest_resolves(){
        QuestCard qc = (QuestCard) game.getEventCard();

        String input = "\n";

        for (int i = 0; i < (qc.getSponsorPlayer().getHand().size() + (qc.getStageAmount() + qc.sponsorUsedCards())) - 12; i++){
            input += "0\n";
        }

        game.setInput(new Scanner(input));
        qc.resolveQuest(game);
    }

    @Then("the quest has {int} winners")
    public void quest_no_winners(int winners){
        QuestCard qc = (QuestCard) game.getEventCard();
        assertEquals(winners, qc.getEligible().size());
    }

    @Then("Player {int} won the quest")
    public void player_won(int player){
        player--;
        QuestCard qc = (QuestCard) game.getEventCard();
        assertTrue(qc.getEligible().contains(game.getPlayer(player)));
    }

    @Then("Player {int} lost the quest")
    public void player_lost(int player){
        player--;
        QuestCard qc = (QuestCard) game.getEventCard();
        assertFalse(qc.getEligible().contains(game.getPlayer(player)));
    }

    @Then("Player {int} has {int} shields")
    public void player_shields(int player, int shield){
        player--;
        assertEquals(shield, game.getPlayer(player).getShieldNum());
    }

    @Then("Player {int} is a winner")
    public void player_winner(int player){
        player--;
        assertTrue(game.outputWinner());
        assertTrue(game.getPlayer(player).getShieldNum() >= 7);
    }

    @Then("Player {int} has {string} hand")
    public void player_hand(int player, String cards){
        player--;
        String hand = String.format("Player %d's Hand: %s ", player+1, cards);
        assertEquals(hand, game.getPlayer(player).toString());
    }

    @Then("Player {int} has {int} cards in their hand")
    public void player_hand_size(int player, int amount) {
        player--;
        assertEquals(amount, game.getPlayer(player).getHand().size());
    }

    @Then("Player {int} has new cards")
    public void player_has_new_cards(int player){
        assertEquals(12, game.getPlayer(player-1).getHand().size());
        //This is the starting hand for all players for a rigged deck
        String hand = String.format("Player %d's Hand: F5 F5 F15 F15 F25 D5 S10 S10 H10 B15 B15 L20  ", player);
        assertNotEquals(hand, game.getPlayer(player-1).toString());
    }

    @Then("player hands")
    public void playerHands(){
        for (Player p : game.getAllPlayers()){
            System.out.println(p);
        }
    }
}
