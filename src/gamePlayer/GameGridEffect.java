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
    private static final String GREY_OUT = "#000000";
     
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
        
        if (myActivePiece != null && myActiveAction != null) {
            //System.out.println("GameGridEffect: action ABOUT TO HIGHLIGHT\n\n");
        	//System.out.println("Size of Action Range:"+myActiveAction.getActionRange(myActivePiece.getLoc()));
            myActiveAction.getAbsoluteActionRange(myActivePiece.getLoc())
                    .forEach(point -> { if (point.getX() < myGrid.getNumRows()
                                     && point.getY() < myGrid.getNumCols()
                                     && point.getX() >= 0 && point.getY() >= 0) {
                                     
                                     SuperTile toHighlight = myGrid.findTile(point);
                                     toHighlight.selectTile(ACTION_RANGE_COLOR);
                                     myHighlightedActions.add(toHighlight);
                                     //System.out.println("EFFECT LOCATION X:"+point.getX()+" Y:"+point.getY());
                                     
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
    public void highlightEffectRange (Point2D.Double loc) {
        clearAllEffectHighlights();
        
        myActivePiece = myViewController.getActivePiece();
        myActiveAction = myViewController.getActiveAction();
        
        if (myActivePiece != null && myActiveAction != null) {
            myActiveAction.getAbsoluteActionRange(myActivePiece.getLoc()).forEach(point -> {
                if (loc.equals(point)){
                    myActiveAction.getEffectRange().forEach(point2 -> {
                    	if(point2.getX()>=0 && point2.getY()>=0 && point2.getX()<= myGrid.getNumCols() &&
                    			point2.getY()<= myGrid.getNumRows()	){
                    	    SuperTile toHighlight = myGrid.findTile(point2);
                            toHighlight.selectTile(EFFECT_RANGE_COLOR);
                            myHighlightedEffects.add(toHighlight);
                    	}
                        });
                    }
                });
            }
    }
    
    /**
     * Uses GRID COORDINATE LOCATION to highlight the current location on the grid
     */
    public void highlightCurrent(Point2D.Double loc, Piece activeP){
        System.out.println("highlight CURRENT");
        
        clearAllPieceHighlights();
        clearAllActionHighlights();
        clearAllEffectHighlights();
        updateActives();
//        System.out.println("GGE highlight location: " + loc.getX() + " " + loc.getY());
        SuperTile toHighlight = myGrid.findTile(loc);
        toHighlight.selectTile(DEFAULT_COLOR);
        myHighlightedPiece.add(toHighlight);
    }
    
    public void greyOutPiece(Point2D.Double loc, Piece p) {
        clearAllPieceHighlights();
        clearAllActionHighlights();
        clearAllEffectHighlights();
        updateActives();
        SuperTile toHighlight = myGrid.findTile(loc);
        toHighlight.selectTile(GREY_OUT);
        myHighlightedPiece.add(toHighlight);
    }
    
    /**
     * Clear all effects in grid, currently only clears highlights
     * @param grid
     */
    private void clearAllEffectHighlights(){
        for (SuperTile st : myHighlightedEffects) {
            myGrid.findTile(st.getCoordinates()).deselectTile();
        }
        myHighlightedEffects.clear();
    }
    
    public void clearAllActionHighlights(){
        for (SuperTile st : myHighlightedActions) {
            myGrid.findTile(st.getCoordinates()).deselectTile();
        }
        myHighlightedActions.clear();
    }
    
    public void clearAllPieceHighlights(){
        for (SuperTile st : myHighlightedPiece) {
            myGrid.findTile(st.getCoordinates()).deselectTile();
        }
        myHighlightedPiece.clear();
    }
    
    private void updateActives() {
        myActivePiece = myViewController.getActivePiece();
        myActiveAction = myViewController.getActiveAction();
    }
    
    /**
     * Checks if the mouse movement has it hovering over an action tile Only
     * then will it update the highlight of the effect range
     * 
     * @param mouseX
     * @param mouseY
     * @return
     */
    public boolean isHoveringOverActionHighlight(double mouseX, double mouseY) {
            for (SuperTile st : myHighlightedActions) {
                    if (myViewController.getGrid().findClickedTile(mouseX, mouseY)
                                    .getCoordinates() == st.getCoordinates()) {
                            return true;
                    }
            }
            return false;
    }

    
    
    
}
