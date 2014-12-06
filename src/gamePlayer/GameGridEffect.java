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
    ViewController myViewController;
    Highlighter myHighlighter;
     
    public GameGridEffect(ViewController vc){
        myHighlighter = new Highlighter();
        myViewController = vc;
     }
    
    /**
     * Return the highlighter used for the grid
     * @return
     */
    public Highlighter getHighlighter(){
        return myHighlighter;
    }
    
    /**
     * Highlight the tiles that represent the possible range of the action
     * selected
     */
    @FXML
    public void highlightActionRange (GUIGrid grid, Piece activePiece, Action activeAction) {

        clearAllEffects(grid);
        if (activePiece != null && activeAction != null) {

            activeAction.getActionRange(activePiece.getLoc())
                    .forEach(
                             point -> {
                                 if (point.getX() < grid.getRow()       //assuming this method exists
                                     && point.getY() < grid.getCol()    //assuming this method exists
                                     && point.getX() > 0 && point.getY() > 0) {
                                     Node n = grid.get((int) point.getX(), (int) point.getY()); //assuming this method exists
//                                      myHighlighter.addDropShadow(n, Color.YELLOW);     
                                     myHighlighter.highlight(grid, point, Color.YELLOW);
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
    public void highlightEffectRange (MouseEvent me, Color c, GUIGrid grid, Piece activePiece, Action activeAction, Point2D loc) {
        
        if (activePiece != null && activeAction != null) {
            activeAction.getActionRange(activePiece.getLoc()).forEach(point -> {
                
//                Point2D loc = myViewController.findPosition(me.getSceneX(),me.getSceneY());

                if (loc.equals(point)) {
                    activeAction.getEffectRange().forEach(point2 -> {
//                        Node n =grid.get((int) (loc.getX() + point2.getX()),   //assuming this method exists
//                                           (int) (loc.getY() + point2.getY()));
//                         myHighlighter.addDropShadow(n, c);
                        myHighlighter.highlight(grid, loc, c);
                        });
                    }
                });
            }
        }
    
    /**
     * Highlight the current location on the grid
     */
    public void highlightCurrent(GUIGrid grid, Point2D loc, Color c){
        myHighlighter.highlight(grid, loc, c);
    }
    
    /**
     * Clear all effects in grid
     * @param grid
     */
    public void clearAllEffects(GUIGrid grid){
        grid.getChildren().forEach(node->node.setEffect(null));
    }
    
    
}