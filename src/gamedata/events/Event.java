//This entire file is part of my masterpiece.
//MIKE ZHU

package gamedata.events;

import gamedata.events.conditions.Condition;
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
 * Events are stored within the Level, but require data from GUIGrid (lists of all Piece
 * and Patch objects) to run. This is done by passing the method runEvent via a lambda 
 * into the GUIGrid, rather than sending all of that data out of GUIGrid. 
 * 
 * @author Mike Zhu
 *
 */
public class Event {
	private String myName;
	private List<Condition> myConditions;
	private List<GlobalAction> myGlobalActions;

	/**
	 * Constructor for making an Event from scratch
	 * @param name = name of the Event
	 */
	public Event (String name) {
		myConditions = new ArrayList<Condition>();
		myGlobalActions = new ArrayList<GlobalAction>();

		/**
		 * Handles user error - when name input is null, this default "null" event is made
		 */
		if(name.equals("") || name==null){
			myName = "Nameless Event";
		}
		else{
			myName = name;
		}
	}

	/**
	 * Constructor for event with existing data, aka from game data unwrapping
	 * @param name = Event name 
	 * @param conditions = Previously added Conditions
	 * @param globalActions = Previously added GlobalActions
	 */
	public Event(String name, List<Condition> conditions, List<GlobalAction> globalActions) {
		myName = name;
		myConditions = conditions;
		myGlobalActions = globalActions;
	}

	/**
	 * Method called by the Level during each "event running" cycle
	 * (can be at the end of a user action, end of turn, end of level, etc.).
	 * 
	 * The list of Conditions is evaluated, and if all return true, the list of Actions
	 * is run in order.
	 * 
	 * This method is passed from Level into GUIGrid using lambda functions to minimize 
	 * the passing around of data collections. 
	 */
	public void runEvent (List<IHasStats> sources, GUIGrid grid) {
		boolean allConditionsFulfilled = true;

		for (Condition c : myConditions) {
			if (!c.evaluate(sources)) {
				allConditionsFulfilled = false;
			}
		}

		if (allConditionsFulfilled) {
			for (GlobalAction a : myGlobalActions) {
				a.doBehavior(grid);
			}
		}
	}

	@Override
	public String toString(){
		return myName;
	}

	/**
	 * Getters for the list of Conditions and GlobalActions. The getter is needed to pass 
	 * Conditions and GlobalActions into the EventEditor GUI, in order to populate 
	 * ListViews. This allows the user to see and modify the Event's Condition and 
	 * GlobalAction lists.
	 * @return
	 */
	public List<Condition> getConditions(){
		return myConditions;
	}

	public List<GlobalAction> getGlobalActions(){
		return myGlobalActions;
	}
}
