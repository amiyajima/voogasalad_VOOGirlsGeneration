package tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import gamedata.action.Action;
import gamedata.action.ConcreteAction;
import gamedata.adapters.ActionDeserializer;
import gamedata.wrappers.ActionDataIndividual;
import gamedata.wrappers.ConcreteActionData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Tests deserialization by type adapter
 * @author Rica
 *
 */
public class TypeAdapterTester {
    
    public static void main(String[] args) {
        testActionAdapter();
    }

    private static void testActionAdapter () {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Action.class, new ActionDeserializer());
        Gson myGson = builder.create();
        
        try {
            String link = "src/resources/json/Patch-Rica.json";
            BufferedReader br = new BufferedReader(new FileReader(link));
            ActionDataIndividual adi = myGson.fromJson(br, ConcreteActionData.class);
            ConcreteActionData cad = (ConcreteActionData) adi;
            System.out.println(cad.getName());
            System.out.println(adi);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
