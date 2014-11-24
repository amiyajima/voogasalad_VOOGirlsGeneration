package gamedata.wrappers;

import java.util.List;

/**
 * Wrapper for a list of patches in gamedata
 * @author Rica
 *
 */
public class PatchData {
    private List<PatchDataIndividual> myPatches;

    public PatchData (List<PatchDataIndividual> patches) {
        myPatches = patches;
    }

    public List<PatchDataIndividual> getPatches () {
        return myPatches;
    }

}
