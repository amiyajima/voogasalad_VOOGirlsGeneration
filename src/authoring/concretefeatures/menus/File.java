package authoring.concretefeatures.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class File extends Menu{
	public File(){
		super("File");
		MenuItem newProject = new MenuItem("New Project");
		MenuItem open = new MenuItem("Open Project...");
		MenuItem save = new MenuItem("Save Project...");
		MenuItem export = new MenuItem("Export Project as Game...");
		MenuItem exit = new MenuItem("Exit");
		
		getItems().addAll(newProject, open, save, export, exit);
	}
	
}
