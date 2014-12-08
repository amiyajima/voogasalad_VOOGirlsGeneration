package authoring.concretefeatures;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Used in StatsOperationBox. The value of this component is set equal to values determined by
 * SingleMultiplierBoxes in a StatsOperationBox
 * 
 * Implementation modeled off of SingleMultiplierBox
 * 
 * @author annamiyajima
 *
 */
public class ModifiedStat extends HBox{
    public static final int MODIFIED_STAT_BOX_WIDTH = 75;
    public static final int STATS_BOX_WIDTH = 120;
    private static final String STYLESHEET = "resources/stylesheets/singlemultiplierbox_layout.css";
    
    private ChoiceBox<String> myStatRef;
    private TextField myStat;
    
    public ModifiedStat(){
        getStylesheets().add(STYLESHEET);

        myStatRef = new ChoiceBox<String>();
        myStatRef.setPrefWidth(MODIFIED_STAT_BOX_WIDTH);
        myStatRef.getItems().addAll("actor", "receiver", "constant");
        myStat = new TextField();

        myStat.setPrefWidth(STATS_BOX_WIDTH);

        myStat.setPromptText("Reference stat or constant");

        getChildren().addAll( myStatRef, myStat);
}
}
