package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.util.HashMap;
import java.util.Map;

import authoring.concretefeatures.TerrainEntry;
import authoring.concretefeatures.UnitEntry;
import authoring.data.PatchData;
import authoring.data.PieceData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author VOOGirls Generation
 * 
 * GUI components for the library displayed on the left side of the
 * game authoring environment, which contains all instantiated units
 * and terrain. From here, the user can open the Unit/TerrainEditors
 * to edit the units and terrain, as well as select them for
 * placement on the grid.
 */
public class LibraryView extends TabPane {
	
	private final int HEIGHT = 300;
	private final int WIDTH = 580;
	private final String UNITS = "Units";
	private final String TERRAIN = "Terrain";
	private final String DELETE = "Delete";
	private final String EDIT = "Edit";
	private final String GLOBAL = "Global Commands";
	private final String PIECES = "Piece Templates";
	private final String PATCHES = "Patch Templates";
	private SandyGrid myGrid;
	private PieceData myPieceData;
	private PatchData myPatchData;
	private Map<String, VBox> myLibraryMap;
	private Map<String, Tab> myTabMap;
	private SingleSelectionModel<Tab> mySelection;
	private Piece currentUnit;
	private Patch currentTerrain;
	private boolean doNothing;
	private boolean unitSelected;
	private boolean reset;
	
	/**
	 * LibraryView constructor. Initializes two tabs - one for units,
	 * one for terrain. Units and terrain are added dynamically to
	 * their respective tabs as they are created in the UnitCreator
	 * and TerrainCreator.
	 */
	public LibraryView(PieceData pieceData, PatchData patchData, SandyGrid grid){
		mySelection = this.getSelectionModel();
		this.setPrefSize(HEIGHT, WIDTH);
		myPieceData = pieceData;
		myPatchData = patchData;
		myGrid = grid;
		doNothing = true;
		unitSelected = false;
		reset = true;
		
		Tab unitTab = new Tab(UNITS);
		unitTab.setClosable(false);
		ScrollPane unitContent = new ScrollPane();
		VBox unitLibrary = new VBox();
		unitLibrary.setSpacing(3);
		Button unitDelete = new Button(DELETE);
		Button unitEdit = new Button(EDIT);
		unitDelete.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				unitSelected = true;
				reset = true;
			}
		});
		unitEdit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				//TODO: Implement Editing 
			}
		});
		unitLibrary.getChildren().addAll(new Label(GLOBAL),
				new HBox(unitEdit, unitDelete), new Separator(), new Label(PIECES));
		unitContent.setContent(unitLibrary);
		unitTab.setContent(unitContent);
		
		Tab terrainTab = new Tab(TERRAIN);
		terrainTab.setClosable(false);
		ScrollPane terrainContent = new ScrollPane();
		VBox terrainLibrary = new VBox();
		terrainLibrary.setSpacing(3);
		Button terrainDelete = new Button(DELETE);
		Button terrainEdit = new Button(EDIT);
		terrainDelete.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				unitSelected = false;
				reset = true;
			}
		});
		terrainEdit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				//TODO: Implement Editing 
			}
		});
		terrainLibrary.getChildren().addAll(new Label(GLOBAL),
				new HBox(terrainEdit, terrainDelete), new Separator(), new Label(PATCHES));
		terrainContent.setContent(terrainLibrary);
		terrainTab.setContent(terrainContent);
		
		this.getTabs().addAll(unitTab, terrainTab);
		this.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e){
				if(e.getCode() == KeyCode.ESCAPE){
					doNothing = true;
				}
			}
		});
		myLibraryMap = new HashMap<String, VBox>();
		myLibraryMap.put(UNITS, unitLibrary);
		myLibraryMap.put(TERRAIN, terrainLibrary);
		myTabMap = new HashMap<String, Tab>();
		myTabMap.put(UNITS, unitTab);
		myTabMap.put(TERRAIN, terrainTab);
		setGridActionEvents();
	}
	
	private void setGridActionEvents() {
		myGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				myGrid.handleSingleClick(event, currentUnit, currentTerrain,
						doNothing, unitSelected, reset);
			}
		});
		myGrid.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				myGrid.handleDrag(event, currentUnit, currentTerrain,
						doNothing, unitSelected, reset);
			}
		});
	}
	
	public void selectUnit(Piece unit){
		currentUnit = unit;
		doNothing = false;
		unitSelected = true;
		reset = false;
	}
	
	public void selectTerrain(Patch terrain){
		currentTerrain = terrain;
		doNothing = false;
		unitSelected = false;
		reset = false;
	}

	public void addPiece(UnitEntry unit){
		mySelection.select(myTabMap.get(UNITS));
		myLibraryMap.get(UNITS).getChildren().add(unit);
		myPieceData.add(unit.getUnit());
	}
	
	public void addPatch(TerrainEntry terrain){
		mySelection.select(myTabMap.get(TERRAIN));
		myLibraryMap.get(TERRAIN).getChildren().add(terrain);
		myPatchData.add(terrain.getTerrain());
	}
	
	public void removePiece(UnitEntry unit){
		myPieceData.remove(unit.getUnit());
		myLibraryMap.get(UNITS).getChildren().remove(unit);
		myGrid.removePieces(unit.getUnit());
		doNothing = true;
	}
	
	public void removePatch(TerrainEntry terrain){
		myPatchData.remove(terrain.getTerrain());
		myLibraryMap.get(TERRAIN).getChildren().remove(terrain);
		myGrid.removePatches(terrain.getTerrain());
		doNothing = true;
	}
}