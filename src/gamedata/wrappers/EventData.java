package gamedata.wrappers;

/**
 * Wrapper for event data
 * @author Rica
 *
 */
public class EventData {
    private String myName;
    private ConditionData myConditionData;
    private GlobalActionData myGlobalActionData;
    
    public EventData(String name, ConditionData conditionData, GlobalActionData globalActionData) {
        myName = name;
        myConditionData = conditionData;
        myGlobalActionData = globalActionData;
    }

    public String getMyName () {
        return myName;
    }

    public ConditionData getMyConditionData () {
        return myConditionData;
    }

    public GlobalActionData getMyGlobalActionData () {
        return myGlobalActionData;
    }
    
    

}
