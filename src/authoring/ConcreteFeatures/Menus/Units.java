package authoring.ConcreteFeatures.Menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class Units extends Menu{
	public Units(){
		super("Units");
		MenuItem unitCreator = new MenuItem("Unit Creator");
		
		getItems().addAll(unitCreator);
	}
}
