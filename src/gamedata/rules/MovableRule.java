package gamedata.rules;

import java.util.List;
import java.util.Map;


/**
 * A rule that checks if the piece can move to the given patch 
 */
public class MovableRule extends Rule {

    private Map<Integer,List<Integer>> myPossiblePatches;

    /**
     * @param patches map with keys as state of patch and values as a list of
     * pieces that can be on that patch
     */
    public MovableRule (Map<Integer,List<Integer>> patches) {
        myPossiblePatches = patches;
    }
    
    
    @Override
    public boolean conditionsMet (int patchState) {
    	//TODO: SET THIS TO THE CORRECT LOGIC
		return false;
        
    }
    
    public boolean conditionsMet (int pieceState, int patchState){
        for(int i:myPossiblePatches.get(pieceState)){
            if(i == patchState){
                return true;
            }
        }
        return false;
    }

}
