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
import authoring.data.ActionData;
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
    private ActionData myActionData;

    protected ActionController (VBox vbox,
                                ScrollPane propertiesSPane,
                                GUIGridReference gridRef,
                                ActionData actions) {
        super(vbox, propertiesSPane, gridRef);
        myActionData = actions;
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
        Consumer<Action> okLambda = (Action action) -> {
            globalNewBtnOnClickAction();
        };

        super.myPropertiesSPane.setContent(new ActionEditor(okLambda));
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
    protected void initEntryEditBtn (Action entry, Button editBtn) {

        // TODO: THIS ONLY SORT OF WORKS
        // WORKS WHEN YOU CLICK ON LHS PANE THEN RHS PANE THEN DONE.
        // ALSO LEVELS ARE SORTED IN ORDER OR MOST RECENTLY MODIFIED
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent click) {
                Consumer<Action> okLambda = (Action action) -> {

                    myVBox.getChildren().remove(entry);

                    HBox entryHolderBox = myEntryMap.get(entry);
                    entryHolderBox.getChildren().clear();

                    myEntryMap.get(entry);

                };

                List<Player> playersRO = null;

                myPropertiesSPane.setContent(new ActionEditor(okLambda, entry));

            }

        });
    }

    @Override
    protected HBox makeEntryBox (Action entry) {
        HBox entryBox = new HBox();
        //Label nameLabel = new Label(entry.getId());
        entryBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                //setAndDisplayGrid(entry);
            }
        });
        //entryBox.getChildren().add(nameLabel);
        return entryBox;
    }

    @Override
    protected void initEntryDelBtn (Action entry, Button delBtn) {
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myVBox.getChildren().remove(myEntryMap.get(entry));
            }
        });
    }
}
