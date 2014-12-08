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


public class ActionEditor extends VBox {
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";

    private static final String CREATOR_TITLE = "Action Creator";
    private static final String EDITOR_TITLE = "Action Editor";
    private static final String LABEL_CSS = "-fx-font-size: 14pt;";

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

        HBox statsBox = new SingleMultiplierBox();

        VBox gridSizeVBox = new VBox();
        Label gridSizeLabel = new Label("Grid Size: ");

        VBox rowVBox = new VBox();
        Label rowLabel = new Label("Rows");
        TextField rowField = new TextField("" + myGridRows);
        rowVBox.getChildren().addAll(rowLabel, rowField);
        VBox colVBox = new VBox();
        Label colLabel = new Label("Columns");
        TextField colField = new TextField("" + myGridCols);
        colVBox.getChildren().addAll(colLabel, colField);

        HBox gridSizeHBox = new HBox();
        gridSizeHBox.getChildren().addAll(rowVBox, colVBox);
        gridSizeVBox.getChildren().addAll(gridSizeLabel, gridSizeHBox);

        HBox tileHeightHBox = new HBox();
        Label heightLabel = new Label("Tile Height: ");
        TextField heightField = new TextField("" + myTileHeight);
        tileHeightHBox.getChildren().addAll(heightLabel, heightField);

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
                myGridRows = Integer.parseInt(rowField.getText());
                myGridCols = Integer.parseInt(colField.getText());
                myTileHeight = Double.parseDouble(heightField.getText());
                myGrid = new GUIGrid(myGridCols, myGridRows, myTileHeight, "Square Grid");

                Action action = new ConcreteAction(myEditorTitle, null, null, null, null);
                myOkLambda.accept(action);
            }
        });

        getChildren().addAll(labelBox, nameBox, new Separator(), statsBox, gridSizeVBox,
                             new Separator(), tileHeightHBox, new Separator(),
                             finalizeBtnsHBox);

    }

}
