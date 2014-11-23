package gamedata;

import gamedata.gamecomponents.Patch;
import java.util.List;

public class PatchData {
    private List<Patch> myPatches;
    
    public PatchData(List<Patch> patches) {
        myPatches = patches;
    }
    
    public List<Patch> getPatches() {
        return myPatches;
    }

}