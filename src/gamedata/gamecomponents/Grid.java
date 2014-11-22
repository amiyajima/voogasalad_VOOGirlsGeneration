package gamedata.gamecomponents;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.ReceiverToInventoryConclusion;
import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Contains the Grid defined for a level. Contains the pieces and patches
 * 
 */
public abstract class Grid implements Serializable {

    private int myRow;
    private int myColumn;

    protected Map<Point2D, Patch> myPatches;
    protected Map<Point2D, Piece> myPieces;
    
    private static final int DEFAULT_PATCH_STATE = 1;
    private static final int DEFAULT_PATCH_ID = 1;
    private static final String DEFAULT_PATCH_IMAGE_LOCATION = "";

    /**
     * Default constructor for square grid
     */
    public Grid () {
    }

    /**
     * constructor of grid
     * 
     * @param x number of rows
     * @param y number of columns
     */
    public Grid (int row, int column) {
        System.out.println(row + " " + column);
        System.out.println("Grid constructor called");
        myRow = row;
        myColumn = column;
        System.out.println("Rows and columns set: " + myRow + myColumn);
        myPatches = new HashMap<Point2D, Patch>();
        myPieces = new HashMap<Point2D, Piece>();
        
        for (int x = 0; x < myColumn; x++) {
            for (int y = 0; y < myRow; y++) {
                Patch patch = new SquarePatch(DEFAULT_PATCH_STATE,
                                              DEFAULT_PATCH_ID, DEFAULT_PATCH_IMAGE_LOCATION,
                                              new Point2D.Double(x, y));
                myPatches.put(new Point2D.Double(x, y), patch);
            }
        }
        System.out.println("Patches filled: " + myPatches.size());
        
        Point2D p1 = new Point2D.Double(1, 1);
        Point2D p2 = new Point2D.Double(2, 2);
        Point2D p3 = new Point2D.Double(3, 3);

        List<Point2D> pl1 = new ArrayList<Point2D>();
        pl1.add(p1);
        pl1.add(p2);

        List<Point2D> pl2 = new ArrayList<Point2D>();
        pl1.add(p1);
        pl1.add(p3);

        List<Point2D> pl3 = new ArrayList<Point2D>();
        pl1.add(p2);
        pl1.add(p3);

        List<Movement> movements = new ArrayList<Movement>();
        Movement m1 = new Movement(pl1, pl2);
        Movement m2 = new Movement(pl2, pl3);
        movements.add(m1);
        movements.add(m2);

        StatsSingleMultiplier ssm1 = new StatsSingleMultiplier(0, "actor", "health");
        List<StatsSingleMultiplier> ssmList = new ArrayList<StatsSingleMultiplier>();
        ssmList.add(ssm1);
        
        List<StatsTotalLogic> stlList = new ArrayList<StatsTotalLogic>();
        StatsTotalLogic s1 = new StatsTotalLogic("actor", "health", ssmList);
        stlList.add(s1);

        ActionConclusion ac = new ReceiverToInventoryConclusion();
        
        List<Action> actions = new ArrayList<Action>();
        Action a1 = new ConcreteAction("kill", pl3, pl2, stlList, ac);
        actions.add(a1);
        
        Stats s = new Stats();
        Inventory i = new Inventory();

        for (int x = 0; x < myColumn; x++) {
            for (int y = 0; y < myRow; y++) {
                Piece piece;
                if (x == y) {
                    piece = new Piece("/resources/images/rcd.png", 
                                             movements, actions, s, p3, 5, 6, 7, i);
                }
                else {
                    piece = new Piece("/resources/images/bbybunny.jpeg", 
                                             movements, actions, s, p2, 1, 1, 1, i);
                }
                myPieces.put(piece.getLoc(), piece);
            }
        }
        
        System.out.println("Pieces filled: " + myPieces.size());
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
     * @param coord
     *        of piece
     * @return piece
     */
    public Piece getPiece (Point2D coord) {
        for (Point2D coordinate : myPieces.keySet()) {
            {
                if (coordinate.equals(coord)) { return myPieces.get(coord); }
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
