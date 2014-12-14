package gamedata.events;

import gamedata.events.globalaction.GlobalAction;
import gamedata.gamecomponents.IHasStats;

import java.util.ArrayList;
import java.util.List;

import authoring_environment.GUIGrid;
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
//	private List<Condition> myConditionsObservable;
//	private List<GlobalAction> myGlobalActionsObservable;
	private List<Condition> myConditions;
	private List<GlobalAction> myGlobalActions;


	public Event (String name) {
//		myConditionsObservable = FXCollections.observableArrayList();
//		myGlobalActionsObservable = FXCollections.observableArrayList();
		myConditions = new ArrayList<Condition>();
		myGlobalActions = new ArrayList<GlobalAction>();
		
		if(name.equals("") || name==null){
			myName = "Nameless Event";
		}
		else{
			myName = name;
		}
	}
	
	/**
	 * Constructor for event with existing data, aka from game data unwrapping
	 * @param name
	 * @param conditions
	 * @param globalActions
	 */
	public Event(String name, List<Condition> conditions, List<GlobalAction> globalActions) {
	    myName = name;
	    myConditions = conditions;
	    myGlobalActions = globalActions;
	}

	/**
	 * Method called by an external Event manager during each "event running" cycle
	 * (can be at the end of a user action, end of turn, end of level, etc.).
	 * 
	 * The list of Conditions is evaluated, and if all return true, the list of Actions
	 * is run in order.
	 */
	public void runEvent (List<IHasStats> sources, GUIGrid grid) {
		boolean allConditionsFulfilled = true;

		for (Condition c : myConditions) {
			if (!c.evaluate(sources)) {
				allConditionsFulfilled = false;
			}
		}
		
		System.out.println("THE CONDITION EVALUATES TO: " + allConditionsFulfilled);

		if (allConditionsFulfilled) {
			for (GlobalAction a : myGlobalActions) {
				a.doBehavior(grid);
			}
		}
	}

	@Override
	public String toString(){
		String myString = "Event " + myName + ": conditions - ";
		for (Condition c : myConditions) {
		    myString += c.toString() + " ";
		}
		myString += " globalactions - ";
		for (GlobalAction g : myGlobalActions) {
		    myString += g.toString() + " ";
		}
		return myString;
	}

        public List<Condition> getConditions(){
		return myConditions;
	}

	public List<GlobalAction> getGlobalActions(){
		return myGlobalActions;
	}
}
