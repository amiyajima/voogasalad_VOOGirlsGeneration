package authoring.data;

import gamedata.events.Event;
import gamedata.gamecomponents.Grid;
import gamedata.goals.Goal;
import gamedata.rules.Rule;

import java.util.List;

import authoring_environment.ShapeGrid;

/**
 * Stores data for levels in the authoring environment.
 * Needs getter and setter methods so that data can
 * be manipulated by the user.
 * 
 * @author Jennie Ju
 *
 */
//TODO: Consolidate this with the GameData Level
public class AuthoringLevel {
	
	private ShapeGrid myGrid;
    private List<Event> myEvents;
	private String myId;
    
    public AuthoringLevel(ShapeGrid grid, List<Event> events, String id) {
    	myGrid = grid;
    	myEvents = events;
    	myId = id;
    }
    
   public String getId() {
	   return myId;
   }
   
    
    

}
