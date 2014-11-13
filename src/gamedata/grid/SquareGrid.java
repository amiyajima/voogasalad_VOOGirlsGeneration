package gamedata.grid;

import gamedata.patch.Patch;

public class SquareGrid extends Grid {
	private Patch[][] myGrid;

	public SquareGrid(int x, int y) {
		myGrid = new Patch[x][y];
	}
	
	public Patch getPatch(int x, int y){
		return myGrid[x][y];
	}
	
	public void setPatch(int x, int y, Patch p){
		myGrid[x][y] = p;
	}

}
