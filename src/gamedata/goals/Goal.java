package gamedata.goals;

import gamedata.gamecomponents.Level;


public abstract class Goal {

    public Goal () {

    }

    /**
     * 
     * @param l
     * @return
     */
    public abstract int checkGameState (Level l);
}
