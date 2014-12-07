package gamedata.events;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;


/**
 * Events are action-reaction pairs that allow users to define complex game rules.
 * Events contain a list of Conditions and Actions. Conditions observe game state, and
 * when all Conditions evaluate to true, the list of Actions are run. Through composition,
 * the behavior of any Event object is highly customizable.
 * 
 * Conditions and Actions are implemented in code, rather than through the authoring
 * environment ("hard-coded"). By allowing users to chain together any number
 * of Conditions and Actions, we provide huge flexibility in determining game rules.
 * 
 * @author Mike Zhu
 *
 */
public class Event {
	private String myName;
	private List<Condition> myConditions;
	private List<GlobalAction> myGlobalActions;

	public Event (String name) {
		myConditions = FXCollections.observableArrayList();
		myGlobalActions = FXCollections.observableArrayList();
		if(name.equals("") || name==null){
			myName = "Nameless Event";
		}
		else{
			myName = name;
		}
	}

	/**
	 * Method called by an external Event manager during each "event running" cycle
	 * (can be at the end of a user action, end of turn, end of level, etc.).
	 * 
	 * The list of Conditions is evaluated, and if all return true, the list of Actions
	 * is run in order.
	 */
	public void runEvent () {
		boolean allConditionsFulfilled = true;

		for (Condition c : myConditions) {
			if (!c.evaluate()) {
				allConditionsFulfilled = false;
			}
		}

		if (allConditionsFulfilled) {
			for (GlobalAction a : myGlobalActions) {
				a.doBehavior();
			}
		}
	}

	@Override
	public String toString(){
		return myName;
	}


	public List<Condition> getConditions(){
		return myConditions;
	}

	public List<GlobalAction> getGlobalActions(){
		return myGlobalActions;
	}
}
