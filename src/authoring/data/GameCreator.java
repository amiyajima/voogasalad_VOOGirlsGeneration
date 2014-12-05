package authoring.data;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.goals.Goal;
import gamedata.rules.Rule;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates game for data to save
 * @author Rica
 *
 */
public class GameCreator {
    Game myGame;
    
    /**
     * Initialization of game creator creates an empty game
     */
    public GameCreator() {
        myGame = new Game();
    }
    
    /**
     * Creates game using the levels and number of players you have
     * @param myLevels
     * @param numPlayers
     * @return
     */
    public Game createGame(List<AuthoringLevel> myAuthoringLevels, int numPlayers) {
        List<Level> myLevels = new ArrayList<Level>();
        for (AuthoringLevel al : myAuthoringLevels) {
            myLevels.add(convertAuthoringToLevel(al));
        }
        myGame = new Game(myLevels, numPlayers);
        return myGame;
    }
    
    /**
     * Converts an authoring level to a regular level
     * @param aLevel
     * @return
     */
    private Level convertAuthoringToLevel(AuthoringLevel aLevel) {
        // TODO This returns a DEFAULT level because Level and AuthoringLevel haven't
        // been changed to be similar yet
        // return new Level(aLevel.getGrid(), aLevel.getEvents());
        return new Level();
    }
}
