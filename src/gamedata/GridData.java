package gamedata;

import java.util.List;

/**
 * Wrapper for the grid
 * @author Rica
 *
 */
public class GridData {
    private int myRow;
    private int myColumn;
    private List<PatchData> myPatches;
    private List<PieceData> myPieces;
    
    public GridData(int row, int column, List<PatchData> patches, List<PieceData> pieces) {
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
    
    public List<PatchData> getPatches() {
        return myPatches;
    }
    
    public List<PieceData> getPieces() {
        return myPieces;
    }
}
