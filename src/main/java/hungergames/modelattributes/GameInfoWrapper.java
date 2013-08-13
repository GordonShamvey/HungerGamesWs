package hungergames.modelattributes;

import hungergames.services.GameProperties;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alf
 * Date: 11.08.13
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public class GameInfoWrapper {

    public GameInfoWrapper() {
        gameProps = new GameProperties();
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

    public long getPlayersCount() {
        return gameProps.getPlayersCount();
    }

    public void setPlayersCount(long playersCount) {
        gameProps.setPlayersCount(playersCount);
    }

    private GameProperties gameProps;
    private Map<String, Integer> playersInfoMap;
}
