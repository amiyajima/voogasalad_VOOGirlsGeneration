package gamedata.rules;

import java.util.List;


/**
 * A rule that checks if the piece can move to the given patch 
 *
 */
public class MovableRule extends Rule {

    private List<Integer> myNoZones;

    /**
     * @param patches list of states of patches that a Piece cannot move on
     */
    public MovableRule (List<Integer> patches) {
        myNoZones = patches;
    }

    @Override
    public boolean conditionsMet (int patchState) {
        return (myNoZones.contains(patchState));
    }

}
