package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.util.HashMap;
import java.util.Map;

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
	private Map<String, VBox> myLibraryMap;
	private Map<String, Tab> myTabMap;
	private SingleSelectionModel<Tab> mySelection;
	public static Piece currentlySelectedUnit;
	public static Patch currentlySelectedTerrain;
	public static boolean unitSelected;
	public static boolean reset;
	
	/**
	 * LibraryView constructor. Initializes two tabs - one for units,
	 * one for terrain. Units and terrain are added dynamically to
	 * their respective tabs as they are created in the UnitCreator
	 * and TerrainCreator.
	 */
	public LibraryView(){
		mySelection = this.getSelectionModel();
		this.setPrefSize(HEIGHT, WIDTH);
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
		this.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e){
				if(e.getCode() == KeyCode.ESCAPE){
					unitSelected = false;
				}
			}
		});
		unitLibrary.getChildren().addAll(new Label("Global Commands"),new HBox(unitEdit,unitDelete),new Separator()
		,new Label("Piece Templates"));
		unitContent.setContent(unitLibrary);
		unitTab.setContent(unitContent);
		
		Tab terrainTab = new Tab(TERRAIN);
		terrainTab.setClosable(false);
		ScrollPane terrainContent = new ScrollPane();
		VBox terrainLibrary = new VBox();
		Button terrainDelete = new Button(DELETE);
		terrainDelete.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				unitSelected = false;
				reset = true;
			}
		});
		terrainLibrary.getChildren().add(terrainDelete);
		terrainContent.setContent(terrainLibrary);
		terrainTab.setContent(terrainContent);
		
		this.getTabs().addAll(unitTab, terrainTab);
		myLibraryMap = new HashMap<String, VBox>();
		myLibraryMap.put(UNITS, unitLibrary);
		myLibraryMap.put(TERRAIN, terrainLibrary);
		myTabMap = new HashMap<String, Tab>();
		myTabMap.put(UNITS, unitTab);
		myTabMap.put(TERRAIN, terrainTab);
	}
	
	/**
	 * Method to add units and terrain to their respective tabs in
	 * the LibraryView.
	 * 
	 * @param content : The LibraryEntry to be added to the library.
	 * @param library : Specifies whether to add the content to the
	 * "Unit Library" or the "Terrain Library".
	 */
	public void addToLibrary(HBox content, String library){
		mySelection.select(myTabMap.get(library));
		myLibraryMap.get(library).getChildren().add(content);
	}
	
	public void removeFromLibrary(HBox content, String library){
		myLibraryMap.get(library).getChildren().remove(content);
	}
}