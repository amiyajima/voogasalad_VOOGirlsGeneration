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
	
	public Tile[][] getGridTiles(){
		return grid;
	}

}
