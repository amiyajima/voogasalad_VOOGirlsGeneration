package gamePlayer;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import authoring_environment.GUIGrid;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class GameGridEffect {
//    ViewController myViewController;
    Highlighter myHighlighter;
     
    public GameGridEffect(){
        myHighlighter = new Highlighter();
     }
    
    /**
     * Highlight the tiles that represent the possible range of the action
     * selected
     */
    @FXML
    protected void highLightActionRange (GUIGrid grid, Piece activePiece, Action activeAction) {

        grid.clearEffect();
        if (activePiece != null && activeAction != null) {

            activeAction.getActionRange(activePiece.getLoc())
                    .forEach(
                             point -> {
                                 if (point.getX() < grid.getRow()
                                     && point.getY() < grid.getCol()
                                     && point.getX() > 0 && point.getY() > 0) {
                                     Node n = grid.get((int) point.getX(), (int) point.getY());
                                      myHighlighter.addDropShadow(n, Color.YELLOW);
                                 }
                             });
        }
    }

    /**
     * Highlight the effect range of an action if to be applied at a given
     * position Highlight the effect range of an action if to be applied at a
     * given position
     * 
     * @param n
     * @param red
     */
    protected void highLightEffectRange (MouseEvent me, Color c, GUIGrid grid, Piece activePiece, Action activeAction) {
        
        if (activePiece != null && activeAction != null) {
            activeAction.getActionRange(activePiece.getLoc()).forEach(point -> {
                Point2D temp = findPosition(me.getSceneX(),me.getSceneY());
                
                if (temp.equals(point)) {
                    activeAction.getEffectRange().forEach(point2 -> {
                        Node n =grid.get((int) (temp.getX() + point2.getX()), 
                                           (int) (temp.getY() + point2.getY()));
                         myHighlighter.addDropShadow(n, c);
                        });
                    }
                });
            }
        }
    
    
    
    
}
