package authoring_environment;

import javafx.scene.control.ScrollPane;
import authoring.data.PatchData;
import authoring.data.PieceData;

public class SandyGridView extends ScrollPane {
	private SandyGrid myGrid;
	
	public SandyGridView(int tilesAcross, int tilesDown, int viewWidth, int viewHeight, int tileSize, 
			PieceData pieceData, PatchData patchData) {

		this.setPrefSize(viewWidth, viewHeight);
		this.setMaxSize(viewWidth, viewHeight);
		this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		myGrid = new SandyGrid(tilesAcross, tilesDown, tileSize, pieceData, patchData);
		super.setContent(myGrid);
	}
}