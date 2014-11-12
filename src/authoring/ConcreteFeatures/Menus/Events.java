package authoring.ConcreteFeatures.Menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class Events extends Menu{
	public Events(){
		super("Events");
		MenuItem eventsCreator = new MenuItem("EventsCreator");
	
		getItems().addAll(eventsCreator);
	}
}
