package gamedata.wrappers;

import gamedata.events.Event;
import gamedata.events.conditions.Condition;
import gamedata.events.globalaction.GlobalAction;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for event data
 * @author Rica
 *
 */
public class EventDataIndividual {
    private String myName;
    private List<ConditionDataIndividual> myConditions;
    private List<GlobalActionDataIndividual> myGlobalActions;
    
    public EventDataIndividual(String name, List<ConditionDataIndividual> conditionData, List<GlobalActionDataIndividual> globalActionData) {
        myName = name;
        myConditions = conditionData;
        myGlobalActions = globalActionData;
    }

    public String getMyName () {
        return myName;
    }

    public List<ConditionDataIndividual> getMyConditionData () {
        return myConditions;
    }

    public List<GlobalActionDataIndividual> getMyGlobalActionData () {
        return myGlobalActions;
    }

    /**
     * Event unwrapper
     * @return
     */
    public Event getEventFromData () {
        List<Condition> myConditionsFromData = new ArrayList<Condition>();
        for (ConditionDataIndividual cdi : myConditions) {
            myConditionsFromData.add(cdi.getConditionFromData());
        }
        List<GlobalAction> myGlobalActionsFromData = new ArrayList<GlobalAction>();
        for (GlobalActionDataIndividual gadi : myGlobalActions) {
            myGlobalActionsFromData.add(gadi.getGlobalActionFromData());
        }
        Event myEventFromData = new Event(myName, myConditionsFromData, myGlobalActionsFromData);
        return myEventFromData;
    }
    
    

}
