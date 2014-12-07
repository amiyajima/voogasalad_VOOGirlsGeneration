package authoring_environment;

import java.awt.geom.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

/**
 * Tile of different shapes
 * 
 * @author Mengen Huang, Jennie Ju
 */
public abstract class SuperTile extends Group {
    private static final Color DEFAULT_HIGHLIGHT_COLOR = Color.web("#0000FF", 0.3);
    protected Shape myShape;
    // private int mySize;
    private double myImageSize;
    private Point2D myLocation;
    private Point2D myImageLocation;
    private Point2D myCoordinates;

    protected transient ImageView myPieceImage;
    protected transient Shape myPatchImage;
    private Shape myHighlight;

    /**
     * 
     * @param bgShape Tile shape
     * @param size Tile size
     * @param loc Grid coordination
     */
    public SuperTile (double size, Point2D loc) {
        myCoordinates = loc;
        makeShapeTile(size, loc);
        setStyle("-fx-cursor: hand");
        super.getChildren().addAll(myShape, myPatchImage, myPieceImage, myHighlight);
    }

    protected void makeShapeTile (double size, Point2D loc) {

        myLocation = calculatePixelLocation(size, loc);
        myImageLocation = calculateImageLocation(size, loc);
        myImageSize = calculateImageSize(size);

        myShape = makeShape(size, myLocation);
        makeHighlight(size);
        setCheckeredColor((int) loc.getX(), (int) loc.getY(), myShape);

        myPieceImage = initImageView(myImageSize);
        myPatchImage = makeShape(size, myLocation);
        myPatchImage.setFill(Color.TRANSPARENT);

        alignNodes(myImageLocation, myPieceImage);
    }

    /**
     * Creates the highlight settings for this tile
     * 
     * @param size
     */

    private void makeHighlight (double size) {
        myHighlight = makeShape(size, myLocation);
        myHighlight.setFill(DEFAULT_HIGHLIGHT_COLOR);
        myHighlight.setVisible(false);
    };

    /**
     * Select a tile to highlight, passing in a hex color
     * 
     * @param color
     */
    public void selectTile (String color) {
        myHighlight.setFill(Color.web(color, 0.3));
        myHighlight.setVisible(true);
    }

    /**
     * De-highlight something
     */
    public void deselectTile () {
        // System.out.println("De-highlighting tile at " + myLocation.getX() + ", " +
        // myLocation.getY());
        myHighlight.setVisible(false);
    }

    public boolean ifSelected () {
        return myHighlight.isVisible();
    }

    public void setPieceImage (ImageView imageView) {
        myPieceImage.setImage(imageView.getImage());
        myPieceImage.setVisible(true);
    }

    public void setPatchImage (ImageView imageView) {
        myPatchImage.setFill(new ImagePattern(imageView.getImage()));
    }
    
    public void removePieceImage () {
    	myPieceImage.setVisible(false);
    }
    
    public void removePatchImage () {
        myPatchImage.setFill(Color.TRANSPARENT);
    }

    protected abstract Shape makeShape (double size, Point2D coordinates);

    protected abstract Point2D calculatePixelLocation (double size, Point2D loc);
    
    protected abstract Point2D calculateImageLocation(double size, Point2D loc);
    
    protected abstract double calculateImageSize(double size);
    
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

    private ImageView initImageView (double size) {
        ImageView imgView = new ImageView();
        imgView.setFitHeight(size);
        imgView.setFitWidth(size);
        imgView.setVisible(true);
        return imgView;
    }

    private void alignNodes (Point2D coord, Node ... nodes) {
        for (Node node : nodes) {
            node.setLayoutX(coord.getX());
            node.setLayoutY(coord.getY());
        }
    }

    /**
     * Pixel location
     * 
     * @return
     */
    public Point2D getCoordinates () {
        return myCoordinates;
    }

    /**
     * Grid coordinate location
     * 
     * @return
     */
    public Point2D getLocation () {
        return myLocation;
    }
}
