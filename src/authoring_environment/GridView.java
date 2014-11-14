package authoring_environment;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridView extends Pane{

	public GridView(){
		this.setPrefSize(500, 500);
		this.setMaxSize(500, 500);
		this.setMinSize(500, 500);
		setStyle("-fx-background-color:black;");
//		for (int i=0;i<80;i++){
//			for (int j=0;j<58;j++){
//				this.getChildren().add(new Tile(i,j));
//			}
//		}
//		Rectangle test=new Rectangle();
//		test.setFill(Color.BLACK);
//		test.setLayoutX(50);
//		test.setLayoutY(50);
//		this.getChildren().add(test);
//		System.out.println("asfa");
//	    this.setVisible(true);

	}
}
