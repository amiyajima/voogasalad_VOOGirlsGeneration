package gamedata.wrappers;

import gamedata.gamecomponents.Patch;
import java.util.List;

/**
 * Wrapper for patchdata in GridData
 * @author Rica
 *
 */
public class PatchData {
    private List<Patch> myPatches;

    public PatchData (List<Patch> patches) {
        myPatches = patches;
    }

    public List<Patch> getPatches () {
        return myPatches;
    }

}
