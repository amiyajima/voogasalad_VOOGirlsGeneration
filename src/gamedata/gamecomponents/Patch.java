package gamedata.gamecomponents;

import java.awt.geom.Point2D;
import javafx.scene.image.ImageView;

/**
 * @author Sandy Lee
 *
 */
public class Patch extends GridComponent {

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
		super(name, imageLocation, p);
	}
	
	/**
	 * Deep cloning constructor for a Patch
	 * @param clone - Patch instance to be cloned
	 */
	public Patch(Patch clone) {
		super(clone);
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