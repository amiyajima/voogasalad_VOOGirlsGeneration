package authoring_environment;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuView extends MenuBar {
	public MenuView(){
		this.setPrefSize(1000, 20);
		createFileMenu();
	}

	private void createFileMenu() {
		Menu file=new Menu("File");
		this.getMenus().add(file);
	}
	
}
