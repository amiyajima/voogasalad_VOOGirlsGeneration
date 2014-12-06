package authoring_environment;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;


/**
 * The GUI components for the grid displayed on the right side of the game authoring
 * environment. It displays all the unit/terrain which are chose to put on it. It also
 * demonstrates currently selected tile. It scrolls when the size of the grid exceeds
 * a certain size.
 * 
 * @author Mengen Huang, Jennie Ju
 *
 */
public class GridView extends ScrollPane {

	public GridView(int viewWidth, int viewHeight) {
		this.setPrefSize(viewWidth, viewHeight);
		this.setMaxSize(viewWidth, viewHeight);
		this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		SuperGrid grid = new SuperGrid(10, 10, 50,"Square Grid");

		grid.displayPane(this);

	}


}
