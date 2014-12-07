package authoring.eventeditor;

import gamedata.gamecomponents.Patch;

import java.awt.geom.Point2D;
import java.util.function.Consumer;

import authoring_environment.UIspecs;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EqualsEditorPane extends Pane{

	private static final int HEIGHT = 150;
	private static final int WIDTH = 150;
	private static final String NAME_LABEL = "Name";
	private static final String NAME_PROMPT = "Enter Condition name...";
	private static final String COMPONENTS_LABEL = "Expression";
	private static final String EQUALS_LABEL = "EQUALS";
	private static final Insets MARGINS = new Insets(5, WIDTH / 5, 10, WIDTH / 5);
	private static final String LABEL_CSS = "-fx-font-size: 14pt;";

	private Consumer<Patch> myOkLambda;

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
		ChoiceBox<String> refType1 = new ChoiceBox<>();
		ChoiceBox<String> refName1 = new ChoiceBox<>();
		TextField statName1 = new TextField();

		refType1.getItems().addAll("Piece", "Patch", "Player", "Constant");
		refType1.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, selectedType) -> setUpEditor(oldValue, selectedType, refName1, statName1));

		refName1.getItems().addAll(); 

		
		ChoiceBox<String> refType2 = new ChoiceBox<>();
		ChoiceBox<String> refName2 = new ChoiceBox<>();
		TextField statName2 = new TextField();
		
		refType2.getItems().addAll("Piece", "Patch", "Player", "Constant");
		refType2.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, selectedType) -> setUpEditor(oldValue, selectedType, refName1, statName1));

		refName2.getItems().addAll(); 
		
		Label equalsLabel = new Label(EQUALS_LABEL);

		leftHandSide.getChildren().addAll(refType1, refName1, statName1, equalsLabel);
		rightHandSide.getChildren().addAll(refType2, refName2, statName2);
		
		components.getChildren().addAll(componentsLabel, leftHandSide, rightHandSide);

		box.getChildren().addAll(labelBox, names, components);

		getChildren().add(box);
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
