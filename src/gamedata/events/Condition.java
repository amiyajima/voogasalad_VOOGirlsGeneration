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
	
	public abstract boolean evaluate();
}
