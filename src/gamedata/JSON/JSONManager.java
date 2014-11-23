package gamedata.JSON;

import gamedata.gamecomponents.Game;
import gamedata.goals.Goal;
import gamedata.rules.Rule;
import gamedata.wrappers.GoalData;
import gamedata.wrappers.LevelData;
import gamedata.wrappers.PlayerData;
import gamedata.wrappers.RuleData;
import gameengine.player.Player;
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
    public void writeToJSON (Rule r, String fileName) {
        // Gson gson = new Gson();

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Rule.class, new RuleAdapter());
        // builder.registerTypeAdapter(Goal.class, new GoalDeserializer());
        // builder.registerTypeAdapter(Rule.class, new GenericTypeAdapter("gamedata.rules"));
        Gson gson = builder.create();
        String json = gson.toJson(r);
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
        // Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        //builder.registerTypeAdapter(Rule.class, new RuleAdapter());
        // builder.registerTypeAdapter(Goal.class, new GoalDeserializer());
        builder.registerTypeAdapter(Rule.class, new GenericTypeAdapter("gamedata/rules"));
        Gson gson = builder.create();

        BufferedReader br = new BufferedReader(new FileReader(jsonFileLocation));

        Rule rule = gson.fromJson(br, Rule.class);
        System.out.println(rule.toString());

        RuleData ruledata = gson.fromJson(br, RuleData.class);
        System.out.println(ruledata.toString());

        GoalData g = gson.fromJson(br, GoalData.class);
        System.out.println(g.toString());

        PlayerData myPlayers = gson.fromJson(br, PlayerData.class);
        System.out.println(myPlayers.getPlayers().get(0).getID());
        System.out.println(myPlayers.getPlayers().get(1).getID());

        RuleData myRules = gson.fromJson(br, RuleData.class);
        System.out.println(myRules.getRules().get(0).toString());

        Player player = gson.fromJson(br, Player.class);
        System.out.println(player.toString());

        LevelData myLevels = gson.fromJson(br, LevelData.class);
        System.out.println(myLevels.getLevels().size());

        return null;
    }

}
