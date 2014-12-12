package gamedata.JSON;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.StatsModifier;
import gamedata.events.Event;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.GridComponent;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.goals.Goal;
import gamedata.rules.Rule;
import gamedata.stats.Stats;
import gamedata.wrappers.ActionData;
import gamedata.wrappers.ActionDataIndividual;
import gamedata.wrappers.EventDataIndividual;
import gamedata.wrappers.GameData;
import gamedata.wrappers.GoalData;
import gamedata.wrappers.GridData;
import gamedata.wrappers.LevelDataIndividual;
import gamedata.wrappers.PatchData;
import gamedata.wrappers.PatchDataIndividual;
import gamedata.wrappers.PieceData;
import gamedata.wrappers.PieceDataIndividual;
import gamedata.wrappers.PlayerDataIndividual;
import gamedata.wrappers.StatsData;
import gameengine.movement.Movement;
import gameengine.player.Player;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import authoring.data.PatchInstanceData;
import authoring_environment.GUIGrid;
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
            // e.printStackTrace();
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

        GameData gameData = myGson.fromJson(br, GameData.class);
        System.out.println(gameData.toString());
        
        PatchData patchData = gameData.getMyLevels().get(0).getGrid().getPatches();
        System.out.println(patchData.toString());
        List<Patch> myPatches = patchData.getPatchesFromData();
        System.out.println("Patch #1: " + myPatches.get(0));
        
        EventDataIndividual edi = gameData.getMyLevels().get(0).getMyEventData().get(0);
        System.out.println(edi.toString());
        Event myEvent = edi.getEventFromData();
        System.out.println("Event: " + myEvent);
        
        ActionDataIndividual adi = gameData.getMyLevels().get(0).getGrid().getPieces().getPieces().get(0).getMyActions().get(0);
        System.out.println(adi.toString());
        Action myAction = adi.getActionFromData();
        System.out.println("Action: " + myAction);
        
        Player humanPlayer = gameData.getMyPlayers().get(0).getPlayerFromData();
        System.out.println("Human Player: " + humanPlayer.getID() + " " + humanPlayer.getNumMovesPlayed());
        Player aiPlayer = gameData.getMyPlayers().get(1).getPlayerFromData();
        System.out.println("Supposed AI Player: " + aiPlayer.getID() + " " + humanPlayer.getNumMovesPlayed());
        
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
    
    
    public void registerTypeAdapters (GsonBuilder builder) {
        //builder.registerTypeAdapter(Event.class, new GenericTypeAdapter<Event>("gamedata.events"));
        builder.registerTypeAdapter(StatsModifier.class, new GenericTypeAdapter<StatsModifier>("gamedata.action"));
        builder.registerTypeAdapter(Player.class, new GenericTypeAdapter<StatsModifier>("gameengine.player"));
        builder.registerTypeAdapter(Action.class, new GenericTypeAdapter<Action>("gamedata.action"));
        builder.registerTypeAdapter(ActionConclusion.class, new ActionConclusionTypeAdapter<ActionConclusion>("gamedata.action"));
        builder.registerTypeAdapter(GridComponent.class, new GenericTypeAdapter<GridComponent>("gamedata.gamecomponent"));
        builder.registerTypeAdapter(SuperGrid.class, new GenericTypeAdapter<SuperGrid>("authoring_environment"));
    }

}
