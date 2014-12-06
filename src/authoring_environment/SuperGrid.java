package authoring_environment;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.layout.Pane;

public class SuperGrid extends Pane {
        private static final String CIRCLE_GRID = "Circle Grid";
        private static final String HEXAGON_GRID = "Hexagon Grid";
        private static final String SQUARE_GRID = "Square Grid";
        protected int myRows;
        protected int myCols;
        protected int myTileSize;
        
        protected List<List<SuperTile>> myGrid;
        
        public SuperGrid(int cols, int rows, int tileSize, String shape) {
                
                myRows = rows;
                myCols = cols;
                myTileSize = tileSize;
                initGridTiles(shape);
        }
        
        private void initGridTiles(String shape){
                myGrid = new LinkedList<List<SuperTile>>();
                for (int r = 0; r < myRows; r++) {
                        List<SuperTile> tileCol = new LinkedList<SuperTile>();
                        for (int c = 0; c < myCols; c++) {
                                Point2D location=new Point2D.Double(r,c);
                                SuperTile tile = makeShapeTile(shape, myTileSize, location);
                                tileCol.add(tile);
                                super.getChildren().add(tile);
                        }
                        myGrid.add(tileCol);
                }
        }

        private SuperTile makeShapeTile(String shape, int tileSize, Point2D location) {
                switch(shape){
                case SQUARE_GRID:
                        return new SquareTile(tileSize,location);
                case HEXAGON_GRID:
                        return new HexagonTile(tileSize,location);
                case CIRCLE_GRID:
                        return new CircleTile(tileSize,location);
                default:
                        return new SquareTile(tileSize,location);
                }
        }    

}