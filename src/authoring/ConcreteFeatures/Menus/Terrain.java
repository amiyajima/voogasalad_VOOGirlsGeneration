package authoring.ConcreteFeatures.Menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class Terrain extends Menu{
	public Terrain(){
		super("Terrain");
		MenuItem terrainCreator = new MenuItem("Terrain Creator");
		
		getItems().addAll(terrainCreator);
	}
}
