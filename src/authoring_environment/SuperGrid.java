package authoring_environment;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

/**
 * Superclass of grids with different behavior.
 * Can use tiles of different shapes.
 * 
 * @author Meng'en Huang
 *
 */
public class SuperGrid {
	private static final String CIRCLE_GRID = "Circle Grid";
	private static final String HEXAGON_GRID = "Hexagon Grid";
	private static final String SQUARE_GRID = "Square Grid";
	protected int myNumRows;
	protected int myNumCols;
	protected double myTileHeight;
	protected String myShape;

	protected Pane myPane;
	protected List<List<SuperTile>> myGrid;

	public SuperGrid() {
		this(1, 1, 40, SQUARE_GRID);
	}

	public SuperGrid(int cols, int rows, double tileHeight, String shape) {
		myPane = new Pane();
		myNumRows = rows;
		myNumCols = cols;
		myTileHeight = tileHeight;
		myShape = shape;
		initGridTiles(shape);
	}

	protected void clearGridImages() {
		for (List<SuperTile> row : myGrid) {
			for (SuperTile tile : row) {
				tile.removePieceImage();
				tile.removePatchImage();
			}
		}
	}

	protected void initGridTiles(String shape) {
		myGrid = new LinkedList<List<SuperTile>>();
		for (int row = 0; row < myNumRows; row++) {
			List<SuperTile> tileCol = new LinkedList<SuperTile>();
			for (int col = 0; col < myNumCols; col++) {
				Point2D location = new Point2D.Double(col,row);
				SuperTile tile = makeShapeTile(shape, myTileHeight, location);
				
				tileCol.add(tile);
				myPane.getChildren().add(tile);
			}
			myGrid.add(tileCol);
		}
	}
	
	private SuperTile makeShapeTile(String shape, double tileSize, Point2D location) {
		switch (shape) {
		case SQUARE_GRID:
			return new SquareTile(tileSize, location);
		case HEXAGON_GRID:
			return new HexagonTile(tileSize, location);
		case CIRCLE_GRID:
			return new CircleTile(tileSize, location);
		default:
			return new SquareTile(tileSize, location);
		}
	}

	/**
	 * Returns the int number of rows in the grid
	 * (Note: Row = Y dimension)
	 */
	public int getRow() {
		return myNumRows;
	}

	/**
	 * Returns the int number of columns in the grid
	 * (Note: Col = X dimension)
	 */
	public int getCol() {
		return myNumCols;
	}
	
	/**
	 * Return the height of the SuperTiles
	 * @return double height of the SuperTiles
	 */
	public double getTileHeight() {
		return myTileHeight;
	}
	
	/**
	 * Sets the given ScrollPane as the parent
	 * to myPane containing the grid SuperTiles
	 * @param parent - ScrollPane parent of mypane
	 */
	public void displayPane(ScrollPane parent) {
		parent.setContent(myPane);
	}
	
	/**
	 * Get the grid coordinate of the mouse click event
	 * for x and y pixel locations
	 * 
	 * @param xLoc - x pixel location
	 * @param yLoc - y pixel location
	 * @return Point2D containing the grid coordinations of the mouse click
	 * as (col, row) or (x, y)
	 */
	public Point2D findClickedCoordinate(double xLoc, double yLoc){
		SuperTile tile = findClickedTile(xLoc, yLoc);
		return tile.getCoordinates();
	}
	
	/**
	 * Uses PIXEL mouse location to find a SuperTile that was
	 * clicked on
	 * 
	 * @param xLoc - x pixel location of click
	 * @param yLoc - y pixel location of click
	 * @return SuperTile that was clicked, null if none found
	 */
	public SuperTile findClickedTile(double xLoc, double yLoc){
		for (List<SuperTile> rows : myGrid){
			for (SuperTile tile  :rows){
				if (tile.myShape.contains(xLoc,yLoc)){
					return tile;
				}
			}
		}
		return null;
	}

	/**
	 * Use GRID COORDINATE LOCATION to find a SuperTile
	 * 
	 * @param coor - Point2D storing the coordinate location
	 * as (col, row) or (X,Y)
	 * @return SuperTile at that coordinate
	 */
	public SuperTile findTile(Point2D coor){
		int col = (int) coor.getX();
		int row = (int) coor.getY();
		return myGrid.get(row).get(col);
	}
	
	

}
