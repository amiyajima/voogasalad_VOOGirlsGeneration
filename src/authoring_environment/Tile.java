package authoring_environment;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Pane{
	private static final int TILE_SIDE_LENGTH = 40;
	private int mySize;

	public Tile(int x,int y,int size){
		mySize=size;
		Rectangle t=new Rectangle(mySize,mySize);
		if (((x%2==0)&&(y%2==0)) || ((x%2==1) && (y%2==1))) t.setFill(Color.RED);
//		t.setFill(Color.BLACK);
		this.setLayoutX(x*mySize);
		this.setLayoutY(y*mySize);
		this.getChildren().add(t);
	}

	public int getSize(){
		return mySize;
	}
	public void setSize(int newSize){
		mySize=newSize;
	}
}
