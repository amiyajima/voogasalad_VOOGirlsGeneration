package gamedata.wrappers;

import java.awt.geom.Point2D;
import java.util.Map;

/**
 * Wrapper for the Grid in LevelsData
 * @author Rica
 *
 */
public class GridData {
    private int myRow;
    private int myColumn;
    private Map<Point2DData, PatchData> myPatches;
    private Map<Point2DData, PieceData> myPieces;
    
    public GridData(int row, int column, Map<Point2DData, PatchData> patches, Map<Point2DData, PieceData> pieces) {
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
    
    public Map<Point2DData, PatchData> getPatches() {
        return myPatches;
    }
    
    public Map<Point2DData, PieceData> getPieces() {
        return myPieces;
    }
}
