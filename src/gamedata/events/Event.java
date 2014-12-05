package gamedata.events;

import gamedata.events.GlobalAction;
import java.util.List;

public class Event {
	List<Condition> myConditions;
	List<GlobalAction> myGlobalActions;
	
	public Event(List<Condition> conditions, List<GlobalAction> actions){
		myConditions = conditions;
		myGlobalActions = actions;
	}
	
	public void runEvent(){
		boolean conditionsFulfilled = true;
		
		for(Condition c: myConditions){
			if(!c.evaluate()){
				conditionsFulfilled = false;
			}
		}
		
		if(conditionsFulfilled){
			for(GlobalAction a: myGlobalActions){
				//TODO: Find a way to run actions where the ACTOR is the game (god?) 
//				a.doBehavior(actor, receivers);
			}
		}
		
	}
}
