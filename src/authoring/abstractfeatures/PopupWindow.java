package authoring.abstractfeatures;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class representing popup windows in authoring environment. This window will be filled 
 * with CreatorPane subclasses - EventCreator, TerrainCreator, and UnitCreator. Class
 * exists to define a useful constructor.
 * @author Mike Zhu
 *
 */
public class PopupWindow extends Stage{

	public PopupWindow(double h, double w, String name, Parent pane){
		setHeight(h);
		setWidth(w);
		setTitle(name);
		//TODO: add x and y location for the window
		setScene(new Scene(pane));
	}
}