package gamePlayer;


//probably won't use this
import java.awt.geom.Point2D;
import authoring_environment.GUIGrid;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Responsible for Highlighting the Grid
 * 
 * @author
 *
 */
public class XHighlighter {
    
    
	/**
	 * Turns on highlighting of a Patch at loc with Color c
	 * by adding a dropshadow to the node at loc
	 * @param loc
	 * @param c
	 */
	public void highlight(GUIGrid grid, Point2D loc, Color c) {
	    Node n = grid.get((int)loc.getX(), (int)loc.getY(), c);    //assuming this method exists
	    if (n != null) {
	        DropShadow ds = new DropShadow();
	        ds.setRadius(30.0);
	        ds.setOffsetX(0.0);
	        ds.setOffsetY(0.0);
	        ds.setColor(c);
	        n.setEffect(ds);
	    }
	  
//	    addDropShadow(grid.get((int) loc.getX(), (int) loc.getY()), c);
	}

	/**
	 * Turns off highlighting of a Patch at loc
	 * 
	 * @param loc
	 */
	public void unhighlight(ScrollPane myGridPane, Point2D loc) {
	    System.out.println("unhighlight");
//		Node n = myGridPane.get((int) loc.getX(), (int) loc.getY());        //assuming this method exists
//		if (n != null) {
//			n.setEffect(null);
//		}
	}

//	/**
//	 * Adds a Drop Shadow effect to a Node
//	 * 
//	 * @param n
//	 * @param c
//	 */
//	public void addDropShadow(Node n, Color c) {
//		if (n != null) {
//			DropShadow ds = new DropShadow();
//			ds.setRadius(30.0);
//			ds.setOffsetX(0.0);
//			ds.setOffsetY(0.0);
//			ds.setColor(c);
//			n.setEffect(ds);
//		}
//	}
}
