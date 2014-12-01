package gamedata.gamecomponents;

import gamedata.action.Action;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import java.util.LinkedList;
import java.util.List;
import java.awt.geom.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Class for pieces. Pieces are the primary unit for
 * game play. They have movement and can carry out
 * various actions during the game.
 * 
 * @authors Sandy Lee, Jesse Ling, Jennie Ju
 *
 */
public class Piece {

	private String myName;
    private String myImageLocation;
    private transient ImageView myImageView;
    private List<Action> myActions;
    private List<Movement> myPath;
    private Stats myStats;
    private Point2D myLoc;
    private int myPlayerID;
    private boolean myShouldRemove;
    private Inventory myInventory;

    /**
     * Piece constructor
     * 
     * @param imageLoc - url of the piece's image location
     * @param movement - List of Movement defining how/where the
     *        piece moves relative to its current position
     * @param actions - List of Actions defining what actions are available
     *        for each piece to perform
     * @param stats - the Piece's stats, already defined
     * @param loc - Point2D containing the piece's current coordinates
     * @param tid - Piece's type ID, serves as a reference to this type of piece
     * @param uid - Piece's unique ID, serves as a reference to this specific instance of piece
     * @param playerID - Piece's player ID, serves as a reference to which player
     *        this piece belongs to
     * @param inventory - Piece's inventory if the user chooses to use an inventory
     */

    //TODO: Think about playerID concept
    public Piece (String name, String imageLoc, List<Movement> movement, 
    		List<Action> actions, Stats stats, Point2D loc, int playerID, Inventory inventory) {
    	myName = name;
        myImageLocation = imageLoc;
        setImageView(myImageLocation);
        myPath = movement;
        myActions = actions;
        myStats = stats;
        myLoc = loc;
        myPlayerID = playerID;
        myShouldRemove = false;
        myInventory = inventory;
    }

    /**
     * Cloning constructor for deeping cloning of a piece
     * @param clone - Piece instance to be cloned
     */
    public Piece(Piece clone) {
    	myName = clone.myName;
    	myImageLocation = clone.myImageLocation;
    	setImageView(myImageLocation);
    	myPath = new LinkedList<Movement>(clone.myPath);
    	myActions = new LinkedList<Action>(clone.myActions);
    	myStats = new Stats(clone.myStats);
    	myLoc = new Point2D.Double(clone.myLoc.getX(),clone.myLoc.getY());
    	myPlayerID = clone.myPlayerID;
    	myShouldRemove = false;
    	myInventory = null; // TODO: NOPE. NO INVENTORY.
    }
    
    private void setImageView(String imageLocation) {
    	if(myImageLocation.startsWith("/")){
        	myImageView = new ImageView(new Image(getClass().getResourceAsStream(imageLocation)));
        }
        else{
        	myImageView = new ImageView(new Image(imageLocation));
        }
    }
    
    /**
     * Returns the name for this type of piece
     */
    public String getName () {
        return myName;
    }
    
    /**
     * Returns the image location url (for data saving)
     */
    public String getImageLocation () {
        return myImageLocation;
    }

    /**
     * Returns the ImageView of the piece for display
     */
    public ImageView getImageView () {
        return myImageView;
    }

    /**
     * Returns the int ID for the player controlling this piece
     */
    public int getPlayerID () {
        return myPlayerID;
    }

    /**
     * Sets the piece's location to the specified Point2D
     * 
     * @param p - Point2D of the piece's new location
     */
    public void setLoc (Point2D p) {
        myLoc = p;
    }

    /**
     * Returns the Point2D indicating the piece's coordinates
     */
    public Point2D getLoc () {
        return myLoc;
    }

    /**
     * Returns the piece's stats
     */
    public Stats getStats () {
        return myStats;
    }

//    public double getStat (String s) {
//        if (myStats.getStatsMap().containsKey(s)) { return myStats.getStatsMap().get(s); }
//        return 0;
//    }

    /**
     * Adds an Action to the piece's list of Actions
     */
    public void addAction (Action a) {
    	if(!myActions.contains(a)){
    		myActions.add(a);
    	}
    }

    /**
     * Removes an Action from the piece's list of Actions
     */
    public void removeAction (Action a) {
        myActions.remove(a);
    }

    /**
     * Returns the list of the piece's
     * available actions.
     * Takes into account inventory if relevant
     * 
     * @return List of available actions
     */
    public List<Action> getActions () {
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
    public void markForRemoval () {
        myShouldRemove = true;
    }

    /**
     * Checks if the piece should be removed
     * 
     * @return boolean for whether or not the
     *         piece should be removed
     */
    public boolean shouldRemove () {
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
    public boolean addToInventory (Piece item) {
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
    public void removeFromInventory (Piece item) {
        if (myInventory != null && item != this) {
            myInventory.removeItem(item);
        }
    }
    
    public Inventory getInventory(){
    	return myInventory;
    }

    //TODO this was throwing an error, temporary fix
    public Object getUniqueID () {
        return myName.hashCode();
    }

}
