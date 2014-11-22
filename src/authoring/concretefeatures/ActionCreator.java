package authoring.concretefeatures;

import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring.data.ActionData;

/**
 * Opens a dialog for creating new Actions in the authoring environment
 * 
 * @author Mike Zhu, Rica Zhang, Jennie Ju
 *
 */
public class ActionCreator extends PopupWindow {

	public static final int HEIGHT = 400;
	public static final int WIDTH = 420;
	public static final String NAME = "Action Creator";
	private static final String STYLESHEET = "/resources/stylesheets/actioncreator_layout.css";

	private List<SingleMultiplierBox> operationsList;
	private ActionData myActionData;

	private String myName;
	private List<Point2D> myAttackRange;
	private List<Point2D> myEffectRange;
	private List<StatsTotalLogic> myStatsLogics;
	private ActionConclusion myConclusion;

	/**
	 * Constructor for an ActionCreator popup window
	 * 
	 * @param actionData
	 *            - class where user-created Actions are stored
	 */
	public ActionCreator(ActionData actionData) {
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
	protected void initialize() {
		ScrollPane root = new ScrollPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.getStylesheets().add(STYLESHEET);

		VBox mainVBox = new VBox();
		mainVBox.getStyleClass().add("vbox");
		mainVBox.setId("vbox-main");
		VBox nameVBox = new VBox();
		VBox rangeVBox = new VBox();
		rangeVBox.getStyleClass().add("vbox");
		VBox targetVBox = new VBox();
		VBox operationsVBox = new VBox();
		VBox conclusionVBox = new VBox();

		// Action name
		TextField nameField = new TextField();
		initNameField(nameVBox, nameField);
		// Set the Action Range
		initSetRangeButton(rangeVBox, "Action Range:", myAttackRange, this);
		// Set the Effect Range
		initSetRangeButton(rangeVBox, "Effect Range (Splashzone):",
				myEffectRange, this);
		// Target stat to be modified
		ChoiceBox<String> targetChoice = new ChoiceBox<String>();
		TextField moddedStat = new TextField();
		initStatsModifier(targetVBox, targetChoice, moddedStat);
		// Operations
		initOperationsBox(operationsVBox);

		Button create = new Button("Create new action");
		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent click) {
				myName = nameField.getText();
				myStatsLogics = getStatsLogics(targetChoice, moddedStat);
				myActionData.add(new ConcreteAction(myName, myAttackRange,
						myEffectRange, myStatsLogics, myConclusion));
				close(); // close window once action is made
			}
		});

		mainVBox.getChildren().addAll(nameVBox, rangeVBox, new Separator(),
				targetVBox, operationsVBox, new Separator(), conclusionVBox,
				new Separator(), create);
		root.setContent(mainVBox);
		setScene(scene);
	}

	// TODO: really need to take in multiple stats
	private List<StatsTotalLogic> getStatsLogics(
			ChoiceBox<String> targetChoice, TextField moddedStat) {
		String target = targetChoice.getValue();
		String stat = moddedStat.getText();

		List<StatsTotalLogic> stlList = new LinkedList<StatsTotalLogic>();
		List<StatsSingleMultiplier> multiplierLogic = getSingleMultipliers(operationsList);
		stlList.add(new StatsTotalLogic(target, stat, multiplierLogic));
		return stlList;
	}

	private List<StatsSingleMultiplier> getSingleMultipliers(
			List<SingleMultiplierBox> smbList) {
		List<StatsSingleMultiplier> ssmList = new LinkedList<StatsSingleMultiplier>();
		for (SingleMultiplierBox smb : smbList) {
			ssmList.add(smb.getSingleMultipler());
		}
		return ssmList;
	}

	private void initNameField(VBox nameBox, TextField nameField) {
		Label nameLabel = new Label("Action name");
		nameField.setPromptText("Enter action name");
		nameBox.getChildren().addAll(nameLabel, nameField);
	}

	private void initSetRangeButton(VBox rangeBox, String label,
			List<Point2D> myRange, ActionCreator ac) {
		Label rangeLabel = new Label(label);
		Button setRange = new Button("Set Range...");
		setRange.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				PopupWindow actionRangeEditor = new RangeEditor(ac,label);
				actionRangeEditor.show();
				// TODO: set myRange in here somewhere (within RangeEditor?)
			}
		});
		rangeBox.getChildren().addAll(rangeLabel, setRange);
	}

	private void initStatsModifier(VBox targetVBox,
			ChoiceBox<String> targetChoice, TextField moddedStat) {
		// TODO: Need to make it so that we can take in multiple target stats
		// Target whose stat will be modified
		Label targetLabel = new Label("Action target");
		targetChoice.getItems().addAll("Actor", "Receiver");

		// Particular statistic modified
		moddedStat.setPromptText("Stat to be modified");
		HBox targetAndStatHBox = new HBox();
		targetAndStatHBox.getChildren().addAll(targetChoice, moddedStat);
		targetVBox.getChildren().addAll(targetLabel, targetAndStatHBox);
	}

	private void initOperationsBox(VBox operationsBox) {
		Label operationsLabel = new Label("Operations to be performed");
		Button newOperation = new Button("New operation");
		operationsBox.getChildren().addAll(operationsLabel, newOperation);

		newOperation.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent click) {
				SingleMultiplierBox operation = new SingleMultiplierBox();
				operationsList.add(operation);
				operationsBox.getChildren().add(operation);
			}
		});
	}

	public void setAttackRange(List<Point2D> l) {
		myAttackRange = l;
	}

	public void setEffectRange(List<Point2D> l) {
		myEffectRange = l;
	}

}
