package authoring_environment;

import authoring.data.PatchData;
import authoring.data.PieceData;
import javafx.scene.layout.BorderPane;

/**
 * The GUI contains all the parts in authoring environment. It sets the size 
 * and the position of the parts in the GUI. 
 * @author huangmengen
 *
 */
public class VoogaView extends BorderPane {
	
	private final int NUM_TILES_ACROSS = 20;
	private final int NUM_TILES_DOWN = 20;
	private final int GRID_VIEW_WIDTH = 700;
	private final int GRID_VIEW_HEIGHT = 580;
	private final int TILE_SIDE_LENGTH = 40;

	private MenuView mySettingsView;
	private LibraryView myLibraryView;
	private SandyGridView myGridView;
	private PieceData myPieceData;
	private PatchData myPatchData;
	
	public VoogaView(){
		myPieceData = new PieceData();
		myPatchData = new PatchData();
		myLibraryView = new LibraryView(myPieceData, myPatchData);
		mySettingsView = new MenuView(myLibraryView);
		myGridView = new SandyGridView(NUM_TILES_ACROSS, NUM_TILES_DOWN, GRID_VIEW_WIDTH,
				GRID_VIEW_HEIGHT, TILE_SIDE_LENGTH, myPieceData, myPatchData);
		
		setTop(mySettingsView);
		setLeft(myLibraryView);
		setRight(myGridView);
	}
	
//	public void setGrid(int width, int height){
//	    //tile length should be adjusted
//	   myGridView = new GridView(width, height, TILE_SIDE_LENGTH);
//	}
}