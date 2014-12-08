package authoring.data;

import gamedata.gamecomponents.Game;

/**
 * Stores all the data use in the Authoring environment
 * and needed for the game player/json
 *
 */
public class GameAuthoringData {
	private LevelData myLevelData;
	private PieceTypeData myPieceTypeData;
	private PatchTypeData myPatchTypeData;
	private ActionData myActionData;
	private GamePropertiesData myGamePropertiesData;

	public GameAuthoringData(LevelData levelData, PieceTypeData pieceTypeData,
			PatchTypeData patchTypeData, ActionData actionData,
			GamePropertiesData gamePropertiesData) {
		myLevelData = levelData;
		myPieceTypeData = pieceTypeData;
		myPatchTypeData = patchTypeData;
		myActionData = actionData;
		myGamePropertiesData = gamePropertiesData;
	}

	/**
	 * Creates a Game from the stored num players and level data
	 * 
	 * @return Game made from authoring environment data
	 */
	public Game createGame() {
		int numPlayers = myGamePropertiesData.getNumPlayers();
		return myLevelData.createGame(numPlayers);
	}
}
