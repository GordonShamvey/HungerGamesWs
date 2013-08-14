package algoritmmicgames.validators;

import algoritmmicgames.modelattributes.GameInfoWrapper;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class GamePropertiesValidator implements Validator {

    private final int MAX_ROUND_NUM_VALUE = 100000;
    private final int MIN_ROUND_NUM_VALUE = 1;
    private final int MAX_PLAYER_COUNT = 10;
    private final int MIN_PLAYER_COUNT = 0;

    @Override
    public boolean supports(Class clazz) {
        return GameInfoWrapper.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GameInfoWrapper gameInfo = (GameInfoWrapper) target;

        if(gameInfo.getMaxRounds() > MAX_ROUND_NUM_VALUE) {
            errors.rejectValue("maxRounds", "Max.gameInfoWrapper.maxRounds", new Integer[]{MAX_ROUND_NUM_VALUE}, "");
        }

        if(gameInfo.getMaxRounds() < MIN_ROUND_NUM_VALUE) {
            errors.rejectValue("maxRounds", "Min.gameInfoWrapper.maxRounds", new Integer[]{MIN_ROUND_NUM_VALUE}, "");
        }

        for(String name : gameInfo.getPlayersInfoMap().keySet()) {
            if(gameInfo.getPlayersInfoMap().get(name) > MAX_PLAYER_COUNT) {
                errors.rejectValue("playersInfoMap", "Max.gameInfoWrapper.playerNumber", new Integer[]{MAX_PLAYER_COUNT}, "");
            }

            if(gameInfo.getPlayersInfoMap().get(name) < MIN_PLAYER_COUNT) {
                errors.rejectValue("playersInfoMap", "Min.gameInfoWrapper.playerNumber", new Integer[]{MIN_PLAYER_COUNT}, "");
            }
        }


    }

}
