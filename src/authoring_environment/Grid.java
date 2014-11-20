package authoring_environment;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Grid extends Pane{

	private int myWidth;
	private int myHeight;
	private int myTileSize;
	public Tile[][] grid;

	public Grid(int width, int height, int tilesize){
		myWidth=width;
		myHeight=height;
		myTileSize=tilesize;
		grid=new Tile[myWidth][myHeight];

//		setStyle("-fx-background-color:blue;");

		initiateGrids();
		initiateGridLines();
//		sampleSelected();
		
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
				grid[i][j]=new Tile(i,j,myTileSize);
				this.getChildren().add(grid[i][j]);

			
			}
		}

	}
	
	public int getGridWidth(){
		return myWidth;
	}
	
	public int getGridHeight(){
		return myHeight;
	}
	
	public void sampleSelected(){
		for (Tile[] line:grid){
			for (Tile tile: line){
				tile.setOnMouseClicked(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						tile.switchSelected();
					}
					
				});

			}
		}
	}
	
	public Tile getTile(int x, int y){
		return grid[x][y];
	}
	
}

