package authoring.abstractfeatures;

import javafx.stage.Stage;

/**
 * Class representing popup windows in authoring environment. This window will be filled 
 * with CreatorPane subclasses - EventCreator, TerrainCreator, and UnitCreator. Class
 * exists to define a useful constructor.
 * 
 * @author Mike Zhu
 */
public abstract class PopupWindow extends Stage{
	
	/**
	 * Method to set up the components of a given PopupWindow.
	 */
	protected abstract void initialize();
}