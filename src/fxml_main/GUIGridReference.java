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
	private GUIGrid myGrid;
	
	public GUIGridReference() {
		//default grid
		myGrid = new GUIGrid(0,0,0,"Square Grid");
	}
	
	public GUIGrid getGrid() {
		return myGrid;
	}
	
	public void setGrid(GUIGrid grid) {
		myGrid = grid;
	}
}
