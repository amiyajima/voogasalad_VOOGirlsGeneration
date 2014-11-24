package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.Group;
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
	private Point2D myLocation;
	
	protected ImageView myPieceImage;
	protected ImageView myPatchImage;

	public SandyTile(Shape bgShape, int size, Point2D loc) {
		myShape = bgShape;
		mySize = size;
		myLocation = loc;
		
		myPieceImage = initImageView();
		myPatchImage = initImageView();
		super.getChildren().addAll(myShape, myPatchImage, myPieceImage);
	}
	
	public Point2D getLocation() {
		return myLocation;
	}
	
	private ImageView initImageView() {
		ImageView imgView = new ImageView();
		imgView.setFitHeight(mySize);
        imgView.setFitWidth(mySize);
        imgView.setLayoutX(myLocation.getX() * mySize);
        imgView.setLayoutY(myLocation.getY() * mySize);
        imgView.setVisible(false);
        return imgView;
	}
}