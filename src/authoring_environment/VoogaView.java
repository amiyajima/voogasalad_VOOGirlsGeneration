package authoring_environment;

import authoring.data.PatchData;
import authoring.data.PieceData;
import javafx.scene.layout.BorderPane;

/**
 * The GUI contains all the parts in authoring environment. It sets the size 
 * and the position of the parts in the GUI.
 * 
 * @author huangmengen
 */
public class VoogaView extends BorderPane {
	
	private final int NUM_TILES_ACROSS = 20;
	private final int NUM_TILES_DOWN = 20;
	private final int GRID_VIEW_WIDTH = 700;
	private final int GRID_VIEW_HEIGHT = 580;
	private final int TILE_SIDE_LENGTH = 40;
	
	private LibraryView myLibraryView;
	private SandyGridView myGridView;
	private MenuView mySettingsView;
	private PieceData myPieceData;
	private PatchData myPatchData;
	
	public VoogaView(){
		myPieceData = new PieceData();
		myPatchData = new PatchData();
		SandyGrid grid = new SandyGrid(NUM_TILES_ACROSS, NUM_TILES_DOWN,
				TILE_SIDE_LENGTH, myPieceData, myPatchData);
		myLibraryView = new LibraryView(grid);
		myGridView = new SandyGridView(grid, GRID_VIEW_WIDTH, GRID_VIEW_HEIGHT);
		mySettingsView = new MenuView(myLibraryView);
		
		setTop(mySettingsView);
		setLeft(myLibraryView);
		setRight(myGridView);
	}
}