package gamedata.wrappers;

import gamedata.events.conditions.Condition;
import gamedata.events.conditions.IsDead;

/**
 * Wrapper for individual conditions
 * @author Rica
 *
 */
public class ConditionDataIndividual {
    private String myDescription;
    
    public ConditionDataIndividual(String description) {
        myDescription = description;
    }

    public String getMyDescription () {
        return myDescription;
    }

    /**
     * Condition unwrapper, uses reflection to construct the appropriate one
     * @return
     */
    public Condition getConditionFromData () {
        // TODO for now this assumes that the condition is isDead
        Condition myConditionFromData = new IsDead(myDescription);
        return myConditionFromData;
    }
   


}
