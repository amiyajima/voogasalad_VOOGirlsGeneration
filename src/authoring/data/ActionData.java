package authoring.data;

import gamedata.action.GlobalAction;
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

public class ActionData implements AuthoringData<GlobalAction> {
	private List<GlobalAction> myActions;
	private transient Map<String, GlobalAction> myActionsMap;
	
	/**
	 * Constructor for new ActionData,
	 * initializes empty list of Actions
	 */
	public ActionData() {
		myActions = new ArrayList<>();
		myActionsMap = new HashMap<>();
	}
	
	public ObservableList<String> getActionNames(){
		ObservableList<String> names = FXCollections.observableArrayList();
		for(String s: myActionsMap.keySet()){
			names.add(s);
		}
		return names;
	}
	
	@Override
	public List<GlobalAction> get(){
		return myActions;
	}
	
	@Override
	public void add(GlobalAction... actions) {
	    for (GlobalAction a : actions) {
	        myActions.add(a);
	        myActionsMap.put(a.toString(),  a);

	    }
	}
	
	@Override
	public void remove(GlobalAction... actions) {
	          for (GlobalAction a : actions) {
	                myActions.remove(a);
	                myActionsMap.remove(a.toString());
	            }
	}
	
	@Override
	public void clear() {
		myActions.clear();
		myActionsMap.clear();
	}

    public GlobalAction getAction (String s) {
        return myActionsMap.get(s);
    }
}
