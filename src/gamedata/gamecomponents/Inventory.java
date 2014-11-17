package gamedata.gamecomponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Inventory {
    private Map<Piece, Integer> myNumberPieces;
    // list of pieces in my inventory
    private List<Piece> myInventory;

    /**
     * instructor for inventory
     */
    public Inventory () {
        myNumberPieces = new HashMap<Piece, Integer>();
        myInventory = new ArrayList<Piece>();

    }
    
    public void addPieceToInv (Piece piece) {
        if (myInventory.add(piece)) {
            myNumberPieces.put(piece, 1);
        }
        else {
            myNumberPieces.put(piece, myNumberPieces.get(piece) + 1);
        }
    }

    public void removeItem (Piece piece) {
        int num = myNumberPieces.get(piece);
        if (num > 1) {
            myNumberPieces.put(piece, num - 1);
        }
        else {
            // remove from inventory and from the map that keeps the num of such piece
            myNumberPieces.remove(piece);
            for (Piece p : myInventory) {
                if (piece.equals(p)) {
                    myInventory.remove(piece);
                }
            }
        }
    }

    public void setInventory (List<Piece> pieces) {
        myInventory = pieces;
    }

    public int getNumPiece (Piece piece) {
        return myNumberPieces.get(piece);
    }


    public Piece getPiece (Piece piece) {
        if (myInventory.isEmpty()) { return null; }
        for (Piece p : myInventory) {
            if (p.equals(piece)) { return p; }
        }
        return null;
    }

    public boolean isEmpty () {
        return myInventory.isEmpty();
    }
    
    public List<Piece> getInventory () {
        return myInventory;
    }


}
