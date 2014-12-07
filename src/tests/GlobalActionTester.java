package tests;

import static org.junit.Assert.*;
import java.awt.geom.Point2D;
import org.junit.Test;
import gamedata.events.CreatePieceGlobalAction;
import gamedata.events.DeletePieceGlobalAction;
import gamedata.events.SwitchPlayerGlobalAction;
import gamedata.events.LevelChangeGlobalAction;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

public class GlobalActionTester {
    private JSONBobTester myBob = new JSONBobTester();
    private Game myGame;
    private CreatePieceGlobalAction myCreatePiece;
    private DeletePieceGlobalAction myDeletePiece;
    private SwitchPlayerGlobalAction myEndTurn;
    private LevelChangeGlobalAction myLevelChange;
    
    private Piece pieceToCreate;
    private static final String DEFAULT_LEVEL_JUMP = "Level 1";
    private static final int DEFAULT_TURN_CHANGE =  12345;
    private static final Point2D.Double DEFAULT_PIECE_LOCATION = new Point2D.Double(3,3);
    
    public GlobalActionTester() {
        myGame = myBob.createNewGame();
        pieceToCreate = myBob.createNewPiece(myGame.getCurrentLevel().getGrid(), DEFAULT_PIECE_LOCATION);
        myCreatePiece = new CreatePieceGlobalAction(myGame, pieceToCreate);
        myDeletePiece = new DeletePieceGlobalAction(myGame, pieceToCreate);
        myEndTurn = new SwitchPlayerGlobalAction(myGame, DEFAULT_TURN_CHANGE);
        myLevelChange = new LevelChangeGlobalAction(myGame, DEFAULT_LEVEL_JUMP);
    }
    
    @Test
    public void TestCreatePiece() {
        myCreatePiece.doBehavior();
        assertEquals(myGame.getCurrentLevel().getGrid().getPiece((int)DEFAULT_PIECE_LOCATION.x, (int)DEFAULT_PIECE_LOCATION.y), pieceToCreate);
    }
    
    @Test
    public void TestDeletePiece() {
        myDeletePiece.doBehavior();
        assertNull(myGame.getCurrentLevel().getGrid().getPiece(DEFAULT_PIECE_LOCATION));
    }
    
    @Test
    public void TestEndTurn() {
        myEndTurn.doBehavior();
        assertEquals(myGame.getCurrentPlayer().getID(), 12345);
    }
    
    @Test
    public void TestLevelChange() {
        myLevelChange.doBehavior();
        assertEquals(myGame.getCurrentLevel().getId(), "Level 1");
    }

}
