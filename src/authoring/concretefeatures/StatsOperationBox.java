package authoring.concretefeatures;

import javafx.scene.layout.HBox;
import authoring.abstractfeatures.PopupWindow;


/**
 * Combines SingleMultiplierBoxes to allow definition of a stat operation.
 * Used to define actions
 * 
 * @author annamiyajima
 *
 */
public class StatsOperationBox extends PopupWindow {

    public StatsOperationBox(){
        
    }

    @Override
    protected void initialize () {
        HBox statsBox = new SingleMultiplierBox();
    }
}
