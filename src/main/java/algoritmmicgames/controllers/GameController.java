package algoritmmicgames.controllers;

import algoritmmicgames.services.GameEngine;
import algoritmmicgames.abstraction.GameFactory;
import algoritmmicgames.validators.GamePropertiesValidator;
import algoritmmicgames.modelattributes.GameInfoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class GameController {

    @Autowired
    GameFactory gameFactory;

    GameEngine gameEngine;

    public void setGameEngine() {

        ApplicationContext context= new ClassPathXmlApplicationContext("beans.xml");
        gameFactory = (GameFactory) context.getBean("gameFactory");

        gameEngine = GameEngine.getInstance(gameFactory);
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    @RequestMapping(value = "/main_page", method = RequestMethod.GET)
    public ModelAndView displayForm() {

        setGameEngine();

        gameEngine.setPlayersFolder(System.getProperty("user.dir") + "\\webapp\\HungerGamesWebService\\HungerGamesBots");

        GameInfoWrapper gameInfoWrapper = new GameInfoWrapper();
        gameInfoWrapper.setGameProps(gameEngine.getGameProperties());
        gameInfoWrapper.setPlayersInfoMap(gameEngine.getPlayersInfoMap());

        ModelAndView mv = new ModelAndView("main_page");
        mv.addObject("gameInfoWrapper", gameInfoWrapper);

        return mv;
    }

    @RequestMapping(value = "/playgame", method = RequestMethod.POST)
    public String playGame(@ModelAttribute("gameInfoWrapper") @Valid GameInfoWrapper gameInfoWrapper,  BindingResult result, Model map) {

        GamePropertiesValidator validator = new GamePropertiesValidator();
        validator.validate(gameInfoWrapper, result);

        if(result.hasErrors()) {
            return "main_page";
        }

        //ModelAndView mvOut = new ModelAndView("main_page");

        gameEngine.setMaxRounds(gameInfoWrapper.getMaxRounds());
        gameEngine.setPlayersToPlay(gameInfoWrapper.getPlayersInfoMap());

        String log = gameEngine.playGame();

        map.addAttribute("gamelog", log);

        return "main_page";
    }
}
