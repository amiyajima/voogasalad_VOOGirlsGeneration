package authoring.concretefeatures;

import gamedata.gamecomponents.Patch;
import authoring.abstractfeatures.PopupWindow;

/**
 * @author Martin Tamayo
 * 
 * GUI component for editing the properties of a given terrain.
 * This can be accessed by clicking on the hyperlink of the
 * terrain's name in the LibraryView of the game authoring
 * environment.
 */
public class TerrainEditor extends PopupWindow {
	
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String NAME = "Terrain Editor";
	private Patch myTerrain;
	
	/**
	 * Constructor that sets the dimensions of the TerrainEditor
	 * GUI component and initializes it.
	 * 
	 * @param terrain : Patch class of the terrain to edit.
	 */
	public TerrainEditor(Patch terrain){
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		myTerrain = terrain;
		initialize();
	}
	
	@Override
	protected void initialize() {
		// TO DO: Make terrain editor window.
	}
}