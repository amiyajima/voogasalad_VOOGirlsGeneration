package gamePlayer;

import gamedata.gamecomponents.Piece;

/**
 * The class representing the state of the grid before any piece on the grid is
 * selected
 *
 */
public class SelectState implements IGridState {
        private ViewController myController;

	public SelectState(ViewController controller) {
		myController = controller;
	}

	@Override
	public void onClick(Piece piece) {
		if (piece != null && piece.getPlayerID()==myController.getGame().getCurrentPlayer().getID()) {
			myController.updateInventory(piece);
		        myController.updateStats(piece);
			myController.setActivePiece(piece);
			myController.updateActions(piece);
			myController.highlightSelected(piece);
		}
	}
}
