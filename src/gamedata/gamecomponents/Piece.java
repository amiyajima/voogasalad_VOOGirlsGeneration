package gamedata.gamecomponents;

import gamedata.action.Action;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import java.util.LinkedList;
import java.util.List;
import java.awt.geom.Point2D;

/**
 * Class for pieces. Pieces are the primary unit for game play. They have
 * movement and can carry out various actions during the game.
 * 
 * @authors Sandy Lee, Jesse Ling, Jennie Ju, Martin Tamayo
 */
public class Piece extends GridComponent {
	
	/**
	 * The unit's movement range.
	 */
	private Movement myMovement;
	
	/**
	 * List containing the Actions a piece can execute
	 */
	private List<Action> myActions;
	
	/**
	 * Contains the stats for a piece
	 */
	private Stats myStats;
	
	/**
	 * ID of the player whom owns the piece
	 */
	private int myPlayerID;
	
	/**
	 * Tag for garbage collection to remove the piece
	 */
	private boolean myShouldRemove;
	
	/**
	 * Inventory of the piece
	 */
	private transient Inventory myInventory;

	/**
	 * Piece constructor
	 * 
	 * @param id
	 *            - Unique string ID for the piece or patch.
	 * @param imageLoc
	 *            - url of the piece's image location
	 * @param movement
	 *            - List of Movement defining how/where the piece moves relative
	 *            to its current position
	 * @param actions
	 *            - List of Actions defining what actions are available for each
	 *            piece to perform
	 * @param stats
	 *            - the Piece's stats, already defined
	 * @param loc
	 *            - Point2D containing the piece's current coordinates
	 * @param tid
	 *            - Piece's type ID, serves as a reference to this type of piece
	 * @param uid
	 *            - Piece's unique ID, serves as a reference to this specific
	 *            instance of piece
	 * @param playerID
	 *            - Piece's player ID, serves as a reference to which player
	 *            this piece belongs to
	 * @param inventory
	 *            - Piece's inventory if the user chooses to use an inventory
	 */
	public Piece(String id, String name, String imageLoc, Movement movement, List<Action> actions,
			Stats stats, Point2D.Double loc, int playerID, Inventory inventory) {
		super(id, name, imageLoc, loc);
		myMovement = movement;
		myActions = actions;
		myStats = stats;
		myPlayerID = playerID;
		myShouldRemove = false;
		myInventory = inventory;
	}

	/**
	 * Cloning constructor for deep cloning of a piece
	 * 
	 * @param clone
	 *            - Piece instance to be cloned
	 */
	public Piece(Piece clone, Point2D.Double placeHere) {
		super(clone, placeHere);
		myMovement = clone.myMovement;
		myActions = new LinkedList<Action>(clone.myActions);
		myStats = new Stats(clone.myStats);
		myPlayerID = clone.myPlayerID;
		myShouldRemove = false;
		myInventory = null; // TODO: NOPE. NO INVENTORY.
	}

	/**
	 * Returns the int ID for the player controlling this piece
	 */
	public int getPlayerID() {
		return myPlayerID;
	}

	/**
	 * Returns the piece's stats
	 */
	public Stats getStats() {
		return myStats;
	}
	
	/**
	 * Getter method for unit's Movement
	 */
	public Movement getMovement() {
		return myMovement;
	}
	
	/**
	 * Adds an Action to the piece's list of Actions
	 */
	public void addAction(Action a) {
		if (!myActions.contains(a)) {
			myActions.add(a);
		}
	}

	/**
	 * Removes an Action from the piece's list of Actions
	 */
	public void removeAction(Action a) {
		myActions.remove(a);
	}

	/**
	 * Returns the list of the piece's available actions. Takes into account
	 * inventory if relevant
	 * 
	 * @return List of available actions
	 */
	public List<Action> getActions() {
		List<Action> actions = new LinkedList<Action>(myActions);
		actions.add(0, myMovement);
		if (myInventory != null) {
			actions.addAll(myInventory.getItemActions());
		}
		return actions;
	}

	/**
	 * Marks the myShouldRemove boolean to true to flag for piece removal from
	 * board
	 */
	public void markForRemoval() {
		myShouldRemove = true;
	}

	/**
	 * Checks if the piece should be removed
	 * 
	 * @return boolean for whether or not the piece should be removed
	 */
	public boolean shouldRemove() {
		return myShouldRemove;
	}

	/**
	 * Adds an item to the inventory as long as there is an inventory and the
	 * item added is not the piece holding the inventory.
	 * 
	 * @param item
	 *            - piece to be added to inventory
	 * @return boolean stating whether item was added
	 */
	public boolean addToInventory(Piece item) {
		if (myInventory != null && item != this) {
			myInventory.addItem(item);
			return true;
		}
		return false;
	}

	/**
	 * Removes an item form the inventory as long as there is an inventory and
	 * the item added is not the piece holding the inventory.
	 * 
	 * @param item
	 *            - piece to be removed from the inventory
	 */
	public void removeFromInventory(Piece item) {
		if (myInventory != null && item != this) {
			myInventory.removeItem(item);
		}
	}

	/**
	 * Returns the inventory that the piece contains
	 * 
	 * @return Inventory of the Piece
	 */
	public Inventory getInventory() {
		return myInventory;
	}

	/**
	 * returns a uniqueID identifier
	 * 
	 * @return
	 */
	public Object getUniqueID() {
		return myName.hashCode();
	}

	/**
	 * Setter to set the player ID who owns the piece
	 * 
	 * @param id
	 *            int representing player ID
	 */
	public void setPlayerID(int id) {
		myPlayerID = id;
	}
	
	@Override
	public String toString() {
		String myString = "Piece #" + myID + " " + myName + " Location: " + "(" + myLoc.getX() + " " + myLoc.getY() + ")" 
				+ "\n  Image: " + myImageLocation + " Movement: " + myMovement.toString() 
				+ "\n  Actions: " + myActions.toString();
		return myString;
	}
}
