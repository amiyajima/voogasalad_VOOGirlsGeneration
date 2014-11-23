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
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE  = "INSTANCE";
    
    @Override
    public JsonElement serialize(Rule src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        //result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        //result.add("properties", context.serialize(src, src.getClass())); 
        String className = src.getClass().getCanonicalName();
        result.addProperty(CLASSNAME, className);
        JsonElement elem = context.serialize(src); 
        result.add(INSTANCE, elem);
        return result;
    }
    
    @Override
    public Rule deserialize (JsonElement json, Type typeOfT, 
                             JsonDeserializationContext context) throws JsonParseException {
        /*
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
        */
        JsonObject jsonObject =  json.getAsJsonObject();
        JsonElement element = jsonObject.get(CLASSNAME);
        String className = element.getAsString();
        //JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonObject.get(CLASSNAME);
        //String className = jsonPrimitive.getAsString();

        Class<?> klass = null;
        try {
            klass = Class.forName(className);
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return context.deserialize(jsonObject.get(INSTANCE), klass);
    }

}
