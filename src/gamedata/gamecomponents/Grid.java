package gamedata.gamecomponents;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;


public abstract class Grid {
    private int myRow;
    private int myColumn;

    private Map<Point2D, Patch> myPatches;
    private Map<Point2D, Piece> myPieces;

    public Grid (int row, int column) {
        row = myRow;
        column = myColumn;

        myPatches = new HashMap<Point2D, Patch>();
        myPieces = new HashMap<Point2D, Piece>();

        setGrid();

    }

    // set up grid by initializing patches on it
    private void setGrid () {
        for (int x = 0; x < myColumn; x++) {
            for (int y = 0; y < myRow; y++) {
                Patch patch = new SquarePatch();
                myPatches.put(new Point2D(x, y), patch);
            }
        }
    }

    public void setPiece (Piece piece, Point2D coord) {
        for (Point2D coordinate : myPieces.keySet()) {
            if (coordinate.equals(coord)) {
                myPieces.put(coord, piece);
            }
        }
    }

    public void setPatch (Patch patch, Point2D coord) {
        for (Point2D coordinate : myPieces.keySet()) {
            if (coordinate.equals(coord)) {
                myPatches.put(coord, patch);
            }
        }
    }

    public Patch getPatch (Point2D coord) {
        for (Point2D coordinate : myPatches.keySet()) {
            {
                if (coordinate.equals(coord)) {
                    return myPatches.get(coord);
                }
            }
        }
        // no patch exists on such coord
        return null;
    }

    public Piece getPiece (Point2D coord) {
        for (Point2D coordinate : myPieces.keySet()) {
            {
                if (coordinate.equals(coord)) {
                    return myPieces.get(coord);
                }
            }
        }
        // no piece exists on such coord
        return null;
    }
    
    public void removePiece (Point2D coord) {
        Piece piece = myPieces.get(coord);
        myPieces.remove(coord, piece);
    }

    public void removePatch (Point2D coord) {
        Patch patch = myPatches.get(coord);
        myPatches.remove(coord, patch);
    }

    public int getColumn () {
        return myColumn;
    }

    public int getRow () {
        return myRow;
    }
}
