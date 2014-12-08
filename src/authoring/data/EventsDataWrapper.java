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

	private List<Piece> myPieceTypes;
	private List<Patch> myPatchTypes;

	private List<Player> myPlayers;

	public EventsDataWrapper(List<Piece> pieceTypes, List<Patch> patchTypes, List<Player> players){
		myPieceTypes = pieceTypes;
		myPatchTypes = patchTypes;
		
		myPlayers = players;
	}

	public List<Piece> getPieceTypes(){
		return myPieceTypes;
	}

	public List<Patch> getPatchTypes(){
		return myPatchTypes;
	}

	public List<Player> getPlayers(){
		return myPlayers;
	}
}
