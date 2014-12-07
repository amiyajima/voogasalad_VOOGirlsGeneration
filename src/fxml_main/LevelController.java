package fxml_main;

import gamedata.gamecomponents.Level;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.LevelData;
import authoring_environment.GUIGrid;

/**
 * 
 * @author Jennie Ju
 *
 */
public class LevelController extends GridComponentAbstCtrl<Level> {
	private ScrollPane myGridSPane;
	private LevelData myLevelData;
	
	protected LevelController (VBox vbox, ScrollPane propertiesSPane,
			ScrollPane gridSPane, GUIGrid currGrid, LevelData levels) {
		super(vbox, propertiesSPane, currGrid);
		myGridSPane = gridSPane;
		myLevelData = levels;
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
		Consumer<Level> okLambda = (Level level) -> {
			addEntry(level);
			myLevelData.add(level);
			setAndDisplayGrid(level);
			};
		super.myPropertiesSPane.setContent(new LevelEditor(okLambda));
	}
	
	private void setAndDisplayGrid(Level level) {
		myCurrentGrid = level.getGrid();
		myCurrentGrid.displayPane(myGridSPane);
	}

	@Override
	protected void initGlobalEditBtn(Button editBtn) {
		//do nothing
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
				setAndDisplayGrid(entry);
			}
		});
		entryBox.getChildren().add(nameLabel);
		return entryBox;
	}

	@Override
	protected void initEntryEditBtn(Level entry, Button editBtn) {
		Consumer<Level> okLambda = (Level level) -> {
			myLevelData.replace(entry, level);
			
		    HBox entryBox = myEntryMap.get(entry);
		    HBox imgNameBox = myIndivEntMap.get(entryBox);
		    
		    entryBox.getChildren().remove(imgNameBox);
		    HBox newImgNameBox = makeEntryBox(level);	
		    
		    entryBox.getChildren().add(newImgNameBox);
		    myIndivEntMap.replace(entryBox, newImgNameBox);
		};
		myPropertiesSPane.setContent(new LevelEditor(okLambda, entry));
	}

	@Override
	protected void initEntryDelBtn(Level entry, Button delBtn) {
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myLevelData.remove(entry);
				myVBox.getChildren().remove(myEntryMap.get(entry));
			}
		});
	}
}
