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
    GUIGrid myGrid;
    Piece myActivePiece;
    Action myActiveAction;
     
    public GameGridEffect(ViewController vc){
        myHighlighter = new Highlighter();
        myViewController = vc;
        myGrid = myViewController.getGrid();
        myActivePiece = myViewController.getActivePiece();
        myActiveAction = myViewController.getActiveAction();
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
    public void highlightActionRange () {

        clearAllEffects(myGrid);
        if (myActivePiece != null && myActiveAction != null) {

            myActiveAction.getActionRange(myActivePiece.getLoc())
                    .forEach(point -> {
                                 if (point.getX() < myGrid.getRow()       //assuming this method exists
                                     && point.getY() < myGrid.getCol()    //assuming this method exists
                                     && point.getX() > 0 && point.getY() > 0) {
                                     Node n = myGrid.get((int) point.getX(), (int) point.getY()); //assuming this method exists
//                                      myHighlighter.addDropShadow(n, Color.YELLOW);     
                                     myHighlighter.highlight(myGrid, point, Color.YELLOW);
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
    public void highlightEffectRange (MouseEvent me, Color c, Point2D loc) {
        
        if (myActivePiece != null && myActiveAction != null) {
            myActiveAction.getActionRange(myActivePiece.getLoc()).forEach(point -> {
                
//                Point2D loc = myViewController.findPosition(me.getSceneX(),me.getSceneY());

                if (loc.equals(point)) {
                    myActiveAction.getEffectRange().forEach(point2 -> {
//                        Node n =grid.get((int) (loc.getX() + point2.getX()),   //assuming this method exists
//                                           (int) (loc.getY() + point2.getY()));
//                         myHighlighter.addDropShadow(n, c);
                        myHighlighter.highlight(myGrid, loc, c);
                        });
                    }
                });
            }
        }
    
    /**
     * Highlight the current location on the grid
     */
    public void highlightCurrent(Point2D loc, Color c){
        myHighlighter.highlight(myGrid, loc, c);
    }
    
    /**
     * Clear all effects in grid
     * @param grid
     */
    public void clearAllEffects(GUIGrid grid){
        grid.getChildren().forEach(node->node.setEffect(null));
    }
    
    
}
