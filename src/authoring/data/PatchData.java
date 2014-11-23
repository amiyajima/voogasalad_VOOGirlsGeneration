package authoring.data;

import gamedata.gamecomponents.Patch;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for storing the patches created by the user
 * Stored in the authoring environment
 * 
 * @author Sandy Lee
 */
public class PatchData implements AuthoringData<Patch> {
    private List<Patch> myPatches;

    /**
     * Constructor for new PatchData,
     * initializes empty list of Patch
     */
    public PatchData () {
        myPatches = new LinkedList<Patch>();
    }

    @Override
    public void add (Patch patch) {
    	System.out.println("Added patch at location x = " + patch.getLoc().getX() + ", y = " + patch.getLoc().getY());
        myPatches.add(patch);
    }

    @Override
    public void remove (Patch patch) {
    	System.out.println("Removed patch at location x = " + patch.getLoc().getX() + ", y = " + patch.getLoc().getY());
        myPatches.remove(patch);
    }

    @Override
    public void clear () {
        myPatches.clear();
    }
    
    public List<Patch> getPatches(){
        return myPatches;
    }
}
