package hungergames.controllers;

import hungergames.services.HungerGameEngine;
import hungergames.modelattributes.GameInfoWrapper;
import hungergames.validators.GamePropertiesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: CSD
 * Date: 07.08.13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class GameController {

    @Autowired
    HungerGameEngine gameEngine;

    public void setGameEngine(HungerGameEngine engine) {
        gameEngine = engine;
    }

    public HungerGameEngine getGameEngine() {
        return gameEngine;
    }

    @RequestMapping(value = "/main_page", method = RequestMethod.GET)
    public ModelAndView displayForm() {

        gameEngine.setPlayersFolder("D:\\HungerGamesBots");

        GameInfoWrapper gameInfoWrapper = new GameInfoWrapper();
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

        gameEngine.setGameProps(gameInfoWrapper.getGameProps());
        gameEngine.setPlayersToPlay(gameInfoWrapper.getPlayersInfoMap());

        String log = gameEngine.playGame();

        map.addAttribute("gamelog", log);

        return "main_page";
    }
}
