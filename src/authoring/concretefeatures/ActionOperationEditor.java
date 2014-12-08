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
 * Combines SingleMultiplierBoxes to allow definition of a stat operation.
 * Used to define actions
 * 
 * @author annamiyajima
 *
 */
public class ActionOperationEditor extends PopupWindow {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 420;
    private static final String WINDOW_TITLE = "Action Operation Editor";
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";

    private Stats myStats;
    private List<StatsOperationBox> myBoxes;

    public ActionOperationEditor () {
        this(new Stats());
    }

    public ActionOperationEditor (Stats stats) {
        myStats = stats;
        System.out.println("ActionOperationEditor stats are " + stats);
        setHeight(WINDOW_HEIGHT);
        setWidth(WINDOW_WIDTH);
        setTitle(WINDOW_TITLE);
        myBoxes = new ArrayList<StatsOperationBox>();
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

        //mainVBox.getChildren().addAll(newStatBtn, statsVBox, doneBtn);
        root.setContent(mainVBox);
        setScene(scene);
    }

    private Button makeDoneButton (VBox statsVBox) {
        // TODO Auto-generated method stub
        return null;
    }

    private Button makeAddButton (VBox statsVBox) {
        Button addBtn = new Button("Add New Operation");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                StatsOperationBox scb = new StatsOperationBox();
                myBoxes.add(scb);
                addStatHBox(statsVBox, scb);
            }
        });
        return addBtn;
    }

    private void addStatHBox (VBox statsVBox, StatsOperationBox scb) {
        // TODO Auto-generated method stub

    }

    private void initStatsEditorBox (VBox statsVBox) {
        
    }
}
