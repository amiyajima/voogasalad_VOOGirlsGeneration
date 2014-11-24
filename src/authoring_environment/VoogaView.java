package authoring_environment;

import authoring.data.PatchData;
import authoring.data.PatchTypeData;
import authoring.data.PieceData;
import authoring.data.PieceTypeData;
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
	private PieceData myPieces;
	private PatchData myPatches;
	private PieceTypeData myPieceData;
	private PatchTypeData myPatchData;
	
	public VoogaView(){
		myPieces = new PieceData();
		myPatches = new PatchData();
		myPieceData = new PieceTypeData();
		myPatchData = new PatchTypeData();
		SandyGrid grid = new SandyGrid(NUM_TILES_ACROSS, NUM_TILES_DOWN,
				TILE_SIDE_LENGTH, myPieces, myPatches);
		myLibraryView = new LibraryView(grid, myPieceData, myPatchData);
		myGridView = new SandyGridView(grid, GRID_VIEW_WIDTH, GRID_VIEW_HEIGHT);
		mySettingsView = new MenuView(myLibraryView);
		
		setTop(mySettingsView);
		setLeft(myLibraryView);
		setRight(myGridView);
	}
}