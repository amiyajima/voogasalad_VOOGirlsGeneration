package gamedata.JSON;

import com.google.gson.*;
import gamedata.rules.Rule;
import java.lang.reflect.Type;

/**
 * Custom deserializer that deserializes the Rule class
 * @author annamiyajima
 *
 */
public class RuleAdapter implements JsonSerializer<Rule>,JsonDeserializer<Rule> {
    @Override
    public JsonElement serialize(Rule src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getCanonicalName()));
        result.add("properties", context.serialize(src, src.getClass())); 
        
        
        return result;
    }
    
    @Override
    public Rule deserialize (JsonElement json,
                                        Type typeOfT,
                                        JsonDeserializationContext context)

                                                                           throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");
        try {
            return context.deserialize(element,
                                       Class.forName("com.googlecode.whiteboard.model." + type));
        }
        catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }

    }

}
