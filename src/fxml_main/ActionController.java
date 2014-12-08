package fxml_main;

import gamedata.action.Action;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gameengine.player.Player;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.LevelData;
import authoring.data.PatchTypeData;
import authoring.data.PieceTypeData;


/**
 * 
 * @author annamiyajima
 *
 */
public class ActionController extends GridComponentAbstCtrl<Action> {
    private ScrollPane myGridSPane;
    private LevelData myLevelData;
    private PieceTypeData myPieceTypes;
    private PatchTypeData myPatchTypes;

    protected ActionController (VBox vbox, ScrollPane propertiesSPane,
                                ScrollPane gridSPane, GUIGridReference gridRef, LevelData levels,
                                PieceTypeData pieceTypes, PatchTypeData patchTypes) {
        super(vbox, propertiesSPane, gridRef);
        myGridSPane = gridSPane;
        myLevelData = levels;
        myPieceTypes = pieceTypes;
        myPatchTypes = patchTypes;
    }

    @Override
    protected void initGlobalNewBtn (Button newBtn) {
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                globalNewBtnOnClickAction();
            }
        });
    }

    private void globalNewBtnOnClickAction () {
        // TODO: Need to not hard-code square, have it passed through the constructor
        // as maybe a gridshapeproperty (new class?)
        Consumer<Level> okLambda = (Level level) -> {

            addEntry(level);
            myLevelData.add(level);
            myPieceTypes.addObserver(level.getGrid());
            myPatchTypes.addObserver(level.getGrid());
            setAndDisplayGrid(level);
        };

        List<Piece> piecesRO = Collections.unmodifiableList(myPieceTypes.getData());
        List<Patch> patchesRO = Collections.unmodifiableList(myPatchTypes.getData());
        List<Player> playersRO = null;

        EventsDataContainer wrapper = new EventsDataContainer(piecesRO, patchesRO, playersRO);
        super.myPropertiesSPane.setContent(new LevelEditor(okLambda, wrapper));
    }

    private void setAndDisplayGrid (Level level) {

        myGridReference.setGrid(level.getGrid());
        myGridReference.getGrid().displayPane(myGridSPane);
    }

    @Override
    protected void initGlobalEditBtn (Button editBtn) {
        // do nothing
    }

    @Override
    protected void initGlobalDelBtn (Button delBtn) {
        // do nothing
    }

    @Override
    protected void initEntryEditBtn (Level entry, Button editBtn) {

        // TODO: THIS ONLY SORT OF WORKS
        // WORKS WHEN YOU CLICK ON LHS PANE THEN RHS PANE THEN DONE.
        // ALSO LEVELS ARE SORTED IN ORDER OR MOST RECENTLY MODIFIED
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                Consumer<Level> okLambda = (Level level) -> {

                    myVBox.getChildren().remove(entry);
                    addEntry(level);

                    myPieceTypes.addObserver(level.getGrid());
                    myPatchTypes.addObserver(level.getGrid());
                    myLevelData.replace(entry, level);

                    HBox entryHolderBox = myEntryMap.get(entry);
                    entryHolderBox.getChildren().clear();

                    myLevelData.replace(entry, level);

                    myEntryMap.get(entry);

                    setAndDisplayGrid(level);

                };
                List<Piece> piecesRO = Collections.unmodifiableList(myPieceTypes.getData());
                List<Patch> patchesRO = Collections.unmodifiableList(myPatchTypes.getData());
                List<Player> playersRO = null;

                EventsDataContainer wrapper =
                        new EventsDataContainer(piecesRO, patchesRO, playersRO);

                myPropertiesSPane.setContent(new LevelEditor(okLambda, entry, wrapper));

            }

        });
    }

    @Override
    protected void initEntryDelBtn (Level entry, Button delBtn) {
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myLevelData.remove(entry);
                myVBox.getChildren().remove(myEntryMap.get(entry));
            }
        });
    }

    @Override
    protected HBox makeEntryBox (Action entry) {
        HBox entryBox = new HBox();
        Label nameLabel = new Label(entry.getId());
        entryBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                setAndDisplayGrid(entry);
            }
        });
        entryBox.getChildren().add(nameLabel);
        return entryBox;
    }

    @Override
    protected void initEntryEditBtn (Action entry, Button editBtn) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initEntryDelBtn (Action entry, Button delBtn) {
        // TODO Auto-generated method stub

    }
}
