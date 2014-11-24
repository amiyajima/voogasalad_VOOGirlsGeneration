package authoring.concretefeatures;

import gamedata.gamecomponents.Patch;
import authoring.abstractfeatures.LibraryEntry;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * @author Martin Tamayo
 * 
 * An HBox containing data for a terrain patch. TerrainEntries are
 * added to the LibraryView in the game authoring environment.
 */
public class TerrainEntry extends LibraryEntry {
	
	private Patch myTerrain;
	
	/**
	 * Constructs the TerrainEntry, which is displayed as an HBox in the
	 * LibraryView in the game authoring environment. This class also
	 * contains the Patch class for the terrain.
	 * 
	 * @param image : ImageView of the image of the terrain.
	 * @param link : Name of terrain. Links to TerrainEditor.
	 * @param terrain : Actual Patch class for the terrain.
	 */
	public TerrainEntry(Patch terrain, ImageView image, Label name, Button editButton, Button delButton){
		this.getChildren().addAll(delButton, editButton, image, name);
		myTerrain = terrain;
	}
	
	/**
	 * Method to retrieve the Patch class contained within this TerrainEntry class.
	 * 
	 * @return Patch associated with the TerrainEntry.
	 */
	public Patch getTerrain(){
		return myTerrain;
	}
}