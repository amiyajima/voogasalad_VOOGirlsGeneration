package authoring.concretefeatures;

import authoring.abstractfeatures.PopupWindow;

public class TerrainEditor extends PopupWindow {
	
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String NAME = "Terrain Editor";
	
	public TerrainEditor(){
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		initialize();
	}
	
	@Override
	protected void initialize() {
		
	}
}