package authoring.concretefeatures;

import java.awt.geom.Point2D;
import java.util.ArrayList; 
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import authoring_environment.Grid;
import authoring_environment.GridView;
import authoring_environment.Tile;

/**
 * The view of the grid especially for selecting the range.  
 * @author huangmengen
 *
 */
public class RangeGrid extends GridView{


	private static final String DEFAULT_CENTRAL_IMAGE="images/images.jpeg";

	private Grid sampleGrid;
	private int centerX;
	private int centerY;
	private List<Point2D> myRange;

	public RangeGrid(int width, int height, int tileSize,List<Point2D> range) {
		super(width, height, tileSize);	
		myRange=range;
		sampleGrid=getGrid();
		sampleSelected();
		centerX=sampleGrid.getGridWidth()/2;
		centerY=sampleGrid.getGridHeight()/2;
		this.setContent(sampleGrid);	
//		getCenterColumn();
	}

	/**
	 * Update the grid with new number of tiles in rows and columns from user type in.
	 * Demonstrate the selected tiles and set a image in the center of the grid. 
	 * @param widthGridNumber: The number of tiles in a row.
	 * @param heightGridNumber: The number of tiles in a column.
	 * @param myTileSize: The preferred size of the tile.
	 */
	public void update(int widthGridNumber,int heightGridNumber,int myTileSize){
	
		sampleGrid=new Grid(widthGridNumber,heightGridNumber,myTileSize);
		sampleSelected();
		centerX=widthGridNumber/2;
		centerY=heightGridNumber/2;
		Image image=new Image(getClass().getResourceAsStream(DEFAULT_CENTRAL_IMAGE));
		addCenterImage(image);
		showSelectedRange();
		this.setContent(sampleGrid);
	}

	private void showSelectedRange() {
		for (Point2D position:myRange){
			int x=(int) (position.getX()+centerX);
			int y=(int) (centerY-position.getY());
//			System.out.println(x);
//			System.out.println(y);
			if ((x<=centerX*2) && (y<=centerY*2)){
				sampleGrid.getTile(x,y).selecteTile(true);
			}
		}
	}

	/**
	 * Add the image at the center of the grid as a reference. 
	 * @param image: The image to put in the center.
	 */
	public void addCenterImage(Image image){
		Tile central=sampleGrid.getTile(centerX, centerY);
		central.addSurfaceImage(image);		
	}
	
	/**
	 * Collect all the coordination of selected tiles relative to the center tile
	 * as Point2D in a list.
	 * @return The list of relative coordination relative to the center tile. 
	 */
	public List<Point2D> rangeSelectedList(){
		List<Point2D> selectedList=new ArrayList<Point2D>();
		
		for (int i=0;i<sampleGrid.getGridWidth();i++) {
			for (int j=0;j<sampleGrid.getGridHeight();j++) {
				if(sampleGrid.getGridTiles()[i][j].getSelected()){
					selectedList.add(new Point2D.Double(i-centerX,centerY-j));
//					System.out.println((i-centerX)+","+(centerY-j));
				}
			}
		}
		myRange=selectedList;
//		test=4;
//		System.out.println("t="+ test);
		return selectedList;

	}
	
	public void rangeColumn(int column,boolean toChoose){
		for (int i=0;i<sampleGrid.getGridHeight();i++) {
			sampleGrid.getTile(centerX+column, i).selecteTile(toChoose);
		}
	}
	
	public void rangeRow(int row,boolean toChoose){
		for (int i=0;i<sampleGrid.getGridWidth();i++) {
			sampleGrid.getTile(i, centerY-row).selecteTile(toChoose);
		}
	} 
	
	public void rangeRadius(int radius,boolean toChoose){
		for (int i=(centerX-radius);i<=(centerX+radius);i++){
			for (int j=(centerY-radius);j<=(centerY+radius);j++){
				sampleGrid.getTile(i, j).selecteTile(toChoose);
			}
		}
	}
	
	public void rangeAll(boolean toChoose) {
		for (int i=0;i<sampleGrid.getGridWidth();i++) {
			for (int j=0;j<sampleGrid.getGridHeight();j++) {
				sampleGrid.getTile(i, j).selecteTile(toChoose);
			}
		}
	}
	
	private void sampleSelected() {
		for (Tile[] line : sampleGrid.getGridTiles()) {
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
	
	public void setRange(List<Point2D> range){
		myRange=range;
	}
	
	public List<Point2D> getRange(){
		return myRange;
	}


	
}
