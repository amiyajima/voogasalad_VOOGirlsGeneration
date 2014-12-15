package authoring.data;

import gamedata.gamecomponents.GridComponent;
import gamedata.gamecomponents.Piece;

/**
 * Class for storing the each type of piece created by the user.
 * Stored in the game authoring environment.
 * 
 * @author Martin Tamayo
 */
public class PieceInstanceData extends GridComponentInstanceData {
	
	@Override
	public void replace(GridComponent origEl, GridComponent newEl) {
		remove(origEl);
		add((GridComponent)(new Piece((Piece)newEl, origEl.getLoc())));
	}
}
