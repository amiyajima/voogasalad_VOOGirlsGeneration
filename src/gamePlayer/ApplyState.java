package gamePlayer;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;
import javafx.scene.input.MouseEvent;
import authoring_environment.SuperTile;

/**
 * Class representing the state of the grid when a particular action has been
 * selected and is ready to be applied on the grid
 * 
 * @author Yoon, Rica
 *
 */
public class ApplyState implements IGridState {

	private ViewController myController;
	private GameGridEffect myGameGridEffect;
	private Game myGame;
	private SuperTile activeTile;

	public ApplyState(ViewController controller) {
		System.out.println("New ApplyState");
		myController = controller;
		myGameGridEffect = controller.getGameGridEffect();
		myGame = controller.getGame();

		myController.getGridPane().setOnMouseMoved(
				event -> handleMouseMove(event));
	}

	/**
	 * When the mouse of moved, it will find a new effect range to highlight
	 * relative to the current location
	 * 
	 * @param event
	 */
	private void handleMouseMove(MouseEvent event) {
		if (myController.getGrid().findClickedTile(event.getX(), event.getY()) != null
				&& hoveringOverActionHighlight(event.getX(), event.getY())) {
			activeTile = myController.getGrid().findClickedTile(event.getX(),
					event.getY());
			myController.getGameGridEffect().highlightEffectRange(
					activeTile.getLocation());
		}
	}

	/**
	 * Checks if the mouse movement has it hovering over an action tile Only
	 * then will it update the highlight of the effect range
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @return
	 */
	private boolean hoveringOverActionHighlight(double mouseX, double mouseY) {
		for (SuperTile st : myGameGridEffect.getActionHighlights()) {
			if (myController.getGrid().findClickedTile(mouseX, mouseY)
					.getLocation() == st.getLocation()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onClick(Piece piece) {
		Piece actor = myController.getActivePiece();
		if (piece == null) {
			piece = new Piece(actor,myController.getCurrentClick());
			piece.setLoc(myController.getCurrentClick());
		}
		myController.getActiveAction().doBehavior(actor, piece);
		myController.getGrid().repopulateGrid();
		myGameGridEffect.clearAllPieceHighlights();
		myGameGridEffect.clearAllActionHighlights();
		myController.clearActions();
		myController.setGridState(new SelectState(myController));
		myController.changeCursor(myController.CURSOR_GLOVE_TEST);
		myController.setActivePiece(null);
		myController.setActiveAction(null);
		myController.getGame().getCurrentLevel().runGameEvents();
	}

}
