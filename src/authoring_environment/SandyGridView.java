package authoring_environment;

import javafx.scene.control.ScrollPane;
import authoring.data.PatchData;
import authoring.data.PieceData;

public class SandyGridView extends ScrollPane {
	private SandyGrid myGrid;
	
	
	public SandyGridView(int width, int height, int tileSize, 
			PieceData pieceDat, PatchData patchDat) {

		this.setPrefSize(width, height);
		this.setMaxSize(width, height);
		myGrid = new SandyGrid(width/tileSize, height/tileSize,
				tileSize, pieceDat, patchDat);
		
		super.setContent(myGrid);
		
	}
}
