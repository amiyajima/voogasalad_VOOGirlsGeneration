package gamedata.wrappers;

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

}
