package gamedata.gamecomponents;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Point2D;


/**
 *
 *
 */
public abstract class Grid {
    protected int myRow;
    protected int myColumn;

    protected Map<Point2D, Patch> myPatches;
    protected Map<Point2D, Piece> myPieces;

    /**
     * default initialize of grid
     */
    public Grid () {
        this(1, 1);
    }

    /**
     * initializes grid
     * 
     * @param row of the grid
     * @param column of the grid
     */
    public Grid (int row, int column) {
        row = myRow;
        column = myColumn;

        myPatches = new HashMap<Point2D, Patch>();
        myPieces = new HashMap<Point2D, Piece>();

        setGrid();

    }

    /**
     * set up grid by initializing patches on it
     */
    abstract void setGrid ();

    /**
     * places a piece on the grid
     * 
     * @param piece to be put on grid
     * @param coord of piece
     */
    public void setPiece (Piece piece, Point2D coord) {
        for (Point2D coordinate : myPieces.keySet()) {
            if (coordinate.equals(coord)) {
                myPieces.put(coord, piece);
            }
        }
    }

    /**
     * places a patch on the grid
     * 
     * @param patch to be put on grid
     * @param coord of patch
     */
    abstract void setPatch (Patch patch, Point2D coord);

    /**
     * gets the patch on the given coordinate
     * 
     * @param coord of patch
     * @return patch
     */
    abstract Patch getPatch (Point2D coord);

    /**
     * gets the piece on the given coordinate
     * 
     * @param coord of piece
     * @return piece
     */
    abstract Piece getPiece (Point2D coord);

    /**
     * removes the piece on the given coordinate
     * 
     * @param coord of piece
     */
    abstract void removePiece (Point2D coord);

    /**
     * removes the patch on the given coordinate
     * 
     * @param coord of remove
     */
    abstract void removePatch (Point2D coord);

    /**
     * @return number of columns of the grid
     */
    public int getColumn () {
        return myColumn;
    }

    /**
     * @return number of rows of the grid
     */
    public int getRow () {
        return myRow;
    }

}
