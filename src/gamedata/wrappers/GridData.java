package gamedata.wrappers;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Wrapper for the Grid in LevelsData
 * 
 * @author Rica, annamiyajima
 *
 */
// TODO see if I can change mypatches/mypieces to a list of PatchDataIndividual/PieceDataIndividual
// instead of PatchData/PieceData and have things still work
public class GridData {
    private int myRow;
    private int myColumn;
    // private Map<Point2DData, PatchData> myPatches;
    // private Map<Point2DData, PieceData> myPieces;
    private List<PatchData> myPatches;
    private List<PieceData> myPieces;

    public GridData (int row, int column, ArrayList<PatchData> patches, ArrayList<PieceData> pieces) {
        myRow = row;
        myColumn = column;
        myPatches = patches;
        myPieces = pieces;
    }

    public int getRow () {
        return myRow;
    }

    public int getColumn () {
        return myColumn;
    }

    public List<PatchData> getPatches () {
        return myPatches;
    }

    public List<PieceData> getPieces () {
        return myPieces;
    }

    public String toString () {
        return "GridData: # pieces = " + myPieces.size() + " # patches " + myPatches.size();
    }
}
