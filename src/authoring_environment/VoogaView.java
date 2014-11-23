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
	private static final int GRID_VIEW_WIDTH = 700;
	private static final int GRID_VIEW_HEIGHT = 580;
	private static final int TILE_SIDE_LENGTH = 40;

	private MenuView mySettingsView;
	private LibraryView myLibraryView;
	private SandyGridView myGridView;
	
	public VoogaView(){
		myLibraryView = new LibraryView(new PieceData(), new PatchData());
		mySettingsView = new MenuView(myLibraryView);
		myGridView = new SandyGridView(GRID_VIEW_WIDTH,GRID_VIEW_HEIGHT,TILE_SIDE_LENGTH,
				new PieceData(), new PatchData());
		
		setTop(mySettingsView);
		setLeft(myLibraryView);
		setRight(myGridView);
	}
	
//	public void setGrid(int width, int height){
//	    //tile length should be adjusted
//	   myGridView = new GridView(width, height, TILE_SIDE_LENGTH);
//	}
}