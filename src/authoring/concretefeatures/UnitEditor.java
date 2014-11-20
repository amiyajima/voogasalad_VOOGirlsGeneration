package authoring.concretefeatures;

import gamedata.gamecomponents.Piece;
import authoring.abstractfeatures.PopupWindow;

public class UnitEditor extends PopupWindow {
	
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String NAME = "Unit Editor";
	private Piece myUnit;
	
	public UnitEditor(Piece unit){
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		myUnit = unit;
		initialize();
	}
	
	@Override
	protected void initialize() {
		// TO DO: Make unit editor window.
	}
}