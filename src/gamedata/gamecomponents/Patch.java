package gamedata.gamecomponents;

/**
 * A patch contains a state. State defines how you affect a piece when its on you.
 * 
 *
 */
public abstract class Patch {

    private int myState;

    public Patch (int state) {
        myState = state;
    }

}
