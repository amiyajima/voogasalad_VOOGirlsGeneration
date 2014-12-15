package authoring_environment;

import java.awt.geom.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

/**
 * Super class of tile of different shapes. Contains all the methods related
 * to operation on the tile.
 * 
 * @author Mengen Huang, Jennie Ju
 */
public abstract class SuperTile extends Group {
    private static final Color DEFAULT_HIGHLIGHT_COLOR = Color.web("#0000FF", 0.3);
    protected Shape myShape;
    // private int mySize;
    private double myImageSize;
    private Point2D.Double myLocation;
    private Point2D.Double myImageLocation;
    private Point2D.Double myCoordinates;

    protected transient Shape myPieceImage;
    protected transient Shape myPatchImage;
    private transient Shape myHighlight;

    /**
     * Define the tile of shape and group all the elements in the tile.
     * 
     * @param size: The side length/height of the shape.
     * @param loc: Grid location of the tile.
     */
    public SuperTile (double size, Point2D.Double loc) {
        myCoordinates = loc;
        makeShapeTile(size, loc);
        setStyle("-fx-cursor: hand");
        super.getChildren().addAll(myShape, myPatchImage, myPieceImage, myHighlight);
    }

    /**
     * Make the tile of the specific shape and align the shape, the patch, the piece,
     * and the highlight shape on it. Set the color of background shape 
     * color and highlight color. The patch is a shape so that patch image will 
     * fill in the shape. The piece only takes the space of the biggeset square
     * inside the tile. 
     * 
     * @param size: The side length or height of the shape.
     * @param loc: Grid location of the tile.
     * 
     */
    protected void makeShapeTile (double size, Point2D.Double loc) {

        myLocation = calculatePixelLocation(size, loc);
        myImageLocation = calculateImageLocation(size, loc);
        myImageSize = calculateImageSize(size);

        myShape = makeShape(size, myLocation);
        makeHighlight(size);
        setCheckeredColor((int) loc.getX(), (int) loc.getY(), myShape);

        myPieceImage = makeShape(size, myLocation);
        myPatchImage = makeShape(size, myLocation);
        myPieceImage.setFill(Color.TRANSPARENT);
        myPatchImage.setFill(Color.TRANSPARENT);

        //alignNodes(myImageLocation, myPieceImage);
    }

    /**
     * Creates the highlight shape the same as the tile 
     * and define the default settings for this tile.
     * 
     * @param size: The side length or height of the shape.
     * 
     */

    private void makeHighlight (double size) {
        myHighlight = makeShape(size, myLocation);
        myHighlight.setFill(DEFAULT_HIGHLIGHT_COLOR);
        myHighlight.setVisible(false);
    };

    /**
     *Highlight the tile and pass in a hex color of the highlight.
     * 
     * @param color: The color of the highlight.
     * @return TODO
     */
    public boolean selectTile (String color) {
        myHighlight.setFill(Color.web(color, 0.3));
        myHighlight.setVisible(true);
        return true;
    }

    /**
     * De-highlight the tile.
     * @return TODO
     */
    public boolean deselectTile () {
        myHighlight.setVisible(false);
        return true;
    }

    /**
     * Return a boolean value indicating if the tile is highlighted.
     * @return If the tile is highlighted.
     */
    public boolean isSelected () {
        return myHighlight.isVisible();
    }

    /**
     * Show an image inside the tile which takes the place of the 
     * biggest square inside the shape.
     * 
     * @param imageView: The imageView which contains the image to put on the 
     * tile as a piece.
     */
    public void setPieceImage (ImageView imageView) {
    	
    	
        myPieceImage.setFill(new ImagePattern(imageView.getImage()));


    }

    /**
     * Set the Image of the Patch on the tile.
     * @param imageView: The imageView which contains the image to fill the 
     * tile as a patch.
     */
    public void setPatchImage (ImageView imageView) {
        myPatchImage.setFill(new ImagePattern(imageView.getImage()));
    }
    
    /**
     * Remove the piece image on the tile by setting it invisible.
     * 
     */
    public void removePieceImage () {
    	myPieceImage.setFill(Color.TRANSPARENT);
    }
    
    /**
     * Remove the patch image on the tile by setting the patch a transparent shape.
     * 
     */
    public void removePatchImage () {
        myPatchImage.setFill(Color.TRANSPARENT);
    }

    /**
     * Make the tile of a specific shape.
     * @param size: The side length/height of the shape.
     * @param coordinates: The pixel coordinate of the tile.
     * @return The tile of a shape with its location and its size defined.
     */
    protected abstract Shape makeShape (double size, Point2D.Double coordinates);

    /**
     * Calculate the pixel location of the tile.
     * @param size: The height or side length of the shape.
     * @param loc: The grid location of the tile with the top left as (0,0).
     * @return The pixel location of the tile.
     */
    protected abstract Point2D.Double calculatePixelLocation (double size, Point2D.Double loc);
    
    /**
     * Calculate the location of the image inside the tile that the image takes 
     * the place of the biggest square inside the tile.
     *
     * @param size: The height or side length of the shape.
     * @param loc: The grid location of the tile with the top left as (0,0).
     * @return The pixel location of the image.
     */
    protected abstract Point2D.Double calculateImageLocation(double size, Point2D.Double loc);
    
    /**
     * 
     * @param size: The height or side length of the tile.
     * @return The side length of the biggest square in the tile.
     */
    protected abstract double calculateImageSize(double size);
    
    
    /**
     * Set the background shape color as white or whitesmoke and the edge of the 
     * tile as gray.
     * @param row: The row the tile is on. (The y grid location of the tile).
     * @param col: The column the tile is on. (The x grid location of the tile).
     * @param shape: The shape of the tile.
     */
    private void setCheckeredColor (int row, int col, Shape shape) {
        if (((row % 2 == 0) && (col % 2 == 0)) || ((row % 2 == 1) && (col % 2 == 1))) {
            shape.setFill(Color.WHITESMOKE);
        }
        else {
            shape.setFill(Color.WHITE);
        }
        shape.setStroke(Color.GRAY);
        shape.setStrokeWidth(0.75);
    }

    /**
     * Initiate ImageView as a square and visible at default.
     * @param size: The side length of the biggest square inside the tile.
     * @return The square imageView of the size passed in.
     */
    private ImageView initImageView (double size) {
        ImageView imgView = new ImageView();
        imgView.setFitHeight(size);
        imgView.setFitWidth(size);
        imgView.setVisible(true);
        return imgView;
    }

    /**
     * Align the passed in node at the same pixel location.
     * @param coord: The pixel location of the nodes.
     * @param nodes: The node to be aligned. 
     */
    private void alignNodes (Point2D.Double coord, Node ... nodes) {
        for (Node node : nodes) {
            node.setLayoutX(coord.getX());
            node.setLayoutY(coord.getY());
        }
    }

    /**
     * Get the grid location of the tile.
     * 
     * @return The grid location of the tile.
     */
    public Point2D.Double getCoordinates () {
        return myCoordinates;
    }

    /**
     * Get the pixel location of the tile.
     * 
     * @return The pixel location of the tile.
     */
    public Point2D.Double getLocation () {
        return myLocation;
    }
    
    /**
     * Get the imageView of the piece.
     * @return The imageView of the piece.
     */
    public Shape getPieceImage(){
    	return myPieceImage;
    }
}
