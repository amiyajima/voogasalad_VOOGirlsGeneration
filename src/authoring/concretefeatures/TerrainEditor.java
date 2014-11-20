package authoring.concretefeatures;

import gamedata.gamecomponents.Patch;
import authoring.abstractfeatures.PopupWindow;

public class TerrainEditor extends PopupWindow {
	
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String NAME = "Terrain Editor";
	private Patch myTerrain;
	
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