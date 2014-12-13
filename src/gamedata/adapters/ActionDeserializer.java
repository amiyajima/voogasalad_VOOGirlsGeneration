package gamedata.adapters;

import gamedata.JSON.ActionConclusionTypeAdapter;
import gamedata.JSON.GenericTypeAdapter;
import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.StatsModifier;
import gamedata.gamecomponents.GridComponent;
import gamedata.wrappers.ActionConclusionData;
import gamedata.wrappers.ActionDataIndividual;
import gamedata.wrappers.ConcreteActionData;
import gamedata.wrappers.StatsTotalLogicData;
import gameengine.player.Player;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import authoring_environment.SuperGrid;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ActionDeserializer implements JsonDeserializer<ActionDataIndividual> {
    private Gson myGson;
    
    private String myID;
    private List<Point2D.Double> myAttackRange;
    private List<Point2D.Double> myEffectRange;
    private List<StatsTotalLogicData> myStatsLogics;
    private ActionConclusionData myConclusion;
    
    public ActionDeserializer() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StatsModifier.class, new GenericTypeAdapter<StatsModifier>("gamedata.action"));
        builder.registerTypeAdapter(ActionConclusion.class, new ActionConclusionTypeAdapter<ActionConclusion>("gamedata.action"));
        myGson = builder.create();
    }
    
    @Override
    public ActionDataIndividual deserialize (JsonElement json, Type typeOfT, 
                                             JsonDeserializationContext context) throws JsonParseException {
        JsonObject myJson = json.getAsJsonObject();
        String type = myJson.get("type").getAsString();
        System.out.println("\n\nType: " + type);
        JsonObject properties = myJson.get("properties").getAsJsonObject();
        
        if (type.equals("ConcreteAction")) {
            myID = properties.get("myID").getAsString();
            
            JsonArray myAttackJson = properties.get("myAttackRange").getAsJsonArray();
            Point2D.Double[] myAttacks = new Point2D.Double[myAttackJson.size()];
            myAttackRange = Arrays.asList(myAttacks);
            
            JsonArray myEffectJson = properties.get("myEffectRange").getAsJsonArray();
            Point2D.Double[] myEffects = new Point2D.Double[myEffectJson.size()];
            myEffectRange = Arrays.asList(myEffects);
            
            JsonArray myStatsJson = properties.get("myStatsLogics").getAsJsonArray();
            StatsTotalLogicData[] myStats = new StatsTotalLogicData[myStatsJson.size()];
            myStatsLogics = Arrays.asList(myStats);
            
            JsonObject myConclusionJson = properties.get("myConclusion").getAsJsonObject();
            myConclusion = myGson.fromJson(myConclusionJson, ActionConclusionData.class);
            return new ConcreteActionData(myID, myAttackRange, myEffectRange, myStatsLogics, myConclusion);
        }
        else {
            System.out.println("ActionDeserializer: Could not identify type.");
            return null;
        }
    }

}
