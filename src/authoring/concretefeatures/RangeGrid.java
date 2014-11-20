package authoring.concretefeatures;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import authoring_environment.Grid;
import authoring_environment.GridView;
import authoring_environment.LibraryView;
import authoring_environment.Tile;

public class RangeGrid extends GridView{
	private static final String DEFAULT_CENTRAL_IMAGE="/PowerStar.png";
	private Grid sampleGrid;
	private List<Integer[]> selectedList=new ArrayList<Integer[]>();

	public RangeGrid(int width, int height, int tileSize) {
		super(width, height, tileSize);	

		sampleGrid=getGrid();
		sampleGrid.sampleSelected();
		addCenterImage(new Image(DEFAULT_CENTRAL_IMAGE));
		this.setContent(sampleGrid);	
		
	}

	public void update(int widthGridNumber,int heightGridNumber,int myTileSize){
		sampleGrid=new Grid(widthGridNumber,heightGridNumber,myTileSize);
		sampleGrid.sampleSelected();
		this.setContent(sampleGrid);
	}

	public void addCenterImage(Image image){
		Tile central=sampleGrid.getTile(sampleGrid.getGridWidth()/2, 
				sampleGrid.getGridHeight()/2);
		central.addSurfaceImage(image);
		central.surfaceImage.setVisible(true);
		
	}


}
