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
    
    public GameAuthoringData() {
    	initData();
    }
    
    private void initData() {
    	myLevelData = new LevelData();
        myPieceTypeData = new PieceTypeData();
        myPatchTypeData = new PatchTypeData();
    	myActionData = new ActionData();
        myGamePropertiesData = new GamePropertiesData();
    }
    
    public void initDataPointers(LevelData levelPtr, PieceTypeData pieceTypePtr,
    		PatchTypeData patchTypePtr, ActionData actionPtr,
    		GamePropertiesData gamePropertiesPtr) {
    	levelPtr = myLevelData;
    	pieceTypePtr = myPieceTypeData;
    	patchTypePtr = myPatchTypeData;
    	actionPtr = myActionData;
    	gamePropertiesPtr = myGamePropertiesData;
    			
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
