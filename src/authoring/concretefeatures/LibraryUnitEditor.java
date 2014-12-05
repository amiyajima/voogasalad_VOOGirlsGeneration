package authoring.concretefeatures;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import authoring.createedit.ModulesList;
import authoring.data.ActionData;

/**
 * @author Martin Tamayo, Jennie Ju
 * 
 * GUI component for editing the stats of a given unit.
 * This can be accessed by clicking on the hyperlink of
 * the unit's name in the LibraryView of the game
 * authoring environment.
 */
public class LibraryUnitEditor extends TitledPane {

	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	private final String NAME = "Unit Editor";
	private final String UNIT_NAME_LABEL = "Name";
	private final String IMAGE_LABEL = "Unit image";
	private final String LOAD_IMAGE_LABEL = "Load image";
	private final String TEMPLATE_LABEL = "Update unit";
	private final String DELETE = "Delete";
	private final String EDIT = "Edit";
	
	private Piece myUnit;

	private Stats myStats;
	private List<Action> myActions;

	private ActionData myAvailableActions;

	/**
	 * Constructor that sets the dimensions of the UnitEditor
	 * GUI component and initializes it.
	 * 
	 * @param unit : Piece class of the unit to edit.
	 * @param myAvailableActions 
	 */
	public LibraryUnitEditor(Piece unit, ActionData availableActions){
		
		myUnit = unit;

		myStats = myUnit.getStats();
		myActions = myUnit.getActions();

		myAvailableActions = availableActions;
		
		ScrollPane root = new ScrollPane();

		VBox mainVBox = new VBox();

		Button setStatsBtn = new Button("Set Stats...");
		setStatsBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StatsTotalEditor edit = new StatsTotalEditor(myStats);
				edit.show();
			}
		});

		//DIFFERENT FROM UNIT CREATOR
		ObservableList<String> addedActionNames = FXCollections.observableArrayList();
		for(Action a: myActions){
			addedActionNames.add(a.toString());
		}

		ModulesList modList = new ModulesList(myAvailableActions.getActionNames(), addedActionNames);

		Button goButton = new Button(TEMPLATE_LABEL);
		goButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				myActions = addSelectedActions(modList.getSelectedActions());  
				for(Action a: myActions){
					myUnit.addAction(a);
				}
				setContent(null);
			}
		});

		mainVBox.getChildren().addAll(setStatsBtn, modList, goButton);
		root.setContent(mainVBox);
		setContent(root);
	}

	protected List<Action> addSelectedActions(List<String> selected){
		List<Action> list = new ArrayList<>();
		for(String s: selected){
			list.add(myAvailableActions.getAction(s));
		}
		return list;
	}
}