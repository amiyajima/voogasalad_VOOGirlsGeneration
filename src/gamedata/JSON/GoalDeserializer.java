package gamedata.JSON;

import com.google.gson.*;
import gamedata.goals.Goal;
import java.lang.reflect.Type;

/**
 * Custom deserializer that deserializes the Goal class
 * @author annamiyajima
 *
 */
public class GoalDeserializer implements JsonDeserializer<Goal> {

    @Override
    public Goal deserialize (JsonElement json,
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
