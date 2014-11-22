package authoring_environment;

import authoring.concretefeatures.menus.*;
import authoring.data.ActionData;
import authoring.data.PatchData;
import javafx.scene.control.MenuBar;

public class MenuView extends MenuBar {
	
	public MenuView(LibraryView library){
		this.setPrefSize(1000, 20);
		
		getMenus().addAll(new File(), new GlobalRules(), new Events(), new Units(library), 
						  new Terrain(library, new PatchData()), new Actions(library, new ActionData()));
		// TODO: ACTION DATA NEEDS TO GO SOMEWHERE ELSE
	}
}
