package algoritmmicgames.services.hungergames;

import algoritmmicgames.services.GameProperties;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

public class HungerGameProperties extends GameProperties {

    @Range(min = 1, max = 150) @NumberFormat(style = NumberFormat.Style.NUMBER)
    private long maxRounds = 1;

    public HungerGameProperties() {

    }

}
