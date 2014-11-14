package authoring_environment;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Pane{
	public Tile(int x,int y){
		this.setPrefSize(10, 10);
		Rectangle t=new Rectangle();
		t.setFill(Color.BLACK);
		this.setLayoutX(x*10);
		this.setLayoutY(y*10);
		this.getChildren().add(t);
	}

}
