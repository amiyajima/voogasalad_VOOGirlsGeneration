package fxml_main;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gameengine.player.Player;

import java.util.Collections;
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
import authoring.data.EventsDataWrapper;
import authoring.data.LevelData;
import authoring.data.PatchTypeData;
import authoring.data.PieceTypeData;

/**
 * 
 * @author Jennie Ju
 *
 */
public class LevelController extends GridComponentAbstCtrl<Level> {
	private ScrollPane myGridSPane;
	private LevelData myLevelData;
	private PieceTypeData myPieceTypes;
	private PatchTypeData myPatchTypes;

	protected LevelController (VBox vbox, ScrollPane propertiesSPane,
			ScrollPane gridSPane, GUIGridReference gridRef, LevelData levels,
			PieceTypeData pieceTypes, PatchTypeData patchTypes) {
		super(vbox, propertiesSPane, gridRef);
		myGridSPane = gridSPane;
		myLevelData = levels;
		myPieceTypes = pieceTypes;
		myPatchTypes = patchTypes;
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
			myLevelData.add(level);			
			addEntry(level);

			addComponentObservingLevel(level);
			setAndDisplayGrid(level);
		};

		List<Piece> piecesRO = Collections.unmodifiableList(myPieceTypes.getData());
		List<Patch> patchesRO = Collections.unmodifiableList(myPatchTypes.getData());
		List<Player> playersRO = null;

		EventsDataWrapper wrapper = new EventsDataWrapper(piecesRO, patchesRO, playersRO);
		myPropertiesSPane.setContent(new LevelEditor(okLambda, wrapper));
	}

	@Override
	protected void initGlobalEditBtn(Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				myLevelData.getData().get(0).runGameEvents();
			}
		});
	}

	@Override
	protected void initGlobalDelBtn(Button delBtn) {
		delBtn.setVisible(false);
	}

	@Override
	protected HBox makeEntryBox(Level entry) {
		HBox entryBox = new HBox();
		Label nameLabel = new Label(entry.getId());
		// sets the current grid reference
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
		editBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent click) {
				Consumer<Level> okLambda = (Level level) -> {

					HBox entryBox = makeCompleteEntryBox(level);
					HBox entryHolderBox = myEntryMap.get(entry);
					entryHolderBox.getChildren().clear();
					entryHolderBox.getChildren().add(entryBox);
					myEntryMap.put(level, entryHolderBox);
					myPieceTypes.addObserver(level.getGrid());
					myPatchTypes.addObserver(level.getGrid());
					myLevelData.replace(entry, level);
					setAndDisplayGrid(level);
					myPropertiesSPane.setContent(null);
				};
				List<Piece> piecesRO = Collections.unmodifiableList(myPieceTypes.getData());
				List<Patch> patchesRO = Collections.unmodifiableList(myPatchTypes.getData());
				List<Player> playersRO = null;

				EventsDataWrapper wrapper = new EventsDataWrapper(piecesRO, patchesRO, playersRO);

				myPropertiesSPane.setContent(new LevelEditor(okLambda, entry, wrapper));
			}
		});
	}

	@Override
	protected void initEntryDelBtn(Level entry, Button delBtn) {
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myLevelData.remove(entry);
				myVBox.getChildren().remove(myEntryMap.get(entry));
				if (myGridReference.getGrid() == entry.getGrid()) {
					myGridReference.resetGrid();
					myGridReference.getGrid().displayPane(myGridSPane);
				}
				myPropertiesSPane.setContent(null);
			}
		});
	}
	
	private void setAndDisplayGrid(Level level) {
		myGridReference.setGrid(level.getGrid());
		myGridReference.getGrid().displayPane(myGridSPane);
	}
	
	private void addComponentObservingLevel(Level level) {
		myPieceTypes.addObserver(level.getGrid());
		myPatchTypes.addObserver(level.getGrid());
	}
}
