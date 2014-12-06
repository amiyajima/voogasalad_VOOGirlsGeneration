package authoring_environment;

import java.awt.geom.Point2D;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
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
	private Shape myShape;
//	private int mySize;
	private int myImageSize;
	private Point2D myCoordinates;
	private Point2D myLocation;
	private Point2D myImageCoord;
	protected ImageView myPieceImage;
	protected ImageView myPatchImage;
	private Shape myHighlight;

	/**
	 * 
	 * @param bgShape Tile shape
	 * @param size Tile size
	 * @param loc Grid coordination
	 */
	public SuperTile(int size,Point2D loc) {
		
		myLocation=loc;
		
		makeShapeTile(size,loc);
		
		setStyle("-fx-cursor: hand");
		
		super.getChildren().addAll(myShape,myPatchImage,myPieceImage,myHighlight);
	}
	

	protected void makeShapeTile(int size, Point2D loc){
		myShape=makeShape(size);
		makeHighlight(size);
		setCheckeredColor((int)loc.getX(),(int)loc.getY(),myShape);
		myCoordinates=calculateCoord(size,loc);
		myImageCoord=calculateImageCoord(size,loc);
		myImageSize=calculateImageSize(size);
		
		myPieceImage = initImageView(myImageSize);
		myPatchImage = initImageView(myImageSize);
		
		alignNodes(myCoordinates,myShape,myHighlight);
		alignNodes(myImageCoord,myPatchImage,myPieceImage);

	}


	private void makeHighlight(int size) {
		myHighlight=makeShape(size);
		myHighlight.setFill(Color.web("#0000FF", 0.3));
		myHighlight.setVisible(false);
	};
	
	public void selectTile(){
		myHighlight.setVisible(true);
	}
	
	public void deselectTile(){
		myHighlight.setVisible(false);
	}
	
	public boolean ifSelected(){
		return myHighlight.isVisible();
	}
	
	public void addPiece(Image image){
		myPieceImage.setImage(image);
	}
	
	public void addPatch(Image image){
		myPatchImage.setImage(image);
	}


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
 
	private void alignNodes(Point2D coord, Node...nodes) {
		for (Node node : nodes) {
			node.setLayoutX(coord.getX());
			node.setLayoutY(coord.getY());
		}
	}
	
	protected Point2D getLocation(){
		return myLocation;
	}
}
