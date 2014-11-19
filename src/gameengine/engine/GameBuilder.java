package gameengine.engine;

import java.io.FileReader;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gameengine.player.Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    final static String DEFAULT_JSON_DIRECTORY = "./src/json";
    final static String SAMPLE_JSON_SOURCE = "./src/json/sample.json";

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

    private static final String jsonLine = "test test test";

    /**
     * Write a game and its contents into a JSON file.
     * 
     * @param game
     */
    public void writePlayersToJSONFile (List<Player> players) {
        Gson gson = new Gson();

        // gson.fromjson deserializes a json object and creates a object of

        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(players);

        System.out.println(json);

        try {
            // write converted json data to a file named "CountryGSON.json"
            FileWriter writer = new FileWriter(DEFAULT_JSON_DIRECTORY + "/saved_config.json");
            writer.write(json);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Given a filePath, read in a JSON file and construct maps with the given information
     * 
     * @param filePath
     * @throws FileNotFoundException
     */
    public void readFromJSONFile (String filePath) throws FileNotFoundException {
        Gson gson = new Gson();

        // gson.fromjson deserializes a json object and creates a object of

        JsonParser parser = new JsonParser();

        Object obj = parser.parse(new FileReader(mySampleJson));
        JsonObject jsonObject = (JsonObject) obj;

        System.out.println("Reading JSON from a file");
        System.out.println("----------------------------");
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        // for each heading that says level

        Player player = gson.fromJson(br, Player.class);

    }

    /**
     * fills in myPlayers, myLevels, myPatches, and myPieces using a map, which contains
     * all the components of the chosen game, parsed by a Parser
     */
    public void initiateComponents (Map<String, String> components) {
        Map jsonJavaRootObject =
                new Gson().fromJson("{/*whatever your mega complex object*/}", Map.class);

        Map<String, Object> javaRootMapObject = new Gson().fromJson(jsonLine, Map.class);

        System.out.println(
                (
                (Map)
                (
                (List)
                (
                (Map)
                (
                javaRootMapObject.get("data")
                )
                ).get("translations")
                ).get(0)
                ).get("translatedText")
                );
    }
}
