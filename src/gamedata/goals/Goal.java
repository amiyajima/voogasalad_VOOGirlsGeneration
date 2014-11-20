package gamedata.goals;

import gamedata.gamecomponents.Level;


/**
 * A goal defines the win conditions for each level.
 *
 */
public abstract class Goal {

    public Goal () {

    }

    /**
     * Checked after every move that the player makes.
     * If the level has been won, return 1.
     * If it was lost, return -1.
     * Otherwise, return 0.
     * 
     * @param l
     * @return
     */
    public abstract int checkGameState (Level l);
}