package gamedata.action;

import java.util.List;

/**
 * Stores the overall logic for one stats modifying equation.
 * Note, a StatsModifyingAction contains a list of StatsTotalLogics.
 * 
 * @author jujs100
 *
 */
public class StatsTotalLogic {
	private String myTarget;
	private String myValue; // probably shouldn't call this a value, think of better name
	private List<StatsSingleMultiplier> myLogic;
	
	public StatsTotalLogic(String target, String value, List<StatsSingleMultiplier> logic) {
		myTarget = target;
		myValue = value;
		myLogic = logic;
	}
	
	public boolean checkTarget(String targetToCheck) {
		return targetToCheck.equals(myTarget);
	}
	
	public String getValue() {
		return myValue;
	}
	
	public List<StatsSingleMultiplier> getMultiplierLogic() {
		return myLogic;
	}
}
