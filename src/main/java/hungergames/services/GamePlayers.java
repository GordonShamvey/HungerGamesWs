package hungergames.services;

import hungergames.api.Huntable;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alf
 * Date: 11.08.13
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */
public class GamePlayers {

    private Map<String, Class> playersClasses;

    public GamePlayers() {
        playersClasses = new HashMap<String, Class>();
    }

    public void setPlayersClasses(Map<String, Class> playersClasses) {
        this.playersClasses = playersClasses;
    }

    public void loadPlayersJars(String folderName) {

        playersClasses = new HashMap<String, Class>();

        File folder = new File(folderName);

        for (final File fileEntry : folder.listFiles()) {

            URLClassLoader child = null;
            try {
                child = new URLClassLoader(new URL[]{fileEntry.toURL()}, this.getClass().getClassLoader());
                String fileName = fileEntry.getName();
                String className = fileName.substring(0,fileName.length() - ".jar".length());
                Class classToLoad = Class.forName(className, true, child);
                playersClasses.put(className, classToLoad);

            } catch (MalformedURLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }

    }

    public List<String> getAvailablePlayersList() {

        List<String> availablePlayers = new ArrayList<String>();
        availablePlayers.addAll(playersClasses.keySet());

        return availablePlayers;
    }

    public Map<String, Integer> getPlayersInfoMap() {

        Map<String, Integer> playersInfo = new HashMap<>();

        for(String name : playersClasses.keySet()) {
            playersInfo.put(name, 1);
        }

        return playersInfo;
    }

    public Map<String, Huntable> getPlayersToPlay() {

        Map<String, Huntable> playersBots = new HashMap<>();

        for(String botName : playersClasses.keySet()) {

            try {
                Huntable instance = (Huntable)playersClasses.get(botName).newInstance();
                playersBots.put(botName, instance);
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return playersBots;
    }

    public Map<String, Huntable> getPlayersToPlay(Map<String, Integer> playersCounts) {

        Map<String, Huntable> playersBots = new HashMap<>();

        for (String botName : playersCounts.keySet()) {

            try {
                for (int i = 0; i < playersCounts.get(botName); i++) {

                    Huntable instance = (Huntable) playersClasses.get(botName).newInstance();
                    playersBots.put(botName + i, instance);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return playersBots;
    }

    public void UpdatePlayersInfo(String folderName) {
        loadPlayersJars(folderName);
    }
}
