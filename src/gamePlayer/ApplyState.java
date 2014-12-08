package gamePlayer;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;
import javafx.scene.input.MouseEvent;
import authoring_environment.SuperTile;

/**

 * Class representing the state of the grid when a particular action
 * has been selected and is ready to be applied on the grid
 * @author Eric, Yiran, Yoon, Rica

 *
 */
public class ApplyState implements IGridState {
//        public static final String CURSOR_ATTACK_TEST = "resources/images/Cursor_attack.png";
//        public static final String CURSOR_GLOVE_TEST = "resources/images/pointer-glove.png";
	
        private ViewController myController;
	private GameGridEffect myGameGridEffect;
	private Game myGame;
	private SuperTile activeTile;

	public ApplyState(ViewController controller) {
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
				&& myGameGridEffect.isHoveringOverActionHighlight(event.getX(), event.getY())) {
			activeTile = myController.getGrid().findClickedTile(event.getX(),
					event.getY());
			myController.getGameGridEffect().highlightEffectRange(
					activeTile.getCoordinates());
		}
	}

	@Override
	public void onClick(Piece piece) {
		Piece actor = myController.getActivePiece();
		if (piece == null && myController.getActiveAction().toString().equals("Movement")) {
			piece = new Piece(actor,myController.getCurrentClick());
			piece.setLoc(myController.getCurrentClick());
		}
		myController.getActiveAction().doBehavior(actor, piece);
		myController.getGrid().repopulateGrid();
		myGameGridEffect.clearAllPieceHighlights();
		myGameGridEffect.clearAllActionHighlights();
		myController.clearActions();
		myController.setGridState(new SelectState(myController));
//		myController.changeCursor(myController.CURSOR_GLOVE_TEST);
		myController.setActivePiece(null);
		myController.setActiveAction(null);
		myGame.getCurrentLevel().runGameEvents();
	}

}
