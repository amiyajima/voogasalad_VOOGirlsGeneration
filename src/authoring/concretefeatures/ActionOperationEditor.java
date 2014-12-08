package authoring.concretefeatures;

import gamedata.action.ActionConclusion;
import gamedata.action.StatsTotalLogic;
import gamedata.stats.Stats;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
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
import authoring.data.ActionData;


/**
 * Combines SingleMultiplierBoxes to allow definition of a stat operation.
 * Used to define actions
 * 
 * @author annamiyajima
 *
 */
public class ActionOperationEditor extends PopupWindow {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 550;
    private static final String NAME = "Action Operation Editor";
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";

    private List<StatsOperationBox> operationsList;
    private ActionData myActionData;
    
    private String myName;
    private List<Point2D> myAttackRange;
    private List<Point2D> myEffectRange;
    private List<StatsTotalLogic> myStatsLogics;
    private ActionConclusion myConclusion;

    public ActionOperationEditor () {
        this(new ActionData());
    }

    public ActionOperationEditor (ActionData actionData) {
        operationsList = new ArrayList<>();
        myActionData = actionData;

        myName = "";
        myAttackRange = new LinkedList<Point2D>();
        myEffectRange = new LinkedList<Point2D>();
        myStatsLogics = new LinkedList<StatsTotalLogic>();
        myConclusion = null; // should set to empty conclusion (not null)

        setHeight(HEIGHT);
        setWidth(WIDTH);
        setTitle(NAME);
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

    private Button makeDoneButton (VBox statsVBox) {
        Button doneButton = new Button("Done");
        doneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                //myStats.clear();
                for (StatsOperationBox sbc : myBoxes) {
                    if (!sbc.isEmpty()) {
//                        String name = sbc.getStatName();
//                        Double val = sbc.getStatValue();
//                        myStats.add(name, val);
                    }
                }
                close();
            }
        });
        return doneButton;
    }

    private Button makeAddButton (VBox statsVBox) {
        Button addBtn = new Button("Add New Operation");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("ActionOperationEditor: makeAddButton event called");
                StatsOperationBox sob = new StatsOperationBox();
                myBoxes.add(sob);
                addStatHBox(statsVBox, sob);
            }
        });
        return addBtn;
    }

    private void addStatHBox (VBox statsVBox, StatsOperationBox sob) {
        HBox statsHBox = new HBox();
        statsHBox.getStyleClass().add("hbox");
        Button delStatBtn = makeDeleteButton(statsVBox, statsHBox, sob);
        statsHBox.getChildren().addAll(sob, delStatBtn);
        statsVBox.getChildren().addAll(statsHBox);
    }

    private Button makeDeleteButton (VBox statsVBox, HBox statsHBox, StatsOperationBox sob) {
        Button delBtn = new Button("-");
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("ActionOperationEditor: delete button pressed");
                myBoxes.remove(sob);
                statsVBox.getChildren().remove(statsHBox);
            }
        });
        return delBtn;
    }

    private void initStatsEditorBox (VBox statsVBox) {
        initStatsOperationBoxes(statsVBox);
    }

    private void initStatsOperationBoxes (VBox statsVBox) {
        //repopulate with existing statsOperationBoxes
        
    }
}
