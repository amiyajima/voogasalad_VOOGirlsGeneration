package gamedata.wrappers;

import authoring.data.PatchInstanceData;
import authoring.data.PieceInstanceData;
import authoring_environment.GUIGrid;

/**
 * Wrapper for the Grid in LevelsData
 * 
 * @author Rica, annamiyajima
 *
 */

public class GridData {
    private PieceData myPieceData;
    private PatchData myPatchData;
    private int myNumRows;
    private int myNumCols;
    private double myTileHeight;
    private String myShape;

    public GridData (PieceData pieces, PatchData patches, int numRows, int numCols, double tileHeight,
                     String shape) {
        myPieceData = pieces;
        myPatchData = patches;
        myNumRows = numRows;
        myNumCols = numCols;
        myTileHeight = tileHeight;
        myShape = shape;
    }

    public PieceData getPieces () {
        return myPieceData;
    }

    public PatchData getPatches () {
        return myPatchData;
    }

    public int getMyNumRows () {
        return myNumRows;
    }

    public int getMyNumCols () {
        return myNumCols;
    }

    public double getMyTileHeight () {
        return myTileHeight;
    }

    public String getMyShape () {
        return myShape;
    }

    public GUIGrid getGridFromData() {
        /*
        public GUIGrid (int cols, int rows, double tileHeight, String shape,
                        PieceData pieceData, PatchInstanceData patchData) {
                super(cols, rows, tileHeight, shape);
                myPieceData = pieceData;
                myPatchData = patchData;
                //TODO: repopulate grid
        }
        */
        PieceInstanceData myPieceInstanceData = new PieceInstanceData(myPieceData.getPiecesFromData());
        PatchInstanceData myPatchInstanceData = new PatchInstanceData(myPatchData.getPatchesFromData());
        GUIGrid myGrid = new GUIGrid(myNumCols, myNumRows, myTileHeight, myShape,
                                     myPieceInstanceData, myPatchInstanceData);
        return myGrid;
    }

    public String toString () {
        String myString = "GridData: # pieces = " + myPieceData.getPieces().size();
        String string2 =  " # patches " + myPatchData.getPatches().size();
        return myString + string2;
    }
}
