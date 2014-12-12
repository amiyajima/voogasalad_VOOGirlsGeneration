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
	protected String myStatRef;
	protected String myStat;
	
	protected StatsModifier(String target, String stat) {
		myStatRef = target;
		myStat = stat;
	}
	
	/**
	 * Checks whether the target is that named by the parameter
	 * @param targetToCheck - String to check if the target is 
	 * the actor or the receiver
	 * @return boolean of target checking
	 */
	protected boolean checkTarget(String targetToCheck) {
		return targetToCheck.equals(myStatRef);
	}
	
	/**
	 * Returns the name of the stat to be modified 
	 */
	protected String getStatName() {
		return myStat;
	}
	
	/**
	 * Returns the name of the reference whose stat will be modified
	 * (actor or receiver)
	 */
	protected String getStatRef() {
		return myStatRef;
	}
	
}
