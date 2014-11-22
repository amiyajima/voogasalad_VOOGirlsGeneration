package gamedata.gamecomponents;


import java.awt.geom.Point2D;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.ImageView;



/**
 * Contains the Grid defined for a level. Contains the pieces and patches
 * @Author Jesse Ling, Sandy Lee
 * 
 */
public abstract class Grid {

    private int myRow;
    private int myColumn;

    protected Map<Point2D, Patch> myPatches;
    protected Map<Point2D, Piece> myPieces;
    private static final int DEFAULT_PATCH_STATE = 1;
    private static final int DEFAULT_PATCH_ID = 1;
    private static final ImageView DEFAULT_PATCH_IMAGE_VIEW = new ImageView();

    /**
     * Default constructor for square grid
     */
    public Grid () {
        this(5, 5);
    }

    /**
     * constructor of grid
     * 
     * @param x
     *        number of rows
     * @param y
     *        number of columns
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
    public abstract void setGrid ();

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
     * @param patch
     *        to be put on grid
     * @param coord
     *        of patch
     */
    public void setPatch (Patch patch, Point2D coord) {
        for (Point2D coordinate : myPieces.keySet()) {
            if (coordinate.equals(coord)) {
                myPatches.put(coord, patch);
            }
        }
    }

    /**
     * gets the patch on the given coordinate
     * 
     * @param coord
     *        of patch
     * @return patch
     */
    public Patch getPatch (Point2D coord) {
        for (Point2D coordinate : myPatches.keySet()) {
            {
                if (coordinate.equals(coord)) { return myPatches.get(coord); }
            }
        }
        // no patch exists on such coord
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
        // no piece exists on such coord
        return null;
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
     * @param coord
     *        of remove
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
