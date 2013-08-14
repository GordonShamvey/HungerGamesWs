package algoritmmicgames.services.hungergames;

import algoritmmicgames.abstraction.Game;
import algoritmmicgames.services.Logger;
import hungergames.api.Huntable;
import hungergames.api.Moves;

import java.util.*;

public class HungerGame implements Game {

    public static HungerGame getInstance() {
        return new HungerGame();
    }

    public void setPlayers(Map<String, Object> players) {
        this.players = players;
    }

    public void setMaxRounds(long maxRounds) {
        this.maxRounds = maxRounds;
    }

    public String play() {
        logger.clear();
        initParticipiants();

        logger.addToLog("Participants count: " + participants.size());
        logger.addToLog("Max rounds: " + maxRounds);

        while(participants.size() > 1 && currentRound < maxRounds)
        {
            playRound();

            logger.addToLog("Round " + currentRound);
            for(HuntableProfile player : participants)
            {
                logger.addToLog(player.getJarName() + ": " + player.getCurrentFood() + " food");
            }

            checkPlayers();
        }

        logger.addToLog("Game over after round " + currentRound);

        return logger.getLog();
    }

    private HungerGame() {
        players = new HashMap<>();
        participants = new ArrayList<>();
    }

    private void initParticipiants() {
        participants = new ArrayList<>();
        currentRound = 0;

        for(String playName : players.keySet()) {
            participants.add(new HuntableProfile(playName, (Huntable)players.get(playName)));
        }

        for(HuntableProfile player : participants)
        {
            player.setCurrentFood(300 * (participants.size() - 1));
        }
        //
    }

    private void playRound() {
        currentRound++;

        //Generate new m
        Random r = new Random();
        currentM = r.nextInt(participants.size() * (participants.size() - 1) - 1) + 1;

        //Shuffle participants
        Collections.shuffle(participants);

        List<List<Moves>> players_decisions = new ArrayList<>();

        //Each player makes his decision

        for(HuntableProfile hp : participants)
        {
            ArrayList<Double> reputations = getPlayersReputations(hp);

            players_decisions.add(hp.getPlayer().hunt_choices(currentRound, hp.getCurrentFood(), hp.getReputation() , currentM, reputations));
        }

        //Update players food and reputation
        int hunts_count = updatePlayerProfile(players_decisions);

        //Check extra food
        if(hunts_count >= currentM) for (HuntableProfile hp : participants) {
            hp.updateFood(2 * (participants.size() - 1));
        }

    }

    private void checkPlayers() {
        for (Iterator<HuntableProfile> it = participants.iterator(); it.hasNext(); ) {

            HuntableProfile hp = it.next();
            if (hp.getCurrentFood() <= 0) {
                logger.addToLog(hp.getJarName() + " has lost in round " + this.currentRound);
                it.remove();
            }

        }
    }

    // I dont know how to implement it other way, due to rules and interface
    private int updatePlayerProfile(List<List<Moves>> playersDecisions) {
        int hunts_num = 0;
        for (int i = 0; i < participants.size(); i++) {

            List<Moves> player_moves = playersDecisions.get(i);

            int k = 0;
            for (int j = 0; j < i; j++)
            {
                List<Moves> opp_moves = playersDecisions.get(j);
                participants.get(i).updateReputation(player_moves.get(k));

                if(player_moves.get(k) == Moves.Hunt)
                {
                    hunts_num++;
                    if(opp_moves.get(i - 1) == Moves.Hunt)
                    {
                        participants.get(i).updateFood(0);
                    }
                    else
                    {
                        participants.get(i).updateFood(-3);
                    }
                }
                else
                {
                    if(opp_moves.get(i - 1) == Moves.Hunt)
                    {
                        participants.get(i).updateFood(1);
                    }
                    else
                    {
                        participants.get(i).updateFood(-2);
                    }
                }
                k++;
            }
            for (int j = i + 1; j < participants.size(); j++)
            {
                List<Moves> opp_moves = playersDecisions.get(j);
                participants.get(i).updateReputation(player_moves.get(k));

                if(player_moves.get(k) == Moves.Hunt)
                {
                    hunts_num++;
                    if(opp_moves.get(i) == Moves.Hunt)
                    {
                        participants.get(i).updateFood(0);
                    }
                    else
                    {
                        participants.get(i).updateFood(-3);
                    }
                }
                else
                {
                    if(opp_moves.get(i) == Moves.Hunt)
                    {
                        participants.get(i).updateFood(1);
                    }
                    else
                    {
                        participants.get(i).updateFood(-2);
                    }
                }
                k++;
            }
        }

        return hunts_num;
    }

    private ArrayList<Double> getPlayersReputations(
            HuntableProfile current_player) {

        ArrayList<Double> reputations = new ArrayList<Double>();
        for (HuntableProfile hp : participants) {
            if (hp != current_player)
                reputations.add(hp.getReputation());
        }

        return reputations;
    }

    public static void main(String[] args) {

        HungerGame game = new HungerGame();
        game.play();
        System.out.println("Game has finished after round " + game.currentRound);
    }

    private Map<String, Object> players;
    private List<HuntableProfile> participants = null;
    private Logger logger = new Logger();
    private long currentRound = 0;
    private long currentM = 0;
    private long maxRounds = 10000;

}
