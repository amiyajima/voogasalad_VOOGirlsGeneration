package gamedata.wrappers;

import gamedata.events.Condition;
import gamedata.events.Event;
import gamedata.events.GlobalAction;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for event data
 * @author Rica
 *
 */
public class EventDataIndividual {
    private String myName;
    private ConditionData myConditionData;
    private GlobalActionData myGlobalActionData;
    
    public EventDataIndividual(String name, ConditionData conditionData, GlobalActionData globalActionData) {
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

    /**
     * Event unwrapper
     * @return
     */
    public Event getEventFromData () {
        List<Condition> myConditionsFromData = myConditionData.getConditionsFromData();
        List<GlobalAction> myGlobalActionsFromData = myGlobalActionData.getGlobalActionsFromData();
        Event myEventFromData = new Event(myName, myConditionsFromData, myGlobalActionsFromData);
        return myEventFromData;
    }
    
    

}
