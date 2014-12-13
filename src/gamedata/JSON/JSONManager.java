package gamedata.JSON;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.StatsModifier;
import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;
import gamedata.action.conclusions.ReceiverToInventoryConclusion;
import gamedata.adapters.ActionDeserializer;
import gamedata.events.Event;
import gamedata.events.conditions.Condition;
import gamedata.events.conditions.IsDead;
import gamedata.events.globalaction.DeletePieceAtLocation;
import gamedata.events.globalaction.GlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.GridComponent;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.goals.Goal;
import gamedata.rules.Rule;
import gamedata.stats.Stats;
import gamedata.wrappers.ActionData;
import gamedata.wrappers.ActionDataIndividual;
import gamedata.wrappers.EventDataIndividual;
import gamedata.wrappers.GameData;
import gamedata.wrappers.GridData;
import gamedata.wrappers.LevelDataIndividual;
import gamedata.wrappers.PatchData;
import gamedata.wrappers.PatchDataIndividual;
import gamedata.wrappers.PieceData;
import gamedata.wrappers.PieceDataIndividual;
import gamedata.wrappers.PlayerDataIndividual;
import gamedata.wrappers.StatsData;
import gameengine.movement.Movement;
import gameengine.player.Player;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import authoring.data.PatchInstanceData;
import authoring_environment.GUIGrid;
import authoring_environment.SuperGrid;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;


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

    public Game readFromJSONFile (String jsonFileLocation) throws FileNotFoundException {
        System.out.println("JSONManager: read method called");
        BufferedReader br = new BufferedReader(new FileReader(jsonFileLocation));
        
        Game pi = myGson.fromJson(br, Game.class);
        System.out.println(pi.toString());

        /*
        GameData gameData = myGson.fromJson(br, GameData.class);
        System.out.println(gameData.toString());
        
        PatchData patchData = gameData.getMyLevels().get(0).getGrid().getPatches();
        System.out.println(patchData.toString());
        List<Patch> myPatches = patchData.getPatchesFromData();
        System.out.println("Patch #1: " + myPatches.get(0));
        
        EventDataIndividual edi = gameData.getMyLevels().get(0).getMyEventData().get(0);
        System.out.println(edi.toString());
        Event myEvent = edi.getEventFromData();
        System.out.println("Event: " + myEvent);
        
        ActionDataIndividual adi = gameData.getMyLevels().get(0).getGrid().getPieces().getPieces().get(0).getMyActions().get(0);
        System.out.println(adi.toString());
        Action myAction = adi.getActionFromData();
        System.out.println("Action: " + myAction);
        
        Player humanPlayer = gameData.getMyPlayers().get(0).getPlayerFromData();
        System.out.println("Human Player: " + humanPlayer.getID() + " " + humanPlayer.getNumMovesPlayed());
        Player aiPlayer = gameData.getMyPlayers().get(1).getPlayerFromData();
        System.out.println("Supposed AI Player: " + aiPlayer.getID() + " " + humanPlayer.getNumMovesPlayed());
        
        Game myGame = gameData.getGameFromData();
        System.out.println("Game: " + myGame.toString());
        */
        return pi;
    }    
    
    public void registerTypeAdapters (GsonBuilder builder) {
        builder.registerTypeAdapter(StatsModifier.class, new GenericTypeAdapter<StatsModifier>("gamedata.action"));
        builder.registerTypeAdapter(Player.class, new GenericTypeAdapter<Player>("gameengine.player"));
        builder.registerTypeAdapter(Action.class, new GenericTypeAdapter<ActionDataIndividual>("gamedata.action"));
        builder.registerTypeAdapter(ActionConclusion.class, new ActionConclusionTypeAdapter<ActionConclusion>("gamedata.action"));
        builder.registerTypeAdapter(GridComponent.class, new GenericTypeAdapter<GridComponent>("gamedata.gamecomponent"));
        builder.registerTypeAdapter(Condition.class, new GenericTypeAdapter<Condition>("gamedata.events.conditions"));
        builder.registerTypeAdapter(GlobalAction.class, new GenericTypeAdapter<GlobalAction>("gamedata.events.globalaction"));
        builder.registerTypeAdapter(SuperGrid.class, new GenericTypeAdapter<SuperGrid>("authoring_environment"));
    }

}
