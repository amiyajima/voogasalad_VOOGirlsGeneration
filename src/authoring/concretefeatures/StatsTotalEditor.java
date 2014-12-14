package authoring.concretefeatures;

import gamedata.stats.Stats;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;


/**
 * Popup window for editing the stats for a
 * type of piece. Allows for full editing of
 * the names and default values for a type of
 * piece.
 * 
 * @author Jennie Ju
 *
 */
public class StatsTotalEditor extends PopupWindow {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 420;
    private static final String WINDOW_TITLE = "Stats Editor";
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";

    private Stats myStats;
    private List<StatsCreatorBox> myBoxes;

    public StatsTotalEditor () {
        this(new Stats());
    }

    /**
     * Creates an editor popup for creating stats
     * and editing default stat values
     * 
     * @param stats - Stats of the piece whose stats
     *        are being edited
     */
    public StatsTotalEditor (Stats stats) {
        myStats = stats;
        System.out.println("StatsTotalEditor stats are " + stats);
        setHeight(WINDOW_HEIGHT);
        setWidth(WINDOW_WIDTH);
        setTitle(WINDOW_TITLE);
        myBoxes = new ArrayList<StatsCreatorBox>();
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
        VBox statsVBox = new VBox();
        initStatsEditorBox(statsVBox);
        Button newStatBtn = makeAddButton(statsVBox);
        Button doneBtn = makeDoneButton(statsVBox);

        mainVBox.getChildren().addAll(newStatBtn, statsVBox, doneBtn);
        root.setContent(mainVBox);
        setScene(scene);
    }

    private void initStatsEditorBox (VBox statsVBox) {
        initStatsCreatorBoxes(statsVBox);
    }

    private void initStatsCreatorBoxes (VBox statsVBox) {
        Set<String> statNames = new HashSet<String>(myStats.getStatNames());
        for (String name : statNames) {
            double val = myStats.getValue(name);
            StatsCreatorBox scb = new StatsCreatorBox(name, val);
            myBoxes.add(scb);
            addStatHBox(statsVBox, scb);
        }
    }

    private Button makeAddButton (VBox statsVBox) {
        Button addBtn = new Button("Add New Stat");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                StatsCreatorBox scb = new StatsCreatorBox();
                myBoxes.add(scb);
                addStatHBox(statsVBox, scb);
            }
        });
        return addBtn;
    }

    private void addStatHBox (VBox statsVBox, StatsCreatorBox scb) {
        HBox statsHBox = new HBox();
        statsHBox.getStyleClass().add("hbox");
        Button delStatBtn = makeDeleteButton(statsVBox, statsHBox, scb);
        statsHBox.getChildren().addAll(scb, delStatBtn);
        statsVBox.getChildren().addAll(statsHBox);
    }

    private Button makeDeleteButton (VBox statsVBox, HBox statsHBox,
                                     StatsCreatorBox scb) {
        Button delBtn = new Button("-");
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myBoxes.remove(scb);
                statsVBox.getChildren().remove(statsHBox);
                myStats.remove(scb.getStatName());
            }
        });
        return delBtn;
    }

    private Button makeDoneButton (VBox statsVbox) {
        Button doneButton = new Button("Done");
        doneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
//                myStats.clear();
                for (StatsCreatorBox sbc : myBoxes) {
                    if (!sbc.isEmpty()) {
                        String name = sbc.getStatName();
                        Double val = sbc.getStatValue();
                        myStats.add(name, val);
                    }
                }
                close();
            }
        });
        return doneButton;
    }

}
