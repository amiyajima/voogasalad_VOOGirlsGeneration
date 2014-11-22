package gamedata;

import gamedata.action.Action;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gameengine.movement.Movement;
import gameengine.player.Player;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.sun.xml.internal.ws.api.message.Message;


/**
 * Interprets and parses the JSON then builds the game components.
 * Calls factories to initialize each component.
 *
 * Creates a list of levels and a list of players to be used in initializing the game
 *
 * @author annamiyajima
 */
public class JSONManager {
    final static String DEFAULT_JSON_DIRECTORY = "./src/resources/";
    final static String SAMPLE_JSON = "./src/resources/test.json";

    /**
     * A game builder should be created for every time the entire program runs.
     * The same game builder should load information for the gamePlayer to play and then save
     * information for the authoring environment to modify
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
    public Game readFromJSONFile (String jsonFileLocation) throws FileNotFoundException {
        System.out.println("read method called");
        jsonFileLocation = "C:\\Users\\Rica\\Desktop\\sample.json";
        InputStream in = new FileInputStream(jsonFileLocation);
        
        Gson gson = new Gson();
        /*
        JsonReader reader = new JsonReader(new InputStreamReader(in));
        reader.beginArray();
        while (reader.hasNext()) {
            System.out.println(readMessage(reader));
        }
        */
        BufferedReader br = new BufferedReader(new FileReader(jsonFileLocation));
        Player player = gson.fromJson(br, Player.class);
        System.out.println(player.getID());
        Game game = gson.fromJson(br, Game.class);
        System.out.println(game.getPlayers().get(0));
        return game;
    }
    /*
    public List<Message> readMessagesArray(JsonReader reader) throws IOException {
        List<Message> messages = new ArrayList<Message>();

        reader.beginArray();
        while (reader.hasNext()) {
          messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
      }
    
    public Message readMessage(JsonReader reader) throws IOException {
        long id = -1;
        String text = null;
        User user = null;
        List<Double> geo = null;

        reader.beginObject();
        while (reader.hasNext()) {
          String name = reader.nextName();
          if (name.equals("id")) {
            id = reader.nextLong();
          } else if (name.equals("text")) {
            text = reader.nextString();
          } else if (name.equals("geo") && reader.peek() != JsonToken.NULL) {
            geo = readDoublesArray(reader);
          } else if (name.equals("user")) {
            user = readUser(reader);
          } else {
            reader.skipValue();
          }
        }
        reader.endObject();
        return new Message(id, text, user, geo);
      }
*/
    
}
