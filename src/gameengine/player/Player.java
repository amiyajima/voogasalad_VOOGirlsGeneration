package gameengine.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.input.KeyCode;
import gamedata.action.Action;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;
import gameengine.movement.Movement;

import java.awt.geom.Point2D;

import authoring_environment.GUIGrid;

/**
 * A player object that contains the logic for playing each level. This object
 * requires no parameters for initialization.
 *
 * @Author Jesse, Rica, Sandy
 */

public abstract class Player {

	protected int myNumMovesPlayed;
	protected int myID;

	/**
	 * Default constructor
	 */
	public Player() {
		this(0);
	}

	/**
	 * Constructs a player with a specific ID
	 * 
	 * @param id
	 *            int ID corresponding to the Player
	 * @param
	 */
	public Player(int id) {
		myNumMovesPlayed = 0;
		myID = id;
	}

	public abstract void startTurn(Level l);

	/**
	 * Resets number of moves played for the player
	 */
	public void resetMovesPlayed() {
		myNumMovesPlayed = 0;
	}

	/**
	 * Getter to return the ID of the player
	 * 
	 * @return int ID of the player
	 */
	public int getID() {
		return myID;
	}

	public abstract void play();

	public void playTurn() {
		myNumMovesPlayed++;
	}

	/**
	 * Getter for Number of Moves Played
	 * 
	 * @return
	 */
	public int getNumMovesPlayed() {
		return myNumMovesPlayed;
	}

	/**
	 * ToString Method for Player Information. For debugging purposes.
	 */
	public String toString() {
		return "Type:" + this.getClass().getName() + " ID:" + myID
				+ " NumMovesPlayed:" + myNumMovesPlayed;
	}
	
	public abstract String getType();

}
