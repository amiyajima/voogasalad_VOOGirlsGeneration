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

	private MenuView mySettingsView;
	private WorkspaceView myWorkspaceView;
	
	private PieceData myPieceData;
	private PatchData myPatchData;
	
	public VoogaView(){
		myPieceData = new PieceData();
		myPatchData = new PatchData();
	
		myWorkspaceView = new WorkspaceView();
		mySettingsView = new MenuView(myWorkspaceView);
		
		setTop(mySettingsView);
		setBottom(myWorkspaceView);
	}
}