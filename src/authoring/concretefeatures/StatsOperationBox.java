package authoring.concretefeatures;

import java.util.List;
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
public class StatsOperationBox extends HBox {
    private static final int STAT_BOX_WIDTH = 150;
    private static final int VALUE_BOX_WIDTH = 150;
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";

    private static final String EQUALS_LABEL = "=";

    // private List<SingleMultiplierBox> mySingleMultipliers;
    private SingleMultiplierBox mySingleMultiplier;
    private ModifiedStat myModifiedStat;

    public StatsOperationBox () {
        initStatsOperationBox();
    }

    private void initStatsOperationBox () {
        getStylesheets().add(STYLESHEET);
        getStyleClass().add("hbox");
        Label equalsLabel = new Label(EQUALS_LABEL);
        myModifiedStat = new ModifiedStat();
        mySingleMultiplier = new SingleMultiplierBox();
        getChildren().addAll(equalsLabel, mySingleMultiplier);
    }

    public boolean isEmpty () {
        return false;
        //return mySingleMultiplier.empty() || myModifiedStat.empty();
    }
}
