package gamedata.gamecomponents;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public abstract class Patch {

	private int myState;
	private int myID;
	private Point2D myLoc;
	private ImageView myImage;

	public Patch(int state, int id, ImageView image, Point2D p) {
		myState = state;
		myID = id;
		// imageLocation in some form like "images/myImage.jpg"
		myImage = image;
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
		return myImage;
	}

	public void setMyImage(ImageView myImage) {
		this.myImage = myImage;
	}

}
