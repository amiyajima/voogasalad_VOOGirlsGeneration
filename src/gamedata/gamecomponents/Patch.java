package gamedata.gamecomponents;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class Patch {

    private int myState;
    private int myID;
    private ImageView myImageView;

    public Patch (int state, int id, String imageLocation) {
        myState = state;
        myID = id;
        // imageLocation in some form like "images/myImage.jpg"
        myImageView = new ImageView(new Image(getClass().getResourceAsStream(imageLocation)));
    }

    // put image on the patch?
    public void draw () {
    }

    public int getMyState () {
        return myState;
    }

    public void setMyState (int myState) {
        this.myState = myState;
    }

    public int getMyID () {
        return myID;
    }

    public void setMyID (int myID) {
        this.myID = myID;
    }

    public ImageView getMyImage () {
        return myImageView;
    }

    public void setMyImage (ImageView myImage) {
        this.myImageView = myImage;
    }

}
