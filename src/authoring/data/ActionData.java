package authoring.data;

import gamedata.action.Action;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Class for storing the actions created by the user
 * Stored in the authoring environment
 * 
 * @author Jennie Ju
 */

/*
 * TODO: Remove the duplicated code that simultaneously modifies the map and list.
 * There should be an easier way to do this.
 */

public class ActionData implements AuthoringData<Action> {

    private List<Action> myActions;

    /**
     * Constructor for new ActionData,
     * initializes empty list of Actions
     */
    public ActionData () {
        myActions = new ArrayList<>();
    }

    public ActionData (List<Action> actions) {
        myActions = actions;
    }

    public ObservableList<String> getActionNames () {
        ObservableList<String> ids = FXCollections.observableArrayList();
        for (Action a : myActions) {
            ids.add(a.getName());
        }
        return ids;
    }

    /**
     * Returns the Action with the given String id.
     * Returns null if not found.
     */
    public Action getAction (String id) {
        for (Action a : myActions) {
            if (a.toString().equals(id)) { return a; }
        }
        return null;
    }

    @Override
    public void add (Action a) {
        myActions.add(a);
    }

    @Override
    public boolean canAdd (Action element) {
        for (Action a : myActions) {
            if (element.toString().equals(a.toString())) { return false; }
        }
        return true;
    }

    @Override
    public void remove (Action a) {
        myActions.remove(a);
    }

    @Override
    public void replace (Action origEl, Action newEl) {
        remove(origEl);
        add(newEl);
    }

    @Override
    public List<Action> getData () {
        return myActions;
    }
}
