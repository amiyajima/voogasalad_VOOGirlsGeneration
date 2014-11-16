package gamedata.gamecomponents;

public class SquareGrid extends Grid {
    private Patch[][] myGrid;

    public SquareGrid(){
        super();
    }
    
    public SquareGrid (int x, int y) {
        super(x, y);
        myGrid = new Patch[x][y];
    }

    public Patch getPatch (int x, int y) {
        return myGrid[x][y];
    }

    public void setPatch (int x, int y, Patch p) {
        myGrid[x][y] = p;
    }

}
