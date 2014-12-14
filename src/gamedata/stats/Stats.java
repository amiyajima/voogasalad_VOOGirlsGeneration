package gamedata.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
/**
 * Numerical stats class.
 * Stats are contained in every piece.
 * Stats map a stat name to a double value.
 * 
 * @author Jennie Ju
 *
 */
public class Stats {
	private Map<String, Double> myStats;

	/**
	 * Stats constructor for initializing empty stats map
	 */
	public Stats() {
		myStats = new HashMap<String, Double>();
	}

	/**
	 * Stats constructor for initializing with already
	 * created map of names to doubles
	 * @param stats - map of stat names to doubles
	 */
	public Stats(Map<String, Double> stats) {
		myStats = stats;
	}
	
	/**
	 * Cloning constructor for deep cloning of stats
	 * @param clone - Stats instance to be cloned
	 */
	public Stats(Stats clone) {
		myStats = new HashMap<String,Double>(clone.myStats);
	}

	/**
	 * Adds a new stat to the stats map
	 */
	public void add(String name, double value) {
		myStats.put(name, value);
	}

	/**
	 * Removes a stat from the stats map by name
	 */
	public void remove(String name) {
		myStats.remove(name);
	}

	/**
	 * Gets the value of the stat indicated by name
	 */
	public double getValue(String name) {
		return myStats.get(name);
	}

	/**
	 * Sets the value of the stat with the 
	 * indicated name to the value specified
	 */
	public void setValue(String name, double value) {
		myStats.replace(name, value);
	}

	/**
	 * Clears the stats map
	 */
	public void clear() {
		myStats.clear();
	}
	
	/**
	 * Checks if the stats contain the specified stat
	 */
	public boolean contains(String statName) {
		return myStats.containsKey(statName);
	}

	/**
	 * Returns a set of all the stat names
	 */
	public List<String> getStatNames() {
	    return FXCollections.observableArrayList(myStats.keySet());
	}


}
