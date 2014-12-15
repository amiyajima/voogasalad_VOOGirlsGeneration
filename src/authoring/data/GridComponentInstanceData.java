// This entire file is part of my masterpiece.
// MARTIN TAMAYO

package authoring.data;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import gamedata.gamecomponents.GridComponent;
import gamedata.gamecomponents.Piece;

public abstract class GridComponentInstanceData implements AuthoringData<GridComponent> {
	
	private List<GridComponent> myData;
	
    public GridComponentInstanceData() {
        myData = new LinkedList<GridComponent>();
    }
    
    public GridComponentInstanceData(List<GridComponent> gcs) {
        myData = gcs;
    }
    
    @Override
	public void add(GridComponent gc) {
		myData.add(gc);
	}
    
	@Override
	public boolean canAdd(GridComponent element) {
		String elementID = element.getID();
		Point2D.Double elementLoc = element.getLoc();
		for (GridComponent gc : myData){
			String id = gc.getID();
			Point2D.Double loc = gc.getLoc();
			if ((elementID.equals(id)) && (elementLoc.equals(loc))){
				return false;
			}
		}
		return true;
		// Only returns true if the entire for loop is iterated over without returning false.
	}
	
	@Override
	public void remove(GridComponent gc) {
		myData.remove(gc);
	}

	@Override
	public List<GridComponent> getData() {
		return myData;
	}
    
    public List<Point2D.Double> replace(GridComponent componentType) {
    	List<Point2D.Double> pointsToReplace = new ArrayList<Point2D.Double>();
    	myData.forEach(gc -> {
    		if (gc.getID().equals(componentType.getID())) {
    			replace(gc, componentType);
    			pointsToReplace.add(gc.getLoc());
    		}
    	});
    	return pointsToReplace;
    }
	
	public List<Point2D.Double> removeUnknown(Set<String> idSet) {
		List<GridComponent> componentsToRemove = new ArrayList<GridComponent>();
		List<Point2D.Double> pointsToRemove = new ArrayList<Point2D.Double>();
		for (GridComponent gc : myData) {
    		if (!idSet.contains(gc.getID())) {
				componentsToRemove.add(gc);
				gc.getImageView().setImage(null);
    			pointsToRemove.add(gc.getLoc());
    		}
		}
		myData.removeAll(componentsToRemove);
		return pointsToRemove;
	}
	
	public void removePieceAtLoc(Point2D.Double location){
		for(GridComponent gc : myData){
			if(location.equals(gc.getLoc())){
				myData.remove(gc);
				return;
			}
		}
	}
	
	public boolean unitAtLoc(Piece unit, int x, int y){
		Point2D.Double location = new Point2D.Double(x, y);
		for(GridComponent gc : myData){
			if(location.equals(gc.getLoc()) && unit.toString().equals(gc.toString())){
				myData.remove(gc);
				return true;
			}
		}
		return false;
		// Only returns false if the entire for loop is iterated over without returning true.
	}
}
