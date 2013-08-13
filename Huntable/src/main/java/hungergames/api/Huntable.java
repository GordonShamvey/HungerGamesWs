package hungergames.api;

import java.util.List;

public interface Huntable {

	/**
	 * @param args
	 */
	public List<Moves> hunt_choices(long round_number, long current_food, Double current_reputation, long m, List<Double> player_reputations);

	public void hunt_outcomes(List<Integer> food_earnings);
	
	public void round_end(long award, long m, long number_hunters);

}

