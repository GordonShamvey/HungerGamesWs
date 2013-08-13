package hungergames.services;

import hungergames.api.Huntable;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Alf
 * Date: 11.08.13
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
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
        playersInfo.put("Slacker", 1);
        playersInfo.put("SmartPlayer", 1);

        GamePlayers players = new GamePlayers();
        players.loadPlayersJars("D:\\HungerGamesBots");
        Map<String, Huntable> res = players.getPlayersToPlay(playersInfo);
        assertEquals("Size should be 2", 2, res.size());
    }

    @Test
    public void testGetPlayersToPlay() throws Exception {

    }

    @Test
    public void testUpdatePlayersInfo() throws Exception {

    }
}
