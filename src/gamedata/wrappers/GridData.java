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

public class GridData {
    private int myRow;
    private int myColumn;
    private PatchData myPatches;
    private PieceData myPieces;

    public GridData (int row, int column, PatchData patches, PieceData pieces) {
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

    public PatchData getPatches () {
        return myPatches;
    }

    public PieceData getPieces () {
        return myPieces;
    }

    public String toString () {
        return "GridData: # pieces = " + myPieces.getPieces().size() + " # patches " + myPatches.getPatches().size();
    }
}
