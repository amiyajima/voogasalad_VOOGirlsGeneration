// This entire file is part of my masterpiece.
// Sandy Lee

package fxml_main;

import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D.Double;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.ActionData;
import authoring.data.GamePropertiesData;
import authoring.data.PieceTypeData;


/**
 * Controller which allows the user to create/edit/delete patches in the
 * authoring environment.
 * 
 * @author Sandy Lee, Martin Tamayo
 */
public class PieceController extends GridComponentAbstCtrl<Piece> {

    private PieceTypeData myPieceTypes;
    private ActionData myActionData;
    private GamePropertiesData myGameProperties;

    /**
     * Instantiates a PieceController with PieceTypeData, ActionData and GameProperties
     */
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
    protected void initEntryDelBtn (Piece entry, Button delBtn) {
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myPieceTypes.remove(entry);
                myVBox.getChildren().remove(myEntryMap.get(entry));
            }
        });
    }

    @Override
    protected HBox initEntryBox (Piece entry) {
        return setBoxLayout(entry.getName(), entry.getImageView());
    }

    @Override
    protected void placeEntry (Piece entry, Double coor) {
        myGridReference.getGrid().addPieceAtLoc(entry, coor);

    }

    @Override
    protected void globalEditPropertiesPane (Double coor) {
        Piece piece =  myGridReference.getGrid().getPiece(coor);
        myPropertiesSPane.setContent(new PieceEditor(piece,
                                                   myGameProperties
                                                           .getNumPlayers()));        
    }

    @Override
    protected void removeEntry (Double coor) {
        myGridReference.getGrid().removePieceAtCoordinate(coor);
    }

    @Override
    protected void replaceEntry (Piece orig, Piece newP) {
        myPieceTypes.replace(orig, newP);   
    }

    @Override
    protected void editPropertiesPane (Consumer<Piece> okLambda, Piece entry) {
        myPropertiesSPane.setContent(new PieceTypeEditor(okLambda, entry,
                                                         myActionData, myGameProperties
                                                                 .getGridShape()));    
    }
}
