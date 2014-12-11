package gamedata.JSON;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.events.Event;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.GridComponent;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.goals.Goal;
import gamedata.rules.Rule;
import gamedata.stats.Stats;
import gamedata.wrappers.ActionData;
import gamedata.wrappers.ActionDataIndividual;
import gamedata.wrappers.GameData;
import gamedata.wrappers.GoalData;
import gamedata.wrappers.GridData;
import gamedata.wrappers.LevelDataIndividual;
import gamedata.wrappers.PatchData;
import gamedata.wrappers.PatchDataIndividual;
import gamedata.wrappers.PieceDataIndividual;
import gamedata.wrappers.PlayerDataIndividual;
import gameengine.movement.Movement;
import gameengine.player.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import authoring.data.AuthoringPatchData;
import authoring.data.PieceData;
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
    public GridData readFromJSONFile (String jsonFileLocation) throws FileNotFoundException {
        System.out.println("JSONManager: read method called");
        BufferedReader br = new BufferedReader(new FileReader(jsonFileLocation));

        GridData thing = myGson.fromJson(br, GridData.class);
        System.out.println(thing.toString());
        System.out.println(thing.getPatches().getPatches().get(4).toString());

        // JSONParseTester jpt = new JSONParseTester();
        // jpt.testRead(myGson, br);

        // Game myGame = convertToGame(myGameData);

        return thing;
    }

    /**
     * Method that converts a game data object into a game object. may be an issue as every aspect
     * of it is in a data wrapper
     * 
     * @param myGameData
     * @return
     
    private Game convertToGame (GameData gameData) {
        List<PlayerDataIndividual> myPlayerData = gameData.getPlayerData();
        List<LevelDataIndividual> myLevelData = gameData.getLevelData();
        PlayerDataIndividual myCurrentPlayerData = gameData.getCurrentPlayerData();
        LevelDataIndividual myCurentLevelData = gameData.getCurrentLevelData();
        
        for(LevelDataIndividual l : myLevelData){
            GridData gridData = l.getGrid();
            
            List<Patch> patches = new ArrayList<Patch>();
            for(PatchData pd : gridData.getPatches()){
                
            }
            List<Piece> pieces = new ArrayList<Piece>();
            for(PieceData pd : gridData.getPieces()){
                
            }
            
            Grid grid = new Grid(gridData.getRow(), gridData.getColumn(), pieces, patches);
            
            Level currentLevel = new Level(grid,l.getGoals(), l.getRules());
        }

        Game newGame = new Game(myPlayerData.size(), );

        return null;
    }
*/
    
    public void registerTypeAdapters (GsonBuilder builder) {
        builder.registerTypeAdapter(Event.class, new GenericTypeAdapter<Event>("gamedata.events"));
        builder.registerTypeAdapter(Action.class, new GenericTypeAdapter<Action>("gamedata.action"));
        builder.registerTypeAdapter(ActionConclusion.class, new ActionConclusionTypeAdapter<ActionConclusion>("gamedata.action"));
        builder.registerTypeAdapter(GridComponent.class, new GenericTypeAdapter<GridComponent>("gamedata.gamecomponent"));
        builder.registerTypeAdapter(SuperGrid.class, new GenericTypeAdapter<SuperGrid>("authoring_environment"));
    }

}
