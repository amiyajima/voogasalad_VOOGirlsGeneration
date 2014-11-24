package gamedata.gamecomponents;

import java.awt.geom.Point2D;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Sandy Lee
 *
 */
public class Patch {

	private String myName;
	private Point2D myLoc;
	private String myImageLocation;
	private transient ImageView myImageView;

	/**
	 * Constructor
	 * 
	 * @param typeID
	 *            ID for this type of patch
	 * @param imageLocation
	 *            imageLocation of patch(form like "images/myImage.jpg")
	 * @param p
	 *            coordinate of patch
	 */
	public Patch(String name, String imageLocation, Point2D p) {
		myName = name;
		myImageLocation = imageLocation;
		if (myImageLocation.startsWith("/")) {
			myImageView = new ImageView(new Image(getClass()
					.getResourceAsStream(imageLocation)));
		} else {
			myImageView = new ImageView(new Image(imageLocation));
		}
		myLoc = p;
	}
	
	/**
	 * Deep cloning constructor for a Patch
	 * @param clone - Patch instance to be cloned
	 */
	public Patch(Patch clone) {
		myImageLocation = clone.myImageLocation;
		setImageView(myImageLocation);
		myLoc = new Point2D.Double(clone.myLoc.getX(),clone.myLoc.getY());
		myName = clone.myName;
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
	 * Getter for patch's name
	 * 
	 * @return String - patch name
	 */
	public String getName() {
		return myName;
	}

	/**
	 * Sets patch's name
	 * 
	 * @param myName = string name of patch
	 *            of patch
	 */
	public void setMyName(String name) {
		this.myName = name;
	}

	/**
	 * Getter for patch's coordinate location
	 * 
	 * @return coord of patch
	 */
	public Point2D getLoc() {
		return myLoc;
	}

	/**
	 * sets patch's coordinate location
	 * 
	 * @param coord
	 * 
	 */
	public void setLoc(Point2D coord) {
		myLoc = coord;
	}

	/**
	 * Getter for patch's image
	 * 
	 * @return image of the patch
	 */
	public ImageView getImageView() {
		return myImageView;
	}

	public String getImageLocation() {
		return myImageLocation;
	}

	/**
	 * Sets chosen image to patch's location
	 * 
	 * @param imageLocation
	 *            image file's location
	 */
	public void setMyImage(String imageLocation) {
		myImageLocation = imageLocation;
	}

}
