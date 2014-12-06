package gameengine.player;

import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import java.util.Set;


/**
 * Defines a simple AI for playing turn-based strategy games
 * 
 * @author Jesse
 *
 */
public class SimpleAIPlayer extends Player {

    Level myLevel;

    public SimpleAIPlayer (int id, Level l) {
        super(id);
        myLevel = l;
    }

    /**
     * 
     */
    public void play () {

    }

    @Override
    public void startTurn () {
        // TODO Auto-generated method stub

    }

}
