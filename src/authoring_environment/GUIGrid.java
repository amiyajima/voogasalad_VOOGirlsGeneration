package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.util.List;

/**
 * Authoring, engine, and player may all use this grid!!
 * 
 * @author Jennie Ju
 *
 */
public class GUIGrid extends SuperGrid {
	
	private List<Piece> myPieceData;
	private List<Patch> myPatchData;
	
	public GUIGrid(int cols, int rows, int tileSize, String shape) {
		super(cols,rows,tileSize,shape);
	}
	
	
}
