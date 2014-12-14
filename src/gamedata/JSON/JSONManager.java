package gamedata.JSON;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.StatsModifier;
import gamedata.events.Condition;
import gamedata.events.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.GridComponent;
import gamedata.gamecomponents.IHasStats;
import gameengine.player.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

        
        Game pi = myGson.fromJson(br, Game.class);
//        System.out.println("Level 1: " + pi.getLevels().get(0).getGrid().toString());
        return pi;
    }    
    
    public void registerTypeAdapters (GsonBuilder builder) {
        builder.registerTypeAdapter(StatsModifier.class, new GenericTypeAdapter<StatsModifier>("gamedata.action"));
        builder.registerTypeAdapter(Player.class, new GenericTypeAdapter<Player>("gameengine.player"));
        builder.registerTypeAdapter(Action.class, new GenericTypeAdapter<Action>("gamedata.action"));
        builder.registerTypeAdapter(ActionConclusion.class, new ActionConclusionTypeAdapter<ActionConclusion>("gamedata.action"));
        builder.registerTypeAdapter(GridComponent.class, new GenericTypeAdapter<GridComponent>("gamedata.gamecomponent"));
        builder.registerTypeAdapter(Condition.class, new GenericTypeAdapter<Condition>("gamedata.events.conditions"));
        builder.registerTypeAdapter(GlobalAction.class, new GenericTypeAdapter<GlobalAction>("gamedata.events.globalaction"));
        builder.registerTypeAdapter(SuperGrid.class, new GenericTypeAdapter<SuperGrid>("authoring_environment"));
        builder.registerTypeAdapter(IHasStats.class, new GenericTypeAdapter<IHasStats>("gamedata.gamecomponent"));
    }

}
