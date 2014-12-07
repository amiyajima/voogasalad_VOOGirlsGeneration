package authoring.data;

import gamedata.gamecomponents.Patch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class PatchTypeData extends Observable implements AuthoringData<Patch> {

	private List<Patch> myPatches;

	public PatchTypeData() {
		myPatches = new LinkedList<Patch>();
	}

	@Override
	public void add(Patch p) {
		myPatches.add(p);
	}
	
	@Override
	public void remove(Patch p) {
		myPatches.remove(p);
		setChanged();
		notifyObservers();
	}
	
	@Override
	public void replace(Patch origEl, Patch newEl) {
	    myPatches.remove(origEl);
	    add(newEl);
		setChanged();
		notifyObservers(newEl);
	}
	
	@Override
	public List<Patch> getData() {
		return myPatches;
	}
	
    public Set<String> getIdSet() {
    	Set<String> idSet = new HashSet<String>();
		for (Patch p : myPatches) {
			idSet.add(p.getID());
		}
		return idSet;
    }
	
	public boolean containsName(String name) {
		Set<String> nameSet = new HashSet<String>();
		for (Patch p : myPatches) {
			nameSet.add(p.toString());
		}
		return nameSet.contains(name);
	}
}
