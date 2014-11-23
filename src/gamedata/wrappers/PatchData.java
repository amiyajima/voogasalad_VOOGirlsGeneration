package gamedata.wrappers;

import java.util.List;

/**
 * Wrapper for patchdata in GridData
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
