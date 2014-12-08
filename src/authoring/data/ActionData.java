package authoring.data;

import gamedata.action.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for storing the actions created by the user
 * Stored in the authoring environment
 * 
 * @author Jennie Ju
 */

/* TODO: Remove the duplicated code that simultaneously modifies the map and list.
 * There should be an easier way to do this.
 */

public class ActionData implements AuthoringData<Action> {
	
	private List<Action> myActions;
	private transient Map<String, Action> myActionsMap;
	
	/**
	 * Constructor for new ActionData,
	 * initializes empty list of Actions
	 */
	public ActionData() {
		myActions = new ArrayList<>();
		myActionsMap = new HashMap<>();
	}
	
	public ObservableList<String> getActionIDs(){
		ObservableList<String> ids = FXCollections.observableArrayList();
		for(String s: myActionsMap.keySet()){
			ids.add(s);
		}
		return ids;
	}

    public Action getAction (String s) {
        return myActionsMap.get(s);
    }

	@Override
	public void add(Action a) {
		myActions.add(a);
        myActionsMap.put(a.toString(),  a);
	}

	@Override
	public boolean canAdd(Action element) {
		for (String id:myActionsMap.keySet()){
			if (element.toString().equals(id)){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void remove(Action a) {
		myActions.remove(a);
        myActionsMap.remove(a.toString());
	}

	@Override
	public void replace(Action origEl, Action newEl) {
		// TODO : Does ActionData need this method?
		return;
	}

	@Override
	public List<Action> getData() {
		return myActions;
	}
}
