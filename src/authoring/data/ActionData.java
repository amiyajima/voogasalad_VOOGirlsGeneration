package authoring.data;

import gamedata.action.Action;

import java.util.LinkedList;
import java.util.List;
/**
 * Class for storing the actions created by the user
 * Stored in the authoring environment
 * 
 * @author Jennie Ju
 */
public class ActionData implements AuthoringData<Action> {
	private List<Action> myActions;
	
	/**
	 * Constructor for new ActionData,
	 * initializes empty list of Actions
	 */
	public ActionData() {
		myActions = new LinkedList<Action>();
	}
	
	@Override
	public void add(Action action) {
		myActions.add(action);
	}
	
	@Override
	public void remove(Action action) {
		myActions.remove(action);
	}
	
	@Override
	public void clear() {
		myActions.clear();
	}
	
}
