package gamedata;

import java.io.FileNotFoundException;


/**
 * Main method used to test Game/Player/Level/Rule/Grid etc. interaction on the
 * back end
 * 
 * @author annamiyajima
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
