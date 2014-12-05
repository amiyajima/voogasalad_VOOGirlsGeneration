package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Tile of different shapes
 * 
 * @author Mengen Huang, Jennie Ju
 */
public abstract class SuperTile extends Group {
	private Shape myShape;
//	private int mySize;
	private int myImageSize;
	private Point2D myCoordinates;
//	private Point2D myLocation;
	private Point2D myImageCoord;
	protected ImageView myPieceImage;
	protected ImageView myPatchImage;

	/**
	 * 
	 * @param bgShape Tile shape
	 * @param size Tile size
	 * @param loc Grid coordination
	 */
	public SuperTile(int size,Point2D loc) {
		
		makeShapeTile(size,loc);
		
		setStyle("-fx-cursor: hand");
		
		super.getChildren().addAll(myShape,myPieceImage,myPatchImage);
	}
	

	protected void makeShapeTile(int size, Point2D loc){
		myShape=makeShape(size);
		setCheckeredColor((int)loc.getX(),(int)loc.getY(),myShape);
		myCoordinates=calculateCoord(size,loc);
		myImageCoord=calculateImageCoord(size,loc);
		myImageSize=calculateImageSize(size);
		
		myPieceImage = initImageView(myImageSize);
		myPatchImage = initImageView(myImageSize);
		
		alignNodes(myCoordinates,myImageCoord, myPieceImage, myPatchImage);

		
		
	};


	protected abstract Shape makeShape(int size);
	
	protected abstract Point2D calculateCoord(int size, Point2D loc);
	
	protected abstract Point2D calculateImageCoord(int size, Point2D loc);

	protected abstract int calculateImageSize(int size);



	private void setCheckeredColor(int row, int col, Shape shape) {
		if (((row % 2 == 0) && (col % 2 == 0)) || ((row % 2 == 1) && (col % 2 == 1))) {
            shape.setFill(Color.WHITESMOKE);
		}
        else {
            shape.setFill(Color.WHITE);
        }
		shape.setStroke(Color.GRAY);
		shape.setStrokeWidth(0.75);
	}
	private ImageView initImageView(int size) {
		ImageView imgView = new ImageView();
		imgView.setFitHeight(size);
		imgView.setFitWidth(size);
		imgView.setVisible(false);
		return imgView;
	}

	private void alignNodes(Point2D layoutCoord, Point2D imageCoord, Node...nodes) {
		myShape.setLayoutX(layoutCoord.getX());
		myShape.setLayoutY(layoutCoord.getY());
		for (Node node : nodes) {
			node.setLayoutX(imageCoord.getX());
			node.setLayoutY(imageCoord.getY());
		}
	}
}
