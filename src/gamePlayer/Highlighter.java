package gamePlayer;

import java.awt.geom.Point2D;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Responsible for Highlighting the Grid
 * 
 * @author
 *
 */
// TODO: Update once new Grids are implemented
public class Highlighter {
	/**
	 * Turns on highlighting of a Patch at loc with Color c
	 * 
	 * @param loc
	 * @param c
	 */
	public void highlightCurrent(Point2D loc, Color c) {
		addDropShadow(myGrid.get((int) loc.getX(), (int) loc.getY()), c);
	}

	/**
	 * Turns off highlighting of a Patch at loc
	 * 
	 * @param loc
	 */
	public void unhighlight(Point2D loc) {
		Node n = myGrid.get((int) loc.getX(), (int) loc.getY());
		if (n != null) {
			n.setEffect(null);
		}
	}

	/**
	 * Adds a Drop Shadow effect to a Node
	 * 
	 * @param n
	 * @param c
	 */
	private void addDropShadow(Node n, Color c) {
		if (n != null) {
			DropShadow ds = new DropShadow();
			ds.setRadius(30.0);
			ds.setOffsetX(0.0);
			ds.setOffsetY(0.0);
			ds.setColor(c);
			n.setEffect(ds);

		}
	}

}
