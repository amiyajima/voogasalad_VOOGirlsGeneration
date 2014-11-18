package authoring.concretefeatures;

import authoring.abstractfeatures.PopupWindow;

public class UnitEditor extends PopupWindow {
	
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String NAME = "Unit Editor";
	
	public UnitEditor(){
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		initialize();
	}
	
	@Override
	protected void initialize() {
		
	}
}