package gamedata.gamecomponents;

import gamedata.events.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.ScrollPane;
import authoring_environment.GUIGrid;

/**
 * Rules define how a player's turn ends Goals define whether or not the level
 * has been won
 *
 */
public class Level extends Observable {
	private static final String DEFAULT_ID = "Default";

	private GUIGrid myGrid;
	/**
	 * Goals defining how to win the level, how to react to changes
	 */
	private List<Event> myEvents;
	private String myId;

	/**
	 * Constructs a default level with a default ID and sets it as NOT the
	 * winning level
	 */
	public Level() {
		this(new GUIGrid(), new ArrayList<Event>(), DEFAULT_ID, false);
	}

	public Level(GUIGrid gr, List<Event> events, String id,
			boolean isWinningLevel) {
		myGrid = gr;
		myEvents = events;
		myId = id;
		// winningLevel = isWinningLevel;
	}

	public void runGameEvents() {
		for (Event e : myEvents) {
			e.runEvent();
		}
		this.garbageCollectPieces();
	}

	/**
	 * Returns the grid contained in this level.
	 * 
	 * @return
	 */
	public GUIGrid getGrid() {
		return myGrid;
	}
	
	/**
	 * Returns the List of Events contained in this level.
	 *
	 * @return
	 */
	public List<Event> getEvents(){
		return myEvents;
	}
	
	/**
	 * toString method used to test JSON read/write
	 */
	public String toString() {
		return "grid:" + myGrid.toString() + " myEvents" + myEvents.toString();
	}

	public void addObserverTo(Observer o) {
		addObserver(o);
	}

	/**
	 * Removes all pieces marked for removal
	 */
	public void garbageCollectPieces() {
		List<Piece> pieces = myGrid.getRemovedPieces();
		List<Piece> toRemove = new ArrayList<Piece>();
		for (Piece p : pieces) {

			/*Inventory i = p.getInventory();
			List<Piece> list = i.getAllInventory();
			// Removes Items
			for (Piece p2 : list) {
				if (p2.shouldRemove()) {
					i.removeItem(p2);
				}
			}*/
			
			// Removes Pieces (Tagging)
			if (p.shouldRemove()) {
				toRemove.add(p);
			}

		}
		// GarbageCollection to Remove Pieces
		for (Piece p : toRemove) {
			myGrid.removePiece(p);
		}
	}

	public void restart() {
		// TODO restarts level
	}

	public String getId() {
		return myId;
	}

	/*
	 * public boolean isWinningLevel() { return winningLevel; }
	 */

}
