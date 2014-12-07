package gamePlayer;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.List;
import authoring_environment.SuperTile;
import javafx.scene.paint.Color;
import gamedata.action.Action;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import gameengine.movement.Movement;


/**
 * Class representing the state of the grid when a particular action
 * has been selected and is ready to be applied on the grid
 *
 */
public class ApplyState implements IGridState {

    private ViewController myController;
    private GameGridEffect myGameGridEffect;
    private Game myGame;
    private SuperTile activeTile;

    public ApplyState (ViewController controller) {
         System.out.println("new ApplyState");
        myController = controller;
        myGameGridEffect = controller.getGameGridEffect();
        myGame = controller.getGame();
        
//        myController.getGrid().findEnteredTile(); //assuming this method works
        
        myController.getGridPane().setOnMouseEntered(event -> {
            myController.changeCursor(myController.CURSOR_ATTACK_TEST);
            
            //first highlight the action range?
            

            
            //find where the mouse is hovering over
            activeTile = myController.getGrid().findClickedTile(event.getX(), event.getY());
            activeTile = myController.getGrid().findClickedTile(0, 0);
            
            activeTile.setOnMouseExited(event3 -> {
            });
        });
//        myController.getGridPane().setOnMouseEntered(event -> {
//            myController.changeCursor(myController.CURSOR_ATTACK_TEST);
//                                       myController.getGridPane().getChildren().forEach(node -> {
//                        node.setOnMouseEntered(event2 -> {
//                            myGameGridEffect.highLightEffectRange(myController.getGridPane(), event2,Color.RED);
//                        });
//                        
//                        node.setOnMouseExited(event3 -> {
//                            myGameGridEffect.highLightActionRange();
//                            // TODO: Put this back in
//                            /*
//                             * if (myController.getActivePiece() != null) {
//                             * myController
//                             * .highlightCurrent(myController
//                             * .getActivePiece()
//                             * .getLoc(),
//                             * Color.BLUE);
//                             * }
//                             */
//                        });
//                    });
//                });
    }

    @Override
    public void onClick (Piece piece) {

        Piece actor = myController.getActivePiece();
//        if (piece == null) {
//            piece = new Piece(actor);
//            piece.setLoc(myController.getCurrentClick());
//        }
        myController.getActiveAction().doBehavior(actor, piece);

        myController.setGridState(new SelectState(myController));

        myController.changeCursor(myController.CURSOR_GLOVE_TEST);
        myController.getGame().getCurrentLevel().garbageCollectPieces();
 
        //still need to update the grid somehow?
//        myController.getGridPane().populateGrid(myController.getGame().getCurrentLevel().getGrid().getPatches(),
//                                            myController.getGame().getCurrentLevel().getGrid().getPieces());

        myController.setActivePiece(null);
        myController.setActiveAction(null);

        //commented out until myGame is fixed
//        checkLevelState();
//        checkPlayerState();
    }

    /**
     * TODO: Temporary Location of GameLoop Check
     */
    private void checkLevelState () {
        if (myGame.getCurrentLevel().levelCompleted()) {
            myGame.nextLevel();
        }
    }

    /**
     * TODO: Temporary Location of GameLoop Check
     */
    private void checkPlayerState () {
        if (myGame.getCurrentLevel().checkTurnEnd(myGame.getCurrentPlayer().getNumMovesPlayed())) {
            myGame.nextPlayer();
        }
    }
}
