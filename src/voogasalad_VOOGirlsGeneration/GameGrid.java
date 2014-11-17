package voogasalad_VOOGirlsGeneration;

import javafx.scene.layout.GridPane;

public abstract class GameGrid extends GridPane{

    protected int r;
    protected int c;
    //private Map<Point2D, Piece> pieceMap;


   // public GameGrid(int row, int c, Map <Point2D, Patch> patchMap, Map <Point2D,Piece> pieceMap){

    public GameGrid(int row, int column){
        super();
        r = row;
        c = column;
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
