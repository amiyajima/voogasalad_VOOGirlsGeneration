package authoring.ConcreteFeatures.Menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class File extends Menu{
	
	public File(){
		super("File");
		MenuItem newGame = new MenuItem("New game");
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
		MenuItem exit = new MenuItem("Exit");
		
		getItems().addAll(newGame, open, save, exit);
	}
}
