package gamedata.wrappers;

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

}
