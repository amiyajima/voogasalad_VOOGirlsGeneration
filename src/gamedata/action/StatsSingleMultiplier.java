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

public class StatsSingleMultiplier extends StatsModifier {
	private double myModifier;
	
	/**
	 * Default constructor for an empty StatsSingleMultiplier
	 */
	public StatsSingleMultiplier() {
		super("","");
		myModifier = 1;
	}
	
	/**
	 * Constructor for StatsSingleMultiplier
	 * @param modifier - double containing scale factor of stat
	 * @param target
	 * @param stat
	 */
	public StatsSingleMultiplier(double modifier, String target, String stat) {
		super(target,stat);
		myModifier = modifier;
	}
	
	/**
	 * Returns the double that will scale the stat
	 */
	protected double getModifier() {
		return myModifier;
	}

	@Override
	public String toString() {
	    String myString = "SSM: " + "Modifier: " + myModifier;
	    myString += " StatRef: " + myStatRef + " Stat: " + myStat;
	    return myString;
	}
	
}
