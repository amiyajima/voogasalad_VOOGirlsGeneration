package gamedata.wrappers;

import java.util.List; 
import gamedata.goals.Goal;
import gamedata.rules.Rule;


/**
 * LevelData wrapper for individual levels in LevelData
 * 
 * @author Rica
 *
 */
public class LevelDataIndividual {
    private GridData myGrid;
    private EventData myEventData;
    private String myId;
    private boolean myGameWon;
    private boolean myGameLost;
    private String myNextLevelID;
    private boolean myTurnOver;

    public LevelDataIndividual (GridData grid, EventData eventData, String id, boolean gameWon, 
                                boolean gameLost, String nextLevelID, boolean turnOver) {
        myGrid = grid;
        myEventData = eventData;
        myId = id;
        myGameWon = gameWon;
        myGameLost = gameLost;
        myNextLevelID = nextLevelID;
        myTurnOver = turnOver;
        
    }

    public GridData getMyGrid () {
        return myGrid;
    }

    public EventData getMyEventData () {
        return myEventData;
    }

    public String getMyId () {
        return myId;
    }

    public boolean isMyGameWon () {
        return myGameWon;
    }

    public boolean isMyGameLost () {
        return myGameLost;
    }

    public String getMyNextLevelID () {
        return myNextLevelID;
    }

    public boolean isMyTurnOver () {
        return myTurnOver;
    }

    public GridData getGrid () {
        return myGrid;
    }

    /*
    public String toString () {
        return "LevelDataIndividual: goals are " + myGoals + " myRules are " + myRules;
    }
    */

}
