package hungergames.services;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

/**
 * Created with IntelliJ IDEA.
 * User: CSD
 * Date: 09.08.13
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class GameProperties {

    @Range(min = 1, max = 150) @NumberFormat(style = NumberFormat.Style.NUMBER)
    long maxRounds = 1;

    long playersCount = 0;

    public long getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(long maxRounds) {
        this.maxRounds = maxRounds;
    }

    public long getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(long playersCount) {
        this.playersCount = playersCount;
    }

}
