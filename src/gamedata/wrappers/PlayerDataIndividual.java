package gamedata.wrappers;

import gamedata.action.Action;
import gameengine.player.HumanPlayer;
import gameengine.player.Player;
import java.awt.geom.Point2D;
import java.util.Map;
import javafx.scene.input.KeyCode;


/**
 * Wrapper for individual player data in Players to see current
 * 
 * @author Rica
 *
 */
public class PlayerDataIndividual {
    private int myNumMovesPlayed;
    private int myID;
    
    public PlayerDataIndividual(int movesPlayed , int ID){
        myNumMovesPlayed = movesPlayed;
        myID = ID;
    }
    
    public String toString(){
        return "PlayerDataIndividual: numMovesPlayed = " + myNumMovesPlayed + " myID = " + myID;
    }

    /**
     * FOR TESTING PURPOSES
     * @return
     */
    public int getID(){
        return myID;
    }

    public Player getPlayerFromData () {
        System.out.println("PDI Creating player: moves " + myNumMovesPlayed + " id " + myID);
        Player myPlayerFromData = new HumanPlayer(myID);
        return myPlayerFromData;
    }
}
