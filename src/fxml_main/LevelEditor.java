package fxml_main;

import gamedata.events.Event;
import gamedata.events.globalaction.GameStateGlobalAction;
import gamedata.events.globalaction.GlobalAction;
import gamedata.gamecomponents.Level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import authoring.data.EventsDataWrapper;
import authoring.data.PlayerData;
import authoring.eventeditor.EventEditorController;
import authoring_environment.GUIGrid;


public class LevelEditor extends VBox {
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";

    private static final String CREATOR_TITLE = "Level Creator";
    private static final String EDITOR_TITLE = "Level Editor";
    private static final String LABEL_CSS = "-fx-font-size: 14pt;";

    private String myId;
    private int myGridRows;
    private int myGridCols;
    private double myTileHeight;
    private String myGridShape;
    private List<Event> myEvents;
    private GUIGrid myGrid;

    private Consumer<Level> myOkLambda;

    private Level myLevel;
    private String myEditorTitle;

	private EventsDataWrapper myData;
	private PlayerData myPlayerData;

    public LevelEditor (Consumer<Level> okLambda, EventsDataWrapper data, String gridShape, PlayerData playerData) {
        myPlayerData = playerData;
        myEditorTitle = CREATOR_TITLE;
    	myId = "";
        myGridRows = 0;
        myGridCols = 0;
        myTileHeight = 0;
        myGrid = new GUIGrid(myGridCols, myGridRows, myTileHeight, gridShape);
        myEvents = new ArrayList<>();
        myLevel = new Level();
        initEditor(okLambda,data,gridShape);
    }

    public LevelEditor (Consumer<Level> okLambda, EventsDataWrapper data,
    		String gridShape,  Level level) {
        myEditorTitle = EDITOR_TITLE;
        myGrid = level.getGrid();
        myId = level.getId();
        myGridRows = myGrid.getNumRows();
        myGridCols = myGrid.getNumCols();
        myTileHeight = myGrid.getTileHeight();
        myEvents = level.getEvents();
        myLevel = level;
        initEditor(okLambda,data,gridShape);
    }

    public void initEditor (Consumer<Level> okLambda, EventsDataWrapper data, String gridShape) {
        myOkLambda = okLambda;
        myData = data;
        myGridShape = gridShape;
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

        HBox idHBox = new HBox();
        Label idLabel = new Label("ID: ");
        TextField idField = new TextField(myId);
        idHBox.getChildren().addAll(idLabel, idField);

        VBox gridSizeVBox = new VBox();
        Label gridSizeLabel = new Label("Grid Size: ");

        VBox rowVBox = new VBox();
        Label rowLabel = new Label("Rows");
        TextField rowField = new TextField("" + myGridRows);
        rowField.setMaxWidth(80);
        rowVBox.getChildren().addAll(rowLabel, rowField);
        VBox colVBox = new VBox();
        Label colLabel = new Label("Columns");
        TextField colField = new TextField("" + myGridCols);
        colField.setMaxWidth(80);
        colVBox.getChildren().addAll(colLabel, colField);

        HBox gridSizeHBox = new HBox();
        gridSizeHBox.getChildren().addAll(rowVBox, colVBox);
        gridSizeVBox.getChildren().addAll(gridSizeLabel, gridSizeHBox);

        HBox tileHeightHBox = new HBox();
        Label heightLabel = new Label("Tile Height: ");
        TextField heightField = new TextField("" + myTileHeight);
        tileHeightHBox.getChildren().addAll(heightLabel, heightField);

        Button eventBtn = new Button("Add Global Events...");
        eventBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle (ActionEvent click) {
                try {
                    showEventsEditorWindow();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        Button okBtn = new Button("OK");
        Button cancelBtn = new Button("Cancel");
        HBox finalizeBtnsHBox = new HBox();
        finalizeBtnsHBox.getChildren().addAll(okBtn, cancelBtn);

        okBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle (ActionEvent event) {
                myId = idField.getText();
                myGridRows = Integer.parseInt(rowField.getText());
                myGridCols = Integer.parseInt(colField.getText());
                myTileHeight = Double.parseDouble(heightField.getText());
                GUIGrid grid = new GUIGrid(myGridCols, myGridRows, myTileHeight, myGridShape,
                		myGrid);
//                System.out.println(myGridShape);
                Level level = new Level(grid, myEvents, myId, false);
                
                myOkLambda.accept(level);
            }
        });

        getChildren().addAll(labelBox, idHBox, new Separator(), gridSizeVBox,
                             new Separator(), tileHeightHBox, new Separator(),
                             eventBtn, finalizeBtnsHBox);

    }

    private void showEventsEditorWindow () throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/authoring/eventeditor/EventEditor.fxml"));
        Parent root = loader.load();

        Stage eventEditorStage = new Stage();
        eventEditorStage.setTitle("Events Editor");
        eventEditorStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        eventEditorStage.setScene(scene);

        EventEditorController controller = loader.getController();
        controller.loadEvents(myEvents);
        controller.loadData(myData);
        //controller.loadPlayerData(myPlayerData);

        eventEditorStage.showAndWait();
    }

}
