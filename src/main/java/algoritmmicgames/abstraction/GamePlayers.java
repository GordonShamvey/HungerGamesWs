package algoritmmicgames.abstraction;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GamePlayers {

    private Map<String, Class> playersClasses;

    public GamePlayers() {
        playersClasses = new HashMap<>();
    }

    public void setPlayersClasses(Map<String, Class> playersClasses) {
        this.playersClasses = playersClasses;
    }

    public List<String> getAvailablePlayersList() {

        List<String> availablePlayers = new ArrayList<>();
        availablePlayers.addAll(playersClasses.keySet());

        return availablePlayers;
    }

    public void UpdatePlayersInfo(String folderName) {
        loadPlayersJars(folderName);
    }

    public abstract void loadPlayersJars(String folderName);

    public abstract Map<String, Integer> getPlayersInfoMap();

    public abstract Map<String, Object> getPlayersToPlay(Map<String, Integer> playersCounts);

}
