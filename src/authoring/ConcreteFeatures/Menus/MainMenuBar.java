package authoring.ConcreteFeatures.Menus;

import javafx.scene.control.MenuBar;

public class MainMenuBar extends MenuBar{
	public MainMenuBar(){
		getMenus().addAll(new File(), new Events(), new Units(), new Terrain());
	}
}
