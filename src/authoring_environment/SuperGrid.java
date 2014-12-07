package authoring_environment;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class SuperGrid {
	private static final String CIRCLE_GRID = "Circle Grid";
	private static final String HEXAGON_GRID = "Hexagon Grid";
	private static final String SQUARE_GRID = "Square Grid";
	protected int myHeight;
	protected int myWidth;
	protected double myTileSize;

	private Pane myPane;
	protected List<List<SuperTile>> myGrid;

	public SuperGrid() {
		this(1, 1, 40, SQUARE_GRID);
	}

	public SuperGrid(int width, int height, double tileSize, String shape) {
		myPane = new Pane();
		myHeight = height;
		myWidth = width;
		myTileSize = tileSize;
		initGridTiles(shape);
	}

	public void displayPane(ScrollPane parent) {
		parent.setContent(myPane);
	}

	protected void initGridTiles(String shape) {
		myGrid = new LinkedList<List<SuperTile>>();
		for (int row = 0; row < myHeight; row++) {
			List<SuperTile> tileCol = new LinkedList<SuperTile>();
			for (int col = 0; col < myWidth; col++) {
				Point2D location = new Point2D.Double(col,row);
				SuperTile tile = makeShapeTile(shape, myTileSize, location);
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

	public int getRow() {
		return myHeight;
	}

	public int getCol() {
		return myWidth;
	}
	

	public SuperTile findClickedTile(double xCoord, double yCoord){
		for (List<SuperTile> rows:myGrid){
			for (SuperTile tile:rows){
				if (tile.myShape.contains(xCoord,yCoord)){
					return tile;
				}
			}
		}
		return null;
	}
	
	public SuperTile findClickedTile(Point2D loc){
	                int row=(int) loc.getX();
	                int col=(int) loc.getY();
	                return myGrid.get(row).get(col);
	        }
	
	
	public double getTileSize() {
		return myTileSize;
	}
}
