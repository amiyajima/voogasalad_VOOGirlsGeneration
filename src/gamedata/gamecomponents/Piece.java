package gamedata.gamecomponents;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import gamedata.action.Action;
import gamedata.stats.Stats;
import gameengine.movement.*;

public class Piece {

	private int myTypeID;
	private int myUniqueID;
	private int myPlayerID;
	private Stats myStats;
	private Point2D myLoc;
	private ImageView myImageView;
	private List<Movement> myPath;
	/**
	 * List containing the actions that a piece has available to it
	 */
	private List<Action> myActions;

	public Piece(ImageView i, List<Movement> m, List<Action> a, Stats stats,
			Point2D p, int pid) {
		myImageView = i;
		myPath = m;
		myActions = a;
		myStats = stats;
		myLoc = p;
		myPlayerID = pid;
	}

	public ImageView getImageView() {
		return myImageView;
	}

	public int getTypeID() {
		return myTypeID;
	}

	public int getUniqueID() {
		return myUniqueID;
	}

	public List<Action> getActions() {
		return myActions;
	}

	public void setLoc(Point2D p) {
		myLoc = p;
	}

	public Point2D getLoc() {
		return myLoc;
	}

	public Stats getStats() {
		return myStats;
	}

	public void addAction(Action a) {
		myActions.add(a);
	}

	public void removeAction(Action a) {
		myActions.remove(a);
	}
}
