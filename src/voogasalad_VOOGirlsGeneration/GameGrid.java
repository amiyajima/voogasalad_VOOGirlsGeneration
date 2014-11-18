package voogasalad_VOOGirlsGeneration;

import gamePlayer.Patch;
import gamedata.gamecomponents.Piece;

import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.layout.GridPane;

public abstract class GameGrid extends GridPane{

    protected int r;
    protected int c;
    private Map<Point2D, Piece> myPieces;
    private Map<Point2D, Patch> myPatches;
    private int h;
    private int v;


    public GameGrid(int r, int c, int h, int v, Map <Point2D, Patch> patchMap, Map <Point2D,Piece> pieceMap){

  //  public GameGrid(int row, int column){
        super();
        this.r = r;
        this.c = c;
        this.h = h;
        this.v = v;
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
    public int getHeight(){
    return v;
    }
    public int getWidth(){
        return h;
    }
   


protected abstract void populateGrid();



}
