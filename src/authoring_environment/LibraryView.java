package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.concretefeatures.TerrainEntry;
import authoring.concretefeatures.UnitEntry;
import authoring.data.PatchTypeData;
import authoring.data.PieceTypeData;


/**
 * @author VOOGirls Generation
 * 
 *         GUI components for the library displayed on the left side of the
 *         game authoring environment, which contains all instantiated units
 *         and terrain. From here, the user can open the Unit/TerrainEditors
 *         to edit the units and terrain, as well as select them for
 *         placement on the grid.
 */
public class LibraryView extends TabPane {

    private final int HEIGHT = 300;
    private final int WIDTH = 580;
    private final String UNITS = "Units";
    private final String TERRAIN = "Terrain";
    private final String DELETE = "Delete";
    private final String EDIT = "Edit";
    private final String GLOBAL = "Global Commands";
    private final String PIECES = "Piece Templates";
    private final String PATCHES = "Patch Templates";
    private PieceTypeData myPieces;
    private PatchTypeData myPatches;
    private SuperGrid myGrid;
    private Map<String, VBox> myLibraryMap;
    private Map<String, Tab> myTabMap;
    private SingleSelectionModel<Tab> mySelection;
    private Piece currentUnit;
    private Patch currentTerrain;
    private boolean doNothing;
    private boolean reset;
    private boolean edit;
    private int unitID;
    private int terrainID;

    /**
     * LibraryView constructor. Initializes two tabs - one for units,
     * one for terrain. Units and terrain are added dynamically to
     * their respective tabs as they are created in the UnitCreator
     * and TerrainCreator.
     */
    public LibraryView (PieceTypeData pieceData, PatchTypeData patchData) {
        mySelection = this.getSelectionModel();
        this.setPrefSize(HEIGHT, WIDTH);
        myPieces = pieceData;
        myPatches = patchData;
        doNothing = true;
        reset = true;
        edit = false;
        unitID = 0;
        terrainID = 0;

        Tab unitTab = new Tab(UNITS);
        unitTab.setClosable(false);
        ScrollPane unitContent = new ScrollPane();
        VBox unitLibrary = new VBox();
        unitLibrary.setPadding(UIspecs.allPadding);

        unitLibrary.setSpacing(5);
        Button unitDelete = new Button(DELETE);
        Button unitEdit = new Button(EDIT);
        unitDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                doNothing = false;
                reset = true;
                edit = false;
            }
        });
        unitEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                doNothing = false;
                reset = false;
                edit = true;
            }
        });
        HBox unitGlobal = new HBox(unitEdit, unitDelete);
        unitGlobal.setPadding(UIspecs.allPadding);
        unitGlobal.setSpacing(5);
        unitLibrary.getChildren().addAll(new Label(GLOBAL), unitGlobal,
                                         new Separator(), new Label(PIECES));
        unitContent.setContent(unitLibrary);
        unitTab.setContent(unitContent);

        Tab terrainTab = new Tab(TERRAIN);
        terrainTab.setClosable(false);
        ScrollPane terrainContent = new ScrollPane();
        VBox terrainLibrary = new VBox();
        terrainLibrary.setPadding(UIspecs.allPadding);
        terrainLibrary.setSpacing(5);
        Button terrainDelete = new Button(DELETE);
        Button terrainEdit = new Button(EDIT);
        terrainDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                doNothing = false;
                reset = true;
                edit = false;
            }
        });
        terrainEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                doNothing = false;
                reset = false;
                edit = true;
            }
        });
        HBox terrainGlobal = new HBox(terrainEdit, terrainDelete);
        terrainGlobal.setPadding(UIspecs.allPadding);
        terrainGlobal.setSpacing(5);
        terrainLibrary.getChildren().addAll(new Label(GLOBAL), terrainGlobal,
                                            new Separator(), new Label(PATCHES));
        terrainContent.setContent(terrainLibrary);
        terrainTab.setContent(terrainContent);

        this.getTabs().addAll(unitTab, terrainTab);
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent e) {
                if (e.getCode() == KeyCode.ESCAPE) {
                    doNothing = true;
                }
            }
        });
        myLibraryMap = new HashMap<String, VBox>();
        myLibraryMap.put(UNITS, unitLibrary);
        myLibraryMap.put(TERRAIN, terrainLibrary);
        myTabMap = new HashMap<String, Tab>();
        myTabMap.put(UNITS, unitTab);
        myTabMap.put(TERRAIN, terrainTab);
    }

    public void associateGrid (SuperGrid activeGrid) {
    	myGrid = activeGrid;
    	setGridActionEvents();
    }

    public int getUnitID () {
        unitID += 1;
        return unitID;
    }

    public int getTerrainID () {
    	terrainID += 1;
        return terrainID;
    }

    public void selectUnit (Piece unit) {
        currentUnit = unit;
        doNothing = false;
        reset = false;
        edit = false;
    }

    public void selectTerrain (Patch terrain) {
        currentTerrain = terrain;
        doNothing = false;
        reset = false;
        edit = false;
    }

    private void setGridActionEvents () {
        myGrid.setOnMouseClicked(event -> handleAction(event));
        myGrid.setOnMouseDragged(event -> handleAction(event));
    }

    protected void handleAction (MouseEvent event) {
    	
        SandyTile tile = myGrid.findTile(event);
        if (doNothing || tile == null) { return; }
        if (mySelection.isSelected(0)) {
            if (currentUnit != null) {
                Piece unit = new Piece(currentUnit);
                if (reset) {
                    myGrid.removeUnit(tile, unit);
                }
                else if (edit) {
                    myGrid.editUnit(tile, unit);
                }
                else {
                    myGrid.addUnit(tile, unit);
                }
            }
        }
        else {
            if (currentTerrain != null) {
                Patch terrain = new Patch(currentTerrain);
                if (reset) {
                    myGrid.removeTerrain(tile, terrain);
                }
                else if (edit) {
                    myGrid.editTerrain(tile, terrain);
                }
                else {
                    myGrid.addTerrain(tile, terrain);
                }
            }
        }
    }

    public void addPiece (UnitEntry unit) {
        mySelection.select(myTabMap.get(UNITS));
        myLibraryMap.get(UNITS).getChildren().add(unit);
        myPieces.add(unit.getUnit());
    }

    public void addPatch (TerrainEntry terrain) {
        mySelection.select(myTabMap.get(TERRAIN));
        myLibraryMap.get(TERRAIN).getChildren().add(terrain);
        myPatches.add(terrain.getTerrain());
    }

    public void removePiece (UnitEntry unit) {
        myLibraryMap.get(UNITS).getChildren().remove(unit);
        myGrid.removePieces(unit.getUnit());
        myPieces.remove(unit.getUnit());
        doNothing = true;
    }

    public void removePatch (TerrainEntry terrain) {
        myLibraryMap.get(TERRAIN).getChildren().remove(terrain);
        myGrid.removePatches(terrain.getTerrain());
        myPatches.remove(terrain.getTerrain());
        doNothing = true;
    }
}
