package authoring_environment;

import javafx.scene.layout.BorderPane;



public class VoogaView extends BorderPane {
	private static final int GRID_VIEW_WIDTH = 700;
	private static final int GRID_VIEW_HEIGHT = 580;
	private static final int TILE_SIDE_LENGTH = 40;
	private MenuView mySettingsView;
	private LibraryView myLibraryView;
	private GridView myGridView;
	
	public VoogaView(){
//		setVisible(true);

		myLibraryView=new LibraryView();
		mySettingsView=new MenuView(myLibraryView);
		myGridView=new GridView(GRID_VIEW_WIDTH,GRID_VIEW_HEIGHT,TILE_SIDE_LENGTH);
		
		setTop(mySettingsView);
		setLeft(myLibraryView);
		setRight(myGridView);
	}
	
}
