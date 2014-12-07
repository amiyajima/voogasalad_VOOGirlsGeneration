package authoring.data;

import gamedata.events.Event;

import java.util.List;

import authoring_environment.GUIGrid;

/**
 * Stores data for levels in the authoring environment.
 * Needs getter and setter methods so that data can
 * be manipulated by the user.
 * 
 * @author Jennie Ju
 *
 */
//TODO: Consolidate this with the GameData Level
public class AuthoringLevel {

	private GUIGrid myGrid;
	private List<Event> myEvents;
	private String myId;

	public AuthoringLevel(GUIGrid grid, List<Event> events, String id) {
		myGrid = grid;
		myEvents = events; // figure out what to do with these
		myId = id;
	}

	/**
	 * Gets the ID of this AuthoringLevel
	 * 
	 * @return String ID of this level
	 */
	public String getId() {
		return myId;
	}
	
	/**
	 * Returns number of rows
	 * @return int number of rows
	 */
	public int getNumRows() {
		return myGrid.getNumRows();
	}
	
	/**
	 * Returns number of columns
	 * @return int number of columns
	 */
	public int getNumCols() {
		return myGrid.getNumCols();
	}

	/**
	 * Sets the current grid to be edited by other authoring environment editors
	 * to the grid contained in this level.
	 * @param currGrid - pointer to the current grid to be edited
	 */
	public void setCurrentGrid(GUIGrid currGrid) {
		currGrid = myGrid;
	}

	public GUIGrid getGrid() {
	    return myGrid;
	}
	
	public List<Event> getEvents() {
	    return myEvents;
	}
}
