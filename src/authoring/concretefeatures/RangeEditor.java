package authoring.concretefeatures;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.RangeGridView;


/**
 * Popup GUI element that allows the user to specify the size of the grid,
 * selects the tiles and returns the list of relative coordination of selected
 * tiles.
 * 
 * @author Meng'en Huang, Jesse Ling, Jennie Ju
 *
 */

public class RangeEditor extends PopupWindow {
	private static final String CUSTOM = "Custom(default)";

	private static final String RADIUS = "Radius";

	private static final String ALL = "All";

	private static final String ROW = "Row";

	private static final String COLUMN = "Column";

	private static final String NAME = "Range Editor";

	private static final int MIN_TILE_SIZE = 30;
	private static final int RANGE_EDITOR_HEIGHT = 800;
	private static final int RANGE_EDITOR_WIDTH = 600;
	private static final int DEFAULT_TILE_SIZE = 40;

	private int myGridLength = RANGE_EDITOR_WIDTH - 100;
	private int myTileSize = DEFAULT_TILE_SIZE;
	private static final String STYLESHEET = "/resources/stylesheets/actioncreator_layout.css";

	private static final String DEFAULT_SHAPE = "SQUARE_GRID";

	private int myGridWidthNumber;
	private int myGridHeightNumber;
	private RangeGridView rangeGridView;
	private List<Point2D.Double> myRange;
	private Button mySelect;
	private Consumer<List<Point2D.Double>> myConsumer;


	
	public RangeEditor (List<Point2D.Double> range, 
			Consumer<List<Point2D.Double>> consumer, String shape) {
		// range.add(new Point2D.Double(1,0));
		// range.add(new Point2D.Double(-1,2));

		myRange = range;
		myConsumer = consumer;
		setHeight(RANGE_EDITOR_HEIGHT);
		setWidth(RANGE_EDITOR_WIDTH);
		setTitle(NAME);

		rangeGridView = new RangeGridView(myGridLength, myGridLength,
				myTileSize, shape, range);
		initialize();
	}


	@Override
	protected void initialize () {
		VBox box = new VBox();
		Scene scene = new Scene(box, RANGE_EDITOR_WIDTH, RANGE_EDITOR_HEIGHT);
		scene.getStylesheets().add(STYLESHEET);

		HBox specifedSelection = new HBox();
		VBox selection = new VBox();
		selection.setMinHeight(50);
		HBox sizeChooser = new HBox();
		sizeChooser.setMinHeight(50);

		// Range Selections

		Label targetLabel = new Label("Select Range");
		ChoiceBox<String> targetChoice = new ChoiceBox<>();
		targetChoice.getItems().addAll(COLUMN, ROW, RADIUS, ALL,
				CUSTOM);
		TextField specifiedData = new TextField();
		Button choose = new Button("Choose");
		Button delete = new Button("Delete");
		choose.setOnAction(new selectRangeHandler(targetChoice, specifiedData, choose));
		delete.setOnAction(new selectRangeHandler(targetChoice, specifiedData, delete));

		selection.getChildren().addAll(targetLabel, targetChoice);

		specifedSelection.getChildren().addAll(selection, specifiedData, choose, delete);

		// Select Button
		mySelect = new Button("Select");
		mySelect.setOnAction(new SelectHandler(this));
//		mySelect.setOnAction(new EventHandler<ActionEvent>(){
//
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				myRange = rangeGridView.returnSelectedList();
//				myConsumer.accept(myRange);
//				//				this.close();
//			}
//
//		});

		// Choose the Size
		VBox horizontal = new VBox();
		VBox times = new VBox();
		VBox vertical = new VBox();
		Label HRadiusLabel = new Label("Horizontal Radius");
		Label multiply = new Label("    X     ");
		Label VRadiusLabel = new Label("Vertical Radius");
		TextField HRadius = new TextField();
		TextField VRadius = new TextField();
		HRadius.setMaxWidth(120);
		VRadius.setMaxWidth(120);
		VRadius.setLayoutX(100);

		horizontal.getChildren().addAll(HRadiusLabel, HRadius);
		times.getChildren().add(multiply);
		vertical.getChildren().addAll(VRadiusLabel, VRadius);

		Button enter = new Button("Enter");
		enter.setLayoutX(300);
		enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myGridWidthNumber = Integer.parseInt(HRadius.getText()) * 2 + 1;
				myGridHeightNumber = Integer.parseInt(VRadius.getText()) * 2 + 1;
				myTileSize = getPrefTileSize(myGridWidthNumber, myGridHeightNumber);
				box.getChildren().clear();
//				List<Point2D.Double> newRange = rangeGridView.returnSelectedList();
//				myRange.clear();
//				myRange.addAll(newRange);
				myRange=rangeGridView.returnSelectedList();

				rangeGridView.update(myGridWidthNumber, myGridHeightNumber,
						myRange);
				box.getChildren().addAll(sizeChooser, enter,
						rangeGridView, specifedSelection, mySelect);
			}
		});



		sizeChooser.getChildren().addAll(horizontal, times, vertical);

		box.getChildren().addAll(sizeChooser, enter, rangeGridView,mySelect);
		setScene(scene);
	}

	private int getPrefTileSize (int gridWidthNumber, int gridHeightNumber) {
		int calculatedTileSize = Math.max(myGridLength
				/ gridWidthNumber, myGridLength / gridHeightNumber);

		int tileSize = (calculatedTileSize < MIN_TILE_SIZE) ? MIN_TILE_SIZE
				: calculatedTileSize;
		return tileSize;
	}

	private class selectRangeHandler implements EventHandler<ActionEvent> {
		ChoiceBox<String> targetChoice;
		TextField specifiedData;
		Button button;

		public selectRangeHandler (ChoiceBox<String> tc, TextField sd, Button b) {
			targetChoice = tc;
			specifiedData = sd;
			button = b;
		}

		@Override
		public void handle (ActionEvent event) {
			String chosen = targetChoice.getValue().toString();
			int parameter;
			try {
				parameter = Integer.parseInt(specifiedData.getText());
			}
			catch (NumberFormatException e) {
				parameter = 0;
			}

			boolean toChoose = (button.getText().equals("Choose")) ? true : false;
			switch (chosen) {
			case COLUMN:
				rangeGridView.getGrid().rangeColumn(parameter, toChoose);
				break;
			case ROW:
				rangeGridView.getGrid().rangeRow(parameter, toChoose);
				break;
			case RADIUS:
				rangeGridView.getGrid().rangeRadius(parameter, toChoose);
				break;
			case ALL:
				rangeGridView.getGrid().rangeAll(toChoose);
				break;
				// case CUSTOM:
				// rangeGridView.getGrid().rangeSelectedList();
				// break;
				// default:
				// mySampleGridView.rangeCenterColumn();
			}
		}

	}

		/**
		 * Event Handler that Sends the Selected and then Closes the Popup
		 */
		private class SelectHandler implements EventHandler<ActionEvent> {
			RangeEditor current;
	
			public SelectHandler (RangeEditor re) {
				current = re;
			}
	
			@Override
			public void handle (ActionEvent event) {
				myRange = rangeGridView.returnSelectedList();
				myConsumer.accept(myRange);
//				for (Point2D.Double point:myRange){
//					System.out.println("dfas");
//					System.out.println(point.getX()+","+point.getY());
//				}
	
				current.close();
			}
		}

	// Can't use this function to create all the vbox containing one label and
	// a textfiled because it makes impossible to get the content of the textfield.
	// Should think of other way to do similar things to avoid duplicated code.
	private VBox makeTextField (String label) {
		VBox vbox = new VBox();
		Label labelText = new Label(label);
		TextField textField = new TextField();
		vbox.getChildren().addAll(labelText, textField);
		return vbox;
	}


}
