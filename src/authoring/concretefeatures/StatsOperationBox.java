package authoring.concretefeatures;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import authoring.abstractfeatures.PopupWindow;


/**
 * Combination of StatsCreatorBoxes to define Stats Operations for actions
 * 
 * @author annamiyajima
 *
 */
public class StatsOperationBox extends HBox{

    public StatsOperationBox () {
        initStatsOperationBox();
    }

    private void initStatsOperationBox () {
        HBox statsBox = new SingleMultiplierBox();
    }
}
