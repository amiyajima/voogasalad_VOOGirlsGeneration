package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
	private Map<String, VBox> myLibraryMap;
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
		this.setPrefSize(HEIGHT, WIDTH);
		unitSelected = false;
		reset = true;
		
		Tab unitTab = new Tab(UNITS);
		unitTab.setClosable(false);
		ScrollPane unitContent = new ScrollPane();
		VBox unitLibrary = new VBox();
		Button unitDelete = new Button(DELETE);
		unitDelete.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				unitSelected = true;
				reset = true;
			}
		});
		unitLibrary.getChildren().add(unitDelete);
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
		myLibraryMap.get(library).getChildren().add(content);
	}
}