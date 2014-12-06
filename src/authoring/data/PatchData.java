package authoring.data;

import gamedata.gamecomponents.Patch;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Class for storing the patches created by the user
 * Stored in the authoring environment
 * 
 * @author Sandy Lee
 */
public class PatchData implements AuthoringData<Patch>, Observer {

	private List<Patch> myPatches;

	/**
	 * Constructor for new PatchData,
	 * initializes empty list of Patch
	 */
	public PatchData () {
		myPatches = new LinkedList<Patch>();
	}

	@Override
	public void add(Patch p) {
		myPatches.add(p);
	}
	
	@Override
	public void replace(Patch origEl, Patch newEl) {
		origEl.setName(newEl.getName());
		origEl.setImage(newEl.getImageLocation());
	}


	@Override
	public void remove(Patch p) {
		myPatches.remove(p);
	}

	@Override
	public List<Patch> getData() {
		return myPatches;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof PatchTypeData) {
			List<Patch> toRemove = new ArrayList<Patch>();
			PatchTypeData typeData = (PatchTypeData) o;
			for (Patch p : myPatches) {
				if (!typeData.containsName(p.getName())) {
					toRemove.add(p);
				}
			}
			
			myPatches.removeAll(toRemove);

			if (arg instanceof Patch) {
				Patch patchType = (Patch) arg;
				for (Patch p : myPatches) {
					replace(p,patchType);
				}
			}
		}
	}


	public void removePatchAtLoc(Point2D location){
		for(Patch patch : myPatches){
			if(location.equals(patch.getLoc())){
				myPatches.remove(patch);
				return;
			}
		}
	}

	// Can we rename this?
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
}