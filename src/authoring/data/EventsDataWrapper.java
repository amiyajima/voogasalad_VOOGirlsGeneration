package authoring.data;

import java.util.List;

import gamedata.gamecomponents.*;
import gameengine.player.Player;

/**
 * Wrapper that holds unmodifiable lists of all Piece, Patch, and Player types
 * @author Mike Zhu
 *
 */
public class EventsDataWrapper {

	private List<GridComponent> myPieceTypes;
	private List<GridComponent> myPatchTypes;

	private List<Player> myPlayers;

	public EventsDataWrapper(List<GridComponent> pieceTypes, List<GridComponent> patchTypes, List<Player> players){
		myPieceTypes = pieceTypes;
		myPatchTypes = patchTypes;
		
		myPlayers = players;
	}

	public List<GridComponent> getPieceTypes(){
		return myPieceTypes;
	}

	public List<GridComponent> getPatchTypes(){
		return myPatchTypes;
	}

	public List<Player> getPlayers(){
		return myPlayers;
	}
}
