package authoring_environment;

import java.awt.geom.Point2D;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

/**
 * SandyTiles are any shape tiles
 * 
 * @author Jennie Ju
 *
 */
public class SandyTile extends Group {
	private Shape myShape;
	private int mySize;
	private Point2D myLocation;
	
	private ImageView myPieceImage;
	private ImageView myPatchImage;

	public SandyTile(Shape bgShape, int size, Point2D loc) {
		myShape = bgShape;
		mySize = size;
		myLocation = loc;
		
		myPieceImage = initImageView();
		myPatchImage = initImageView();
		super.getChildren().addAll(myShape, myPatchImage, myPieceImage);
		setClickEvent();
	}
	
	public double getXLocation() {
		return myLocation.getX();
	}
	
	public double getYLocation() {
		return myLocation.getY();
	}
	
	private void setClickEvent() {
		myShape.setStyle("-fx-cursor: hand");
		myShape.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				System.out.println(getXLocation() + " " + getYLocation());
			}
		});
	}
	
	private ImageView initImageView() {
		ImageView imgView = new ImageView();
		imgView.setFitHeight(mySize);
        imgView.setFitWidth(mySize);
        imgView.setVisible(false);
        return imgView;
	}
	

}
