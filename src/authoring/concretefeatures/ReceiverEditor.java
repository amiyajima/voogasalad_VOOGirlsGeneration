package authoring.concretefeatures;

import java.util.List;
import authoring.abstractfeatures.PopupWindow;


public class ReceiverEditor extends PopupWindow {
    
    private List<String> myPosReceivers;
    
    
    private final int HEIGHT = 400;
    private final int WIDTH = 400;
    private final String NAME = "Action Receivers";
    private String myActor;
    private String myAction;
    
    
    public ReceiverEditor(List<String> receivers, String actor, String action){
        
        myPosReceivers = receivers;
        myActor = actor;
        myAction = action;
        
    }

    @Override
    protected void initialize () {
        // TODO Auto-generated method stub
        
        
    }
    

}
