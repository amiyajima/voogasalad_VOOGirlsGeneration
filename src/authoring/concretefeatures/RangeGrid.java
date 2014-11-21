package authoring.concretefeatures;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import authoring_environment.Grid;
import authoring_environment.GridView;
import authoring_environment.LibraryView;
import authoring_environment.Tile;

public class RangeGrid extends GridView{


	private static final String DEFAULT_CENTRAL_IMAGE="images/images.jpeg";

	private Grid sampleGrid;
	private int centerX;
	private int centerY;

	public RangeGrid(int width, int height, int tileSize) {
		super(width, height, tileSize);	
		sampleGrid=getGrid();
		sampleGrid.sampleSelected();
		this.setContent(sampleGrid);	
	}

	public void update(int widthGridNumber,int heightGridNumber,int myTileSize){
		sampleGrid=new Grid(widthGridNumber,heightGridNumber,myTileSize);
		sampleGrid.sampleSelected();
		Image image=new Image(getClass().getResourceAsStream(DEFAULT_CENTRAL_IMAGE));
		addCenterImage(image);
		this.setContent(sampleGrid);
	}

	public void addCenterImage(Image image){
		centerX=sampleGrid.getGridWidth()/2;
		centerY=sampleGrid.getGridHeight()/2;
		Tile central=sampleGrid.getTile(centerX, centerY);
		central.addSurfaceImage(image);		
	}
	
	public List<Point2D> getSelectedList(){
		List<Point2D> selectedList=new ArrayList<Point2D>();
		
		for (int i=0;i<sampleGrid.getGridWidth();i++) {
			for (int j=0;j<sampleGrid.getGridHeight();j++) {
				if(sampleGrid.grid[i][j].getSelected()){
					selectedList.add(new Point2D(i-centerX,centerY-j));
					System.out.println((i-centerX)+","+(centerY-j));
				}
			}
		}
		return selectedList;
		
	}
}
