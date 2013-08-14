package algoritmmicgames.abstraction;


import java.util.Map;

public interface Game {

    public void setMaxRounds(long roundsNumber);

    public void setPlayers(Map<String, Object> players);

    public String play();

}
