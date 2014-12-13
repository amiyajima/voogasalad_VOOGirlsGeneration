package gamedata.JSON;

import gamedata.goals.PlayerPiecesRemovedGoal;
import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/**
 *Adaptation of Eli's GenericTypeAdapter for Actions.
 *Necessary because ActionConclusions are stored in a subPackage of gamedata.action
 *
 * @param <T> Class for which the TypeAdapter is being created.
 */
public class ActionConclusionTypeAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private String myPackageName;

    /**
     * Constructor for GenericTypeAdapter.
     * 
     * @param packageName Name of package holding the subclass.
     */
    public ActionConclusionTypeAdapter (String packageName) {
        myPackageName = packageName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonElement serialize (T src, Type typeOfSrc,
                                  JsonSerializationContext context) {
//        System.out.println("serialize called in " + typeOfSrc + " adapter");
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T deserialize (JsonElement json, Type typeOfT,
                          JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
//        System.out.println("type " + type + " detected");
        JsonElement element = jsonObject.get("properties");
//        System.out.println("element " + element + " retrieved");
        //System.out.println(PlayerPiecesRemovedGoal.class);
        try {
            return context.deserialize(element, Class.forName(myPackageName + ".conclusions." + type));
        }
        catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }

}
