package authoring.eventeditor;

import gamedata.events.Condition;
import gamedata.events.conditions.ConditionEquals;
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

	private TextField myNameField = new TextField();

	private ChoiceBox<String> myRefType1 = new ChoiceBox<>();
	private ChoiceBox<IHasStats> myRefName1 = new ChoiceBox<>();
	private ChoiceBox<String> myStat1 = new ChoiceBox<>();
	private TextField myVal1 = new TextField();

	private ChoiceBox<String> myRefType2 = new ChoiceBox<>();
	private ChoiceBox<IHasStats> myRefName2 = new ChoiceBox<>();
	private ChoiceBox<String> myStat2 = new ChoiceBox<>();
	private TextField myVal2 = new TextField();

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
		updateFields(condition);
	}

	//TODO: Implement this so we can UPDATE Conditions, in addition to creating and deleting them
	private void updateFields(Condition condition) {

	}

	/**
	 * References the specific values specified by the ChoiceBox and TextField. Then 
	 * constructs a Condition object from those values.
	 * @param doneButton
	 */
	 private void initDoneButton(Button doneButton) {
		doneButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {

				String type1 = myRefType1.getSelectionModel().getSelectedItem();
				IHasStats ref1 = myRefName1.getSelectionModel().getSelectedItem();
				String stat1 = myStat1.getSelectionModel().getSelectedItem();
				String value1 = myVal1.getText();

				String type2 = myRefType1.getSelectionModel().getSelectedItem();
				IHasStats ref2 = myRefName2.getSelectionModel().getSelectedItem();
				String stat2 = myStat2.getSelectionModel().getSelectedItem();
				String value2 = myVal2.getText();

				double referenceVal1 = getTargetValue(type1, ref1, stat1, value1);
				double referenceVal2 = getTargetValue(type2, ref2, stat2, value2);
				
				myCondition = new ConditionEquals(myNameField.getText(), referenceVal1, referenceVal2);
				myDoneLambda.accept(myCondition);
			}


		});
	 }

	/**
	 * Extracts a double from the given source. Handles two input cases:
	 * 1) Type equals Piece, Patch, or Player, and Source equals Stat name. References 
	 * the given stat by name.
	 * 2) Type equals Constant, and Source equals a numerical value. Returns the number.
	 * @param type = Piece, Patch, Player, or Constant
	 * @param ref = Specific IHasStats object that contains target stat
	 * @param source = Name of target stat
	 * @return
	 */
	 private double getTargetValue(String type, IHasStats ref, String source, String value){
		 double val;
		 if("Constant".equals(type)){
			 val = Double.parseDouble(value);    	
		 }
		 else{
			 val = ref.getStats().getValue(source);
		 }
		 return val;
	 }

	 private void setUpComponents(ChoiceBox<String> typeBox,
			 ChoiceBox<IHasStats> componentBox, ChoiceBox<String> statBox, TextField valueField) {
		 typeBox.getItems().addAll("Piece", "Patch", "Player", "Constant");
		 typeBox.getSelectionModel().selectedItemProperty().addListener(
				 (observable, oldValue, selectedType) -> setupEditor(oldValue, selectedType, componentBox, statBox, valueField));
		 componentBox.getSelectionModel().selectedItemProperty().addListener(
				 (observable, oldValue, selectedComponent) -> setupStatsBox(selectedComponent, statBox));
	 }

	 /**
	  * 
	  * @param oldType
	  * @param type
	  * @param refName
	  * @param statName
	  * @param value
	  */
	 private void setupEditor(String oldType, String type, ChoiceBox<IHasStats> refName, ChoiceBox<String> statName, TextField value) {
		 value.setVisible(false);
		 if("Constant".equals(oldType)){
			 refName.setVisible(true);
			 statName.setVisible(true);
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
				 statName.setVisible(false);
				 value.setVisible(true);
				 value.setPromptText("Enter constant value");
				 break;
			 }
		 }
	 }
	 
	 private void setupStatsBox(IHasStats source, ChoiceBox<String> box){
		 box.getItems().addAll(source.getStats().getStatNames());
	 }
	 
	 /**
	  * Initialize all the GUI nonsense. Please ignore this for masterpiece. 
	  */
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
			myNameField = new TextField();
			myNameField.setPromptText(NAME_PROMPT);
			names.getChildren().addAll(nameLabel, myNameField);

			Label componentsLabel = new Label(COMPONENTS_LABEL);

			setUpComponents(myRefType1, myRefName1, myStat1, myVal1);
			setUpComponents(myRefType2, myRefName2, myStat2, myVal2);

			Label equalsLabel = new Label(EQUALS_LABEL);

			leftHandSide.getChildren().addAll(myRefType1, myRefName1, myStat1, myVal1);
			rightHandSide.getChildren().addAll(myRefType2, myRefName2, myStat2, myVal2);

			components.getChildren().addAll(componentsLabel, leftHandSide, equalsLabel, rightHandSide);

			Button doneButton = new Button(DONE_LABEL);
			initDoneButton(doneButton);

			box.getChildren().addAll(labelBox, names, components, doneButton);

			getChildren().add(box);
		}
}
