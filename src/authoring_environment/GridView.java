package authoring_environment;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GridView extends ScrollPane{
	
	private int myWidth;
	private int myHeight;
	private int myTileSize;
	private Grid myGrid;

	public GridView(int width, int height, int tileSize){
		myWidth=width;
		myHeight=height;
		myTileSize=tileSize;
		this.setPrefSize(myWidth, myHeight);
		this.setMaxSize(myWidth, myHeight);
//		this.setMinSize(myWidth, myHeight);
		this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
//		setStyle("-fx-background-color:blue;");
		
		myGrid=new Grid(myWidth/myTileSize,myHeight/myTileSize,
				myTileSize);
		this.setContent(myGrid);
		
	}
	
	public void update(int widthGridNumber,int heightGridNumber,int myTileSize){
		myGrid=new Grid(widthGridNumber,heightGridNumber,myTileSize);
		this.setContent(myGrid);
	}

	
}
