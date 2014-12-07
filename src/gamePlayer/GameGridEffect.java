package gamePlayer;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import authoring_environment.GUIGrid;
import authoring_environment.SuperTile;


public class GameGridEffect {
    ViewController myViewController;
    GUIGrid myGrid;
    Piece myActivePiece;
    Action myActiveAction;
    List<SuperTile> myHighlightedTiles;

    
    public static final String ACTION_RANGE_COLOR = "#FFBF00";
    public static final String EFFECT_RANGE_COLOR = "#DF0101";
    private static final Color DEFAULT_HIGHLIGHT_COLOR = Color.web("#0000FF", 0.3);
    
    private static final String DEFAULT_COLOR = "#0000FF";
     
    public GameGridEffect(ViewController vc){
        myViewController = vc;
        myGrid = myViewController.getGrid();
        myActivePiece = myViewController.getActivePiece();
        myActiveAction = myViewController.getActiveAction();
        myHighlightedTiles = new ArrayList<SuperTile>();
     }
    

    /**
     * Highlight the tiles that represent the possible range of the action
     * selected
     */
    public void highlightActionRange () {
        clearAllEffects();
        System.out.println("Highlighting action111 range");

        
        if (myActivePiece != null && myActiveAction != null) {
            System.out.println("GameGridEffect: action ABOUT TO HIGHLIGHT\n\n");
            myActiveAction.getActionRange(myActivePiece.getLoc())
                    .forEach(point -> { if (point.getX() < myGrid.getRow()
                                     && point.getY() < myGrid.getCol()
                                     && point.getX() > 0 && point.getY() > 0) {
                                     
                                     SuperTile toHighlight = myGrid.findClickedTile(point);
                                     toHighlight.selectTile(ACTION_RANGE_COLOR);
                                     myHighlightedTiles.add(toHighlight);
                                     
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
    public void highlightEffectRange (MouseEvent me) {
        clearAllEffects();
        System.out.println("Highlighting effect range");
        
        myActivePiece = myViewController.getActivePiece();
        myActiveAction = myViewController.getActiveAction();
        
        if (myActivePiece != null && myActiveAction != null) {
            System.out.println("GameGridEffect: effect ABOUT TO HIGHLIGHT\n\n");
            myActiveAction.getActionRange(myActivePiece.getLoc()).forEach(point -> {
//                if (loc.equals(point)   ) {
                if ((me.getX() == point.getX()) & (me.getY() == point.getY())) {
                    myActiveAction.getEffectRange().forEach(point2 -> {
                        SuperTile toHighlight = myGrid.findClickedTile(point);
                        toHighlight.selectTile(EFFECT_RANGE_COLOR);
                        myHighlightedTiles.add(toHighlight);
                        System.out.println("Effect Range Highlight!");
                        });
                    }
                });
            }
        }
    
    /**
     * Uses GRID COORDINATE LOCATION to highlight the current location on the grid
     */
    public void highlightCurrent(Point2D loc, Piece activeP){
        clearAllEffects();
        myActivePiece = activeP;
        System.out.println("GGE highlight location: " + loc.getX() + " " + loc.getY());
        SuperTile toHighlight = myGrid.findClickedTile(loc);
        toHighlight.selectTile(DEFAULT_COLOR);
        myHighlightedTiles.add(toHighlight);
    }
    

    /**
     * Clear all effects in grid, currently only clears highlights
     * @param grid
     */
    private void clearAllEffects(){
        for (SuperTile st : myHighlightedTiles) {
            myGrid.findClickedTile(st.getLocation()).deselectTile();
        }
    }

    
    
    
    
}
