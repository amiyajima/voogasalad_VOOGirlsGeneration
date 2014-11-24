package gamedata.JSON;

import gamedata.gamecomponents.Game;
import gamedata.goals.Goal;
import gamedata.rules.Rule;
import gamedata.wrappers.GameData;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        
        //JSONParseTester jpt = new JSONParseTester();
        //jpt.testRead(myGson, br);
        
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
