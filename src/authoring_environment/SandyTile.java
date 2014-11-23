package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

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
	
	protected Piece myUnit;
	protected Patch myTerrain;
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
	
	public int getXLocation() {
		return (int)myLocation.getX();
	}
	
	public int getYLocation() {
		return (int)myLocation.getY();
	}
	
	private ImageView initImageView() {
		ImageView imgView = new ImageView();
		imgView.setFitHeight(mySize);
        imgView.setFitWidth(mySize);
        imgView.setLayoutX(getXLocation() * mySize);
        imgView.setLayoutY(getYLocation() * mySize);
        imgView.setVisible(false);
        return imgView;
	}
}