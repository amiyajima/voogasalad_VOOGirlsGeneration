package gamedata.wrappers;

/**
 * Wrapper for the Grid in LevelsData
 * 
 * @author Rica, annamiyajima
 *
 */

public class GridData {
    private PieceData myPieceData;
    private PatchData myPatchData;

    public GridData (PieceData pieces, PatchData patches) {
        myPieceData = pieces;
        myPatchData = patches;
    }

    public PatchData getPatches () {
        return myPatchData;
    }

    public PieceData getPieces () {
        return myPieceData;
    }

    public String toString () {
        String myString = "GridData: # pieces = " + myPieceData.getPieces().size();
        String string2 =  " # patches " + myPatchData.getPatches().size();
        return myString + string2;
    }
}
