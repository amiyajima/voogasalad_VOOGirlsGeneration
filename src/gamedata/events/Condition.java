package gamedata.events;

/**
 * Simple interface for how Condition classes will work. 
 * 
 * Conditions will be hardcoded into the game. We will provide the uesr a set of Conditions
 * (~10) 
 * @author Mike Zhu
 *
 */
public interface Condition {
	
	public abstract boolean evaluate();
}
