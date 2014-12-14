package gamedata.gamecomponents;

import gamedata.action.Action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// MIGHT WANT TO ADD MAX CAPACITY OR SOMETHING

/**
 * Inventory to be contained by a piece
 * if the user chooses to add an inventory to
 * a piece.
 * Inventory contains a list of pieces.
 * 
 * @authors Sandy Lee, Jennie Ju
 *
 */
public class Inventory {
	private List<Piece> myInventory;

	/**
	 * Constructor for inventory,
	 * initializes an empty inventory
	 */
	public Inventory () {
		myInventory = new LinkedList<Piece>();
	}

	/**
	 * Adds the indicated pieces to the inventory
	 * @param items - pieces to be added
	 */
	public void addItem (Piece item) {
		myInventory.add(item);    	
	}

	/**
	 * Removes the indicated pieces from the inventory
	 * @param items - pieces to be removed
	 */
	public void removeItem (Piece item) {
		myInventory.remove(item);
	}

	/**
	 * Set the inventory to an already filled list
	 * of pieces
	 * @param items - pieces in the inventory given
	 * as a list of pieces
	 */
	public void setInventory (List<Piece> items) {
		myInventory = new LinkedList<Piece>(items);
	}

	/**
	 * Checks whether inventory is empty or not
	 */
	public boolean isEmpty () {
		return myInventory.isEmpty();
	}

	/**
	 * Returns the actions contained in all the items
	 * as a list of Actions
	 * @return list of Actions contained in the items
	 */
	public List<Action> getItemActions() {
		List<Action> itemActions = new LinkedList<Action>();
		for (Piece item : myInventory) {
			itemActions.addAll(item.getActions());
		}
		return itemActions;
	}
	
	/**
	 * Returns a Map of Piece item name to List of Action names for displaying
	 * the inventory in the Game Player
	 * @return Map<String,List<String>> with Piece item name keys and
	 * Action name values
	 */
	public Map<String,List<String>> getStringDisplay() {
		Map<String,List<String>> strDisp = new HashMap<String,List<String>>();
		for (Piece item : myInventory) {
			String itemName = item.getName();
			List<String> actionNames = item.getActionNames();
			strDisp.put(itemName, actionNames);
		}
		return strDisp;
	}
	
	public List<Piece> getAllInventory(){
		return myInventory;
	}

}
