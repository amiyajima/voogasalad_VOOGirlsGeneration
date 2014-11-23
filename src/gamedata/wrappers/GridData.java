package gamedata.wrappers;

import java.awt.geom.Point2D;
import java.util.Map;

/**
 * Wrapper for the grid
 * @author Rica
 *
 */
public class GridData {
    private int myRow;
    private int myColumn;
    private Map<Point2D, PatchData> myPatches;
    private Map<Point2D, PieceData> myPieces;
    
    public GridData(int row, int column, Map<Point2D, PatchData> patches, Map<Point2D, PieceData> pieces) {
        myRow = row;
        myColumn = column;
        myPatches = patches;
        myPieces = pieces;
    }

    public int getRow() {
        return myRow;
    }
    
    public int getColumn() {
        return myColumn;
    }
    
    public Map<Point2D, PatchData> getPatches() {
        return myPatches;
    }
    
    public Map<Point2D, PieceData> getPieces() {
        return myPieces;
    }
}
