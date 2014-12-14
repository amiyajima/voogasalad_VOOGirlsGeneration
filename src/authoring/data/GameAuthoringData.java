package authoring.data;

import gamedata.gamecomponents.Game;
import gameengine.player.Player;

import java.util.List;

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
	private PlayerStatData myPlayerStatData;

	public GameAuthoringData(LevelData levelData, PieceTypeData pieceTypeData,
			PatchTypeData patchTypeData, ActionData actionData,
			GamePropertiesData gamePropertiesData, PlayerStatData playerStatData) {
		myLevelData = levelData;
		myPieceTypeData = pieceTypeData;
		myPatchTypeData = patchTypeData;
		myActionData = actionData;
		myGamePropertiesData = gamePropertiesData;
		myPlayerStatData = playerStatData;
	}

	/**
	 * Creates a Game from the stored num players and level data
	 * 
	 * @return Game made from authoring environment data
	 */
	public Game createGame() {
		int numPlayers = myGamePropertiesData.getNumPlayers();
		System.out.println(numPlayers);
		Game game = myLevelData.createGame(numPlayers);
		List<Player> players = myPlayerStatData.makePlayers(numPlayers);
		game.addPlayers(players);
		return game;
	}
}
