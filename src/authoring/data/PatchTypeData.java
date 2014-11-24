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
    public void add (Patch patch) {
        myPatches.add(patch);
    }

    @Override
    public void remove (Patch patch) {
        myPatches.remove(patch);
    }

    @Override
    public void clear () {
        myPatches.clear();
    }
}