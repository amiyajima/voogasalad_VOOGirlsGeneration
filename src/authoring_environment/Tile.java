package authoring_environment;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tile extends Pane{
	private static final int TILE_SIDE_LENGTH = 40;
	private int mySize;
	private ImageView myTerrain;
	private ImageView myUnit;

	public Tile(int x,int y,int size){
		mySize=size;
		Rectangle t=new Rectangle(mySize,mySize);
		if (((x%2==0)&&(y%2==0)) || ((x%2==1) && (y%2==1))) t.setFill(Color.RED);
		this.setLayoutX(x*mySize);
		this.setLayoutY(y*mySize);
		this.getChildren().add(t);
		setActionEvent(t);
	}
	
	private void setActionEvent(Rectangle t) {
		t.setStyle("-fx-cursor: hand");
		t.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent m){
				Image image = LibraryView.currentlySelectedImage.getImage();
				t.setFill(new ImagePattern(image));
			}
		});
	}
	
	public int getSize(){
		return mySize;
	}
	
	public void setSize(int newSize){
		mySize=newSize;
	}
}