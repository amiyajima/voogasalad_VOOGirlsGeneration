package authoring.data;

import gamedata.gamecomponents.Patch;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Class for storing the patches created by the user
 * Stored in the authoring environment
 * 
 * @author Sandy Lee
 */
public class PatchInstanceData implements AuthoringData<Patch> {

	private List<Patch> myPatches;

	/**
	 * Constructor for new PatchData,
	 * initializes empty list of Patch
	 */
	public PatchInstanceData () {
		myPatches = new LinkedList<Patch>();
	}
	
	/**
	 * Constructor for patch from game data, aka with already existent data
	 * @param patches
	 */
	public PatchInstanceData (List<Patch> patches) {
	    myPatches = patches;
	}

	@Override
	public void add(Patch p) {
		removePatchAtLoc(p.getLoc());
		myPatches.add(p);
	}
	
	@Override
	public boolean canAdd(Patch element) {
		String elementID = element.getID();
		Point2D.Double elementLoc = element.getLoc();
		for (Patch patch : myPatches){
			String id = patch.getID();
			Point2D.Double loc = patch.getLoc();
			if ((elementID.equals(id)) && (elementLoc.equals(loc))){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void remove(Patch p) {
		myPatches.remove(p);
	}

	@Override
	public void replace(Patch origEl, Patch newEl) {
		origEl.setName(newEl.toString());
		origEl.setImageLocation(newEl.getImageLocation());
	}
	
	@Override
	public List<Patch> getData() {
		return myPatches;
	}
	
	public List<Point2D.Double> replace(Patch patchType) {
    	List<Point2D.Double> pointsToReplace = new ArrayList<Point2D.Double>();
    	myPatches.forEach(patch -> {
    		if (patch.getID().equals(patchType.getID())) {
    			replace(patch, patchType);
    			pointsToReplace.add(patch.getLoc());
    		}
    	});
    	return pointsToReplace;
    }
	
	public List<Point2D.Double> removeUnknown(Set<String> idSet) {
		List<Patch> patchesToRemove = new ArrayList<Patch>();
		List<Point2D.Double> pointsToRemove = new ArrayList<Point2D.Double>();
		for (Patch patch : myPatches) {
    		if (!idSet.contains(patch.getID())) {
				patchesToRemove.add(patch);
				patch.getImageView().setImage(null);
    			pointsToRemove.add(patch.getLoc());
    		}
		}
		myPatches.removeAll(patchesToRemove);
		return pointsToRemove;
	}

	public void removePatchAtLoc(Point2D.Double location){
		for(Patch patch : myPatches){
			if(location.equals(patch.getLoc())){
				myPatches.remove(patch);
				return;
			}
		}
	}

	// Can we rename this?
	public boolean terrainAtLoc(Patch terrain, int x, int y){
		Point2D.Double location = new Point2D.Double(x, y);
		for(Patch patch : myPatches){
			if(location.equals(patch.getLoc()) && terrain.toString().equals(patch.toString())){
				myPatches.remove(patch);
				return true;
			}
		}
		return false;
	}
}
