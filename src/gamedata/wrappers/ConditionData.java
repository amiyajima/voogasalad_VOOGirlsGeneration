package gamedata.wrappers;

import gamedata.events.Condition;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for conditions data structure
 * @author Rica
 *
 */
public class ConditionData {
    private List<ConditionDataIndividual> myConditions;
    
    public ConditionData(List<ConditionDataIndividual> conditions) {
        myConditions = conditions;
    }

    public List<ConditionDataIndividual> getMyConditionData () {
        return myConditions;
    }

    /**
     * Conditions data structure unwrapper
     * @return
     */
    public List<Condition> getConditionsFromData () {
        List<Condition> myConditionsFromData = new ArrayList<Condition>();
        for (ConditionDataIndividual cdi : myConditions) {
            myConditionsFromData.add(cdi.getConditionFromData());
        }
        return myConditionsFromData;
    }

}
