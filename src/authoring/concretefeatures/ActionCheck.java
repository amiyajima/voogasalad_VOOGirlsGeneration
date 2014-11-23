package authoring.concretefeatures;

import authoring.abstractfeatures.PopupWindow;
import authoring_environment.LibraryView;


public class ActionCheck extends PopupWindow {
    
    private final int HEIGHT = 400;
    private final int WIDTH = 400;
    private final String ACTION = "Action";
    private final String NAME = "Available Actions for Units";
    private LibraryView myLibrary;
    
    public ActionCheck(List<Action> actionLst, ){
        
    }

    @Override
    protected void initialize () {
        // TODO Auto-generated method stub

    }

}
