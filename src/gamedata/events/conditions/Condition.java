package gamedata.events.conditions;

import java.util.List;

import gamedata.gamecomponents.IHasStats;

/**
 * Simple interface defining behavior of Condition classes.
 * 
 * Conditions will be hardcoded into the game. We will provide the user a set of Conditions
 * (~5) which should be a flexible platform to build a wide variety of Events.
 * 
 * @author Mike Zhu
 *
 */
public abstract class Condition {

	protected String myDescription;
	
	protected static final String IF = "IF ";
	protected static final String EQUALS = " EQUALS ";
	protected static final String GREATER_THAN = " IS GREATER THAN ";
	protected static final String LESS_THAN = " IS LESS THAN ";


	/**
	 * Protected constructor used to set the description
	 * @param s
	 */
	protected Condition(String s){
		myDescription = s;
	}
	
    /**
     * Subclass constructor should take in whatever targets the Condition is observing, as an
     * unmodifiable data structure.
     */

    /**
     * Return TRUE if the logic of the subclass is met.
     * 
     * @return
     */

    public abstract boolean evaluate(List<IHasStats> objects);
    
    @Override
    public String toString(){
    	return myDescription;
    }

}
