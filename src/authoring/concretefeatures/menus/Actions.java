package authoring.concretefeatures.menus;

import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.ActionCreator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class Actions extends Menu{
	
	private static final String NAME = "Actions";
	private static final String ITEM1 = "Edit Actions";

	public Actions(){
		super(NAME);
		MenuItem eventsCreator = new MenuItem(ITEM1);
		
		setAction(eventsCreator);
		getItems().addAll(eventsCreator);
	}
	
	private void setAction(MenuItem item){
		item.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent t){
				PopupWindow p = new ActionCreator();
				p.show();
			}
		});
	}
}
