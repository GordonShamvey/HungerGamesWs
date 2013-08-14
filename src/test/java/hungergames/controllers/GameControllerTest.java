package hungergames.controllers;

import algoritmmicgames.services.GameEngine;
import algoritmmicgames.abstraction.GameFactory;
import algoritmmicgames.controllers.GameController;
import algoritmmicgames.modelattributes.GameInfoWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GameControllerTest {

    GameController gameController;

    @Before
    public void InitTests() {
        gameController = new GameController();
        gameController.setGameEngine();
        gameController.getGameEngine().setPlayersFolder(System.getProperty("user.dir") + "\\HungerGamesBots");
        gameController.getGameEngine().getGameProperties();
        gameController.getGameEngine().getPlayersInfoMap();
    }

    @After
    public void finalizeTests() {
        gameController = null;
    }

    @Test
    public void testDisplayForm() {
        ModelAndView mv = gameController.displayForm();

        assertEquals("View name should be 'main_page'", "main_page", mv.getViewName());
    }

    @Test
    public void testPlayGame() {

        ApplicationContext context= new ClassPathXmlApplicationContext("beans.xml");
        GameFactory gameFactory = (GameFactory) context.getBean("gameFactory");
        GameEngine gameEngine = GameEngine.getInstance(gameFactory);

        HashMap<String, Integer> playersInfo = new HashMap<String, Integer>();
        playersInfo.put("SmartPlayer", 2);
        playersInfo.put("Slacker", 1);

        GameInfoWrapper gameInfoWrapper = new GameInfoWrapper(gameEngine.getGameProperties());
        gameInfoWrapper.setMaxRounds(10);
        gameInfoWrapper.setPlayersInfoMap(playersInfo);

        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        Model map = new BindingAwareModelMap();
        String viewName = gameController.playGame(gameInfoWrapper, result, map);

        assertFalse("Model should have gamelog attribute", map.containsAttribute("gamelog"));
        assertEquals("View name should be 'main_page'", "main_page", viewName);

        when(result.hasErrors()).thenReturn(false);
        viewName = gameController.playGame(gameInfoWrapper, result, map);

        assertTrue("Model should have gamelog attribute", map.containsAttribute("gamelog"));
        assertEquals("View name should be 'main_page'", "main_page", viewName);

    }

}
