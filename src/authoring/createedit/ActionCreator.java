package authoring.createedit;

import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.SingleMultiplierBox;
import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import java.awt.geom.Point2D;

import utilities.ClassGrabber;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.RangeEditor;
import authoring.concretefeatures.StatsOperationBox;
import authoring.data.ActionData;


/**
 * Opens a dialog for creating new Actions in the authoring environment
 * 
 * @author Mike Zhu, Rica Zhang, Jennie Ju, annamiyajima
 *
 */
public class ActionCreator extends PopupWindow {

    public static final int HEIGHT = 400;
    public static final int WIDTH = 350;
    public static final String NAME = "Action Creator";
    private static final String STYLESHEET = "/resources/stylesheets/actioncreator_layout.css";

    private List<SingleMultiplierBox> operationsList;
    private ActionData myActionData;

    private static final Insets MARGINS = new Insets(20, WIDTH / 8, 20, WIDTH / 8 - 10);
    private static final String LABEL_CSS = "-fx-font-size: 12pt;";
    private static final String DEFAULT_IMAGE = "/resources/images/default_image.png";
    private static final String CONCLUSION_LABEL = "Choose action conclusion";

    private String myName;
    private List<Point2D> myAttackRange;
    private List<Point2D> myEffectRange;
    private List<StatsTotalLogic> myStatsLogics;
    private ActionConclusion myConclusion;

    private List<Class> conclusionsList;

    public ActionCreator () {
        this(new ActionData());
    }

    /**
     * Constructor for an ActionCreator popup window
     * 
     * @param actionData
     *        - class where user-created Actions are stored
     */
    public ActionCreator (ActionData actionData) {
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

    protected void initialize () {
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(STYLESHEET);

        VBox box = new VBox();
        box.setPadding(MARGINS);
        box.setSpacing(10);

        HBox labelBox = new HBox();
        Label label = new Label(NAME);
        label.setStyle(LABEL_CSS);
        labelBox.getChildren().add(label);

        VBox nameVBox = new VBox();
        VBox rangeVBox = new VBox();
        rangeVBox.setSpacing(5);
        rangeVBox.getStyleClass().add("vbox");
        VBox conclusionVBox = new VBox();

        // Action name
        TextField nameField = new TextField();
        nameField.setMaxWidth(WIDTH - WIDTH / 4 - 10);
        initNameField(nameVBox, nameField);
        // Set the Action Range
        initSetRangeButton(rangeVBox, "Action Range:", myAttackRange);
        // Set the Effect Range
        initSetRangeButton(rangeVBox, "Effect Range (Splashzone):", myEffectRange);
        // Target stat to be modified
        VBox totalLogicVBox = new VBox();
        
        // conclusions
        initConclusionsBox(conclusionVBox);

        Button createBtn = new Button("Create new action");
        createBtn.setMaxWidth(WIDTH - WIDTH / 4 - 10);
        createBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                myName = nameField.getText();
                myStatsLogics = getStatsLogics(targetChoice, moddedStat);
                myActionData.add(new ConcreteAction(myName, myAttackRange,
                                                    myEffectRange, myStatsLogics, myConclusion));
                System.out.println("Created Action");
                System.out.println(myActionData.getActionNames().get(0));
                close(); // close window once action is made
            }
        });

        box.getChildren().addAll(labelBox, nameVBox, rangeVBox, new Separator(),
                                 totalLogicVBox, new Separator(), conclusionVBox,
                                 new Separator(), createBtn);
        root.setContent(box);
        setScene(scene);
    }

    private void initConclusionsBox (VBox conclusionVBox) {
        // TODO create label + choicebox populated with types of action conclusions
        Label chooseConclusion = new Label(CONCLUSION_LABEL);
        ChoiceBox conclusionChoiceBox = new ChoiceBox();

        try {
            conclusionsList = Arrays.asList(ClassGrabber.getClasses("gamedata.action.conclusions"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        List<String> displayList = new ArrayList<>();
        for (Class<?> c : conclusionsList) {
            displayList.add(c.toString());
        }
        displayList = trimClassList(displayList);

        conclusionChoiceBox.getItems().addAll(displayList);

        conclusionVBox.getChildren().addAll(chooseConclusion, conclusionChoiceBox);
    }

    private List<String> trimClassList (List<String> conclusionsList) {
        List<String> displayList = new ArrayList<>();
        for (String s : conclusionsList) {
            String trimmed = trimClassPaths(s);
            displayList.add(trimmed);
        }
        return displayList;
    }

    /**
     * Removes the classpath prefixes for each Condition name
     * 
     * @param s
     */
    private String trimClassPaths (String s) {
        int idx = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '.') {
                idx = i;
                break;
            }
        }
        String trimmed = s.substring(idx + 1);
        return trimmed;
    }

    // TODO: really need to take in multiple stats
    private List<StatsTotalLogic> getStatsLogics (ChoiceBox<String> targetChoice,
                                                  TextField moddedStat) {
        String target = targetChoice.getValue();
        String stat = moddedStat.getText();

        List<StatsTotalLogic> stlList = new LinkedList<StatsTotalLogic>();
        List<StatsSingleMultiplier> multiplierLogic = getSingleMultipliers(operationsList);
        stlList.add(new StatsTotalLogic(target, stat, multiplierLogic));
        return stlList;
    }

    private List<StatsSingleMultiplier> getSingleMultipliers (List<SingleMultiplierBox> smbList) {
        List<StatsSingleMultiplier> ssmList = new LinkedList<StatsSingleMultiplier>();
        for (SingleMultiplierBox smb : smbList) {
            ssmList.add(smb.getSingleMultipler());
        }
        return ssmList;
    }

    private void initNameField (VBox nameBox, TextField nameField) {
        Label nameLabel = new Label("Action name");
        nameField.setPromptText("Enter action name");
        nameBox.getChildren().addAll(nameLabel, nameField);
    }

    private void initSetRangeButton (VBox rangeBox, String label, List<Point2D> range) {
        Label rangeLabel = new Label(label);
        Button setRange = new Button("Set Range...");
        setRange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                PopupWindow actionRangeEditor = new RangeEditor(range, "Hexagon Grid");
                actionRangeEditor.show();
            }
        });
        rangeBox.getChildren().addAll(rangeLabel, setRange);
    }

    protected void setAttackRange (List<Point2D> attackRange) {
        myAttackRange = attackRange;
    }

    protected void setEffectRange (List<Point2D> effectRange) {
        myEffectRange = effectRange;
    }
}
