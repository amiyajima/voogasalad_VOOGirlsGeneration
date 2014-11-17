package authoring_environment;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GridView extends Pane{

	private int widthGridNumber;
	private int heightGridNumber;
	private int myWidth;
	private int myHeight;
	private int myTileSize;

	public GridView(int width, int height, int tilesize){
		myWidth=width;
		myHeight=height;
		myTileSize=tilesize;
		this.setPrefSize(myWidth, myHeight);
		this.setMaxSize(myWidth, myHeight);
		this.setMinSize(myWidth, myHeight);
//		setStyle("-fx-background-color:blue;");
		if (myTileSize!=0){
			widthGridNumber=myWidth/myTileSize;
			heightGridNumber=myHeight/myTileSize;
		}


		initiateGrids();
		initiateGridLines();
		
	}

// Need to fix the duplicated code later.
	private void initiateGridLines() {
		for (int i=0;i<=myWidth;i+=myTileSize){
			Line gridLine=new Line(i,0,i,myHeight);
			gridLine.setStroke(Color.WHITE);
			gridLine.setStrokeWidth(1.5);
			this.getChildren().add(gridLine);
		}
		for (int i=0;i<=myHeight;i+=myTileSize){
			Line gridLine=new Line(0,i,myWidth,i);
			gridLine.setStroke(Color.WHITE);
			gridLine.setStrokeWidth(1.5);
			this.getChildren().add(gridLine);
		}
	}


	private void initiateGrids() {
		for (int i=0;i<widthGridNumber;i++){
			for (int j=0;j<heightGridNumber;j++){
				this.getChildren().add(new Tile(i,j,myTileSize));
			
			}
		}
	}
	
	public int getWidthGrid(){
		return widthGridNumber;
	}
	
	public int getHeightGrid(){
		return heightGridNumber;
	}
	
}
