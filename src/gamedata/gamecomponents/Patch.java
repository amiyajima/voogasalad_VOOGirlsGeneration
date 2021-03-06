package gamedata.gamecomponents;

import gamedata.stats.Stats;
import java.awt.geom.Point2D;

/**
 * Class for Patches. Represents a given patch at a given (x,y) position within
 * the grid.
 * 
 * @author Jesse, Sandy Lee, Mengen Huang, Martin Tamayo
 *
 */
public class Patch extends GridComponent {

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Unique string ID for the piece or patch.
	 * @param typeID
	 *            ID for this type of patch
	 * @param imageLocation
	 *            imageLocation of patch(form like "images/myImage.jpg")
	 * @param p
	 *            coordinate of patch
	 */
	public Patch(String id, String name, String imageLocation, Point2D.Double p) {
		super(id, name, imageLocation, p);
	}

	/**
	 * Deep cloning constructor for a Patch
	 * 
	 * @param clone
	 *            - Patch instance to be cloned
	 */
	public Patch(Patch clone, Point2D.Double placeHere) {
		super(clone, placeHere);
	}

	@Override
	public Stats getStats() {
		return null;
	}
	
	@Override
	public String toString() {
	    return myName;
	}
	
	/**
	 * Print string for debugging
	 * @return
	 */
	public String printString() {
		String myString = "Patch #" + myID + " " + myName;
		myString += "loc:(" + myLoc.getX() + " " + myLoc.getY() + ") ";
		myString += myImageLocation;
		return myString;
	}
}
