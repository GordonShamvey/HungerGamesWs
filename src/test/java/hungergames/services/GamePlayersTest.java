package hungergames.services;

import algoritmmicgames.abstraction.GamePlayers;
import algoritmmicgames.services.hungergames.HungerGamePlayers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GamePlayersTest {
    @Test
    public void testSetPlayersClasses() throws Exception {

    }

    @Test
    public void testLoadPlayersJars() throws Exception {

    }

    @Test
    public void testGetAvailablePlayersList() throws Exception {

    }

    @Test
    public void testGetPlayersInfoMap() throws Exception {
        HashMap<String, Integer> playersInfo = new HashMap<String, Integer>();
        playersInfo.put("Slacker", 2);
        playersInfo.put("SmartPlayer", 1);

        GamePlayers players = new HungerGamePlayers();
        players.loadPlayersJars("D:\\HungerGamesBots");
        Map<String, Object> res = players.getPlayersToPlay(playersInfo);
        assertEquals("Size should be 3", 3, res.size());
    }

    @Test
    public void testGetPlayersToPlay() throws Exception {

    }

    @Test
    public void testUpdatePlayersInfo() throws Exception {

    }
}
