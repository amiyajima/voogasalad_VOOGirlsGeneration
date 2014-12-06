package fxml_main;

import gamedata.events.Event;
import gamedata.goals.Goal;
import authoring.data.AuthoringLevel;
import java.util.LinkedList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.concretefeatures.GridEditor;
import authoring.data.AuthoringLevel;
import authoring_environment.GUIGrid;
import authoring_environment.ShapeGrid;
import com.sun.javafx.css.Rule;

/**
 * 
 * @author Jennie Ju
 *
 */
public class LevelController extends GridComponentAbstCtrl<ShapeGrid> {
	private ScrollPane myGridSPane;
	private List<AuthoringLevel> myLevels;

	protected LevelController (VBox vbox, ScrollPane propertiesSPane,
			ScrollPane gridSPane, GUIGrid currGrid, List<AuthoringLevel> myLevels) {
		super(vbox, propertiesSPane, currGrid);
		myGridSPane = gridSPane;
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
		AuthoringLevel newLevel = new AuthoringLevel(null, new LinkedList<Event>(), "myID");
		super.myPropertiesSPane.setContent(new LevelEditor(newLevel,this));
	}

	@Override
	protected void initGlobalEditBtn(Button editBtn) {

	}

	@Override
	protected void initGlobalDelBtn(Button delBtn) {
		//do nothing
	}


	@Override
	protected HBox makeEntryBox(ShapeGrid entry) {
		HBox entryBox = new HBox();

		Label nameLabel = new Label(entry.getID());
		entryBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			}
		});
		return entryBox;
	}
	
	

	@Override
	protected void initEntryEditBtn(ShapeGrid entry, Button editBtn) {
		
	}

	@Override
	protected void initEntryDelBtn(ShapeGrid entry, Button delBtn) {
		// TODO Auto-generated method stub

	}
}
