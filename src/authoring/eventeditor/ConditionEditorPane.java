package authoring.eventeditor;

import gamedata.events.conditions.Condition;
import gamedata.events.conditions.IsDead;
import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gameengine.player.Player;
import java.util.List;
import java.util.function.Consumer;
import utilities.reflection.Reflection;
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

public class ConditionEditorPane extends Pane{

	private static final int WIDTH_OFFSET = 30;
	private static final String COMPONENTS_LABEL = "Expression";
	private static final String DONE_LABEL = "Done";
	private static final Insets MARGINS = new Insets(5, WIDTH_OFFSET, 10, WIDTH_OFFSET);
	private static final String LABEL_CSS = "-fx-font-size: 14pt;";

	private ChoiceBox<String> myRefType = new ChoiceBox<>();
	private ChoiceBox<IHasStats> myRefName = new ChoiceBox<>();
	private ChoiceBox<String> myStat = new ChoiceBox<>();
	private TextField myVal = new TextField();

	private Consumer<Condition> myDoneLambda;
	private Condition myCondition;
	private EventsDataWrapper myData;
	private Class<?> myClass;

	public ConditionEditorPane(Consumer<Condition> doneLambda, EventsDataWrapper data, Class<?> c){
		myDoneLambda = doneLambda;
		myData = data;
		myClass = c;
		initialize();
	}

	/**
	 * References the specific values specified by the ChoiceBox and TextField. Then 
	 * constructs a Condition object from those values.
	 * 
	 * Constructs the Condition using REFLECTION
	 * @param doneButton
	 */
	private void initDoneButton(Button doneButton) {
		doneButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {

				IHasStats ref = myRefName.getSelectionModel().getSelectedItem();
				String stat = myStat.getSelectionModel().getSelectedItem();
				String val = myVal.getText();

				String classPath = myClass.toString();
				classPath = classPath.substring(6);

				if("gamedata.events.conditions.IsDead".equals(classPath)){
					myCondition = new IsDead(val);
				}
				else{
					myCondition = (Condition) Reflection.createInstance(classPath, ref, stat, val);
				}

				myDoneLambda.accept(myCondition);
			}

		});
	}

	private void setUpComponents(ChoiceBox<String> typeBox,
			ChoiceBox<IHasStats> componentBox, ChoiceBox<String> statBox, TextField valueField) {
		typeBox.getItems().addAll("Piece", "Patch", "Player");
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
			/**
			 * Selects between the three types of input into this Condition
			 */
			switch(type){
				case "Piece": 
				{
					List<Piece> pieceTypes = myData.getPieceTypes();
					refName.getItems().addAll(pieceTypes);
					break;
				}
				case "Patch":
				{
					List<Patch> patchTypes = myData.getPatchTypes();
					refName.getItems().addAll(patchTypes);
					break;  
				}
				case "Player":
				{
					List<Player> players = myData.getPlayers();
					refName.getItems().addAll();
					break;
				}

			}
		}

		private void setupStatsBox(IHasStats source, ChoiceBox<String> box){
			box.getItems().addAll(source.getStats().getStatNames());
			box.getItems().addAll("Location");
		}

		/**
		 * Initialize all the GUI nonsense. Please ignore this for masterpiece. 
		 */
		private void initialize(){
			myRefType.setVisible(true);
			myRefName.setVisible(true);
			myStat.setVisible(true);
			myVal.setPromptText("Enter constant value");

			String classPath = myClass.toString();
			classPath = classPath.substring(6);
						
			if("gamedata.events.conditions.IsDead".equals(classPath)){
				myRefType.setVisible(false);
				myRefName.setVisible(false);
				myStat.setVisible(false);
				myVal.setPromptText("Enter name of health stat");
			}
			
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

			Label componentsLabel = new Label(COMPONENTS_LABEL);

			setUpComponents(myRefType, myRefName, myStat, myVal);

			leftHandSide.getChildren().addAll(myRefType, myRefName, myStat, myVal);

			components.getChildren().addAll(componentsLabel, leftHandSide);

			Button doneButton = new Button(DONE_LABEL);
			initDoneButton(doneButton);

			box.getChildren().addAll(labelBox, names, components, doneButton);

			getChildren().add(box);
		}
	}
