package authoring.data;

import gamedata.gamecomponents.Patch;

import java.awt.geom.Point2D;
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
    
    public void removePatch(Point2D location){
		for(Patch patch : myPatches){
			if(location.equals(patch.getLoc())){
				myPatches.remove(patch);
				return;
			}
		}
	}
    
    public boolean terrainAtLoc(Patch terrain, int x, int y){
    	Point2D location = new Point2D.Double(x, y);
		for(Patch patch : myPatches){
			if(location.equals(patch.getLoc()) && terrain.getName().equals(patch.getName())){
				myPatches.remove(patch);
				return true;
			}
		}
		return false;
    }
    
    public List<Patch> getPatches(){
        return myPatches;
    }
}