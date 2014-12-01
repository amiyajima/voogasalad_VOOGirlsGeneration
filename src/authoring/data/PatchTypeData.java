package authoring.data;

import gamedata.gamecomponents.Patch;
import java.util.LinkedList;
import java.util.List;

public class PatchTypeData implements AuthoringData<Patch> {
	
    private List<Patch> myPatches;
    
    public PatchTypeData () {
        myPatches = new LinkedList<Patch>();
    }
    
    @Override
    public void add (Patch... patches) {
        for (Patch p : patches) {
            myPatches.add(p);
        }
    }

    @Override
    public void remove (Patch... patches) {
        for (Patch p : patches) {
            myPatches.remove(p);
        }
    }

    @Override
    public void clear () {
        myPatches.clear();
    }

    @Override
    public List<Patch> get () {
        return myPatches;
    }
}