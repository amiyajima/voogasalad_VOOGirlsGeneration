package gamedata.wrappers;

import gamedata.gamecomponents.Patch;
import java.util.ArrayList;
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

    /**
     * Patch data structure unwrapper
     * @return
     */
    public List<Patch> getPatchesFromData () {
        List<Patch> myPatchesFromData = new ArrayList<Patch>();
        for (PatchDataIndividual pdi : myPatches) {
            myPatchesFromData.add(pdi.getPatchFromData());
        }
        return myPatchesFromData;
    }

}
