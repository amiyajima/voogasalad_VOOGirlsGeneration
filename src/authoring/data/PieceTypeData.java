package authoring.data;

import gamedata.gamecomponents.Piece;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class PieceTypeData extends Observable implements AuthoringData<Piece> {
	
    private List<Piece> myPieces;
    
    public PieceTypeData() {
        myPieces = new LinkedList<Piece>();
    }
    
    @Override
	public void add(Piece p) {
		myPieces.add(p);
	}

	@Override
	public void remove(Piece p) {
		myPieces.remove(p);
		setChanged();
		notifyObservers();
	}

	@Override
	public void replace(Piece origEl, Piece newEl) {
		remove(origEl);
	    add(newEl);
		setChanged();
		notifyObservers(newEl);
	}

	@Override
	public List<Piece> getData() {
		return myPieces;
	}
 
    public Set<String> getIdSet() {
    	Set<String> idSet = new HashSet<String>();
		for (Piece p : myPieces) {
			idSet.add(p.getID());
		}
		return idSet;
    }
    
    public boolean containsName(String name) {
		Set<String> nameSet = new HashSet<String>();
		for (Piece p : myPieces) {
			nameSet.add(p.toString());
		}
		return nameSet.contains(name);
	}
}
