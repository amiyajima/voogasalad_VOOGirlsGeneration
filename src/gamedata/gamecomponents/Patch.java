package gamedata.gamecomponents;
 
import java.awt.geom.Point2D;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Patch {

	private int myState;
	private int myID;
	private Point2D myLoc;
	private String myImageLocation;

	public Patch(int state, int id, String imageLocation, Point2D p) {
		myState = state;
		myID = id;
		// imageLocation in some form like "images/myImage.jpg"
		myImageLocation = imageLocation;
		myLoc = p;
	}

	// put image on the patch?
	public void draw() {
	}

	public int getMyState() {
		return myState;
	}

	public void setMyState(int myState) {
		this.myState = myState;
	}

	public int getMyID() {
		return myID;
	}

	public Point2D getLoc() {
		return myLoc;
	}

	public void setMyID(int myID) {
		this.myID = myID;
	}

	public ImageView getImageView() {
		return new ImageView(new Image(myImageLocation));
	}

	public void setMyImage(String imageLocation) {
		this.myImageLocation = imageLocation;
	}

}
