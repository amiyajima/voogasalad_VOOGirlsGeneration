package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

/**
 * SandyTiles are any shape tiles
 * 
 * @author Jennie Ju
 */
public class SandyTile extends Group {
	private Shape myShape;
	private int mySize;
	private Point2D myCoordinates;
	private Point2D myLocation;
	protected ImageView myPieceImage;
	protected ImageView myPatchImage;

	/**
	 * 
	 * @param bgShape Tile shape
	 * @param size Tile size
	 * @param coor Grid coordination
	 * @param loc Layout coordination
	 */
	public SandyTile(Shape bgShape, int size, Point2D coor,
			Point2D loc) {
		myShape = bgShape;
		mySize = size;
		myCoordinates = coor;
		myLocation = loc;

		myPieceImage = initImageView();
		myPatchImage = initImageView();
		alignNodes(myLocation, myPatchImage, myPieceImage);

		super.getChildren().addAll(myShape, myPatchImage, myPieceImage);
	}
	
	public Point2D getLocation() {
		return myCoordinates;
	}

	private ImageView initImageView() {
		ImageView imgView = new ImageView();
		imgView.setFitHeight(mySize);
		imgView.setFitWidth(mySize);
		imgView.setVisible(false);
		return imgView;
	}

	private void alignNodes(Point2D center, Node... nodes) {
		double xCenter = center.getX();
		double yCenter = center.getY();
		for (Node node : nodes) {
			node.setLayoutX(xCenter - mySize/2);
			node.setLayoutY(yCenter - mySize/2);
		}
	}
}