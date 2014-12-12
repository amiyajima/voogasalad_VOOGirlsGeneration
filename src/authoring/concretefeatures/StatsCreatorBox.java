package authoring.concretefeatures;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Creates a new Hbox view that allows users to define and edit
 * stats for different types of pieces
 *
 * @author Jennie Ju
 *
 */
public class StatsCreatorBox extends HBox {
	private static final int STAT_BOX_WIDTH = 150;
	private static final int VALUE_BOX_WIDTH = 150;
	private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	
	private static final String STAT_PROMPT = "Enter stat name";
	private static final String VALUE_PROMPT = "Enter stat value (real number)";
	protected static final String DEFAULT_STAT = "";
	private static final double DEFAULT_VALUE = 0;

	private static final String EQUALS_LABEL = "=";

	private TextField myStatField;
	private TextField myValueField;

	/**
	 * Constructor for a new empty StatsCreatorBox
	 */
	public StatsCreatorBox() {
		initStatsCreatorBox();	
	}
	
	/**
	 * Constructor for a StatsCreatorBox to display
	 * already defined Stats
	 * @param name - String name of the Stat
	 * @param value - double value of the Stat
	 */
	public StatsCreatorBox(String name, double value) {
		initStatsCreatorBox();
		myStatField.setText(name);
		myValueField.setText("" + value);
	}

	/**
	 * Gets the string name of the Stat
	 * @return string name of the Stat
	 */
	public String getStatName() {
		// TODO: Throw error for empty name
		return myStatField.getText();
	}

	/**
	 * Gets the value of the Stat as a double
	 * @return double value of the Stat
	 */
	public double getStatValue() {
		// TODO: Throw error for not a double value
		if (myValueField.getText().trim().equals("")) {
			return DEFAULT_VALUE;
		}
		return Double.parseDouble(myValueField.getText());
	}
	
	public boolean isEmpty(){
		return myValueField.getText().isEmpty() || myStatField.getText().isEmpty();
	}

	private void initStatsCreatorBox() {
		getStylesheets().add(STYLESHEET);
		getStyleClass().add("hbox");
		Label equalsLabel = new Label(EQUALS_LABEL);
		myStatField = new TextField();
		myValueField = new TextField();
		initTextField(myStatField, STAT_BOX_WIDTH, STAT_PROMPT);
		initTextField(myValueField, VALUE_BOX_WIDTH, VALUE_PROMPT);
		getChildren().addAll(myStatField,equalsLabel,myValueField);
	}
	
	private void initTextField(TextField tf, int width, String prompt) {
		tf.setPrefWidth(width);
		tf.setPromptText(prompt);
	}



}
