package hungergames.services;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: CSD
 * Date: 07.08.13
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
public class HungerGameEngine {

    private String log = "";
    private String playersFolder = "";
    private GameProperties gameProps;
    private GamePlayers gamePlayers;
    private HungerGame game;

    public HungerGameEngine() {
        log = "";
        gameProps = new GameProperties();
        gamePlayers = new GamePlayers();
        game = HungerGame.getInstance();
    }

    public String getPlayersFolder() {
        return playersFolder;
    }

    public void setPlayersFolder(String playersFolder) {
        this.playersFolder = playersFolder;
    }

    public GamePlayers getGamePlayers() {
        return gamePlayers;
    }

    public void setGame(HungerGame game) {
        this.game = game;
    }

    public void setGameProps(GameProperties gameProps) {
        this.gameProps = gameProps;
        game.setMaxRounds(gameProps.getMaxRounds());
    }

    public String playGame() {
//        log += "Max rounds: " + gameProps.getMaxRounds() + "\n";
//        log += "Players count: " + gameProps.getPlayersCount() + "\n";

        return game.play();
    }

    public String getLog() {

        return log;
    }

    public static HungerGameEngine getInstance() {
        return new HungerGameEngine();
    }

    public List<String> getPlayersNames() {
        return gamePlayers.getAvailablePlayersList();
    }

    public void setPlayersToPlay(Map<String, Integer> playersCounts) {

        game.setPlayers(gamePlayers.getPlayersToPlay(playersCounts));
    }

    public void loadPlayersJars() {

        gamePlayers.loadPlayersJars(playersFolder);
    }

    public Map<String, Integer> getPlayersInfoMap() {

        gamePlayers.UpdatePlayersInfo(playersFolder);

        return gamePlayers.getPlayersInfoMap();
    }
}
