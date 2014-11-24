package gamedata.JSON;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.goals.Goal;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gamedata.wrappers.GameData;
import gamedata.wrappers.GoalData;
import gamedata.wrappers.GridData;
import gamedata.wrappers.LevelData;
import gamedata.wrappers.LevelDataIndividual;
import gamedata.wrappers.PatchData;
import gamedata.wrappers.PatchDataIndividual;
import gamedata.wrappers.PieceDataIndividual;
import gamedata.wrappers.PlayerData;
import gamedata.wrappers.PlayerDataIndividual;
import gamedata.wrappers.RuleData;
import gameengine.player.Player;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;


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
        // registerTypeAdapters(builder);
        myGson = builder.create();
    }

    /**
     * Write a game and its contents into a JSON file.
     * 
     * @param game
     */
    public void writeToJSON (Game game, String fileName) {
        String json = myGson.toJson(game);
        System.out.println("JSONManager: game converted to json!");
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
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

        GameData myGameData = myGson.fromJson(br, GameData.class);
        System.out.println(myGameData.toString());
        
        // need to add {myLevels: before and } after list
        /*LevelData myLevels = myGson.fromJson(br, LevelData.class);
        System.out.println(myLevels.getLevels().get(0));
        // level data exists and contains levels. goals and rules within the level is empty.
        
        //works using singleLevel.json
        LevelDataIndividual mySingleLevel = myGson.fromJson(br, LevelDataIndividual.class);
        System.out.println(mySingleLevel);

        // works using Grid.json
        GridData gridData = myGson.fromJson(br, GridData.class);
        System.out.println(gridData);

        PlayerDataIndividual playerData = myGson.fromJson(br, PlayerDataIndividual.class);
        System.out.println(playerData);

        // works using SinglePatch.json
        PatchDataIndividual patchData = myGson.fromJson(br, PatchDataIndividual.class);
        System.out.println(patchData.getMyTypeID());

        // works using SinglePiece.json
        PieceDataIndividual pieceData = myGson.fromJson(br, PieceDataIndividual.class);
        System.out.println(pieceData.getMyTypeID());

        // map of patches
        PatchData myPatches = myGson.fromJson(br, PatchData.class);
        System.out.println(myPatches);

        // goal data is created, but contains no goals. does not work if it is called after rule
        // data
        GoalData myGoals = myGson.fromJson(br, GoalData.class);
        System.out.println(myGoals.toString());

        // ruledata exists but contains no rules. does not work if it is called after goal data
        RuleData ruledata = myGson.fromJson(br, RuleData.class);
        System.out.println(ruledata);
        // issue = how to represent "properties" and put it in the IndividualRuleData super class
        // same results whether rule data uses RuleDataIndividual or MoveCountRuleData. seems like
        // the constructor within isn't called.

        // when tested with SingleRule.json, creates a single MoveCountRule
        Rule rule = myGson.fromJson(br, MoveCountRule.class);
        System.out.println(rule.toString());

        // when tested with the SingleGoal json, creates a PlayerPiecesRemovedGoal
        Goal g = myGson.fromJson(br, Goal.class);
        System.out.println(g.toString());

        PlayerData myPlayers = myGson.fromJson(br, PlayerData.class);
        System.out.println(myPlayers.getPlayers().get(0).getID());
        System.out.println(myPlayers.getPlayers().get(1).getID());

        Player player = myGson.fromJson(br, Player.class);
        System.out.println(player.toString());*/

        Game myGame = convertToGame(myGameData);
        
        return myGame;
    }

    /**
     * Method that converts a game data object into a game object. may be an issue as every aspect of it is in a data wrapper
     * @param myGameData
     * @return
     */
    private Game convertToGame (GameData myGameData) {
        
        return null;
    }

    /*
     * public void test(String type, BufferedReader reader){
     * Object deserializedObject = null;
     * Class classDefinition = null;
     * try {
     * classDefinition = Class.forName(type);
     * try {
     * deserializedObject = classDefinition.newInstance();
     * }
     * catch (InstantiationException e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * catch (IllegalAccessException e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * }
     * catch (ClassNotFoundException e) {
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * }
     * deserializedObject = myGson.fromJson(reader, deserializedObject.getClass());
     * System.out.println(deserializedObject.toString());
     * }
     */

    public void registerTypeAdapters (GsonBuilder builder) {
        builder.registerTypeAdapter(Goal.class, new GenericTypeAdapter<Goal>("gamedata.goals"));
        builder.registerTypeAdapter(Rule.class, new GenericTypeAdapter<Rule>("gamedata.rules"));
    }

}
