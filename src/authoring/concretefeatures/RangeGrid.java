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

	public RangeGrid(int width, int height, int tileSize) {
		super(width, height, tileSize);	
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
		this.setContent(sampleGrid);
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
	public List<Point2D> getSelectedList(){
		List<Point2D> selectedList=new ArrayList<Point2D>();
		
		for (int i=0;i<sampleGrid.getGridWidth();i++) {
			for (int j=0;j<sampleGrid.getGridHeight();j++) {
				if(sampleGrid.getGridTiles()[i][j].getSelected()){
					selectedList.add(new Point2D.Double(i-centerX,centerY-j));
				}
			}
		}
		return selectedList;
	}
	
	public List<Point2D> getCenterColumn(){
		List<Point2D> selectedList=new ArrayList<Point2D>();

		for (int i=0;i<sampleGrid.getGridHeight();i++) {
			selectedList.add(new Point2D.Double(0,i-centerY));
			System.out.println("0,"+(i-centerY));

		}
		return selectedList;
	}
	
	public List<Point2D> getCenterRow(){
		List<Point2D> selectedList=new ArrayList<Point2D>();

		for (int i=0;i<sampleGrid.getGridWidth();i++) {
			selectedList.add(new Point2D.Double(i-centerX,0));
			System.out.println((i-centerX)+",0");
		}
		return selectedList;
	} 
	
	public List<Point2D> getCenterRowColumnCross(){
		List<Point2D> selectedList=new ArrayList<Point2D>();
		Set<Point2D> selectedSet=new HashSet<Point2D>();
		selectedSet.addAll(getCenterColumn());
		selectedSet.addAll(getCenterRow());
		selectedList.addAll(selectedSet);
		for (Point2D p:selectedList){
			System.out.println(p.getX()+","+p.getY());
		}
		return selectedList;
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
	
}
