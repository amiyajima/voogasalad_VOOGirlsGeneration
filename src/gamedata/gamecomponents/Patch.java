package gamedata.gamecomponents;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Patch {

	private int myState;
	private int myID;
	private Point2D myLoc;
	private ImageView myImage;

	public Patch(int state, int id, String imageLocation, Point2D p) {
		myState = state;
		myID = id;
		// imageLocation in some form like "images/myImage.jpg"
		myImage = new ImageView(new Image(getClass().getResourceAsStream(
				imageLocation)));
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

	public void setMyID(int myID) {
		this.myID = myID;
	}

	public ImageView getMyImage() {
		return myImage;
	}

	public void setMyImage(ImageView myImage) {
		this.myImage = myImage;
	}

}
