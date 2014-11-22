package authoring.data;

import gamedata.action.Patch;
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
    public void add (Patch action) {
        myPatches.add(action);
    }

    @Override
    public void remove (Patch action) {
        myPatches.remove(action);
    }

    @Override
    public void clear () {
        myPatches.clear();
    }

}
