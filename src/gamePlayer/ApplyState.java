package gamePlayer;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.List;
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
    private Game myGame;

    public ApplyState (ViewController controller) {
        System.out.println("new ApplyState");
        myController = controller;
        myGame = controller.getGame();
        myController
                .getGrid()
                .setOnMouseEntered(event -> {
                                       myController.changeCursor(myController.CURSOR_ATTACK_TEST);

                                       myController
                                               .getGrid()
                                               .getChildren()
                                               .forEach(node -> {
                                                            node.setOnMouseEntered(event2 -> {
                                                                myController
                                                                        .highLightEffectRange(event2,
                                                                                              Color.RED);
                                                            });
                                                            node.setOnMouseExited(event3 -> {
                                                                myController.highLightActionRange();
                                                                if (myController.getActivePiece() != null) {
                                                                    myController
                                                                            .highlightCurrent(myController
                                                                                                      .getActivePiece()
                                                                                                      .getLoc(),
                                                                                              Color.BLUE);
                                                                }
                                                            });

                                                        });

                                   });

    }

    @Override
    public void onClick (Piece piece) {

        Piece actor = myController.getActivePiece();
        if (piece == null) {
            piece = new Piece(actor);
            piece.setLoc(myController.getCurrentClick());
        }
        myController.getActiveAction().doBehavior(actor, piece);

        myController.setGridState(new SelectState(myController));

        myController.changeCursor(myController.CURSOR_GLOVE_TEST);
        myController.getGame().getCurrentLevel().garbageCollectPieces();
        myController.getGrid().populateGrid(myController.getGame().getCurrentLevel().getGrid()
                                                    .getAllPatches(),
                                            myController.getGame().getCurrentLevel().getGrid()
                                                    .getAllPieces());

        myController.setActivePiece(null);
        myController.setActiveAction(null);
        checkLevelState();
        checkPlayerState();
    }

    private void checkLevelState () {
        if (myGame.getCurrentLevel().levelCompleted()) {
            myGame.nextLevel();
        }
    }

    private void checkPlayerState () {
        if (myGame.getCurrentLevel().checkTurnEnd(myGame.getCurrentPlayer().getNumMovesPlayed())) {
            myGame.nextPlayer();
        }
    }
}
