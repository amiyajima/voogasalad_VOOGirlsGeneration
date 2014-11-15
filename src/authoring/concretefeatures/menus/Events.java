package authoring.concretefeatures.menus;

import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.EventsWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class Events extends Menu{
	
	private static final String NAME = "Events";
	private static final String ITEM1 = "Edit Events";

	public Events(){
		super(NAME);
		MenuItem eventsCreator = new MenuItem(ITEM1);
		
		setAction(eventsCreator);
		getItems().addAll(eventsCreator);
	}
	
	private void setAction(MenuItem item){
		item.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent t){
				PopupWindow p = new EventsWindow();
				p.show();
			}
		});
	}
}