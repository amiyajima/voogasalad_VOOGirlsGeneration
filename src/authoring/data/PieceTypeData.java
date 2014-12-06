package authoring.data;

import gamedata.gamecomponents.Piece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

public class PieceTypeData extends Observable implements AuthoringData<Piece> {
	
    private List<Piece> myPieces;
    
    public PieceTypeData() {
        myPieces = new ArrayList<Piece>();
    }
    
    public boolean containsName(String name) {
		return getPieceNames().contains(name);
	}
    
    private Set<String> getPieceNames() {
		Set nameSet = new HashSet<String>();
		for (Piece p : myPieces) {
			nameSet.add(p.getName());
		}
		return nameSet;
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
		origEl = newEl;
		setChanged();
		notifyObservers(origEl);
	}

	@Override
	public List<Piece> getData() {
		return myPieces;
	}
}
