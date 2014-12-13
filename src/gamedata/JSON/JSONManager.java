package gamedata.JSON;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.StatsModifier;
import gamedata.events.conditions.Condition;
import gamedata.events.globalaction.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.GridComponent;
import gameengine.player.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import authoring_environment.SuperGrid;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Creates JSON file from Authoring Environment data. Loads and parses JSON file
 * to create a Game for Game Engine/Game Player.
 *
 * @author annamiyajima, Rica Zhang
 */
public class JSONManager {

    Gson myGson;

    /**
     * Constructor
     */
    public JSONManager () {
        GsonBuilder builder = new GsonBuilder();
        registerTypeAdapters(builder);
        myGson = builder.create();
    }

    /**
     * Write a game and its contents into a JSON file.
     * 
     * @param piece
     * 
     * @param piece
     */
      public void writeToJSON (Object thing, String fileName) {
        String json = myGson.toJson(thing);
        System.out.println("JSONManager: game converted to json!");

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
        }
        catch (IOException e) {
            System.out.println("could not write to: " + fileName);
        }

    }

    /**
     * Given a filePath, read in a JSON file and construct a game with that data
     * 
     * @param JSON file location
     * @throws FileNotFoundException
     */

    public Game readFromJSONFile (String jsonFileLocation) throws FileNotFoundException {
        System.out.println("JSONManager: read method called");
        BufferedReader br = new BufferedReader(new FileReader(jsonFileLocation));
<<<<<<< HEAD

        GameData gameData = myGson.fromJson(br, GameData.class);
        System.out.println(gameData.toString());
        
        PatchData patchData = gameData.getMyLevels().get(0).getGrid().getPatches();
        System.out.println(patchData.toString());
        List<Patch> myPatches = patchData.getPatchesFromData();
        System.out.println("Patch #1: " + myPatches.get(0));
        
//        EventDataIndividual edi = gameData.getMyLevels().get(0).getMyEventData().get(0);
//        System.out.println(edi.toString());
//        Event myEvent = edi.getEventFromData();
//        System.out.println("Event: " + myEvent);
        
//        ActionDataIndividual adi = gameData.getMyLevels().get(0).getGrid().getPieces().getPieces().get(0).getMyActions().get(0);
//        System.out.println(adi.toString());
//        Action myAction = adi.getActionFromData();
//        System.out.println("Action: " + myAction);
        
        Player humanPlayer = gameData.getMyPlayers().get(0).getPlayerFromData();
        System.out.println("Human Player: " + humanPlayer.getID() + " " + humanPlayer.getNumMovesPlayed());
//        Player aiPlayer = gameData.getMyPlayers().get(1).getPlayerFromData();
//        System.out.println("Supposed AI Player: " + aiPlayer.getID() + " " + humanPlayer.getNumMovesPlayed());
        
        Game myGame = gameData.getGameFromData();
        System.out.println("Game: " + myGame.toString());
        return myGame;
    }

    /**
     * Method that converts a game data object into a game object. may be an issue as every aspect
     * of it is in a data wrapper
     * 
     * @param myGameData
     * @return
     */
    private Game convertToGame (GameData gameData) {
        /*
        List<PlayerDataIndividual> myPlayerData = gameData.getMyPlayers();
        List<LevelDataIndividual> myLevelData = gameData.getMyLevels();
        PlayerDataIndividual myCurrentPlayerData = gameData.getMyCurrentPlayer();
        LevelDataIndividual myCurentLevelData = gameData.getMyCurrentLevel();
        
        for(LevelDataIndividual l : myLevelData){
            GridData gridData = l.getGrid();
            PieceData pieceData = gridData.getPieces();
            PatchData patchData = gridData.getPatches();
            
            List<Piece> pieces = new ArrayList<Piece>();
            for(PieceDataIndividual pd : pieceData.getPieces()){
                // TODO Since inventory is not implemented, we pass in an empty inventory
                Inventory emptyInventory = new Inventory();
                //Piece(String id, String name, String imageLoc, List<Action> actions,
                //Stats stats, Point2D loc, int playerID, Inventory inventory)
                Stats myStats = new Stats(pd.getMyStats().getStats());
                List<ActionDataIndividual> myActionData = pd.getMyActions();
                for (ActionDataIndividual actionData : myActionData) {
                    
                }
                //Piece myPiece = new Piece(...
            }
            List<Patch> patches = new ArrayList<Patch>();
            for(PatchDataIndividual pd : patchData.getPatches()){
                Patch myPatch = new Patch(pd.getMyTypeID(), pd.getName(), pd.getMyImageLocation(), pd.getMyLoc());
                patches.add(myPatch);
            }
            
            GUIGrid grid = new Grid(gridData.getRow(), gridData.getColumn(), pieces, patches);
            
            Level currentLevel = new Level(grid,l.getGoals(), l.getRules());
            
        }
         */
        //Game newGame = new Game(myPlayerData.size(), );

        return null;
    }
    
=======
        
        Game pi = myGson.fromJson(br, Game.class);
//        System.out.println("Level 1: " + pi.getLevels().get(0).getGrid().toString());
        return pi;
    }    
>>>>>>> a4c452332a2eec24af9729475a8f22a7408fff0f
    
    public void registerTypeAdapters (GsonBuilder builder) {
        builder.registerTypeAdapter(StatsModifier.class, new GenericTypeAdapter<StatsModifier>("gamedata.action"));
        builder.registerTypeAdapter(Player.class, new GenericTypeAdapter<Player>("gameengine.player"));
        builder.registerTypeAdapter(Action.class, new GenericTypeAdapter<Action>("gamedata.action"));
        builder.registerTypeAdapter(ActionConclusion.class, new ActionConclusionTypeAdapter<ActionConclusion>("gamedata.action"));
        builder.registerTypeAdapter(GridComponent.class, new GenericTypeAdapter<GridComponent>("gamedata.gamecomponent"));
        builder.registerTypeAdapter(Condition.class, new GenericTypeAdapter<Condition>("gamedata.events.conditions"));
        builder.registerTypeAdapter(GlobalAction.class, new GenericTypeAdapter<GlobalAction>("gamedata.events.globalaction"));
        builder.registerTypeAdapter(SuperGrid.class, new GenericTypeAdapter<SuperGrid>("authoring_environment"));
    }

}
