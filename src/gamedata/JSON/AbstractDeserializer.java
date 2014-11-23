package gamedata.JSON;

import com.google.gson.*;
import gamedata.rules.Rule;
import java.lang.reflect.Type;

/**
 * Abstract deserializer that allows the JSON Manager to deserialize an abstract object.
 * 
 * currently works for ruledata
 * @author annamiyajima
 *
 */
public class AbstractDeserializer implements JsonDeserializer<Rule> {

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
