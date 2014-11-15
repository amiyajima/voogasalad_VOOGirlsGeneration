package authoring.concretefeatures.menus;

import authoring_environment.LibraryView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class Units extends Menu{
	
	LibraryView myLibrary;
	
	public Units(LibraryView library){
		super("Units");
		myLibrary = library;
		MenuItem unitCreator = new MenuItem("Unit Creator");
		
		getItems().addAll(unitCreator);
	}
}