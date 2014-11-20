package gamePlayer;


import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.util.Map;
import java.util.Observer;
import javafx.geometry.Point2D;
import javafx.scene.layout.GridPane;

public abstract class GameGrid extends GridPane implements Observer{

    protected int r;
    protected int c;
    private Map<Point2D, Piece> myPieces;
    private Map<Point2D, Patch> myPatches;
    private int h;
    private int v;


    public GameGrid(int r, int c, int h, int w, Map <Point2D, Patch> patchMap, Map <Point2D,Piece> pieceMap){

  //  public GameGrid(int row, int column){
        super();
        this.r = r;
        this.c = c;
        this.setPrefHeight(h);
        this.setPrefWidth(w);
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
