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

	private List<Piece> myPieces;
	private List<Patch> myPatches;
	private List<Player> myPlayers;
	
	public EventsDataWrapper(List<Piece> pieces, List<Patch> patches, List<Player> players){
		myPieces = pieces;
		myPatches = patches;
		myPlayers = players;
	}
	
	public List<Piece> getPieceTypes(){
		return myPieces;
	}
	
	public List<Patch> getPatchTypes(){
		return myPatches;
	}
	
	public List<Player> getPlayerTypes(){
		return myPlayers;
	}
}
