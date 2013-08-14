package algoritmmicgames.services;

import algoritmmicgames.abstraction.Game;
import algoritmmicgames.abstraction.GameFactory;
import algoritmmicgames.abstraction.GamePlayers;

import java.util.List;
import java.util.Map;

public class GameEngine {

    private GameEngine(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
        log = "";
        gameProps = gameFactory.createGameProperties();
        gamePlayers = gameFactory.createGamePlayers();
        game = gameFactory.createGame();
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

    public void setGame(Game game) {
        this.game = game;
    }

    public void setMaxRounds(long maxRounds) {
        gameProps.setMaxRounds(maxRounds);
        game.setMaxRounds(maxRounds);
    }

    public String playGame() {

        return game.play();
    }

    public String getLog() {

        return log;
    }

    public static GameEngine getInstance(GameFactory gameFactory) {

        return new GameEngine(gameFactory);
    }

    public List<String> getPlayersNames() {
        return gamePlayers.getAvailablePlayersList();
    }

    public GameProperties getGameProperties() {
        return gameProps;
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

    private String log = "";
    private String playersFolder = "";

    private Game game;
    private GamePlayers gamePlayers;
    private GameProperties gameProps;
    private GameFactory gameFactory;

}
