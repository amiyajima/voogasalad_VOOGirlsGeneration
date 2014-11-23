package authoring_environment;

import authoring.concretefeatures.menus.*;
import authoring.data.ActionData;
import javafx.scene.control.MenuBar;


public class MenuView extends MenuBar {
	
	private final int LENGTH = 1000;
	private final int HEIGHT = 20;
	
	public MenuView(LibraryView library){
		this.setPrefSize(LENGTH, HEIGHT);
		
		getMenus().addAll(new File(), new GlobalRules(), new Events(), new Units(library), 
						  new Terrain(library), new Actions(library, new ActionData()),
						  new JSONBob());
	}
}