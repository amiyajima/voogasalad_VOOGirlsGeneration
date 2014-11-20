package gameengine.engine;

import java.io.FileReader;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gameengine.player.Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


/**
 * Interprets and parses the JSON then builds the game components.
 * Calls factories to initialize each component.
 *
 * Creates a list of levels and a list of players to be used in initializing the game
 *
 */
public class GameBuilder {
    private List<Player> myPlayers;
    private List<Level> myLevels;
    private List<Patch> myPatches;
    private List<Piece> myPieces;
    private File mySampleJson;
    
    final static String DEFAULT_JSON_DIRECTORY = "./src/resources";
    final static String SAMPLE_JSON_SOURCE = "./src/json";

    /**
     * A game builder should be created for every time the entire program runs.
     * The same game builder should load information for the gamePlayer to play and then save
     * information for the authoring environment to modify
     */
    public GameBuilder () {
        myPlayers = new ArrayList<Player>();
        myLevels = new ArrayList<Level>();
        myPatches = new ArrayList<Patch>();
        myPieces = new ArrayList<Piece>();
        mySampleJson = new File(SAMPLE_JSON_SOURCE);
    }

    public File getFile () {
        return mySampleJson;
    }

    /**
     * Write a game and its contents into a JSON file.
     * 
     * @param game
     */
    public void writePlayerToJSONFile (Player p) {
        Gson gson = new Gson();
        System.out.println("gson created");
        // gson.fromjson deserializes a json object and creates a object of

        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(p);
        System.out.println("game converted to json");
        try {
            // write converted json data to a file named "CountryGSON.json"
            FileWriter writer = new FileWriter(DEFAULT_JSON_DIRECTORY + "/sample.json", true);
            writer.write(json);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }

    /**
     * Write a game and its contents into a JSON file.
     * 
     * @param game
     */
    public void writeLevelToJSONFile (Level l) {
        Gson gson = new Gson();
        System.out.println("gson created");
        // gson.fromjson deserializes a json object and creates a object of

        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(l);
        System.out.println("game converted to json");
        try {
            // write converted json data to a file named "CountryGSON.json"
            FileWriter writer = new FileWriter(DEFAULT_JSON_DIRECTORY + "/sample.json", true);
            writer.write(json);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }
    
    /**
     * Given a filePath, read in a JSON file and construct maps with the given information
     * 
     * @param filePath
     * @throws FileNotFoundException
     */
    public void readFromJSONFile (File f) throws FileNotFoundException {
        FileInputStream input = new FileInputStream(f);
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(input, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
