package gamedata.events;

import gamedata.action.Action;
import java.util.List;

/**
 * 
 * 
 *
 */
public class Event {
	List<Condition> myConditions;
	List<Action> myActions;
	
	public Event(List<Condition> conditions, List<Action> actions){
		myConditions = conditions;
		myActions = actions;
	}
	
	public void runEvent(){
		boolean conditionsFulfilled = true;
		
		for(Condition c: myConditions){
			if(!c.evaluate()){
				conditionsFulfilled = false;
			}
		}
		
		if(conditionsFulfilled){
			for(Action a: myActions){
				//TODO: Find a way to run actions where the ACTOR is the game (god?) 
//				a.doBehavior(actor, receivers);
			}
		}
		
	}
}
