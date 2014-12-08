package fxml_main;

import authoring_environment.GUIGrid;


/**
 * Works as a reference to the current GUIGrid
 * so that the current GUIGrid can be replaced while
 * the controller classes maintain the same pointer
 * to the same GUIGridReference.
 * 
 * Needed because Java can only pass by value :(
 * 
 * @author Jennie Ju
 *
 */
public class GUIGridReference {
	private static final GUIGrid DEFAULT_GRID = new GUIGrid(0,0,0,"Square Grid");
	private GUIGrid myGrid;
	
	public GUIGridReference() {
		// set to default grid
		resetGrid();
	}
	
	public void resetGrid() {
		myGrid = DEFAULT_GRID;
	}
	
	public GUIGrid getGrid() {
		return myGrid;
	}
	
	public void setGrid(GUIGrid grid) {
		myGrid = grid;
	}
}
