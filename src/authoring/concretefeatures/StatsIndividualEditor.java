package authoring.concretefeatures;

import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;


/**
 * Creates a pop-up for editing individual piece's stats.
 * It has default stats (set by the piece's type) with default values.
 * The user is able to change such default values to the values of
 * his choice.
 * 
 * @author Sandy Lee
 * 
 */
public class StatsIndividualEditor extends PopupWindow {
    private static final int STAT_BOX_WIDTH = 100;
    private static final int VALUE_BOX_WIDTH = 100;
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 400;
    private static final String WINDOW_TITLE = "Stats Editor";
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";

    protected static final String DEFAULT_STAT = "";

    private Map<String, TextField> values;
    private Label myStatLabel;
    private TextField myValueField;
    private Stats myStats;

    /**
     * Constructor for an editor popup for editing stats of an individual
     * piece on the grid.
     * 
     * @param piece - individual piece (its stats are to be edited)
     */
    public StatsIndividualEditor (Piece piece) {

        setHeight(WINDOW_HEIGHT);
        setWidth(WINDOW_WIDTH);
        setTitle(WINDOW_TITLE);

        myStats = piece.getStats();
        values = new HashMap<String, TextField>();
        initialize();
    }

    @Override
    protected void initialize () {
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(STYLESHEET);

        VBox mainVBox = new VBox();
        mainVBox.getStyleClass().add("vbox");
        mainVBox.setId("vbox-main");

        // creating label and textfield for stats of the piece to be edited
        Set<String> statNames = myStats.getStatNames();
        for (String name : statNames) {
            HBox statsHBox = initStatsEditorBox(name);
            mainVBox.getChildren().add(statsHBox);
        }

        VBox buttonBox = new VBox();
        createButton(buttonBox, statNames);
        mainVBox.getChildren().add(buttonBox);

        root.setContent(mainVBox);
        setScene(scene);
    }

    /**
     * @param name - name of the stat
     * @return HBox - which contains label for stat name and textfield for value to be entered
     */
    private HBox initStatsEditorBox (String name) {

        HBox statsHBox = new HBox();
        double val = myStats.getValue(name);
        myStatLabel = new Label();
        myStatLabel.setText(name + " = ");
        myStatLabel.setPrefWidth(STAT_BOX_WIDTH);
        
        myValueField = new TextField();
        myValueField.setText("" + val);
        myValueField.setPrefWidth(VALUE_BOX_WIDTH);

        values.put(name, myValueField);
        statsHBox.getChildren().addAll(myStatLabel, myValueField);

        return statsHBox;
    }


    /**
     * Method creates the "create" button.
     * 
     * @param buttonBox - box that contains the "create" button
     * @param statNames - set of strings which are the names of the stats
     */
    private void createButton (VBox buttonBox, Set<String> statNames) {
        Button createBtn = new Button("OK");
        createBtn.setOnAction(new EventHandler<ActionEvent>() {
            // replacing stats with the new values entered
            @Override
            public void handle (ActionEvent click) {
                for (String name : statNames) {
                    myStats.add(name, Double.parseDouble(values.get(name).getText()));
                }
                close();
            }
        });
        buttonBox.getChildren().add(createBtn);
    }

}
