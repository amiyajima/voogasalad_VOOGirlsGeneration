package gamedata;

import gamedata.gamecomponents.Game;
import gameengine.player.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;


/**
 * Interprets and parses the JSON then builds the game components.
 * Calls factories to initialize each component.
 *
 * Creates a list of levels and a list of players to be used in initializing the game
 *
 * @author annamiyajima
 */
public class JSONMaster {
    final static String DEFAULT_JSON_DIRECTORY = "./src/resources/";
    final static String SAMPLE_JSON = "./src/resources/test.json";

    /**
     * A game builder should be created for every time the entire program runs.
     * The same game builder should load information for the gamePlayer to play and then save
     * information for the authoring environment to modify
     */
    public JSONMaster () {
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
            // write converted json data to a file named "CountryGSON.json"
            //FileWriter writer = new FileWriter(DEFAULT_JSON_DIRECTORY + fileName);
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
     * @param filePath
     * @throws FileNotFoundException
     */
    public Player readFromJSONFile (String jsonFileLocation) throws FileNotFoundException {
        System.out.println("read method called");
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(jsonFileLocation));
        
        //Level l = gson.fromJson(br, Level.class);
        //System.out.println(l);
        
        Player p = gson.fromJson(br, Player.class);
        System.out.println(p);
        
        Game g = gson.fromJson(br, Game.class);
        
        return p;
    }

}
