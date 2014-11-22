package authoring_environment;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * The GUI components for the grid displayed on the right side of the game authoring
 * environment. It displays all the unit/terrain which are chose to put on it. It also 
 * demonstrates currently selected tile. It scrolls when the size of the grid exceeds 
 * a certain size.
 * @author huangmengen
 *
 */
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
	
	/**
	 * Get the grid which is the content of the GridView. 
	 * @return Grid which contains all the tiles.
	 */
	public Grid getGrid(){
		return myGrid;
	}
	
	

	
}
