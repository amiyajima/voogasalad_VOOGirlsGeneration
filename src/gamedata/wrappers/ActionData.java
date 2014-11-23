package gamedata.wrappers;

import java.util.List;

public class ActionData {
    private List<ActionDataIndividual> myActions;
    
    public ActionData(List<ActionDataIndividual> actions) {
        myActions = actions;
    }
    
    public List<ActionDataIndividual> getActions() {
        return myActions;
    }
}
