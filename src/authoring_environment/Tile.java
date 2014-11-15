package authoring_environment;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Pane{
	private static final int TILE_SIDE_LENGTH = 40;

	public Tile(int x,int y){
		Rectangle t=new Rectangle(TILE_SIDE_LENGTH,TILE_SIDE_LENGTH);
		if (((x%2==0)&&(y%2==0)) || ((x%2==1) && (y%2==1))) t.setFill(Color.RED);
//		t.setFill(Color.BLACK);
		this.setLayoutX(x*TILE_SIDE_LENGTH);
		this.setLayoutY(y*TILE_SIDE_LENGTH);
		this.getChildren().add(t);
	}

}
