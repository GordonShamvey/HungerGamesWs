package algoritmmicgames.modelattributes;

import algoritmmicgames.services.GameProperties;

import java.util.HashMap;
import java.util.Map;

public class GameInfoWrapper {

    public GameInfoWrapper() {
        gameProps = new GameProperties();
        playersInfoMap = new HashMap<>();
    }

    public GameInfoWrapper(GameProperties gameProps) {
        this.gameProps = gameProps;
    }
    public GameProperties getGameProps() {
        return gameProps;
    }

    public void setGameProps(GameProperties gameProps) {
        this.gameProps = gameProps;
    }

    public Map<String, Integer> getPlayersInfoMap() {
        return playersInfoMap;
    }

    public void setPlayersInfoMap(Map<String, Integer> playersInfoMap) {
        this.playersInfoMap = playersInfoMap;
    }

    public long getMaxRounds() {
        return gameProps.getMaxRounds();
    }

    public void setMaxRounds(long maxRounds) {
        gameProps.setMaxRounds(maxRounds);
    }

    private GameProperties gameProps;
    private Map<String, Integer> playersInfoMap;
}
