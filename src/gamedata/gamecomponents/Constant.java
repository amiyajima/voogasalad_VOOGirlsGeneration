package gamedata.gamecomponents;

import gamedata.stats.Stats;

/**
 * This Constant class is awkwardly written to fit in the framework of other IHasStats
 * objects, containing a Stats file mapping String names to stat values. 
 * 
 * The Stats file will only hold a single value, the user-entered Constant, mapped to 
 * the String "Value." 
 * 
 * For this to work correctly, EditorPane needs to set the stat refName of Condition to 
 * "Value" in the special Constant case. This is a dependency.
 * @author Mike Zhu
 *
 */
public class Constant implements IHasStats{

	private Stats myStats;
	
	public Constant(){
		myStats = new Stats();
		myStats.add("Value", 0);
	}
	
	public Constant(double d){
		myStats = new Stats();
		myStats.add("Value", d);
	}
	
	@Override
	public String toString() {
		return "Constant = " + myStats.getValue("Value");
	}

	@Override
	public Stats getStats() {
		return myStats;
	}

}
