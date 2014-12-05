package authoring.data;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Level;
import java.util.List;

/**
 * Creates game for data to save
 * @author Rica
 *
 */
public class GameCreator {
    Game myGame;
    public GameCreator() {
        myGame = new Game();
    }
    
    public Game createGame(List<Level> myLevels, int numPlayers) {
        myGame = new Game(myLevels, numPlayers);
        return myGame;
    }
}
