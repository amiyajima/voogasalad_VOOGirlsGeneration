package gamedata.action;

/**
 * Super class for stats modifying logic.
 * Removes duplicated methods from
 * StatsSingleMultiplier and StatsTotalLogic.
 * 
 * @author Jennie Ju
 *
 */
public abstract class StatsModifier {
	protected String myTarget;
	protected String myStat;
	
	protected StatsModifier(String target, String stat) {
		myTarget = target;
		myStat = stat;
	}
	
	/**
	 * Checks whether the target is that named by the parameter
	 * @param targetToCheck - String to check if the target is 
	 * the actor or the receiver
	 * @return boolean of target checking
	 */
	protected boolean checkTarget(String targetToCheck) {
		return targetToCheck.equals(myTarget);
	}
	
	/**
	 * Returns the name of the stat to be modified 
	 */
	protected String getStatName() {
		return myStat;
	}
	
}
