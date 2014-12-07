package authoring.eventeditor;

import gamedata.events.Condition;
import gamedata.events.conditions.IfEquals;
import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Patch;

import java.util.function.Consumer;

import authoring_environment.UIspecs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ConditionEditorPane extends Pane{

	private static final int WIDTH_OFFSET = 30;
	private static final String NAME_LABEL = "Name";
	private static final String NAME_PROMPT = "Enter Condition name...";
	private static final String COMPONENTS_LABEL = "Expression";
	private static final String EQUALS_LABEL = "EQUALS";
	private static final String DONE_LABEL = "Done";
	private static final Insets MARGINS = new Insets(5, WIDTH_OFFSET, 10, WIDTH_OFFSET);
	private static final String LABEL_CSS = "-fx-font-size: 14pt;";

	private TextField myName = new TextField();
	
	private ChoiceBox<String> refType1 = new ChoiceBox<>();
	private ChoiceBox<IHasStats> refName1 = new ChoiceBox<>();
	private TextField statName1 = new TextField();
	
	private ChoiceBox<String> refType2 = new ChoiceBox<>();
	private ChoiceBox<IHasStats> refName2 = new ChoiceBox<>();
	private TextField statName2 = new TextField();
	
	private Consumer<Condition> myDoneLambda;
	private Condition myCondition;

	public ConditionEditorPane(Consumer<Condition> doneLambda){
		myDoneLambda = doneLambda;
		initialize();
	}
	
	public ConditionEditorPane (Consumer<Condition> doneLambda, Condition condition) {
		myDoneLambda = doneLambda;
		myCondition = condition;
		initialize();
	}

	private void initialize(){
		VBox box = new VBox();
		box.setPadding(MARGINS);
		box.setSpacing(10);

		HBox labelBox = new HBox();
		Label eventsLabel = new Label();
		eventsLabel.setStyle(LABEL_CSS);
		labelBox.getChildren().add(eventsLabel);

		HBox names = new HBox();
		names.setPadding(UIspecs.allPadding);
		names.setSpacing(5);

		VBox components = new VBox();
		
		HBox leftHandSide = new HBox();
		leftHandSide.setPadding(UIspecs.allPadding);
		leftHandSide.setSpacing(5);
		
		HBox rightHandSide = new HBox();
		rightHandSide.setPadding(UIspecs.allPadding);
		rightHandSide.setSpacing(5);

		Label nameLabel = new Label(NAME_LABEL);
		nameLabel.setPadding(UIspecs.topRightPadding);
		myName = new TextField();
		myName.setPromptText(NAME_PROMPT);
		names.getChildren().addAll(nameLabel, myName);

		Label componentsLabel = new Label(COMPONENTS_LABEL);
		
		setUpComponents(refType1, refName1, statName1);
		setUpComponents(refType2, refName2, statName2);
		
		Label equalsLabel = new Label(EQUALS_LABEL);

		leftHandSide.getChildren().addAll(refType1, refName1, statName1, equalsLabel);
		rightHandSide.getChildren().addAll(refType2, refName2, statName2);
		
		components.getChildren().addAll(componentsLabel, leftHandSide, rightHandSide);

		Button doneButton = new Button(DONE_LABEL);
		initDoneButton(doneButton);

		box.getChildren().addAll(labelBox, names, components, doneButton);

		getChildren().add(box);
	}

	private void initDoneButton(Button doneButton) {
		doneButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle (ActionEvent click) {
				
				IHasStats ref1 = refName1.getSelectionModel().getSelectedItem();
				String stat1 = statName1.getText();
				
				IHasStats ref2 = refName2.getSelectionModel().getSelectedItem();
				String stat2 = statName2.getText();
				 
				myCondition = new IfEquals(myName.getText(), ref1, stat1, ref2, stat2);
				//TODO: construct myCondition
				myDoneLambda.accept(myCondition);
			 }
		 });
	}

	private void setUpComponents(ChoiceBox<String> refType1,
			ChoiceBox<IHasStats> refName1, TextField statName1) {
		refType1.getItems().addAll("Piece", "Patch", "Player", "Constant");
		refType1.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, selectedType) -> setUpEditor(oldValue, selectedType, refName1, statName1));
	}

	private void setUpEditor(String oldType, String type, ChoiceBox<IHasStats> refName, TextField statName) {
		statName.setPromptText("Enter stat name");
		if("Constant".equals(oldType)){
			refName.setVisible(true);
		}
		
		//TODO: IMPLEMENT THIS SHIT. instead of the switch statement can we get all of the piece's instance variables or something?
		//or like each thing can have a list of stats and we display each of the things contained in stats regardless of what it is.
		//and if its a constant, its stat is just its value. 
		/**
		 * Selects between the four types of input into this Condition
		 */
		switch(type){
			case "Piece": 
			{
				break;
			}
			case "Patch":
			{
				break;	
			}
			case "Player":
			{
				break;
			}
			case "Constant": 
			{
				refName.setVisible(false);
				statName.setPromptText("Enter constant value");
				break;
			}
		}
	}
}
