package authoring.concretefeatures;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import authoring.abstractfeatures.PopupWindow;

/**
 * @author Martin Tamayo, Jennie Ju
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
	
	private int myPlayerID;
	private Stats myStats;
	private List<Action> myActions;
	
	/**
	 * Constructor that sets the dimensions of the UnitEditor
	 * GUI component and initializes it.
	 * 
	 * @param unit : Piece class of the unit to edit.
	 */
	public UnitEditor(Piece unit){
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		myUnit = unit;
		
		myPlayerID = myUnit.getPlayerID();
		myStats = myUnit.getStats();
		myActions = myUnit.getActions();
		
		initialize();
	}
	
	@Override
	protected void initialize() {
		ScrollPane root = new ScrollPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		
	}
}