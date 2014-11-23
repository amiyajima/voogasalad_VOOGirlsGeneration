package tests;

import gamedata.JSONManager;
import java.io.FileNotFoundException;

/**
 * Used to test JSON reader in back-end
 * 
 * @author annamiyajima, Rica Zhang
 *
 */
public class VoogaMain {
    public static void main (String[] args) {
        System.out.println("main is running");
        JSONManager jsonManager = new JSONManager();
        try {
            jsonManager.readFromJSONFile("");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
