package authoring.eventeditor;

import gamedata.events.GlobalAction;
import gamedata.events.globalaction.LevelChangeGlobalAction;
import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.util.List;
import java.util.function.Consumer;

import authoring.data.EventsDataWrapper;
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


public class ActionEditorPane extends Pane {

	private static final int WIDTH_OFFSET = 30;
	private static final String NAME_LABEL = "Name";
	private static final String NAME_PROMPT = "Enter Condition name...";
	private static final String COMPONENTS_LABEL = "Expression";
	private static final String AT_LABEL = "At Point";
	private static final String DONE_LABEL = "Done";
	private static final Insets MARGINS = new Insets(5, WIDTH_OFFSET, 10, WIDTH_OFFSET);
	private static final String LABEL_CSS = "-fx-font-size: 14pt;";

	private TextField myNameField = new TextField();

	private ChoiceBox<String> refType1 = new ChoiceBox<>();
	private ChoiceBox<IHasStats> refName1 = new ChoiceBox<>();
	private TextField pointX = new TextField();
	private TextField pointY = new TextField();

	private Consumer<GlobalAction> myDoneLambda;
	private GlobalAction myGlobalAction;
	private EventsDataWrapper myData;


	public ActionEditorPane (Consumer<GlobalAction> doneLambda, EventsDataWrapper data) {
		myDoneLambda = doneLambda;
		myData = data;
		initialize();
	}

	public ActionEditorPane (Consumer<GlobalAction> doneLambda, GlobalAction globalAction) {
		myDoneLambda = doneLambda;
		myGlobalAction = globalAction;
		initialize();
		updateFields(globalAction);
	}

	// TODO: Implement this so we can UPDATE Conditions, in addition to creating and deleting them
	private void updateFields (GlobalAction globalAction) {

	}

	

	/**
	 * References the specific values specified by the ChoiceBox and TextField. Then
	 * constructs a Condition object from those values.
	 * 
	 * @param doneButton
	 */
	 private void initDoneButton(Button doneButton) {
		 doneButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle (ActionEvent click) {

				 String type1 = refType1.getSelectionModel().getSelectedItem();
				 IHasStats ref1 = refName1.getSelectionModel().getSelectedItem();
				 String stat1 = pointX.getText();

				 String type2 = refType1.getSelectionModel().getSelectedItem();

				 double val1 = getTargetValue(type1, ref1, stat1);
				 //                                double val2 = getTargetValue(type2, ref2, stat2);

				 //specify which instance variable we're looking for, then the value we're changing it to.
				 //the value is determined by the user
				 //                                myGlobalAction = new LevelChangeGlobalAction(, null);
				 myDoneLambda.accept(myGlobalAction);
			 }
		 });
	 }

	 /**
	  * Extracts a double from the given source. Handles two input cases:
	  * 1) Type equals Piece, Patch, or Player, and Source equals Stat name. References
	  * the given stat by name.
	  * 2) Type equals Constant, and Source equals a numerical value. Returns the number.
	  * 
	  * @param type = Piece, Patch, Player, or Constant
	  * @param ref = Specific IHasStats object that contains target stat
	  * @param source = Name of target stat
	  * @return
	  */
	 private double getTargetValue (String type, IHasStats ref, String source) {
		 double val;
		 if ("Constant".equals(type)) {
			 val = Double.parseDouble(source);
		 }
		 else {
			 val = ref.getStats().getValue(source);
		 }
		 return val;
	 }

	 private void setUpComponents (ChoiceBox<String> refType,
			 ChoiceBox<IHasStats> refName1, TextField statName1) {
		 refType.getItems().addAll("Piece", "Patch");
		 refType.getSelectionModel()
		 .selectedItemProperty()
		 .addListener(
				 (observable, oldValue, selectedType) -> setUpEditor(oldValue,
						 selectedType,
						 refName1,
						 statName1));
	 }

	 private void setUpEditor (String oldType,
			 String type,
			 ChoiceBox<IHasStats> refName,
			 TextField statName) {
		 statName.setPromptText("Enter stat name");
		 if ("Constant".equals(oldType)) {
			 refName.setVisible(true);
		 }

		 // TODO: IMPLEMENT THIS SHIT. instead of the switch statement can we get all of the piece's
		 // instance variables or something?
		 // or like each thing can have a list of stats and we display each of the things contained
		 // in stats regardless of what it is.
		 // and if its a constant, its stat is just its value.
		 /**
		  * Selects between the four types of input into this Condition
		  */
		 switch (type) {
			 case "Piece": {
				 List<Piece> pieceTypes = myData.getPieceTypes();
				 refName.getItems().addAll(pieceTypes);
				 break;
			 }
			 case "Patch": {
				 List<Patch> patchTypes = myData.getPatchTypes();
				 refName.getItems().addAll(patchTypes);
				 break;
			 }
		 }
	 }
	 
	 private void initialize () {
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

			Label nameLabel = new Label(NAME_LABEL);
			nameLabel.setPadding(UIspecs.topRightPadding);
			myNameField = new TextField();
			myNameField.setPromptText(NAME_PROMPT);
			names.getChildren().addAll(nameLabel, myNameField);

			Label componentsLabel = new Label(COMPONENTS_LABEL);

			setUpComponents(refType1, refName1, pointX);

			Label atLabel = new Label(AT_LABEL);

			leftHandSide.getChildren().addAll(refType1, refName1, atLabel, pointX);

			components.getChildren().addAll(componentsLabel, leftHandSide);

			Button doneButton = new Button(DONE_LABEL);
			initDoneButton(doneButton);

			box.getChildren().addAll(labelBox, names, components, doneButton);

			getChildren().add(box);
		}
}
