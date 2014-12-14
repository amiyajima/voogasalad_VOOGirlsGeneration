package authoring.concretefeatures.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.LibraryView;

public class Terrain extends Menu{
	
	private static final String NAME = "Terrain";
	private static final String NEW = "New Terrain";
	private LibraryView myLibrary;

	public Terrain(LibraryView library){
		super(NAME);
		myLibrary = library;
		MenuItem terrainCreator = new MenuItem(NEW);
		
		setAction(terrainCreator);
		getItems().addAll(terrainCreator);
	}
	
	private void setAction(MenuItem item){
		item.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent t){
				//PopupWindow p = new TerrainCreator(myLibrary);
				//p.show();
			}
		});
	}
}