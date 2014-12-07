package fxml_main;

import gamedata.events.Event;
import gamedata.gamecomponents.Level;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import authoring.eventeditor.EventEditorController;
import authoring_environment.GUIGrid;

public class LevelEditor extends VBox { 
	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	
	private String myId;
	private int myGridRows;
	private int myGridCols;
	private double myTileHeight;
	private ObservableList<Event> myEvents;
	private Level myLevel;
	
	private Consumer<Level> myOkLambda;

	public LevelEditor(Consumer<Level> okLambda) {
		myId = "";
		myGridRows = 0;
		myGridCols = 0;
		myTileHeight = 0;
		myEvents = FXCollections.observableArrayList();
		initEditor(okLambda);
	}
	
	
	public LevelEditor(Consumer<Level> okLambda, Level level) {
		GUIGrid grid = level.getGrid();
		myId = level.getId();
		myGridRows = grid.getRow();
		myGridCols = grid.getCol();
		myTileHeight = grid.getTileSize();
		myLevel = level;
		myEvents = (ObservableList<Event>) level.getEvents(); 
		
		initEditor(okLambda);
	}
	
	public void initEditor (Consumer<Level> okLambda) {
		myOkLambda = okLambda;
		initialize();
	}

	private void initialize() {
		this.getStylesheets().add(STYLESHEET);
		this.getStyleClass().addAll("vbox");
		this.setId("vbox-main");

		HBox idHBox = new HBox();
		Label idLabel = new Label("ID: ");
		TextField idField = new TextField(myId);
		idHBox.getChildren().addAll(idLabel, idField);

		VBox gridSizeVBox = new VBox();
		Label gridSizeLabel = new Label("Grid Size: ");

		VBox rowVBox = new VBox();
		Label rowLabel = new Label("Rows");
		TextField rowField = new TextField(""+myGridRows);
		rowVBox.getChildren().addAll(rowLabel,rowField);
		VBox colVBox = new VBox();
		Label colLabel = new Label("Columns");
		TextField colField = new TextField(""+myGridCols);
		colVBox.getChildren().addAll(colLabel,colField);

		HBox gridSizeHBox = new HBox();
		gridSizeHBox.getChildren().addAll(rowVBox, colVBox);
		gridSizeVBox.getChildren().addAll(gridSizeLabel,gridSizeHBox);
		
		HBox tileHeightHBox = new HBox();
		Label heightLabel = new Label("Tile Height: ");
		TextField heightField = new TextField(""+myTileHeight);
		tileHeightHBox.getChildren().addAll(heightLabel, heightField);

		Button eventBtn = new Button("Add Global Events...");
		eventBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent click) {
				try {
					showEventsEditorWindow();
				} catch (IOException e) {
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
			public void handle(ActionEvent event) {
				myId = idField.getText();
				myGridRows = Integer.parseInt(rowField.getText());
				myGridCols = Integer.parseInt(colField.getText());
				myTileHeight = Double.parseDouble(heightField.getText());
				
				GUIGrid grid = new GUIGrid(myGridCols, myGridRows, myTileHeight,"Square Grid");
				Level level = new Level(grid, myEvents, myId, false);
								
				myOkLambda.accept(level);
			}
		});


		getChildren().addAll(idHBox, new Separator(), gridSizeVBox,
				new Separator(), tileHeightHBox, new Separator(),
				eventBtn, finalizeBtnsHBox);


	}
	
	private void showEventsEditorWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/authoring/eventeditor/EventEditor.fxml"));
		Parent root = loader.load();

		Stage eventEditorStage  = new Stage();
		eventEditorStage.setTitle("Events Editor");
		eventEditorStage.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(root);
		eventEditorStage.setScene(scene);

		EventEditorController controller = loader.getController();
		controller.loadEvents(myEvents);
		
		eventEditorStage.showAndWait();
	}

}
