package gamedata.gamecomponents;

import gamedata.action.Action; 
import gamedata.stats.Stats;
import gameengine.movement.Movement;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.ImageView;

public class Piece {

	private int myTypeID;
	private int myUniqueID;
	private int myPlayerID;
	private ImageView myImageView;

	private Point2D myLoc;
	private Stats myStats;
	private List<Action> myActions;
	private List<Movement> myPath;
	private boolean myShouldRemove;
	private Inventory myInventory;


	public Piece(ImageView i, List<Movement> m, List<Action> a, Stats stats,
			Point2D p, int pid, Inventory inventory) {
		myImageView = i;
		myPath = m;
		myActions = a;
		myStats = stats;
		myLoc = p;
		myPlayerID = pid;
		myShouldRemove = false;
		myInventory = inventory;
	}

	public ImageView getImageView() {
		return myImageView;
	}

	public int getTypeID() {
		return myTypeID;
	}

	public int getUniqueID() {
		return myUniqueID;
	}

	public int getPlayerID() {
		return myPlayerID;
	}

	public void setLoc(Point2D p) {
		myLoc = p;
	}

	public Point2D getLoc() {
		return myLoc;
	}

	public Stats getStats() {
		return myStats;
	}
	
	public double getStat(String s){
		if(myStats.getStatsMap().containsKey(s)){
			return myStats.getStatsMap().get(s);
		}
		return 0;
	}

	public void addAction(Action a) {
		myActions.add(a);
	}

	public void removeAction(Action a) {
		myActions.remove(a);
	}

	/**
	 * Returns the list of the piece's
	 * available actions.
	 * Takes into account inventory if relevant
	 * @return List of available actions
	 */
	public List<Action> getActions() {
		List<Action> actions = new LinkedList<Action>(myActions);
		if (myInventory != null) {
			actions.addAll(myInventory.getItemActions());
		}
		return actions;
	}

	/**
	 * Marks the myShouldRemove boolean to true
	 * to flag for piece removal from board
	 */
	public void markForRemoval() {
		myShouldRemove = true;
	}

	/**
	 * Checks if the piece should be removed
	 * 
	 * @return boolean for whether or not the
	 * piece should be removed
	 */
	public boolean shouldRemove() {
		return myShouldRemove;
	}

	/**
	 * Adds an item to the inventory as long as
	 * there is an inventory and the item added
	 * is not the piece holding the inventory.
	 * 
	 * @param item - piece to be added to inventory
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
	 * Removes an item form the inventory as long as
	 * there is an inventory and the item added
	 * is not the piece holding the inventory.
	 * 
	 * @param item - piece to be removed from the inventory
	 */
	public void removeFromInventory(Piece item) {
		if (myInventory != null && item != this) {
			myInventory.removeItem(item);
		}
	}
	
}
