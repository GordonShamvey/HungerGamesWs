package hungergames.services;

import hungergames.api.Huntable;
import hungergames.api.Moves;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alf
 * Date: 10.08.13
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
public class HungerGame {

    private Map<String, Huntable> players;
    private List<HuntableProfile> participants = null;
    private Logger logger = new Logger();
    private long currentRound = 0;
    private long currentM = 0;
    private long maxRounds = 10000;

    public static HungerGame getInstance() {
        return new HungerGame();
    }

    public void setPlayers(Map<String, Huntable> players) {
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
        participants = new ArrayList<HuntableProfile>();
    }

    private void initParticipiants() {
        participants = new ArrayList<HuntableProfile>();
        currentRound = 0;

        for(String playName : players.keySet()) {
            participants.add(new HuntableProfile(playName, players.get(playName)));
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

        List<List<Moves>> players_decisions = new ArrayList<List<Moves>>();

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
            hp.update_food(2 * (participants.size() - 1));
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

    private int updatePlayerProfile(List<List<Moves>> playersDecisions) {
        int hunts_num = 0;
        for (int i = 0; i < participants.size(); i++) {

            List<Moves> player_moves = playersDecisions.get(i);

            int k = 0;
            for (int j = 0; j < i; j++)
            {
                List<Moves> opp_moves = playersDecisions.get(j);
                participants.get(i).update_reputation(player_moves.get(k));

                if(player_moves.get(k) == Moves.Hunt)
                {
                    hunts_num++;
                    if(opp_moves.get(i - 1) == Moves.Hunt)
                    {
                        participants.get(i).update_food(0);
                    }
                    else
                    {
                        participants.get(i).update_food(-3);
                    }
                }
                else
                {
                    if(opp_moves.get(i - 1) == Moves.Hunt)
                    {
                        participants.get(i).update_food(1);
                    }
                    else
                    {
                        participants.get(i).update_food(-2);
                    }
                }
                k++;
            }
            for (int j = i + 1; j < participants.size(); j++)
            {
                List<Moves> opp_moves = playersDecisions.get(j);
                participants.get(i).update_reputation(player_moves.get(k));

                if(player_moves.get(k) == Moves.Hunt)
                {
                    hunts_num++;
                    if(opp_moves.get(i) == Moves.Hunt)
                    {
                        participants.get(i).update_food(0);
                    }
                    else
                    {
                        participants.get(i).update_food(-3);
                    }
                }
                else
                {
                    if(opp_moves.get(i) == Moves.Hunt)
                    {
                        participants.get(i).update_food(1);
                    }
                    else
                    {
                        participants.get(i).update_food(-2);
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

}
