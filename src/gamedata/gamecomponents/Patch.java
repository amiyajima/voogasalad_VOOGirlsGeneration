package gamedata.gamecomponents;
 
import java.awt.geom.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Sandy Lee
 *
 */
public abstract class Patch {

	private int myTypeID;
	private Point2D myLoc;
	private String myImageLocation;
	private ImageView myImageView;


	/**
	 * Constructor
	 * 
	 * @param typeID ID for this type of patch
	 * @param imageLocation imageLocation of patch(form like "images/myImage.jpg")
	 * @param p coordinate of patch
	 */
	public Patch(int typeID, String imageLocation, Point2D p) {
		myTypeID = typeID;
		myImageLocation = imageLocation;
		myImageView = new ImageView(new Image(imageLocation));
		myLoc = p;
	}


	/**
	 * Getter for state
	 * @return patch's state
	 */
	public int getTypeID() {
		return myTypeID;
	}

	/**
	 * Sets patch's state
	 * @param myState of patch
	 */
	public void setMyType(int type) {
		this.myTypeID = type;
	}


	/**
	 * Getter for patch's coordinate location
	 * @return coord of patch
	 */
	public Point2D getLoc() {
		return myLoc;
	}
	
	/**
         * sets patch's coordinate location
         * @param coord
         * 
         */
        public void setLoc(Point2D coord) {
                myLoc = coord;
        }



	/**
	 * Getter for patch's image
	 * @return image of the patch
	 */
	public ImageView getImageView(){
		return myImageView;
	}
	
	public String getImageLocation(){
		return myImageLocation;
	}

	/**
	 * Sets chosen image to patch's location
	 * @param imageLocation image file's location
	 */
	public void setMyImage(String imageLocation) {
		myImageLocation = imageLocation;
	}

}
