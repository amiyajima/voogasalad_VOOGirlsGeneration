package authoring.eventeditor;

import gamedata.events.Condition;
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

public class EqualsEditorPane extends Pane{

	private static final int WIDTH_OFFSET = 30;
	private static final String NAME_LABEL = "Name";
	private static final String NAME_PROMPT = "Enter Condition name...";
	private static final String COMPONENTS_LABEL = "Expression";
	private static final String EQUALS_LABEL = "EQUALS";
	private static final String DONE_LABEL = "DONE";
	private static final Insets MARGINS = new Insets(5, WIDTH_OFFSET, 10, WIDTH_OFFSET);
	private static final String LABEL_CSS = "-fx-font-size: 14pt;";

	private Consumer<Condition> myDoneLambda;

	private ChoiceBox<String> refType1 = new ChoiceBox<>();
	private ChoiceBox<String> refName1 = new ChoiceBox<>();
	private TextField statName1 = new TextField();
	
	private ChoiceBox<String> refType2 = new ChoiceBox<>();
	private ChoiceBox<String> refName2 = new ChoiceBox<>();
	private TextField statName2 = new TextField();
	
	public EqualsEditorPane(){
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
		TextField terrainName = new TextField();
		terrainName.setPromptText(NAME_PROMPT);
		names.getChildren().addAll(nameLabel, terrainName);

		Label componentsLabel = new Label(COMPONENTS_LABEL);
		
		

		setUpComponents(refType1, refName1, statName1);
		setUpComponents(refType2, refName2, statName2);
		
		Label equalsLabel = new Label(EQUALS_LABEL);

		leftHandSide.getChildren().addAll(refType1, refName1, statName1, equalsLabel);
		rightHandSide.getChildren().addAll(refType2, refName2, statName2);
		
		components.getChildren().addAll(componentsLabel, leftHandSide, rightHandSide);

		box.getChildren().addAll(labelBox, names, components);
		
		Button doneButton = new Button(DONE_LABEL);
		initDoneButton(doneButton);


		getChildren().add(box);
	}

	private void initDoneButton(Button doneButton) {
		doneButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle (ActionEvent click) {
				
				myDoneLambda.accept(null);
			 }
		 });
	}

	private void setUpComponents(ChoiceBox<String> refType1,
			ChoiceBox<String> refName1, TextField statName1) {
		refType1.getItems().addAll("Piece", "Patch", "Player", "Constant");
		refType1.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, selectedType) -> setUpEditor(oldValue, selectedType, refName1, statName1));
	}

	private void setUpEditor(String oldType, String type, ChoiceBox<String> refName, TextField statName) {
		statName.setPromptText("Enter stat name");
		if("Constant".equals(oldType)){
			refName.setVisible(true);
		}
		
		//TODO: IMPLEMENT THIS SHIT
		/**
		 * Selects between the four types of input into this Condition
		 */
		switch(type){
			case "Piece": 
			{
				refName.getItems().addAll("Llama rancher", "Janitor");
				break;
			}
			case "Patch":
			{
				refName.getItems().addAll("Lava", "Rocks");
				break;	
			}
			case "Player":
			{
				refName.getItems().addAll("Player 1", "Player 2");
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
