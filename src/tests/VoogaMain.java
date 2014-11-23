package tests;

import gamedata.JSON.JSONManager;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import java.io.FileNotFoundException;


/**
 * Used to test JSON writer and reader
 * 
 * @author annamiyajima, Rica Zhang
 *
 */
public class VoogaMain {
    public static void testJSONwrite () {
        String saveTo = "src/resources/json/AnnaSample.json";
        // String saveTo = "src/resources/json/RicaSample.json";
        JSONManager myJSONmanager = new JSONManager();
        JSONBobTester jb = new JSONBobTester();
        // myJSONmanager.writeToJSON(jb.createNewGame(), saveTo);
        myJSONmanager.writeToJSON(new MoveCountRule(3), saveTo);
    }

    public static void testJSONload () {
        JSONManager jsonManager = new JSONManager();
        try {
            jsonManager.readFromJSONFile("src/resources/json/RicaSample.json");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        System.out.println("main is running");
        testJSONload();
        // testJSONwrite();
    }
}
