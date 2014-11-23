package gamedata.gamecomponents;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Contains the Grid defined for a level. Contains the pieces and patches
 * 
 * @Author Jesse Ling, Sandy Lee
 * 
 */
public abstract class Grid {
    private int myRow;
    private int myColumn;
    protected Map<Point2D, Patch> myPatches;
    protected Map<Point2D, Piece> myPieces;

    /**
     * Default constructor makes 5x5 grid
     */
    public Grid () {
       this(5, 5);
    }

    /**
     * constructor of grid
     * 
     * @param x number of rows
     * @param y number of columns
     */
    public Grid (int row, int column) {
        myRow = row;
        myColumn = column;
        myPatches = new HashMap<Point2D, Patch>();
        myPieces = new HashMap<Point2D, Piece>();
    }

    /**
     * places a patch on the grid
     * 
     * @param patch to be put on grid
     * @param coord of patch
     */
    public void setPatch (Point2D coord, Patch patch) {
        myPatches.put(coord, patch);
    }

    /**
     * gets the patch on the given coordinate
     * 
     * @param coord of patch
     * @return patch
     */
    public Patch getPatch (Point2D coord) {
        for (Point2D coordinate : myPatches.keySet()) {
            if (coordinate.equals(coord)) { 
                return myPatches.get(coord); 
            }
        }
        return null;
    }

    /**
     * gets the piece on the given coordinate
     * 
     * @param loc
     *        of piece
     * @return piece
     */
    public Piece getPiece (Point2D loc) {
        for (Point2D coordinate : myPieces.keySet()) {
            {
                if (coordinate.equals(loc)) { return myPieces.get(loc); }
            }
        }
        return null;
    }
    
    /**
     * Set a piece on a specified coordinate
     * @param coord
     * @param piece
     */
    public void setPiece(Point2D coord, Piece piece) {
        myPieces.put(coord, piece);
    }

    /**
     * removes the piece on the given coordinate
     * 
     * @param coord
     *        of piece
     */
    public void removePiece (Point2D coord) {
        Piece piece = myPieces.get(coord);
        myPieces.remove(coord, piece);
    }

    /**
     * removes the patch on the given coordinate
     * 
     * @param coord of remove
     */
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

    /**
     * Returns a Piece of a given ID
     * 
     * @param id
     * @return
     */
    public Piece getPiece (int id) {
        for (Map.Entry<Point2D, Piece> entry : myPieces.entrySet()) {
            if (entry.getValue().getUniqueID() == id)
                return entry.getValue();
        }
        return null;
    }

    /**
     * Returns a list of all pieces
     * 
     * @return
     */
    public List<Piece> getAllPieces () {
        List<Piece> all = new ArrayList<Piece>();
        for (Map.Entry<Point2D, Piece> entry : myPieces.entrySet()) {
            all.add(entry.getValue());
        }
        return all;
    }

    /**
     * Returns a list of all patches
     * 
     * @return
     */
    public List<Patch> getAllPatches () {
        List<Patch> all = new ArrayList<Patch>();
        for (Map.Entry<Point2D, Patch> entry : myPatches.entrySet()) {
            all.add(entry.getValue());
        }
        return all;
    }

    /**
     * gets the patch on the given coordinate
     * 
     * @param coord
     *        of patch
     * @return patch
     */
    public Map<Point2D, Patch> getPatches () {
        return myPatches;
    }

    /**
     * gets the piece on the given coordinate
     * 
     * @param coord
     *        of piece
     * @return piece
     */
    public Map<Point2D, Piece> getPieces () {
        return myPieces;
    }

}
