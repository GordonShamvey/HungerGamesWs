package algoritmmicgames.abstraction;

import algoritmmicgames.services.GameProperties;

public interface GameFactory {

    public Game createGame();
    public GamePlayers createGamePlayers();
    public GameProperties createGameProperties();

}
