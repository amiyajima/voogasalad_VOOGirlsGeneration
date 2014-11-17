package gamedata.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Numerical stats class.
 * Stats are contained in every piece.
 * Stats map a stat name to a double value.
 * 
 * @author jujs100
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
	 * Returns a list of all the stat names 
	 */
	public List<String> getStatNames() {
		return new ArrayList<String>(myStats.keySet());
	}

	/**
	 * Returns a list of all the values with indices
	 * corresponding to names returned in getStatNames()
	 */
	public List<Double> getStatValues() {
		List<String> statNames = getStatNames();
		List<Double> values = new ArrayList<Double>();
		for (String name : statNames) {
			values.add(myStats.get(name));
		}
		return values;
	}


}
