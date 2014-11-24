package authoring_environment;

import java.util.List;

import authoring.data.PatchData;
import authoring.data.PieceData;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public abstract class ShapeGrid extends Pane {
	private int myRows;
	private int myCols;
	private int myTileSize;
	
	private PieceData myPieceData;
	private PatchData myPatchData;
	private List<List<SandyTile>> myGrid;
	
	
	protected abstract Shape makeShape(int row, int col, double h);
	
	protected abstract void setCheckeredColor(int row, int col, Shape shape);
}
