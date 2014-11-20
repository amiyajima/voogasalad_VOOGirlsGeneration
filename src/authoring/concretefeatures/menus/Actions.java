package authoring.concretefeatures.menus;

import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.ActionCreator;
import authoring.data.ActionData;
import authoring_environment.LibraryView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class Actions extends Menu{
	
	private static final String NAME = "Actions";
	private static final String ITEM1 = "Edit Actions";
	LibraryView myLibrary; // should this be private?
	
	//TODO: figure out what to put in action constructor
	public Actions(LibraryView library, ActionData actionData){
		super(NAME);
		myLibrary = library;
		MenuItem eventsCreator = new MenuItem(ITEM1);
		
		setAction(eventsCreator, actionData);
		getItems().addAll(eventsCreator);
	}
	
	private void setAction(MenuItem item, ActionData actionData){
		item.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent t){
				PopupWindow p = new ActionCreator(actionData);
				p.show();
			}
		});
	}
}
