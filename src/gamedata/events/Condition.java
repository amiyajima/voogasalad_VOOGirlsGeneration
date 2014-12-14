package gamedata.events;

import java.util.List;

import gamedata.gamecomponents.IHasStats;

/**
 * Simple abstract class defining behavior of Condition classes.
 * 
 * Conditions will be coded into the game. We will provide the user a set of Conditions
 * (~5) which should be a flexible platform to build a wide variety of Events.
 * 
 * @author Mike Zhu
 *
 */
public abstract class Condition {

	protected String myDescription;
	
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
    
    /**
     * Used to display Conditions within the GUI
     */
    @Override
    public String toString(){
    	return myDescription;
    }

}
