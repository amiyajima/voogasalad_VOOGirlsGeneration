package fxml_main;

import gamedata.action.ActionConclusion;
import gamedata.action.ActionStatsEditor;
import gamedata.action.ConcreteAction;
import gamedata.action.StatsTotalLogic;
import gamedata.action.conclusions.DoNothingConclusion;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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
import utilities.Reflection;
import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.RangeEditor;
import authoring.data.GamePropertiesData;


/**
 * @author seungwonlee, Jennie Ju
 *
 */
public class ActionEditor extends Pane {

	private static final int HEIGHT = 100;
	private static final int WIDTH = 100;
	
	private static final String STYLESHEET = "/resources/stylesheets/actioncreator_layout.css";
	private static final Insets MARGINS = new Insets(20, WIDTH / 8, 20, WIDTH / 8 - 10);
	private static final String LABEL_CSS = "-fx-font-size: 12pt;";
	private static final String CONCLUSION_LABEL = "Choose action conclusion";

	private String mySceneTitle;
	
	private String myName;
	private List<Point2D.Double> myAttackRange;
	private List<Point2D.Double> myEffectRange;
	private List<StatsTotalLogic> myStatsLogics;
	private ActionConclusion myConclusion;

	private Consumer<ConcreteAction> myOkLambda;
	private String myGridShape;

	public ActionEditor (Consumer<ConcreteAction> okLambda, String gridShape) {
		myName = "";
		myAttackRange = new LinkedList<Point2D.Double>();
		myEffectRange = new LinkedList<Point2D.Double>();
		myStatsLogics = new LinkedList<StatsTotalLogic>();
		myConclusion = new DoNothingConclusion();
		
		mySceneTitle = "Action Creator";
		initEditor(okLambda, gridShape);
	}

	/**
	 * Constructor for an ActionCreator popup window
	 * 
	 * @param actionData
	 *        - class where user-created Actions are stored
	 */
	public ActionEditor (Consumer<ConcreteAction> okLambda, ConcreteAction action, String gridShape) {
		myName = action.getName();
		myAttackRange = action.getActionRange();
		myEffectRange = action.getEffectRange();
		myStatsLogics = action.getStatsLogics(); // should read in logics
		myConclusion = action.getConclusion(); // should set to empty conclusion (not null)
		mySceneTitle = "Action Editor";
		initEditor(okLambda, gridShape);
	}

	protected void initEditor(Consumer<ConcreteAction> okLambda, String gridShape) {
		myOkLambda = okLambda;
		myGridShape = gridShape;
		setHeight(HEIGHT);
		setWidth(WIDTH);
		ScrollPane root = new ScrollPane();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(STYLESHEET);

		VBox box = new VBox();
		box.setPadding(MARGINS);
		box.setSpacing(10);

		HBox labelBox = new HBox();
		Label label = new Label(mySceneTitle);
		label.setStyle(LABEL_CSS);
		labelBox.getChildren().add(label);

		VBox nameVBox = new VBox();
		VBox rangeVBox = new VBox();
		rangeVBox.setSpacing(5);
		rangeVBox.getStyleClass().add("vbox");
		VBox conclusionVBox = new VBox();
		ChoiceBox<ActionConclusion> conclusionChoiceBox = new ChoiceBox<ActionConclusion>();

		// Action name
		TextField nameField = new TextField();
		nameField.setText(myName);
		nameField.setMaxWidth(WIDTH - WIDTH / 4 - 10);
		initNameField(nameVBox, nameField);
		// Set the Action Range
		initSetRangeButton(rangeVBox, "Action Range:", myAttackRange);
		// Set the Effect Range	
		initSetRangeButton(rangeVBox, "Effect Range (Splashzone):", myEffectRange);
		// Operations
		ActionStatsEditor statsEditor = new ActionStatsEditor(myStatsLogics);
		// conclusions
		initConclusionsBox(conclusionVBox,conclusionChoiceBox);

		Button createBtn = new Button("Create new action");
		createBtn.setMaxWidth(WIDTH - WIDTH / 4 - 10);
		createBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				myName = nameField.getText();
				myStatsLogics = statsEditor.getStatsTotalLogics();
				myConclusion = conclusionChoiceBox.getValue();
				ConcreteAction action = new ConcreteAction 
						(myName, myAttackRange, myEffectRange, myStatsLogics, myConclusion);
				myOkLambda.accept(action);
				
				for (Point2D.Double point: myAttackRange){
					System.out.println("dfasd");
					System.out.println(point.getX()+","+point.getY());
				}
				
			}
		});

		box.getChildren().addAll(labelBox, nameVBox, rangeVBox, new Separator(),
				statsEditor, new Separator(), conclusionVBox,
				new Separator(), createBtn);
		getChildren().add(box);
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
				
//				Point2D.Double p=new Point2D.Double(1.0,1.0);
//				range.add(p);
				Consumer<List<Point2D.Double>> consumer=(List<Point2D.Double> rg)->{
					range.clear();
					range.addAll(rg);
				};
				
				RangeEditor actionRangeEditor = new RangeEditor(range, consumer, myGridShape);
				actionRangeEditor.show();
			}
		});
		rangeBox.getChildren().addAll(rangeLabel, setRange);
	}

	private void initConclusionsBox (VBox conclusionVBox, ChoiceBox<ActionConclusion> choiceBox) {
		Label conclusionLabel = new Label(CONCLUSION_LABEL);
		List<String> conclusionClassNames;
		try {
			conclusionClassNames = Arrays.asList(ClassGrabber.getClassNames("gamedata.action.conclusions"));
		}
		catch (ClassNotFoundException | IOException e) {
			//TODO: Display error
			conclusionClassNames = new ArrayList<String>();
		}
		conclusionClassNames = trimClassList(conclusionClassNames);
		for (String c : conclusionClassNames) {
			ActionConclusion conclusion = (ActionConclusion) Reflection.createInstance(c);
			if (conclusion.toString().equals(myConclusion.toString())) {
				choiceBox.getSelectionModel().select(conclusion);
			}
			choiceBox.getItems().addAll(conclusion);
		}
		conclusionVBox.getChildren().addAll(conclusionLabel, choiceBox);
	}

	private List<String> trimClassList (List<String> conclusionNames) {
		List<String> displayList = new ArrayList<>();
		String prefix = "class ";
		for (String s : conclusionNames) {
			if (s.startsWith(prefix)) {
				String trimmed = s.substring(prefix.length());
				displayList.add(trimmed);
			}
		}
		return displayList;
	}
}


