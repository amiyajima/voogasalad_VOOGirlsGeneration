package authoring.data;

import java.util.LinkedList;
import java.util.List;

import authoring.datawrappers.GridPieceWrapper;

/**
 * @author Martin Tamayo
 *
 */
public class AllPieceWrappers implements AuthoringData<GridPieceWrapper>{
	
	private List<GridPieceWrapper> allPieces;
	
	/**
     * Constructor for AllPieceWrappers,
     * initializes empty list of GridPieceWrappers.
     */
    public AllPieceWrappers() {
        allPieces = new LinkedList<GridPieceWrapper>();
    }
	
	@Override
	public void add(GridPieceWrapper pieceWrapper) {
		allPieces.add(pieceWrapper);
	}

	@Override
	public void remove(GridPieceWrapper pieceWrapper) {
		allPieces.remove(pieceWrapper);
	}

	@Override
	public void clear() {
		allPieces.clear();
	}
}