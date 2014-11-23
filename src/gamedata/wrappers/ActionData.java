package gamedata.wrappers;

import java.util.List;

public class ActionData {
    private List<IndividualActionData> myActions;
    
    public ActionData(List<IndividualActionData> actions) {
        myActions = actions;
    }
    
    public List<IndividualActionData> getActions() {
        return myActions;
    }
}
