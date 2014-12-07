package gamedata.events.globalactions;

import gamedata.gamecomponents.Game;

public interface GlobalAction {

    //private GameComponent myContext;
    //private GameComponent myTarget;
    //private Stat myValue;
    
    // needs to take in context, target, and value

    public void performAction ();

}
