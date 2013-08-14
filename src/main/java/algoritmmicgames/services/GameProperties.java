package algoritmmicgames.services;

public class GameProperties {

    public GameProperties() {
        maxRounds = 1;
    }
    public long getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(long maxRounds) {
        this.maxRounds = maxRounds;
    }

    protected long maxRounds;
}
