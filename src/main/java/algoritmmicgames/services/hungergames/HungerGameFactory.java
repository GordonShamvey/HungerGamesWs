package algoritmmicgames.services.hungergames;

import algoritmmicgames.abstraction.Game;
import algoritmmicgames.abstraction.GameFactory;
import algoritmmicgames.abstraction.GamePlayers;
import algoritmmicgames.services.GameProperties;

public class HungerGameFactory implements GameFactory{

    @Override
    public Game createGame() {
        return HungerGame.getInstance();
    }

    @Override
    public GamePlayers createGamePlayers() {
        return new HungerGamePlayers();
    }

    @Override
    public GameProperties createGameProperties() {
        return new HungerGameProperties();
    }
}
