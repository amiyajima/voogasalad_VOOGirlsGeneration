package fxml_main;

import gamedata.action.Action;
import gamedata.action.ConcreteAction;
import gamedata.events.Event;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.concretefeatures.SingleMultiplierBox;
import authoring_environment.GUIGrid;
import authoring_environment.UIspecs;


public class ActionEditor extends VBox {
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";

    private static final String CREATOR_TITLE = "Action Creator";
    private static final String EDITOR_TITLE = "Action Editor";
    private static final String LABEL_CSS = "-fx-font-size: 14pt;";

    private static final String ATTACK_RANGE_LABEL = "Set attack range";

    private static final String EFFECT_RANGE_LABEL = "Set effect range";

    private static final String STATS_OPERATION_LABEL = "Define operation";

    private String myId;
    private int myGridRows;
    private int myGridCols;
    private double myTileHeight;
    private ObservableList<Event> myEvents;
    private GUIGrid myGrid;
    private Action myCurrentAction;

    private Consumer<Action> myOkLambda;

    private String myEditorTitle;

    public ActionEditor (Consumer<Action> okLambda) {
        myEditorTitle = CREATOR_TITLE;
        myId = "";
        myGridRows = 0;
        myGridCols = 0;
        myTileHeight = 0;
        myEvents = FXCollections.observableArrayList();
        initEditor(okLambda);
    }

    public ActionEditor (Consumer<Action> okLambda, Action entry) {
        this(okLambda);
        myCurrentAction = entry;
    }

    private void initEditor (Consumer<Action> okLambda) {
        myOkLambda = okLambda;
        initialize();
    }

    private void initialize () {
        this.getStylesheets().add(STYLESHEET);
        this.getStyleClass().addAll("vbox");
        this.setId("vbox-main");

        HBox labelBox = new HBox();
        Label eventsLabel = new Label(myEditorTitle);
        eventsLabel.setStyle(LABEL_CSS);
        labelBox.getChildren().add(eventsLabel);

        HBox nameBox = new HBox();
        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField(myId);
        nameBox.getChildren().addAll(nameLabel, nameField);

        HBox attackRangeBox = new HBox();
        attackRangeBox.setPadding(UIspecs.allPadding);
        attackRangeBox.setSpacing(5);
        Button attackRange = new Button(ATTACK_RANGE_LABEL);
        attackRangeBox.getChildren().addAll(attackRange);

        HBox effectRangeBox = new HBox();
        effectRangeBox.setPadding(UIspecs.allPadding);
        effectRangeBox.setSpacing(5);
        Button effectRange = new Button(EFFECT_RANGE_LABEL);
        effectRangeBox.getChildren().addAll(effectRange);

        HBox statsOperationBox = new HBox();
        statsOperationBox.setPadding(UIspecs.allPadding);
        statsOperationBox.setSpacing(5);
        Button statsOperation = new Button(STATS_OPERATION_LABEL);
        statsOperationBox.getChildren().addAll(statsOperation);

        Button okBtn = new Button("OK");
        HBox finalizeBtnsHBox = new HBox();
        finalizeBtnsHBox.getChildren().addAll(okBtn);

        /**
         * What happens when OK button is pressed
         */
        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myId = nameField.getText();

                Action action = new ConcreteAction(myEditorTitle, null, null, null, null);
                myOkLambda.accept(action);
            }
        });

        getChildren().addAll(labelBox, nameBox, attackRangeBox, effectRangeBox,
                             statsOperationBox,
                             finalizeBtnsHBox);

    }

}
