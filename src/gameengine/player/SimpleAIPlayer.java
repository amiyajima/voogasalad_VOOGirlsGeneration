package gameengine.player;

import gamedata.action.Action;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Defines a simple AI for playing turn-based strategy games
 * 
 * @author Jesse, Rica
 *
 */
public class SimpleAIPlayer extends Player {

	private Level myCurrentLevel;

	/**
	 * Constructor
	 * @param id
	 */
	public SimpleAIPlayer(int id) {
		super(id);
	}

	@Override
	public void startTurn(Level l) {
		this.resetMovesPlayed();
		myCurrentLevel = l;
		this.play();
	}

	@Override
	public void play() {
		System.out.println("AI PLAY RUNNING");
		List<Piece> myPieces = myCurrentLevel.getGrid().getPlayerPieces(
				this.myID);
		for (Piece p : myPieces) {
			List<Action> actions = p.getActions();
			Random rand = new Random();
			int randomNum = rand.nextInt((actions.size()));
			if (actions.size() != 0) {
				Action chosenAction = actions.get(1);
				List<Point2D.Double> locs = chosenAction.getAbsoluteActionRange(p
						.getLoc());
				List<Piece> recievers = new ArrayList<Piece>();
				for (Point2D.Double point : locs) {
					Piece rec = myCurrentLevel.getGrid().getPiece(point);
					if (rec != null) {
						recievers.add(rec);
					}
				}
				// System.out.println(recievers.size());
				if (recievers.size() == 0) {
					randomNum = 0;
				} else {
					randomNum = rand.nextInt((recievers.size()));
				}
				if (recievers.size() != 0) {
					Piece rec = recievers.get(randomNum);
					chosenAction.doBehavior(null,p, rec);

				}

			}
			myCurrentLevel.getGrid().repopulateGrid();
			//Trying to slow down AI operation...
		/*	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

		}
	}

	@Override
	public String getType() {
		return "AI";
	}
}
