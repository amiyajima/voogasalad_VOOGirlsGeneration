package authoring.concretefeatures;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import authoring.data.PatchData;
import authoring.data.PieceData;
import authoring_environment.GridView;
import authoring_environment.JennieGrid;
import authoring_environment.SandyGrid;
import authoring_environment.SandyGridView;
import authoring_environment.ShapeGrid;
import authoring_environment.SuperGrid;
import authoring_environment.WorkspaceView;

/**
 * 
 * @author Rica
 *
 */
public class LevelEditor {
    private static final int MIN_TILE_SIZE = 40;
    private static final int GRID_VIEW_HEIGHT = 500;
    private static final int GRID_VIEW_WIDTH = 700;
    
    private WorkspaceView myWorkspaceView;
    private String levelName;
    
    private String myGridType;
    private int myRows;
    private int myCol;
    private SuperGrid shapeGrid;
    private int tileSize;
    
    private PieceData pieceData = new PieceData();
    private PatchData patchData = new PatchData();
    
    /**
     * Upon construction, makes a grid of the specified type and adds it to the Workspace Tab
     * @param wsView
     * @param name
     * @param gridType
     * @param row
     * @param col
     */
    public LevelEditor(WorkspaceView wsView, String gridType, String name, int row, int col) {
        myWorkspaceView = wsView;
        myGridType = gridType;
        levelName = name;
        myRows = row;
        myCol = col;
        tileSize = getPrefTileSize(myRows,myCol);
        
        initializeGrid();
        addWorkspaceTab(shapeGrid, levelName);
    }
    
    //TODO If grid type doesn't match, this may cause a NullPointerException
    private void initializeGrid() {
        //TODO: hard coded grid type
    	//TODO: CHANGE LEVEL ID THIS IS HARDCODED
        if (myGridType.equals("Square Grid")){
           shapeGrid = new SuperGrid(myCol, myRows,
                           tileSize, myGridType);

        }

    }

    private void addWorkspaceTab(SuperGrid superGrid, String name) {
        BorderPane bPane = new BorderPane();
        GridView gridView = new GridView(superGrid, GRID_VIEW_WIDTH, GRID_VIEW_HEIGHT);
        bPane.setRight(gridView);
        
        Tab tab = new Tab();
        tab.setContent(bPane);
                        
        myWorkspaceView.addGrid(superGrid);
        myWorkspaceView.addNextTab(tab, name);
    }
    
    private int getPrefTileSize (int gridWidthNumber, int gridHeightNumber) {
        int calculatedTileSize = Math.max(GRID_VIEW_WIDTH
                                          / gridWidthNumber, GRID_VIEW_HEIGHT/ gridHeightNumber);
        int tileSize = (calculatedTileSize < MIN_TILE_SIZE) ? MIN_TILE_SIZE
                                                           : calculatedTileSize;
        return tileSize;
    }
}
