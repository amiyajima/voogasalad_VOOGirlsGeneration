// This entire file is part of my masterpiece.
// MARTIN TAMAYO

package authoring.data;

import gamedata.gamecomponents.GridComponent;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class GridComponentTypeData extends Observable implements AuthoringData<GridComponent> {
	
	private List<GridComponent> myData;
	
	public GridComponentTypeData(){
		myData = new LinkedList<GridComponent>();
	}
	
	@Override
	public void add(GridComponent element) {
		myData.add(element);
	}

	@Override
	public boolean canAdd(GridComponent element) {
		for (GridComponent gc : myData){
			if (gc.getID().equals(element.getID())){
				return false;
			}
		}
		return true;
		// Only returns true if the entire for loop is iterated over without returning false.
	}

	@Override
	public void remove(GridComponent element) {
		myData.remove(element);
		setChanged();
		notifyObservers();
	}

	@Override
	public void replace(GridComponent origEl, GridComponent newEl) {
		myData.remove(origEl);
	    add(newEl);
		setChanged();
		notifyObservers(newEl);
	}

	@Override
	public List<GridComponent> getData() {
		return myData;
	}
	
	public Set<String> getIdSet() {
    	Set<String> idSet = new HashSet<String>();
		for (GridComponent gc : myData) {
			idSet.add(gc.getID());
		}
		return idSet;
    }
    
    public boolean containsName(String name) {
		Set<String> nameSet = new HashSet<String>();
		for (GridComponent gc : myData) {
			nameSet.add(gc.toString());
		}
		return nameSet.contains(name);
	}
}
