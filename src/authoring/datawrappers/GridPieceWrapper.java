package authoring.datawrappers;

import java.awt.geom.Point2D;

import javafx.scene.image.ImageView;

/**
 * Class for a piece displayed on the grid.
 * It doesn't contain the behavior of an actual
 * piece in the game. It just stores the data needed
 * to build a piece when the game is played, and 
 * thus it is referred to as a wrapper.
 * 
 * @author Jennie
 *
 */
public class GridPieceWrapper {
	private static final int DEFAULT_PLAYERID = 1;
	private static final int DEFAULT_ROTATE = 0;
	private static final int DEFAULT_XSCALE = 1;
	private static final int DEFAULT_YSCALE = 1;
	
	private ImageView myImageView;
	private Point2D myLocation;
	
	private int myUniqueID;	
	private int myTypeID;
	private int myPlayerID;
	
	private double myRotate;
	private double myXScale;
	private double myYScale;

	/**
	 * Constructor for a GridPieceWrapper,
	 * Called when the user decides to place a piece on the
	 * grid in the authoring environment
	 * 
	 * @param imageView - ImageView of the piece
	 * @param uniqueID - Unique ID for this piece
	 * @param typeID - Type ID for the type of piece created
	 */
	public GridPieceWrapper(ImageView imageView, Point2D loc, int uniqueID, int typeID) {
		// TODO: MAKE SURE A NEW IMAGEVIEW IS GIVEN TO THIS CLASS
		myImageView = imageView;
		myLocation = loc;
		
		myUniqueID = uniqueID;
		myTypeID = typeID;
		myPlayerID = DEFAULT_PLAYERID;
		
		myRotate = DEFAULT_ROTATE;
		myXScale = DEFAULT_XSCALE;
		myYScale = DEFAULT_YSCALE;
	}
	
	/**
	 * Returns the ImageView for display on the grid
	 */
	public ImageView getImageView() {
		return myImageView;
	}
	
	/**
	 * Returns the type ID for this piece
	 */
	public int getTypeID() {
		return myTypeID;
	}
	
	/**
	 * Allows for rewriting of the default parameters
	 * for this instance of a piece
	 * @param playerID - the ID of the player controlling this piece
	 * @param rotate - double degrees of rotation of this piece's image
	 * @param hflip - boolean
	 * @param vflip -
	 */
	public void rewriteParameters(int playerID, double rotate,
			boolean hflip, boolean vflip) {
		myPlayerID = playerID;
		myRotate = rotate;
		myXScale = applyFlip(vflip);
		myYScale = applyFlip(hflip);
		
		applyImageTransforms(myImageView);
	}
	
	private int applyFlip(boolean flip) {
		if (flip) {
			return -1; // flips the imageview
		}
		return 1; // keeps original orientation
	}
	
	private void applyImageTransforms(ImageView imageView) {
		imageView.setRotate(myRotate);
		imageView.setScaleX(myXScale);
		imageView.setScaleY(myYScale);
	}

}
