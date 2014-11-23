package gamedata;

import gamedata.gamecomponents.Game;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;


/**
 * Creates JSON file from Authoring Environment data. Loads and parses JSON file
 * to create a Game for Game Engine/Game Player.
 *
 * @author annamiyajima, Rica Zhang
 */
public class JSONManager {
    final static String DEFAULT_JSON_DIRECTORY = "./src/resources/";
    final static String SAMPLE_JSON = "./src/resources/test.json";

    /**
     * Constructor
     */
    public JSONManager () {
    }

    /**
     * Write a game and its contents into a JSON file.
     * 
     * @param game
     */
    public void writeToJSON (Game g, String fileName) {
        Gson gson = new Gson();
        System.out.println("gson created");

        String json = gson.toJson(g);
        System.out.println("game converted to json");
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }

    /**
     * Given a filePath, read in a JSON file and construct a game with that data
     * 
     * @param JSON file location
     * @throws FileNotFoundException
     */
    public Game readFromJSONFile (String jsonFileLocation) throws FileNotFoundException {
        System.out.println("read method called");
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(jsonFileLocation));

        PlayerData myPlayers = gson.fromJson(br, PlayerData.class);
        System.out.println(myPlayers.getPlayers().get(0).getID());

        LevelData myLevels = gson.fromJson(br, LevelData.class);
        System.out.println(myLevels.getLevels().size());

        return null;
    }

}
