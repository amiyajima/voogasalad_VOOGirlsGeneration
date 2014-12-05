package gamedata.events;

/**
 * Simple interface defining behavior of Condition classes. 
 * 
 * Conditions will be hardcoded into the game. We will provide the user a set of Conditions
 * (~5) which should be a flexible platform to build a wide variety of Events. 
 * @author Mike Zhu
 *
 */
public interface Condition {
	
	/**
	 * Constructor should take in whatever targets the Condition is observing, as an
	 * unmodifiable data structure.
	 */
	
	/** 
	 * Return TRUE if the logic of the subclass is met. 
	 * @return
	 */
	public abstract boolean evaluate();
}
