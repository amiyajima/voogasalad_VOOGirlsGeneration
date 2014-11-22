package authoring_environment;

import gamedata.gamecomponents.Patch;
import java.util.ArrayList;
import java.util.List;
import authoring.data.PatchData;
import javafx.event.EventHandler;
import java.awt.geom.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


/**
 * The grid which contains all the tiles and draws the tiles and grid lines.
 * 
 * @author huangmengen
 *
 */
public class Grid extends Pane {

    private int myWidth;
    private int myHeight;
    private int myTileSize;
    private Tile[][] grid;
    private PatchData myPatchData;

    public Grid (int width, int height, int tilesize) {
        myWidth = width;
        myHeight = height;
        myTileSize = tilesize;
        grid = new Tile[myWidth][myHeight];

        // will be adding patch to patchdata whenever a new patch is put onto the grid
        myPatchData = new PatchData();

        // setStyle("-fx-background-color:blue;");

        initiateGrids();
        initiateGridLines();
        // sampleSelected();

    }

    // Need to fix the duplicated code later.
    private void initiateGridLines () {
        for (int i = 0; i <= myWidth * myTileSize; i += myTileSize) {
            Line gridLine = new Line(i, 0, i, myHeight * myTileSize);
            gridLine.setStroke(Color.WHITE);
            gridLine.setStrokeWidth(1.5);
            this.getChildren().add(gridLine);
        }

        for (int i = 0; i <= myHeight * myTileSize; i += myTileSize) {
            Line gridLine = new Line(0, i, myWidth * myTileSize, i);
            gridLine.setStroke(Color.WHITE);
            gridLine.setStrokeWidth(1.5);
            this.getChildren().add(gridLine);
        }
    }

    private void initiateGrids () {
        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                grid[i][j] = new Tile(i, j, myTileSize);
                this.getChildren().add(grid[i][j]);
                setClickEvent(grid[i][j]);
            }
        }
        this.setDragEvent();
    }

    /**
     * Get the number of tiles in a row.
     * 
     * @return The number of tiles in a horizontal line of the grid.
     */
    public int getGridWidth () {
        return myWidth;
    }

    /**
     * Get the number of tiles in a column.
     * 
     * @return The number of tiles in a vertical line of the grid.
     */
    public int getGridHeight () {
        return myHeight;
    }

    public void sampleSelected () {
        for (Tile[] line : grid) {
            for (Tile tile : line) {
                tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent event) {
                        tile.switchSelected();
                    }
                });
            }
        }
    }

    /**
     * Get a specific tile in the grid according to its position.
     * 
     * @param x: The X coordination of the tile
     *        from the left smallest to the right largest.
     * @param y: The Y coordination of the tile
     *        from the bottom smallest to the top largest.
     * @return The tile at the specified position.
     */
    public Tile getTile (int x, int y) {
        return grid[x][y];
    }

    public List<Tile> getSelected () {
        List<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; i < myHeight; j++) {
                if (getTile(i, j).getSelected()) {
                    tiles.add(getTile(i, j));
                }
            }
        }
        return tiles;
    }

    private void setDragEvent () {
        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                int x = (int) event.getX() / myTileSize;
                int y = (int) event.getY() / myTileSize;
                Tile tile = getTile(x, y);
                setContents(tile, x, y);
            }
        });
    }

    private void setClickEvent (Tile tile) {
        tile.setStyle("-fx-cursor: hand");
        tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent m) {
                int xCoord = tile.getX();
                int yCoord = tile.getY();
                setContents(tile, xCoord, yCoord);

                // get the coordinate of where the terrain is being put
                // create a new patch with such coord
                // add such patch to patchdata
            }
        });
    }

    protected void setContents (Tile tile, int xCoord, int yCoord) {
        if (LibraryView.reset) {
            if (LibraryView.unitSelected) {
                tile.myUnit = null;
                tile.unitImage.setVisible(false);
                ;
            }
            else {
                tile.myTerrain = null;
                tile.terrainImage.setVisible(false);
                System.out.println("patch deleted");
                myPatchData.remove(tile.myTerrain);
                ;
            }
        }
        else {
            if (LibraryView.unitSelected) {
                tile.myUnit = LibraryView.currentlySelectedUnit;
                tile.unitImage.setImage(tile.myUnit.getImageView().getImage());
                tile.unitImage.setVisible(true);
            }
            else {
                tile.myTerrain = LibraryView.currentlySelectedTerrain;
               
                tile.myTerrain.setLoc(new Point2D.Double(xCoord, yCoord));
                tile.terrainImage.setImage(tile.myTerrain.getImageView().getImage());
                tile.terrainImage.setVisible(true);
                myPatchData.add(tile.myTerrain);
                System.out.println("patch added");
                
                
                // allows overwriting patches on the same coordinate
                List<Patch> myPatches = myPatchData.getPatches();
                for (int i = 0; i < myPatches.size(); i++) {
                    if ((myPatches.get(i).getLoc().getX()) == xCoord &&
                        (myPatches.get(i).getLoc().getY()) == yCoord) {
                        myPatchData.remove(myPatches.get(i));
                    }            
                }
                
                
            }
        }
    }

    public Tile[][] getGridTiles () {
        return grid;
    }
}
