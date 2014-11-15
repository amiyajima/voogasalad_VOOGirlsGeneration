package authoring_environment;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GridView extends Pane{

	private static final int GRID_VIEW_HEIGHT = 580;
	private static final int GRID_VIEW_WIDTH = 700;
	private static final int TILE_SIDE_LENGTH = 40;


	public GridView(){
		this.setPrefSize(GRID_VIEW_WIDTH, GRID_VIEW_HEIGHT);
		this.setMaxSize(GRID_VIEW_WIDTH, GRID_VIEW_HEIGHT);
		this.setMinSize(GRID_VIEW_WIDTH, GRID_VIEW_HEIGHT);
//		setStyle("-fx-background-color:blue;");
		
		initiateGrids();
		initiateGridLines();
		
	}

// Need to fix the duplicated code later.
	private void initiateGridLines() {
		for (int i=0;i<=GRID_VIEW_WIDTH;i+=TILE_SIDE_LENGTH){
			Line gridLine=new Line(i,0,i,GRID_VIEW_HEIGHT);
			gridLine.setStroke(Color.WHITE);
			gridLine.setStrokeWidth(1.5);
			this.getChildren().add(gridLine);
		}
		for (int i=0;i<=GRID_VIEW_HEIGHT;i+=TILE_SIDE_LENGTH){
			Line gridLine=new Line(0,i,GRID_VIEW_WIDTH,i);
			gridLine.setStroke(Color.WHITE);
			gridLine.setStrokeWidth(1.5);
			this.getChildren().add(gridLine);
		}
	}


	private void initiateGrids() {
		for (int i=0;i<GRID_VIEW_WIDTH/TILE_SIDE_LENGTH;i++){
			for (int j=0;j<GRID_VIEW_HEIGHT/TILE_SIDE_LENGTH;j++){
				this.getChildren().add(new Tile(i,j));
			
			}
		}
	}
}
