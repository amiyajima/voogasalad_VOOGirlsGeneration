package gameengine.player;

import gamePlayer.GameGrid;
import java.util.Set;


/**
 * Defines a simple AI for playing turn-based strategy games
 * 
 * @author Jesse
 *
 */
public class SimpleAIPlayer extends Player {

    // player needs to know what pieces belong to it.
    // get a list of pieces that have available moves.
    private Set myPieces;

    public SimpleAIPlayer (int id, GameGrid g) {
        super(id);
        myPieces = populatePieces(g);
    }

    /**
     * Iterate through the list of pieces available on the grid.
     * 
     * @param g
     * @return
     */
    private Set populatePieces (GameGrid g) {
        // Set allPieces = g.get
        return null;
    }

    /**
     * 
     */
    public void play () {
        
    }

}
