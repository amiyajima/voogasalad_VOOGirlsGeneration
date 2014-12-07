package gamePlayer;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import authoring_environment.GUIGrid;
import authoring_environment.SuperTile;


public class GameGridEffect {
    private ViewController myViewController;
    private GUIGrid myGrid;
    private Piece myActivePiece;
    private Action myActiveAction;
    private List<SuperTile> myHighlightedPiece;
    private List<SuperTile> myHighlightedActions;
    private List<SuperTile> myHighlightedEffects;

    
    public static final String ACTION_RANGE_COLOR = "#FFBF00";
    public static final String EFFECT_RANGE_COLOR = "#DF0101";
    private static final String DEFAULT_COLOR = "#0000FF";
     
    public GameGridEffect(ViewController vc){
        myViewController = vc;
        myGrid = myViewController.getGrid();
        myActivePiece = myViewController.getActivePiece();
        myActiveAction = myViewController.getActiveAction();
        myHighlightedPiece = new ArrayList<SuperTile>();
        myHighlightedActions = new ArrayList<SuperTile>();
        myHighlightedEffects = new ArrayList<SuperTile>();
     }
    

    /**
     * Highlight the tiles that represent the possible range of the action
     * selected
     */

    public void highlightActionRange () {
        clearAllActionHighlights();
        updateActives();
        System.out.println("Highlighting action range");
        
        if (myActivePiece != null && myActiveAction != null) {
            System.out.println("GameGridEffect: action ABOUT TO HIGHLIGHT\n\n");
            myActiveAction.getActionRange(myActivePiece.getLoc())
                    .forEach(point -> { if (point.getX() < myGrid.getRow()
                                     && point.getY() < myGrid.getCol()
                                     && point.getX() > 0 && point.getY() > 0) {
                                     
                                     SuperTile toHighlight = myGrid.findClickedTile(point);
                                     toHighlight.selectTile(ACTION_RANGE_COLOR);
                                     myHighlightedActions.add(toHighlight);
                                     
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
    public void highlightEffectRange (Point2D loc) {
        clearAllEffectHighlights();
        
        myActivePiece = myViewController.getActivePiece();
        myActiveAction = myViewController.getActiveAction();
        
        if (myActivePiece != null && myActiveAction != null) {
//            System.out.println("GameGridEffect: effect ABOUT TO HIGHLIGHT\n\n");
            myActiveAction.getActionRange(myActivePiece.getLoc()).forEach(point -> {
                if (loc.equals(point)){
                    myActiveAction.getEffectRange().forEach(point2 -> {
                        SuperTile toHighlight = myGrid.findClickedTile(point2);
                        toHighlight.selectTile(EFFECT_RANGE_COLOR);
                        myHighlightedEffects.add(toHighlight);
//                        System.out.println("Effect Range Highlight!");
                        });
                    }
                });
            }
    }
    
    /**
     * Uses GRID COORDINATE LOCATION to highlight the current location on the grid
     */
    public void highlightCurrent(Point2D loc, Piece activeP){
        clearAllPieceHighlights();
        clearAllActionHighlights();
        clearAllEffectHighlights();
        updateActives();
        System.out.println("GGE highlight location: " + loc.getX() + " " + loc.getY());
        SuperTile toHighlight = myGrid.findClickedTile(loc);
        toHighlight.selectTile(DEFAULT_COLOR);
        myHighlightedPiece.add(toHighlight);
    }
    

    /**
     * Clear all effects in grid, currently only clears highlights
     * @param grid
     */
    private void clearAllEffectHighlights(){
        for (SuperTile st : myHighlightedEffects) {
            myGrid.findClickedTile(st.getLocation()).deselectTile();
        }
        myHighlightedEffects.clear();
    }
    
    public void clearAllActionHighlights(){
        for (SuperTile st : myHighlightedActions) {
            myGrid.findClickedTile(st.getLocation()).deselectTile();
        }
        myHighlightedActions.clear();
    }
    
    public void clearAllPieceHighlights(){
        for (SuperTile st : myHighlightedPiece) {
            myGrid.findClickedTile(st.getLocation()).deselectTile();
        }
        myHighlightedPiece.clear();
    }
    
    private void updateActives() {
        myActivePiece = myViewController.getActivePiece();
        myActiveAction = myViewController.getActiveAction();
    }

    private void printPieceHighlights() {
        for (SuperTile st : myHighlightedPiece) {
            System.out.println("Piece Highlights: " + st.getLocation().getX() + ", " + st.getLocation().getY());
        }
    }
    
    private void printEffectHighlights() {
        for (SuperTile st : myHighlightedEffects) {
            System.out.println("Effect Highlights: " + st.getLocation().getX() + ", " + st.getLocation().getY());
        }
    }
    
    private void printActionHighlights() {
        for (SuperTile st : myHighlightedActions) {
            System.out.println("Action Highlights: " + st.getLocation().getX() + ", " + st.getLocation().getY());
        }
    }
    
    public List<SuperTile> getActionHighlights() {
        return myHighlightedActions;
    }
    
    
    
}
