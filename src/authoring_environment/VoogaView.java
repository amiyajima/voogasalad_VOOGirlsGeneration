package authoring_environment;

import javafx.scene.layout.BorderPane;

public class VoogaView extends BorderPane {
	private MenuView mySettingsView;
	private LibraryView myLibraryView;
	private GridView myGridView;
	
	public VoogaView(){
//		setVisible(true);
		mySettingsView=new MenuView();
		myLibraryView=new LibraryView();
		myGridView=new GridView();
		
		setTop(mySettingsView);
		setLeft(myLibraryView);
		setRight(myGridView);
	}
	
}
