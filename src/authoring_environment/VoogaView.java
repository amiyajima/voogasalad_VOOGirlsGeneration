package authoring_environment;

import javafx.scene.layout.BorderPane;

public class VoogaView extends BorderPane {
	private MenuView mySettingsView;
	private LibraryView myLibraryView;
	private GridView myGridView;
	
	public VoogaView(){
//		setVisible(true);

		myLibraryView=new LibraryView();
		mySettingsView=new MenuView(myLibraryView);
		myGridView=new GridView();
		
		setTop(mySettingsView);
		setLeft(myLibraryView);
		setRight(myGridView);
	}
	
}
