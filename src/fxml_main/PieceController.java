package fxml_main;

import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.ActionData;
import authoring.data.GamePropertiesData;
import authoring.data.PieceTypeData;
import authoring_environment.GUIGrid;


/**
 * @author Martin Tamayo
 *
 */
public class PieceController extends GridComponentAbstCtrl<Piece> {

    private PieceTypeData myPieceTypes;
    private ActionData myActionData;
    private GamePropertiesData myGameProperties;

    public PieceController (VBox vbox, ScrollPane propertiesSPane, GUIGridReference gridRef,
                            PieceTypeData pieceTypes, ActionData actions,
                            GamePropertiesData gamePropertiesData) {
        super(vbox, propertiesSPane, gridRef);
        myActionData = actions;
        myPieceTypes = pieceTypes;
        myGameProperties = gamePropertiesData;
    }

    @Override
    protected void initGlobalNewBtn (Button newBtn) {
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                // Adds piecetype to data and makes an entry box
                Consumer<Piece> okLambda = (Piece piece) -> {
                    myPieceTypes.add(piece);
                    addEntry(piece);
                };
                myPropertiesSPane.setContent(new PieceTypeEditor(okLambda, myPieceTypes,
                                                                 myActionData, myGameProperties
                                                                         .getGridShape()));
            }
        });
    }

    @Override
    protected void initGlobalEditBtn (Button editBtn) {
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                // Make a MouseEvent for clicking the grid
                EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent e) {
                        GUIGrid grid = myGridReference.getGrid();
                        Point2D.Double coor = grid.findClickedCoordinate(e.getX(), e.getY());
                        Piece piece = grid.getPiece(coor);
                        myPropertiesSPane.setContent(new PieceEditor(piece,
                                                                     myGameProperties
                                                                             .getNumPlayers()));
                    }
                };
                myGridReference.getGrid().paneSetOnMousePressed(clickHandler);
                myGridReference.getGrid().paneSetOnMouseDragged(clickHandler);
            }
        });
    }

    @Override
    protected void initGlobalDelBtn (Button delBtn) {
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                // Make a MouseEvent for clicking the grid
                EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent e) {
                        GUIGrid grid = myGridReference.getGrid();
                        Point2D.Double coor = grid.findClickedCoordinate(e.getX(), e.getY());
                        grid.removePieceAtCoordinate(coor);
                    }
                };
                myGridReference.getGrid().paneSetOnMousePressed(clickHandler);
                myGridReference.getGrid().paneSetOnMouseDragged(clickHandler);
            }
        });
    }

    @Override
    protected HBox makeEntryBox (Piece entry) {
        HBox hb = new HBox();
        Label name = new Label(entry.getName());
        name.setTranslateY(7.5);
        ImageView img = entry.getImageView();
        img.setFitHeight(40);
        img.setFitWidth(40);
        hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent e) {
                        GUIGrid grid = myGridReference.getGrid();
                        Point2D.Double coor = grid.findClickedCoordinate(e.getX(), e.getY());
                        grid.addPieceAtLoc(entry, coor);
                    }
                };
                myGridReference.getGrid().paneSetOnMousePressed(clickHandler);
                myGridReference.getGrid().paneSetOnMouseDragged(clickHandler);
            }
        });
        hb.getChildren().addAll(img, name);
        return hb;
    }

    @Override
    protected void initEntryEditBtn (Piece entry, Button editBtn) {
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                Consumer<Piece> okLambda = (Piece piece) -> {
                    HBox entryBox = makeCompleteEntryBox(piece);

                    HBox entryHolderBox = myEntryMap.get(entry);
                    entryHolderBox.getChildren().clear();
                    entryHolderBox.getChildren().add(entryBox);
                    myEntryMap.put(piece, entryHolderBox);

                    myPieceTypes.replace(entry, piece);
                };
                myPropertiesSPane.setContent(new PieceTypeEditor(okLambda, entry,
                                                                 myActionData, myGameProperties
                                                                         .getGridShape()));
            }
        });
    }

    @Override
    protected void initEntryDelBtn (Piece entry, Button delBtn) {
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myPieceTypes.remove(entry);
                myVBox.getChildren().remove(myEntryMap.get(entry));
            }
        });
    }
}
