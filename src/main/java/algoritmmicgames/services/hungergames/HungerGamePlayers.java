package algoritmmicgames.services.hungergames;

import algoritmmicgames.abstraction.GamePlayers;
import hungergames.api.Huntable;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;


public class HungerGamePlayers extends GamePlayers {

    private Map<String, Class> playersClasses;

    public HungerGamePlayers() {
        playersClasses = new HashMap<String, Class>();
    }

    public void setPlayersClasses(Map<String, Class> playersClasses) {
        this.playersClasses = playersClasses;
    }

    public void loadPlayersJars(String folderName) {

        playersClasses = new HashMap<String, Class>();

        File folder = new File(folderName);

        if (folder.listFiles() == null) return;

        for (final File fileEntry : folder.listFiles()) {

            URLClassLoader child = null;
            try {
                child = new URLClassLoader(new URL[]{fileEntry.toURL()}, this.getClass().getClassLoader());
                String fileName = fileEntry.getName();
                String className = fileName.substring(0, fileName.length() - ".jar".length());
                Class classToLoad = Class.forName(className, true, child);
                playersClasses.put(className, classToLoad);

            } catch (MalformedURLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }

    }

    public Map<String, Integer> getPlayersInfoMap() {

        Map<String, Integer> playersInfo = new HashMap<>();

        for (String name : playersClasses.keySet()) {
            playersInfo.put(name, 1);
        }

        return playersInfo;
    }

    public Map<String, Object> getPlayersToPlay(Map<String, Integer> playersCounts) {

        Map<String, Object> playersBots = new HashMap<>();

        for (String botName : playersCounts.keySet()) {

            try {
                for (int i = 0; i < playersCounts.get(botName); i++) {

                    Object instance = playersClasses.get(botName).newInstance();
                    if (instance instanceof Huntable) {
                        playersBots.put(botName + i, instance);
                    }

                }
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return playersBots;
    }

}
