package gamedata.wrappers;

import java.util.ArrayList;
import java.util.List; 
import authoring_environment.GUIGrid;
import gamedata.events.Event;
import gamedata.gamecomponents.Level;
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
    private List<EventDataIndividual> myEvents;
    private String myId;
    private boolean myGameWon;
    private boolean myGameLost;
    private String myNextLevelID;
    private boolean myTurnOver;
    private boolean isWinningLevel;

    public LevelDataIndividual (GridData grid, List<EventDataIndividual> events, String id, boolean gameWon, 
                                boolean gameLost, String nextLevelID, boolean turnOver) {
        myGrid = grid;
        myEvents = events;
        myId = id;
        myGameWon = gameWon;
        myGameLost = gameLost;
        myNextLevelID = nextLevelID;
        myTurnOver = turnOver;
        
    }

    public GridData getMyGrid () {
        return myGrid;
    }

    public List<EventDataIndividual> getMyEventData () {
        return myEvents;
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
    
    public boolean getIsWinningLevel() {
        return isWinningLevel;
    }

    /**
     * Parses my class's content to create a level
     * @return Level
     */
    public Level getLevelFromData () {
        List<Event> myEventsFromData = new ArrayList<Event>();
        for (EventDataIndividual edi : myEvents) {
            myEventsFromData.add(edi.getEventFromData());
        }
        Level myLevel = new Level(myGrid.getGridFromData(), myEventsFromData, 
                                  myId, isWinningLevel);
        return myLevel;
    }

    /*
    public String toString () {
        return "LevelDataIndividual: goals are " + myGoals + " myRules are " + myRules;
    }
    */

}
