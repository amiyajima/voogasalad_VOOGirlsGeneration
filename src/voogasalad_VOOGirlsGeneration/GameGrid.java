package voogasalad_VOOGirlsGeneration;

import gamePlayer.Patch;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.layout.GridPane;

public abstract class GameGrid extends GridPane{

    protected int r;
    protected int c;
    private Map<Point2D, Piece> myPieces;
    private Map<Point2D, Patch> myPatches;


    public GameGrid(int row, int c, Map <Point2D, Patch> patchMap, Map <Point2D,Piece> pieceMap){

  //  public GameGrid(int row, int column){
        super();
        r = row;
        this.c = c;
        myPieces = pieceMap;
        myPatches = patchMap;
        
        populateGrid();
    }
    public int getRow(){
        return r;
    }
    public int getCol(){
        return c;
    }

   


protected abstract void populateGrid();



}
