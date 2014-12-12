package gamedata.wrappers;

import gamedata.events.GlobalAction;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for global actions data structure
 * @author Rica
 *
 */
public class GlobalActionData {
    private List<GlobalActionDataIndividual> myGlobalActions;
    
    public GlobalActionData(List<GlobalActionDataIndividual> globalActions) {
        myGlobalActions = globalActions;
    }

    public List<GlobalActionDataIndividual> getMyGlobalActions () {
        return myGlobalActions;
    }

    /**
     * Unwrapper for global action data structure
     * @return
     */
    public List<GlobalAction> getGlobalActionsFromData () {
        List<GlobalAction> myGlobalActionsFromData = new ArrayList<GlobalAction>();
        for (GlobalActionDataIndividual gadi : myGlobalActions) {
            myGlobalActionsFromData.add(gadi.getGlobalActionFromData());
        }
        return myGlobalActionsFromData;
    }

}
