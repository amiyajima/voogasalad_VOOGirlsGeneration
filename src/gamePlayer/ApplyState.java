package gamePlayer;

import gamedata.gamecomponents.Piece;
import javafx.scene.input.MouseEvent;
import authoring_environment.SuperTile;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.List;

/**
 * 
 * Class representing the state of the grid when a particular action has been
 * selected and is ready to be applied on the grid
 * 
 * @author Eric, Yiran, Yoon, Rica, Jesse
 * 
 *
 */
public class ApplyState implements IGridState {

	private ViewController myController;
	private GameGridEffect myGameGridEffect;
	private SuperTile activeTile;

	public ApplyState(ViewController controller) {
		myController = controller;
		myGameGridEffect = controller.getGameGridEffect();
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
				&& myGameGridEffect.isHoveringOverActionHighlight(event.getX(),
						event.getY())) {
			activeTile = myController.getGrid().findClickedTile(event.getX(),
					event.getY());
			myController.getGameGridEffect().highlightEffectRange(
					activeTile.getCoordinates());
		}
	}

	@Override
	public void onClick(Piece piece) {
		Piece actor = myController.getActivePiece();
		if (piece == null
				&& myController.getActiveAction().getName().equals("Move")) {
			piece = new Piece(actor, myController.getCurrentClick());
			piece.setLoc(myController.getCurrentClick());
		}
		// System.out.println("ACTION RUNNING:" +
		// myController.getActiveAction().toString());
		
		//do action IFF the piece is part of the highlighted action range
		for (Point2D point:myController.getActiveAction().getAbsoluteActionRange(myController.getActivePiece().getLoc())){
		    if (piece.getLoc().equals(point)){
		        myController.getActiveAction().doBehavior(myController.getGrid(),actor, piece);
		    }
		    else{
		        System.out.println("NOPE NOT HERE");
		    }
		    myGameGridEffect.clearAllPieceHighlights();
		    myGameGridEffect.clearAllActionHighlights();
		    myController.clearActions();
		    myController.setGridState(new SelectState(myController));
		    myController.setActivePiece(null);
		    myController.setActiveAction(null);
		    myController.endAction();
		}
		
		//myController.getGrid().repopulateGrid();

	}

}
