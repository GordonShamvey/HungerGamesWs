package hungergames.controllers;

import hungergames.services.GameProperties;
import hungergames.services.HungerGameEngine;
import hungergames.modelattributes.GameInfoWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Alf
 * Date: 10.08.13
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 */
public class GameControllerTest {

    GameController gameController;
    HungerGameEngine gameEngine;

    @Before
    public void InitTests() {
        gameController = new GameController();
        gameEngine = HungerGameEngine.getInstance();
        gameEngine.setPlayersFolder("D:\\HungerGamesBots");
        gameEngine.loadPlayersJars();
    }

    @After
    public void finalizeTests() {
        gameController = null;
        gameEngine = null;
    }

    @Ignore
    public void testViewCorrectness() {

        GameProperties gameProps = new GameProperties();
        gameProps.setMaxRounds(1);
        gameProps.setPlayersCount(2);

        gameController.setGameEngine(gameEngine);

        HashMap<String, Integer> playersInfo = new HashMap<String, Integer>();
        playersInfo.put("SmartPlayer", 2);

        GameInfoWrapper gameInfoWrapper = new GameInfoWrapper();
        gameInfoWrapper.setGameProps(gameProps);
        gameInfoWrapper.setPlayersInfoMap(playersInfo);

        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        String viewName = gameController.playGame(gameInfoWrapper, result, (Model)new ModelAndView().getModel());

        String gamelog = "Max rounds: " + gameProps.getMaxRounds() + "\n";
        gamelog += "Players count: " + gameProps.getPlayersCount() + "\n";

        assertEquals("View name should be 'main_page'", "main_page", viewName);
//        assertEquals("gamelog should be 'Max rounds: 1\n" +
//                " Players count: 2\n'", gamelog, gameController.getGameEngine().getLog());
//
//        gamelog = "Max rounds: 1\n Players count: 3\n";
//        assertFalse("gamelog should not be equals to 'Max rounds: 1\n Players count: 3\n",
//                    gamelog.equals(gameController.getGameEngine().getLog()));
    }
}
