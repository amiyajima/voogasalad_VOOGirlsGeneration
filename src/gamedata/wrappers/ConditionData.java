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
    private List<ConditionDataIndividual> myConditionData;
    
    public ConditionData(List<ConditionDataIndividual> conditions) {
        myConditionData = conditions;
    }

    public List<ConditionDataIndividual> getMyConditionData () {
        return myConditionData;
    }

    /**
     * Conditions data structure unwrapper
     * @return
     */
    public List<Condition> getConditionsFromData () {
        List<Condition> myConditionsFromData = new ArrayList<Condition>();
        for (ConditionDataIndividual cdi : myConditionData) {
            myConditionsFromData.add(cdi.getConditionFromData());
        }
        return myConditionsFromData;
    }

}
