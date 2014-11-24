package authoring_environment;

import authoring.concretefeatures.menus.*;
import authoring.data.ActionData;
import javafx.scene.control.MenuBar;


public class MenuView extends MenuBar {
	
	private final int LENGTH = 1000;
	private final int HEIGHT = 20;
	
	ActionData myActions;
	LibraryView myLibrary;
	
	public MenuView(WorkspaceView wsView){
		this.setPrefSize(LENGTH, HEIGHT);
		
		myActions = new ActionData();
		
		getMenus().addAll(new File(wsView), new GlobalRules(), new Events(), new Units(myLibrary, myActions), 
						  new Terrain(myLibrary), new Actions(myLibrary, myActions),
						  new JSONBob());
	}
}