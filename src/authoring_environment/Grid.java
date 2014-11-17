package authoring_environment;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Grid extends Pane{

	private int myWidth;
	private int myHeight;
	private int myTileSize;

	public Grid(int width, int height, int tilesize){
		myWidth=width;
		myHeight=height;
		myTileSize=tilesize;
//		setStyle("-fx-background-color:blue;");

		initiateGrids();
		initiateGridLines();
		
	}

// Need to fix the duplicated code later.
	private void initiateGridLines() {
		for (int i=0;i<=myWidth*myTileSize;i+=myTileSize){
			Line gridLine=new Line(i,0,i,myHeight*myTileSize);
			gridLine.setStroke(Color.WHITE);
			gridLine.setStrokeWidth(1.5);
			this.getChildren().add(gridLine);
		}
		for (int i=0;i<=myHeight*myTileSize;i+=myTileSize){
			Line gridLine=new Line(0,i,myWidth*myTileSize,i);
			gridLine.setStroke(Color.WHITE);
			gridLine.setStrokeWidth(1.5);
			this.getChildren().add(gridLine);
		}
	}


	private void initiateGrids() {
		for (int i=0;i<myWidth;i++){
			for (int j=0;j<myHeight;j++){
				this.getChildren().add(new Tile(i,j,myTileSize));
			
			}
		}
	}
	
	public int getWidthGrid(){
		return myWidth;
	}
	
	public int getHeightGrid(){
		return myHeight;
	}
	
}


