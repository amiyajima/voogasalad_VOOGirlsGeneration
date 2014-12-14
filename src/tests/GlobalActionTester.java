package tests;

import static org.junit.Assert.*;
import java.awt.geom.Point2D;
import org.junit.Test;
import gamedata.events.globalaction.DeletePieceAtLocation;
import gamedata.events.globalaction.MakePieceAtLocation;
import gamedata.events.globalaction.LevelChange;
import gamedata.events.globalaction.EndTurn;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Piece;

public class GlobalActionTester {
    private TestGameCreator myBob = new TestGameCreator();
    private Game myGame;
    private MakePieceAtLocation myCreatePiece;
    private DeletePieceAtLocation myDeletePiece;
    private EndTurn myEndTurn;
    private LevelChange myLevelChange;
    
    private Piece pieceToCreate;
    private static final String DEFAULT_LEVEL_JUMP = "Level 1";
    private static final int DEFAULT_TURN_CHANGE =  12345;
    private static final Point2D.Double DEFAULT_PIECE_LOCATION = new Point2D.Double(3,3);
    
    public GlobalActionTester() {
        myGame = myBob.createNewGame();
        pieceToCreate = myBob.createNewPiece(myGame.getCurrentLevel().getGrid(), DEFAULT_PIECE_LOCATION);
        myCreatePiece = new MakePieceAtLocation(myGame, pieceToCreate);
        myDeletePiece = new DeletePieceGlobalAction(myGame, pieceToCreate);
        myEndTurn = new EndTurn(myGame, DEFAULT_TURN_CHANGE);
        myLevelChange = new LevelChange(myGame, DEFAULT_LEVEL_JUMP);
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
