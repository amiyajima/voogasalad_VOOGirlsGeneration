package gamedata.action;


/**
 * Stores one multiplication equation for the
 * StatsModifyingAction.
 * Note, each StatsTotalLogic contains a list
 * of StatsSingleMultipliers.
 * 
 * @author jujs100
 *
 */

public class StatsSingleMultiplier {
	private double myModifier;
	private String myTarget;
	private String myValue; // probably shouldn't call this a value, think of better name
	
	public StatsSingleMultiplier(double modifier, String target, String value) {
		myModifier = modifier;
		myTarget = target;
		myValue = value;
	}
	
	public double getModifier() {
		return myModifier;
	}
	
	public boolean checkTarget(String targetToCheck) {
		return targetToCheck.equals(myTarget);
	}
	
	public String getValue() {
		return myValue;
	}
	
}
