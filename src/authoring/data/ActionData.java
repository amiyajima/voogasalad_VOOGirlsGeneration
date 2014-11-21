package authoring.data;

import gamedata.action.Action;

import java.util.LinkedList;
import java.util.List;
/**
 * Class for storing the actions created by the user
 * Stored in the authoring environment
 * @author jujs100
 *
 */
public class ActionData {
	private List<Action> myActions;
	
	public ActionData() {
		myActions = new LinkedList<Action>();
	}
	
	public void addAction(Action action) {
		myActions.add(action);
	}
	
	public void removeAction(Action action) {
		myActions.remove(action);
	}
	
}
