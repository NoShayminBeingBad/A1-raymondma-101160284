package org.example.Card;

import org.example.Game;
import org.example.Player;
import org.example.ServerInput;
import org.example.ServerOutput;

import java.util.ArrayList;
import java.util.Collections;

public class QuestCard extends EventCard{

    private int stages;
    private int currStage;
    private Player sponsor;
    private ArrayList<Player> eligible;
    private ArrayList<AdventureCard>[] cards;

    private ArrayList<AdventureCard>[] attackers;

    public QuestCard(int stages) {
        super("Quest");
        this.stages = stages;
        this.currStage = 0;
        this.cards = new ArrayList[stages];
        this.attackers = new ArrayList[4];
        this.eligible = new ArrayList<>();
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
        ServerInput input = game.getInput();
        ServerOutput output = game.getOutput();

        sponsor = getSponsor(game);

        if (sponsor == null){
            output.println("No sponsor, quest has been canceled"); output.flush();
            return;
        }


        setStages(input, output);

        game.flushScreen();

        setEligible(game);


        for (int i = 0; i < stages; i++){
            output.println(String.format("Eligible Players for stage %d: ", i+1));
            for (Player p : eligible){
                output.println(String.format("Player %d", p.getNumber()+1));
            }
            output.println("Press <enter> to continue");
            output.flush();
            input.nextLine();

            eligible = getParticipating(game, i, eligible);

            game.flushScreen();

            if (eligible.isEmpty()){
                output.println("No eligible players can take this quest"); output.flush();
                sponsorWins(game);
                return;
            }

            output.println("Participating Players: ");
            for (Player p : eligible){
                output.println(String.format("Player %d", p.getNumber()+1));
            }

            output.println("All participating Players draw a card (Press <enter> to continue)");output.flush();
            input.nextLine();
            game.flushScreen();
            for (Player p : eligible){
                game.dealCardToPlayer(p.getNumber());
                p.trimHand(input, output);
                game.flushScreen();
            }

            this.attackers = new ArrayList[4];
            output.println("All participating Players set up their attack");output.flush();
            input.nextLine();
            for (Player p : eligible){
                setAttack(input, output, p);
                game.flushScreen();
            }

            output.println("Cards will now be revealed");output.flush();
            input.nextLine();

            eligible = getPassed(eligible, i);

            output.println("These players have passed this stage!");
            for (Player p : eligible){
                output.println(String.format("Player %d", p.getNumber()+1));
            }
            output.flush();

            if (eligible.isEmpty()){
                output.println("No eligible players can take this quest"); output.flush();
                sponsorWins(game);
                return;
            }

        }

        resolveQuest(game);

    }

    public void setEligible(Game game){
        eligible = new ArrayList<>();
        int playerTurn = game.getTurnCount();

        for (int i = 0; i < game.getAllPlayers().size() - 1; i++){
            if (sponsor.getNumber() == playerTurn) {
                playerTurn = nextPlayer(playerTurn);
            }
            eligible.add(game.getPlayer(playerTurn));
            playerTurn = nextPlayer(playerTurn);
        }
    }

    public Player getSponsor(Game game){
        ServerInput input = game.getInput();
        ServerOutput output = game.getOutput();

        int sponsorCheck = game.getTurnCount();

        for (int i = 0; i < game.playerAmount(); i++){
            output.println(String.format("This Quest has %d stages", stages)); output.flush();
            game.getPlayer(sponsorCheck).printHand(output);
            output.println(String.format("Player %d, sponsor this quest? (y/n) ", sponsorCheck + 1)); output.flush();
            String decision = input.nextLine();

            if (decision.equals("y")){
                return game.getPlayer(sponsorCheck);
            }else {
                game.flushScreen();
                sponsorCheck = nextPlayer(sponsorCheck);
                continue;
            }
        }

        return null;
    }

    public void sponsorWins(Game game){
        int sum = stages + sponsorUsedCards();
        game.getOutput().println(String.format("Sponsor draw %d cards for defending!", sum));
        game.dealCardsToPlayer(sponsor.getNumber(), sum);

        sponsor.trimHand(game.getInput(), game.getOutput());
    }

    public int sponsorUsedCards(){
        int sum = 0;
        for (ArrayList<AdventureCard> arr : cards){
            sum += arr.size();
        }
        return sum;
    }

    public ArrayList<Player> getParticipating(Game game, int stage, ArrayList<Player> eligible){
        ServerInput input = game.getInput();
        ServerOutput output = game.getOutput();

        ArrayList<Player> newEligible = new ArrayList<>();
        for (Player player : eligible){
            game.flushScreen();
            output.println(getStageCensored(stage));
            player.printHand(output);
            output.println(String.format("Player %d, would you like to participate in this stage? (y/n)", player.getNumber()+1)); output.flush();
            String in = input.nextLine();
            if (in.equalsIgnoreCase("y")){
                newEligible.add(player);
            }
        }

        return newEligible;
    }

    public boolean playerParticipating(Game game, int stage, Player player){
        ServerInput input = game.getInput();
        ServerOutput output = game.getOutput();

        game.flushScreen();
        output.println(getStageCensored(stage));
        player.printHand(output);
        output.println(String.format("Player %d, would you like to participate in this stage? (y/n)", player.getNumber()+1)); output.flush();
        String in = input.nextLine();
        if (!in.equalsIgnoreCase("y")){
            eligible.remove(player);
        }else {
            game.dealCardToPlayer(player.getNumber());
        }

        return in.equalsIgnoreCase("y");
    }

    public void playersWin(ArrayList<Player> players){
        for (Player p : players){
            p.giveShield(stages);
        }
    }

    public void resolveQuest(Game game){
        ServerInput input = game.getInput();
        ServerOutput output = game.getOutput();

        if (eligible.isEmpty()){
            output.println("No eligible players can take this quest. You have been defeated!"); output.flush();
        }else {
            output.println(String.format("Congrats for winning the quest!\nYou will receive %d shields", stages));
            playersWin(eligible);
            game.printShields();
        }

        game.flushScreen();
        input.nextLine();

        game.flushScreen();
        sponsorWins(game);
        game.flushScreen();
    }

    public void setStages(ServerInput input, ServerOutput output) {

        output.println(String.format("Player %d, please set the stages for this quest", sponsor.getNumber()+1));

        for (int i = 0; i < stages; i++){
            setStage(input, output, i);
        }

        output.println("The Quest has been set. (press <return> to clear screen)"); output.flush();
        input.nextLine();
    }

    public void setStage(ServerInput input, ServerOutput output, int stage){
        cards[stage] = new ArrayList<>();
        String foe = "";
        int in = -1;

        output.println(String.format("Stage %d", stage + 1));

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
        cards[stage].add(sponsor.discardCard(in));
        String weapon = "";
        in = -1;
        output.println(getStage(stage));

        while(true){
            output.println("Please select a Weapon to boost your Foe Card (enter 'Quit' to exit):");
            sponsor.printHand(output); output.flush();
            weapon = input.nextLine();

            try {
                in = Integer.parseInt(weapon);
                AdventureCard ac = sponsor.getCard(in);
                if (ac instanceof WeaponCard){
                    if(!repeatWeapon(cards[stage], ac)){
                        cards[stage].add(sponsor.discardCard(in));
                    }else {
                        output.println("That Weapon is already used");
                    }
                }else {
                    output.println("Card selected is not an Weapon Card");
                }
            } catch (NumberFormatException e) {
                if (weapon.equalsIgnoreCase("quit")){
                    if (getStageValue(stage) <= getStageValue(stage-1)){
                        output.println("Insufficient value for this stage");
                        continue;
                    }
                    break;
                }else {
                    output.println("That is not a valid input. Please enter an index or 'Quit'");
                }
            }
        }
        output.println(getStage(stage));
    }

    public void setAttack(ServerInput input, ServerOutput output, Player player){
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
                    /*if (attackers[playerNum].isEmpty()) {
                        output.println("Your attack cannot be empty");
                        continue;
                    }*/
                    break;
                } else {
                    output.println("That is not a valid input. Please enter an index or 'Quit'");
                }
            }
            output.println(getAttack(playerNum));
        }
        output.println(getAttack(playerNum)); output.flush();
    }

    public ArrayList<Player> getPassed(ArrayList<Player> old, int stage){
        ArrayList<Player> newEligible = new ArrayList();
        for (Player p : old){
            if (getAttackValue(p.getNumber()) >= getStageValue(stage)){
                newEligible.add(p);
            }
        }
        this.currStage++;
        return newEligible;
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
        String s = "";
        Collections.sort(cards[stageNum]);
        int sum = 0;

        for (AdventureCard c : cards[stageNum]){
            s = s.concat(String.format("%s ", c.toString()));
            sum += c.getBP();
        }

        s = String.format("Stage %d (%d BP): ", stageNum + 1, sum).concat(s);

        return s;
    }

    public String getStageCensored(int stageNum){
        String s = String.format("Stage %d (?? BP): ", stageNum + 1);

        for (AdventureCard c : cards[stageNum]){
            s = s.concat("[] ");
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
        String s = "";
        Collections.sort(attackers[player]);
        int sum = 0;

        for (AdventureCard c : attackers[player]){
            s = s.concat(String.format("%s ", c.toString()));
            sum += c.getBP();
        }

        s = String.format("Attack from Player %d (%d BP): ", player + 1, sum).concat(s);

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

    public Player getSponsorPlayer(){
        return this.sponsor;
    }

    public ArrayList<Player> getEligible(){
        return eligible;
    }

    public void setEligible(ArrayList<Player> eligible){
        this.eligible = eligible;
    }

    public int getCurrStage(){
        return this.currStage;
    }
}
