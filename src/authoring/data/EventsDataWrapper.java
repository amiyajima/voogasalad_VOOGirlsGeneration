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

	private List<Piece> myLevelPieces;
	private List<Patch> myLevelPatches;
	
	private List<Player> myPlayers;

	public EventsDataWrapper(List<Piece> pieceTypes, List<Patch> patchTypes, List<Player> players){
		myPieceTypes = pieceTypes;
		myPatchTypes = patchTypes;
		
		myPlayers = players;
	}
	
	public void loadLevelPieces(List<Piece> pieces){
		myLevelPieces = pieces;
	}
	
	public void loadLevelPatches(List<Patch> patches){
		myLevelPatches = patches;
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
	
	public List<Piece> getLevelPieces(){
		return myLevelPieces;
	}
	
	public List<Patch> getLevelPatches(){
		return myLevelPatches;
	}
}
