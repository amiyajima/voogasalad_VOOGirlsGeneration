package authoring.concretefeatures.menus;

import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.UnitCreator;
import authoring_environment.LibraryView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class Units extends Menu {
	
	private static final String NAME = "Units";
	private static final String NEW = "New Units";
	LibraryView myLibrary;
	
	public Units(LibraryView library){
		super(NAME);
		myLibrary = library;
		MenuItem unitCreator = new MenuItem(NEW);
		
		setAction(unitCreator);
		getItems().addAll(unitCreator);
	}
	
	private void setAction(MenuItem item){
		item.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent t){
				PopupWindow p = new UnitCreator(myLibrary);
				p.show();
			}
		});
	}
}