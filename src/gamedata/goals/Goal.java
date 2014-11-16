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
    public abstract boolean checkGameState (Level l);
}
