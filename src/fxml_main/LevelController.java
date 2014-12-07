package fxml_main;

import gamedata.events.Event;
import gamedata.gamecomponents.Level;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring_environment.GUIGrid;

/**
 * 
 * @author Jennie Ju
 *
 */
public class LevelController extends GridComponentAbstCtrl<Level> {
	private ScrollPane myGridSPane;
	private List<Level> myLevels;
	
	protected LevelController (VBox vbox, ScrollPane propertiesSPane,
			ScrollPane gridSPane, GUIGrid currGrid, List<Level> levels) {
		super(vbox, propertiesSPane, currGrid);
		myGridSPane = gridSPane;
		myLevels = levels;
	}

	@Override
	protected void initGlobalNewBtn(Button newBtn) {
		newBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				globalNewBtnOnClickAction();
			}
		});
	}
	
	private void globalNewBtnOnClickAction() {
		//TODO: Need to not hard-code square, have it passed through the constructor
		// as maybe a gridshapeproperty (new class?)
		Level newLevel = new Level(null, new LinkedList<Event>(), "myID", false);
		
		Consumer<Level> okLambda = (Level level) -> {
			addEntry(level);
			myCurrentGrid = level.getGrid();
			level.getGrid().displayPane(myGridSPane);
			};
		super.myPropertiesSPane.setContent(new LevelEditor(okLambda));
	}

	@Override
	protected void initGlobalEditBtn(Button editBtn) {

	}

	@Override
	protected void initGlobalDelBtn(Button delBtn) {
		//do nothing
	}


	@Override
	protected HBox makeEntryBox(Level entry) {
		HBox entryBox = new HBox();
		Label nameLabel = new Label(entry.getId());
		entryBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				myCurrentGrid = entry.getGrid();
				entry.getGrid().displayPane(myGridSPane);
			}
		});
		entryBox.getChildren().add(nameLabel);
		return entryBox;
	}
	
	

	@Override
	protected void initEntryEditBtn(Level entry, Button editBtn) {
		
	}

	@Override
	protected void initEntryDelBtn(Level entry, Button delBtn) {
//		myLevels.remove(index)
	}
}
