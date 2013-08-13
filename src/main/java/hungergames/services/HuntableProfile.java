package hungergames.services;

import hungergames.api.Huntable;
import hungergames.api.Moves;

import java.util.List;

public class HuntableProfile {

	String jarName;
	Huntable player;
	long currentFood;
	int reputation;
	
	double h = 0;
    double s = 0;
	
	public HuntableProfile()
	{
		
	}
	
	public HuntableProfile(String jarName, Huntable player, int currentFood)
	{
		this.jarName = jarName;
		this.player = player;
		this.currentFood = currentFood;
		reputation = 0;
	}

    public HuntableProfile(String jarName, Huntable player)
    {
        this.jarName = jarName;
        this.player = player;
        currentFood = 0;
        reputation = 0;
    }

	public String getJarName() {
		return jarName;
	}

	public void setJarName(String jarName) {
		this.jarName = jarName;
	}

	public Huntable getPlayer() {
		return player;
	}

	public void setPlayer(Huntable player) {
		this.player = player;
	}

	public long getCurrentFood() {
		return currentFood;
	}

	public void setCurrentFood(int currentFood) {
		this.currentFood = currentFood;
	}

    public void update_food(int food) {
        this.currentFood += food;
    }

	public Double getReputation() {
        if(s == 0) return 0.0;
        else return h /(h + s);

	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

    public void update_reputation(Moves move)
    {
        if(move == Moves.Hunt) h++;
        else s++;
    }
	
	public void UpdateReputation(List<Moves> dessicions)
	{
		for(Moves des : dessicions)
		{
			if(des == Moves.Hunt) h++;
			else s++;
		}
		
	}

}
