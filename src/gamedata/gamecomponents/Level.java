package gamedata.gamecomponents;

import gamedata.events.Event;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javafx.scene.control.ScrollPane;
import authoring_environment.GUIGrid;

/**
 * Rules define how a player's turn ends Goals define whether or not the level
 * has been won
 * 
 * @author Jesse, Mike
 *
 */
public class Level extends Observable {
	private static final String DEFAULT_ID = "Default";

	/**
	 * GUIGrid containing the state of the level
	 */
	private GUIGrid myGrid;
	/**
	 * Goals defining how to win the level, how to react to changes
	 */
	private List<Event> myEvents;
	/**
	 * ID Identifier for the Level
	 */
	private String myId;

	private boolean isWinningLevel;

	/**
	 * Constructs a default level with a default ID and sets it as NOT the
	 * winning level
	 */
	public Level() {
		this(new GUIGrid(), new ArrayList<Event>(), DEFAULT_ID, false);
	}

	/**
	 * Constructor to create a level
	 * 
	 * @param gr
	 *            GUIGrid Containing the Grid
	 * @param events
	 *            List of events to be applied to the level
	 * @param id
	 *            ID identifier for the Level
	 * @param isWinningLevel
	 *            Boolean indication whether winning the level triggers a win
	 *            clause
	 */
	public Level(GUIGrid gr, List<Event> events, String id,
			boolean isWinningLevel) {
		myGrid = gr;
		myEvents = events;
		myId = id;
		this.isWinningLevel = isWinningLevel;
		initializeDefaultEvents();
	}

	/**
	 * Initiates a default event for testing purposes.
	 */
	private void initializeDefaultEvents() {
		Event die = new Event(myId);
	}

	/**
	 * Runs events using the magic of laaaaaaaaaaambda
	 */
	public void runGameEvents() {
		for (Event e : myEvents) {
			BiConsumer<List<IHasStats>, GUIGrid> eventFunc = (
					List<IHasStats> list, GUIGrid grid) -> e.runEvent(list,
					grid);
			myGrid.runEvents(eventFunc);
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
	public List<Event> getEvents() {
		return myEvents;
	}

	/**
	 * toString method used to test JSON read/write
	 */
	public String toString() {
		String myString = "Level: Level #" + myId + " gamelost "
				+ " iswinning " + isWinningLevel;
		myString += "\ngrid:" + myGrid.toString() + " myEvents"
				+ myEvents.toString();
		return myString;
	}

	/**
	 * Adds an observer to the level
	 * 
	 * @param o
	 *            Observer to be added to the level
	 */
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

			Inventory i = p.getInventory();
			List<Piece> list = i.getAllInventory(); // Removes Items
			for (Piece p2 : list) {
				if (p2.shouldRemove()) {
					i.removeItem(p2);
				}
			}

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

	/**
	 * Restarts the level
	 */
	public void restart() {
		// TODO restarts level
	}

	/**
	 * Getter to get the ID representing the level
	 * 
	 * @return String containing the ID of the level
	 */
	public String getId() {
		return myId;
	}

}
