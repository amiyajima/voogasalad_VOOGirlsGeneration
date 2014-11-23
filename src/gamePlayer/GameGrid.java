package gamePlayer;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.util.Map;
import java.util.Observer;
import javafx.collections.ObservableList;
import java.awt.geom.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public abstract class GameGrid extends GridPane implements Observer{

    private Point2D myCurrentLocation;
    protected int r;
    protected int c;
    private Map<Point2D, Piece> myPieces;
    private Map<Point2D, Patch> myPatches;
    private int h;
    private int v;
    public static final int DEFAULT_HEIGHT = 600;
    public static final int DEFAULT_WIDTH = 800;

    public GameGrid(int r, int c){

        super();
        this.r = r;
        this.c = c;

      //  populateGrid();
        initializeGrid();

    }

    public int getRow(){
        return r;
    }

    public int getCol(){
        return c;
    }
 
    protected Node get(int row, int col){
        Node result = null;
        ObservableList<Node> children = this.getChildren();
        for(Node node : children) {
          // System.out.println("myGrid has a node at:" + myGrid.getRowIndex(node));
            if(this.getRowIndex(node) == row && this.getColumnIndex(node) == col) {
                result = node;
                break;
            }
        }
        return result;
    }


    protected abstract void initializeGrid();

    protected abstract void populateGrid(Map<Point2D, Patch> patches, Map<Point2D, Piece>pieces);



}
