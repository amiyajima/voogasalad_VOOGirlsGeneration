package gamedata.stats;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.image.ImageView;

public class Stats {
	private Set<SingleStat> myStats;

	/**
	 * Stats class constructor
	 * initializes an empty stats set
	 */
	public Stats() {
		myStats = new HashSet<SingleStat>();
	}

	/**
	 * Adds a stat to the list of stats
	 * @param stat - the stat to be added
	 */
	public void add(SingleStat stat) {
		myStats.add(stat);
	}

	public void remove(SingleStat stat) {
		myStats.remove(stat);
	}

	public SingleStat getStat(String statName) {
		for (SingleStat stat : myStats) {
			if (stat.getName().equals(statName)) {
				return stat;
			}
		}
		// Throw error instead
		return null;
	}

	public double getStatValue(String statName) {
		SingleStat stat = getStat(statName);
		if (stat == null) {
			return 0;
		} else {
			return stat.getValue();
		}
	}

	public void setStatValue(String statName, double value) {
		SingleStat stat = getStat(statName);
		if (stat != null) {
			stat.setValue(value);
		}
	}



public void remove(String statName) {
	for (SingleStat stat : myStats) {
		if (stat.getName().equals(statName)) {
			myStats.remove(stat);
			return;
		}
	}
}

public void clear() {
	myStats.clear();
}

public List<String> getStatNames() {
	List<String> statNames = new ArrayList<String>();
	for (SingleStat stat : myStats) {
		statNames.add(stat.getName());
	}
	return statNames;
}

public List<Double> getStatValues() {
	List<Double> statValues = new ArrayList<Double>();
	for (SingleStat stat : myStats) {
		statValues.add(stat.getValue());
	}
	return statValues;
}

public List<ImageView> getStatImages() {
	List<ImageView> statImages = new ArrayList<ImageView>();
	for (SingleStat stat : myStats) {
		statImages.add(stat.getImageView());
	}
	return statImages;
}

}
