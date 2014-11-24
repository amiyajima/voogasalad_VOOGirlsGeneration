package gamePlayer;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.awt.geom.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * The View of square game grid. It observes the (Level in) Game
 * and updates (re-populate the grid and update scores of players)
 * upon notified.
 *
 */
// TODO: maybe observing Game instead of Level.? (need access to player scores.)

public class SquareGameGrid extends GameGrid {

    public static final String TEST_IMAGE = "/src/voogasalad_VOOGirlsGeneration/turtle.png";

    public SquareGameGrid (int row, int col) {
        // will handle different size, stubbing it for now.
        super(row, col);

    }


    @Override
    public void update (Observable o, Object arg) {
        if (o instanceof Level) {
            System.out.println("updated!");
            populateGrid(((Level) o).getGrid().getAllPatches(), ((Level) o).getGrid().getAllPieces());

        }

    }

    /**
     * The method to initialize the Grid. it populates the grid with
     * stackpanes that allows for adding pieces and patches images.
     * 
     */
    @Override
    protected void initializeGrid () {

        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                StackPane sp = new StackPane();
                sp.setAlignment(Pos.CENTER);
                sp.setPrefHeight(500 / c);
                sp.setPrefWidth(500 / this.r);

                this.add(sp, i, j);
               
                

            }

        }
    }
    
    
    // TODO: implement the logic in View Controller class.

    /**
     * populates the grid according to the given the patch and piece maps.
     * 
     */

    //TODO: REFACTOR!
    @Override
    protected void populateGrid (List<Patch> patches, List<Piece> pieces) {
        this.getChildren().forEach(node -> {
            ((StackPane) node).getChildren().clear();
        });
        initializeGrid();
        patches.forEach(patch -> {
            int x = (int)(patch.getLoc().getX());
            int y = (int ) (patch.getLoc().getY());
            Node n = get(x,y);
            ((StackPane)n).getChildren().add(patch.getImageView());
        
        });
        pieces.forEach(piece -> {
            int x = (int)(piece.getLoc().getX());
            int y = (int ) (piece.getLoc().getY());
            Node n = get(x,y);
            ((StackPane)n).getChildren().add(piece.getImageView());
        
        });
    }
    

    
}
