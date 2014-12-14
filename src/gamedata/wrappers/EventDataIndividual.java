package gamedata.wrappers;

import gamedata.events.Condition;
import gamedata.events.GlobalAction;
import java.util.List;

//TODO: remove hard coded condition/global action type
public class EventDataIndividual {
    private String myName;
    private List<IsDeadConditionData> myConditions;
    private List<DeletePieceAtLocationData> myGlobalActions;
    
    public EventDataIndividual(String name, List<IsDeadConditionData> conditions, List<DeletePieceAtLocationData> globalActions){
        myName = name;
        myConditions = conditions;
        myGlobalActions = globalActions;
    }
    
    public String toString(){
        return "EventDataIndividual: name = " + myName + " conditions = " + myConditions + " global actions = " + myGlobalActions;
    }
}
