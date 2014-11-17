package gamedata.gamecomponents;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class Patch {

    private int myState;
    private int myID;
    private ImageView myImageView;

    /**
     * constructor of patch
     * @param state of the patch
     * @param id of the patch
     * @param imageLocation path of image that is to be used
     */
    public Patch (int state, int id, String imageLocation) {
        myState = state;
        myID = id;
        // imageLocation in some form like "images/myImage.jpg"
        myImageView = new ImageView(new Image(getClass().getResourceAsStream(imageLocation)));
    }


    // put image on the patch?
    public void draw () {
    }

    /**
     * @return state of the patch
     */
    public int getMyState () {
        return myState;
    }

    /**
     * sets the state for a certain patch
     * @param myState
     */
    public void setMyState (int myState) {
        this.myState = myState;
    }

    /**
     * @return ID of the patch
     */
    public int getMyID () {
        return myID;
    }

    /**
     * sets the ID for a certain patch
     * @param myID
     */
    public void setMyID (int myID) {
        this.myID = myID;
    }

    /**
     * @return image of the patch
     */
    public ImageView getMyImage () {
        return myImageView;
    }

    /**
     * sets the image of a certain patch
     * @param myImage
     */
    public void setMyImage (ImageView myImage) {
        this.myImageView = myImage;
    }

}
