package authoring.data;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

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
    	System.out.println("Added patch at location x = " + patch.getLoc().getX() + ", y = " + patch.getLoc().getY());
        myPatches.add(patch);
        for(Patch p : myPatches){
			double x = p.getLoc().getX();
			double y = p.getLoc().getY();
			System.out.println(x + ", " + y);
		}
    }

    @Override
    public void remove (Patch patch) {
    	System.out.println("Removed patch at location x = " + patch.getLoc().getX() + ", y = " + patch.getLoc().getY());
        myPatches.remove(patch);
        for(Patch p : myPatches){
			double x = p.getLoc().getX();
			double y = p.getLoc().getY();
			System.out.println(x + ", " + y);
		}
    }

    @Override
    public void clear () {
        myPatches.clear();
    }
    
    public void removePatch(Point2D location){
		for(Patch patch : myPatches){
			if(location.equals(patch.getLoc())){
				myPatches.remove(patch);
			}
		}
	}
    
    public boolean terrainAtLoc(Patch terrain, int x, int y){
    	Point2D location = new Point2D.Double(x, y);
		for(Patch patch : myPatches){
			if(location.equals(patch.getLoc()) && patch.getTypeID() == patch.getTypeID()){
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