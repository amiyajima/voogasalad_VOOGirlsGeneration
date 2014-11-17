package authoring.concretefeatures.menus;

import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.TerrainCreator;
import authoring_environment.LibraryView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class Terrain extends Menu{
	
	private static final String NAME = "Terrain";
	private static final String ITEM1 = "New Terrain";
	private LibraryView myLibrary;

	public Terrain(LibraryView library){
		super(NAME);
		myLibrary = library;
		MenuItem eventsCreator = new MenuItem(ITEM1);
		
		setAction(eventsCreator);
		getItems().addAll(eventsCreator);
	}
	
	private void setAction(MenuItem item){
		item.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent t){
				PopupWindow p = new TerrainCreator(myLibrary);
				p.show();
			}
		});
	}
}