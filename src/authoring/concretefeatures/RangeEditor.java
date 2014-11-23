package authoring.concretefeatures;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;

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

	private static final String COLUMN_ROW_CROSS = "Column & Row Cross";

	private static final String ROW = "Row";

	private static final String COLUMN = "Column";

	private static final String NAME = "Range Editor";

	private static final int MIN_TILE_SIZE = 30;
	private static final int RANGE_EDITOR_HEIGHT = 800;
	private static final int RANGE_EDITOR_WIDTH = 600;
	private static final int DEFAULT_TILE_SIZE = 40;
	
	private int myGridLength = RANGE_EDITOR_WIDTH - 100;
	private int myTileSize = DEFAULT_TILE_SIZE;
	private int myGridWidthNumber;
	private int myGridHeightNumber;
	private RangeGrid mySampleGridView;


	public RangeEditor(List<Point2D> range) {
		setHeight(RANGE_EDITOR_HEIGHT);
		setWidth(RANGE_EDITOR_WIDTH);
		setTitle(NAME);
		mySampleGridView = new RangeGrid(myGridLength, myGridLength,
				myTileSize);
		mySampleGridView.setRange(range);
		initialize();
	}

	@Override
	protected void initialize() {
		VBox box = new VBox();
//		HBox specifedSelection=new HBox();
		VBox selection = new VBox();
		selection.setMinHeight(50);
		HBox sizeChooser = new HBox();
		sizeChooser.setMinHeight(50);

		// Range Selections
		Label targetLabel = new Label("Select Range");
		ComboBox<String> targetChoice = new ComboBox<>();
		targetChoice.getItems().addAll(COLUMN, ROW, RADIUS,
				CUSTOM);
		targetChoice.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						
						switch (newValue) {
						case COLUMN:
							mySampleGridView.rangeCenterColumn();
							break;
						case ROW:
							mySampleGridView.rangeCenterRow();
							break;
						case RADIUS:
							mySampleGridView.rangeRadius(1);
							break;
						case CUSTOM:
							mySampleGridView.rangeSelectedList();
							break;
//						default:
//							mySampleGridView.rangeCenterColumn();
						}
						
					}

				});
		
		selection.getChildren().addAll(targetLabel, targetChoice);

		// Select Button
		Button select = new Button("Select");

		// Choose the Size
		VBox horizontal = new VBox();
		VBox times = new VBox();
		VBox vertical = new VBox();
		Label HRadiusLabel = new Label("Horizontal Radius");
		Label multiply = new Label("    X      ");
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
			public void handle(ActionEvent event) {
				myGridWidthNumber = Integer.parseInt(HRadius.getText()) * 2 + 1;
				myGridHeightNumber = Integer.parseInt(VRadius.getText()) * 2 + 1;
				int calculatedTileSize = Math.max(myGridLength
						/ myGridWidthNumber, myGridLength / myGridHeightNumber);

				myTileSize = (calculatedTileSize < MIN_TILE_SIZE) ? MIN_TILE_SIZE
						: calculatedTileSize;

				box.getChildren().clear();
//				mySampleGridView = new RangeGrid(myGridLength, myGridLength,
//						myTileSize);
				mySampleGridView.update(myGridWidthNumber, myGridHeightNumber,
						myTileSize);
				box.getChildren().addAll(sizeChooser, enter,
						mySampleGridView, selection,select);
			}
		});

		select.setOnAction(new SelectHandler(this));

		sizeChooser.getChildren().addAll(horizontal, times, vertical);

		box.getChildren().addAll(sizeChooser, enter);
		setScene(new Scene(box));
	}

	/**
	 * Event Handler that Sends the Selected and then Closes the Popup
	 */
	private class SelectHandler implements EventHandler<ActionEvent> {
		RangeEditor current;

		public SelectHandler(RangeEditor re) {
			current = re;
		}

		@Override
		public void handle(ActionEvent event) {
			List<Point2D> range = mySampleGridView.getRange();
//			myRange = range;
			range.addAll(mySampleGridView.rangeSelectedList());

			for (Point2D p:range){
				System.out.println(p.getX()+","+p.getY());
			}

			current.close();
		}
	}
	
	//Can't use this function to create all the vbox containing one label and
	// a textfiled because it makes impossible to get the content of the textfield.
	//Should think of other way to do similar things to avoid duplicated code.
	private VBox makeTextField(String label){
		VBox vbox=new VBox();
		Label labelText = new Label(label);
		TextField textField = new TextField();
		vbox.getChildren().addAll(labelText,textField);
		return vbox;
	}

}
