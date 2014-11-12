package authoring.concretefeatures.menus;

import authoring.abstractfeatures.CreatorPane;
import authoring.abstractfeatures.PopupWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class Units extends Menu{
	
	private static final String NAME = "Units Creator";
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 500;

	public Units(){
		super("Units");
		MenuItem eventsCreator = new MenuItem(NAME);
		
		setAction(eventsCreator);
		getItems().addAll(eventsCreator);
	}
	
	private void setAction(MenuItem item){
		item.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent t){
				PopupWindow p = new PopupWindow(WINDOW_HEIGHT, WINDOW_WIDTH, NAME, new CreatorPane());
				p.show();
			}
		});
	}
}
