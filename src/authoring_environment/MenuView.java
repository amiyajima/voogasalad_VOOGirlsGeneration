package authoring_environment;

import authoring.concretefeatures.menus.*;
import authoring.data.ActionData;
import javafx.scene.control.MenuBar;


public class MenuView extends MenuBar {
	
	private final int LENGTH = 1000;
	private final int HEIGHT = 20;
	
	
	public MenuView(WorkspaceView wsView, LibraryView library){
		this.setPrefSize(LENGTH, HEIGHT);
		
		ActionData actions = new ActionData();
		
		getMenus().addAll(new File(wsView), new GlobalRules(), new Events(), new Units(library, actions), 
						  new Terrain(library), new Actions(library, actions),
						  new JSONBob());
	}
}