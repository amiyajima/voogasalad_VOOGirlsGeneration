package gamedata.gamecomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import gamedata.goals.*;
import gamedata.rules.*;

/**
 * Rules define how a player's turn ends Goals define whether or not the level
 * has been won
 *
 */
//TODO change MoveCountRule back to superclass Rule
public class Level extends Observable {

	private Grid myGrid;
	/**
	 * Goals defining how to win the level
	 */
	private List<Goal> myGoals;
	/**
	 * Rules defining how the players turn ends
	 */
	private List<Rule> myRules;

	public Level() {
		this(new SquareGrid(), new ArrayList<Goal>(), new ArrayList<Rule>());
	}

	public Level(Grid gr, List<Goal> goals, List<Rule> rules) {
		myGrid = gr;
		myGoals = goals;
		myRules = rules;
	}

	/**
	 * Check if the level has been won after every move the player makes Returns
	 * true if the level has been won, false if it has not.
	 * 
	 * @return
	 */
	public boolean levelCompleted() {
		setChanged();
		notifyObservers();
		for (Goal g : myGoals) {
			if (g.checkGameState(this) == 1) {
				System.out.println("Level Complete");
				return true;
			}
		}
		return false;
	}

	/**
	 * Check rules to see if a player's turn is over. Returns true if the turn
	 * is over, false if the turn continues.
	 * 
	 * @return
	 */
	public boolean checkTurnEnd(int numTurnsPlayed) {
		for (Rule r : myRules) {
			if (r.conditionsMet(numTurnsPlayed)) {
				System.out.println("Player Turn Complete");
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the grid contained in this level.
	 * 
	 * @return
	 */
	public Grid getGrid() {
		return myGrid;
	}

	/**
	 * toString method used to test JSON read/write
	 */
	public String toString() {
		// return "Level's tostring method called";
		return "grid:" + myGrid.toString() + " myGoals" + myGoals.toString()
				+ " myRules" + myRules.toString();
	}

	public void addObserverTo(Observer o) {
		addObserver(o);
	}

	/**
	 * Removes all pieces marked for removal
	 */
	public void garbageCollectPieces() {
		List<Piece> pieces = myGrid.getAllPieces();
		List<Piece> toRemove = new ArrayList<Piece>();
		for(Piece p:pieces){
			//For Testing Purposes Only.
			if(p.getStats().getValue("health")<=0){
				toRemove.add(p);
			}
			
		/*	Inventory i = p.getInventory();
			List<Piece> list = i.getAllInventory();
			for(Piece p2:list){
				if(p2.shouldRemove()){
					i.removeItem(p2);
				}
			}
			if(p.shouldRemove()){
				myGrid.removePiece(p);
			}*/
		}
		for(Piece p:toRemove){
			myGrid.removePiece(p);
		}
	}

}
