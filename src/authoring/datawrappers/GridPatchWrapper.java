package authoring.datawrappers;

import java.awt.geom.Point2D;
import javafx.scene.image.ImageView;


/**
 * Class for a patch displayed on the grid.
 * It doesn't contain the behavior of an actual
 * piece in the game. It just stores the data needed
 * to build a patch when the game is played, and
 * thus it is referred to as a wrapper.
 * 
 * @author Sandy Lee
 *
 */
public class GridPatchWrapper {
    private static final Point2D DEFAULT_COORDINATE = new Point2D.Double(0,0);
    
    private String myImageLocation;
    private Point2D myLocation;
    private int myTypeID;

    /**
     * Constructor for a GridPieceWrapper,
     * Called when the user decides to place a piece on the
     * grid in the authoring environment
     * 
     * @param uniqueID - Unique ID for this piece
     * @param imagLoc - location of the image of the piece
     * @param loc - location of the patch on the grid
     * 
     */

    public GridPatchWrapper (int typeId, String imgLoc, Point2D loc) {
        // TODO: MAKE SURE A NEW IMAGEVIEW AND NAME ARE GIVEN TO THIS CLASS
        myImageLocation = imgLoc;
        myLocation = loc;
        myTypeID = typeId;
    }

    /**
     * Returns the image location for display on the grid
     */
    public String getImageLoc () {
        return myImageLocation;
    }

    /**
     * Returns the image location for display on the grid
     */
    public Point2D getLoc () {
        return myLocation;
    }

    /**
     * Allows for rewriting of the default parameters
     * for this instance of a piece
     * @param coord 
     */
    public void rewriteParameters(Point2D coord) {
            myLocation = coord;
    }


}
