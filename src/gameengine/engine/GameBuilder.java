package gameengine.engine;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gameengine.player.Player;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

/**
 * Interprets and parses the JSON then builds the game components.
 * Calls factories to initialize each component.
 *
 */
public class GameBuilder {
    private List<Player> myPlayers;
    private List<Level> myLevels;
    private List<Patch> myPatches;
    private List<Piece> myPieces;

    private static final String jsonLine = "test test test";
    
    public void getJSONFile () {

    }

    /**
     * fills in myPlayers, myLevels, myPatches, and myPieces using a map, which contains
     * all the components of the chosen game, parsed by a Parser
     */
    public void initiateComponents (Map<String, String> components) {
        Map jsonJavaRootObject =
                new Gson().fromJson("{/*whatever your mega complex object*/}", Map.class);

        Map<String, Object> javaRootMapObject = new Gson().fromJson(jsonLine, Map.class);

        System.out.println(
                (
                (Map)
                (
                (List)
                (
                (Map)
                (
                javaRootMapObject.get("data")
                )
                ).get("translations")
                ).get(0)
                ).get("translatedText")
                );
    }
}
