package gamedata.rules;

import java.util.Map;


/**
 * A rule that checks if the piece can move to the given patch 
 */
public class MovableRule extends Rule {

    private Map<Integer,Integer> myPossiblePatches;

    /**
     * @param patches map with keys as state of patch and values as a list of
     * pieces that can be on that patch
     */
    public MovableRule (Map<Integer,Integer> patches) {
        myPossiblePatches = patches;
    }

    @Override
    public boolean conditionsMet (int patchState) {
        
    }

}
