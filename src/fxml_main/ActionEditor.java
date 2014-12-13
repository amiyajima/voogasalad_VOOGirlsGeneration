package fxml_main;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.SingleMultiplierBox;
import gamedata.action.StatsTotalLogic;
import gamedata.action.TotalLogicBox;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utilities.ClassGrabber;
import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.RangeEditor;
import authoring.data.ActionData;


/**
 * @author seungwonlee, Jennie JU
 *
 */
public class ActionEditor extends Pane {

    public static final int HEIGHT = 100;
    public static final int WIDTH = 100;
    public static final String NAME = "Action Creator";
    private static final String STYLESHEET = "/resources/stylesheets/actioncreator_layout.css";
    private static final Insets MARGINS = new Insets(20, WIDTH / 8, 20, WIDTH / 8 - 10);
    private static final String LABEL_CSS = "-fx-font-size: 12pt;";
    private static final String CONCLUSION_LABEL = "Choose action conclusion";

    private String myName;
    private List<Point2D.Double> myAttackRange;
    private List<Point2D.Double> myEffectRange;
    private List<StatsTotalLogic> myStatsLogics;
    private ActionConclusion myConclusion;
    private List<Class> conclusionsList;
    
    private Consumer<Action> myOkLambda;
    private String myGridShape;
    private List<TotalLogicBox> myTotalLogicBoxes;

    public ActionEditor (Consumer<Action> okLambda, String gridShape) {
        myName = "";
        myAttackRange = new LinkedList<Point2D.Double>();
        myEffectRange = new LinkedList<Point2D.Double>();
        myStatsLogics = new LinkedList<StatsTotalLogic>();
        myConclusion = null;
        initEditor(okLambda, gridShape);
    }

    /**
     * Constructor for an ActionCreator popup window
     * 
     * @param actionData
     *        - class where user-created Actions are stored
     */
    public ActionEditor (Consumer<Action> okLambda, Action action, String gridShape) {
        myName = action.toString();
        myAttackRange = action.getActionRange();
        myEffectRange = action.getEffectRange();
        myStatsLogics = new LinkedList<StatsTotalLogic>(); // should read in logics
        myConclusion = null; // should set to empty conclusion (not null)

        setHeight(HEIGHT);
        setWidth(WIDTH);
        initEditor(okLambda, gridShape);
    }

    protected void initEditor(Consumer<Action> okLambda, String gridShape) {
        myOkLambda = okLambda;
        
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
        VBox operationsVBox = new VBox();
        VBox conclusionVBox = new VBox();

        // Action name
        TextField nameField = new TextField();
        nameField.setMaxWidth(WIDTH - WIDTH / 4 - 10);
        initNameField(nameVBox, nameField);
        // Set the Action Range
        initSetRangeButton(rangeVBox, "Action Range:", myAttackRange);
        // Set the Effect Range
        initSetRangeButton(rangeVBox, "Effect Range (Splashzone):", myEffectRange);
        // Operations
        initStatsOperationsBox(operationsVBox);
        // conclusions
        initConclusionsBox(conclusionVBox);

        Button createBtn = new Button("Create new action");
        createBtn.setMaxWidth(WIDTH - WIDTH / 4 - 10);
        createBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                myName = nameField.getText();
                myStatsLogics = getStatsLogics(myTotalLogicBoxes);
                Action action = new ConcreteAction 
                		(myName, myAttackRange, myEffectRange, myStatsLogics, myConclusion);
                myOkLambda.accept(action);
            }
        });

        box.getChildren().addAll(labelBox, nameVBox, rangeVBox, new Separator(),
                                 operationsVBox, new Separator(), conclusionVBox,
                                 new Separator(), createBtn);
        getChildren().add(box);

    }
    
    public List<StatsTotalLogic> getStatsLogics (List<TotalLogicBox> totalLogic) {
        List<StatsTotalLogic> stlList = new LinkedList<StatsTotalLogic>();
        for (TotalLogicBox tlb : totalLogic) {
     	   stlList.add(tlb.getStatsLogic());
        }
        return stlList;
    }
    
    private void initStatsOperationsBox (VBox operationsVBox) {
    	
    	
    	
    	Button addOpBtn = new Button ("Add new operation");
    	addOpBtn.setOnAction(new EventHandler<ActionEvent>() {
    		
			@Override
			public void handle(ActionEvent event) {
				TotalLogicBox tlb = new TotalLogicBox();
	    		Button delOpBtn = new Button ("Delete operation");
			}
        	
    	});
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


    private void initNameField (VBox nameBox, TextField nameField) {
        Label nameLabel = new Label("Action name");
        nameField.setPromptText("Enter action name");
        nameBox.getChildren().addAll(nameLabel, nameField);
    }

    //TODO: shape should be passed in
    private void initSetRangeButton (VBox rangeBox, String label, List<Point2D.Double> range) {
        Label rangeLabel = new Label(label);
        Button setRange = new Button("Set Range...");
        setRange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                PopupWindow actionRangeEditor = new RangeEditor(range, myGridShape);
                actionRangeEditor.show();
            }
        });
        rangeBox.getChildren().addAll(rangeLabel, setRange);
        
    }

//    protected void setAttackRange (List<Point2D> attackRange) {
//        myAttackRange = attackRange;
//    }
//
//    protected void setEffectRange (List<Point2D> effectRange) {
//        myEffectRange = effectRange;
//    }
}

