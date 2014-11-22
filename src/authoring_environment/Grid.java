package authoring_environment;

import java.util.ArrayList; 
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * The grid which contains all the tiles and draws the tiles and grid lines.
 * @author huangmengen
 *
 */
public class Grid extends Pane {

	private int myWidth;
	private int myHeight;
	private int myTileSize;
	private Tile[][] grid;

	public Grid(int width, int height, int tilesize) {
		myWidth = width;
		myHeight = height;
		myTileSize = tilesize;
		grid = new Tile[myWidth][myHeight];

		// setStyle("-fx-background-color:blue;");

		initiateGrids();
		initiateGridLines();
		// sampleSelected();

	}

	// Need to fix the duplicated code later.
	private void initiateGridLines() {
		for (int i = 0; i <= myWidth * myTileSize; i += myTileSize) {
			Line gridLine = new Line(i, 0, i, myHeight * myTileSize);
			gridLine.setStroke(Color.WHITE);
			gridLine.setStrokeWidth(1.5);
			this.getChildren().add(gridLine);
		}

		for (int i = 0; i <= myHeight * myTileSize; i += myTileSize) {
			Line gridLine = new Line(0, i, myWidth * myTileSize, i);
			gridLine.setStroke(Color.WHITE);
			gridLine.setStrokeWidth(1.5);
			this.getChildren().add(gridLine);
		}
	}

	private void initiateGrids() {
		for (int i = 0; i < myWidth; i++) {
			for (int j = 0; j < myHeight; j++) {
				grid[i][j] = new Tile(i, j, myTileSize);
				this.getChildren().add(grid[i][j]);
			}
		}
		setActionEvent();
	}

	/**
	 * Get the number of tiles in a row.
	 * @return The number of tiles in a horizontal line of the grid.
	 */
	public int getGridWidth() {
		return myWidth;
	}

	/**
	 * Get the number of tiles in a column.
	 * @return The number of tiles in a vertical line of the grid.
	 */
	public int getGridHeight() {
		return myHeight;
	}
	
	public void sampleSelected() {
		for (Tile[] line : grid) {
			for (Tile tile : line) {
				tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						tile.switchSelected();
					}
				});
			}
		}
	}

	/**
	 * Get a specific tile in the grid according to its position.
	 * @param x: The X coordination of the tile
	 * 			from the left smallest to the right largest.
	 * @param y: The Y coordination of the tile
	 * 			from the bottom smallest to the top largest.
	 * @return The tile at the specified position.
	 */
	public Tile getTile(int x, int y) {
		return grid[x][y];
	}

	public List<Tile> getSelected() {
		List<Tile> tiles = new ArrayList<Tile>();
		for(int i=0; i<myWidth; i++){
			for(int j=0; i<myHeight;j++){
				if(getTile(i,j).getSelected()){
					tiles.add(getTile(i,j));
				}
			}
		}
		return tiles;
	}

	private void setActionEvent() {
		this.setStyle("-fx-cursor: hand");
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent m){
				int x = (int)m.getX() / myTileSize;
				int y = (int)m.getY() / myTileSize;
				Tile tile = getTile(x, y);
				setContents(tile);
			}
		});
	}

	protected void setContents(Tile tile) {
		if(LibraryView.reset){
			if(LibraryView.unitSelected){
				tile.myUnit = null;
				tile.unitImage.setVisible(false);;
			}
			else{
				tile.myTerrain = null;
				tile.terrainImage.setVisible(false);;
			}
		}
		else{
			if(LibraryView.unitSelected){
				tile.myUnit = LibraryView.currentlySelectedUnit;
				tile.unitImage.setImage(tile.myUnit.getImageView().getImage());
				tile.unitImage.setVisible(true);
			}
			else{
				tile.myTerrain = LibraryView.currentlySelectedTerrain;
				tile.terrainImage.setImage(tile.myTerrain.getImageView().getImage());
				tile.terrainImage.setVisible(true);
			}
		}
	}
	
	public Tile[][] getGridTiles(){
		return grid;
	}

}
