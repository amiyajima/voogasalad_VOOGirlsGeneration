package authoring.concretefeatures;

import gamedata.gamecomponents.Piece;
import authoring.abstractfeatures.PopupWindow;

/**
 * @author VOOGirls Generation
 * 
 * GUI component for editing the stats of a given unit.
 * This can be accessed by clicking on the hyperlink of
 * the unit's name in the LibraryView of the game
 * authoring environment.
 */
public class UnitEditor extends PopupWindow {
	
	private final int HEIGHT = 400;
	private final int WIDTH = 400;
	private final String NAME = "Unit Editor";
	private Piece myUnit;
	
	/**
	 * Constructor that sets the dimensions of the UnitEditor
	 * GUI component and initializes it.
	 * 
	 * @param terrain : Piece class of the unit to edit.
	 */
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