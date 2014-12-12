package gamedata.action;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * GUI element used in ActionEditor. This allows a user to specify a single "operation"
 * on stats. In a stats-modifying action, each operation adds to the target stat the value
 * from a reference, scaled by a positive or negative number. This GUI element allows the
 * user to specify the scalar and reference stat (or constant).
 * 
 * @author Mike Zhu, Jennie Ju
 *
 */
public class SingleMultiplierBox extends HBox{

	public static final int MULTIPLIER_BOX_WIDTH = 75;
	public static final int STATS_BOX_WIDTH = 120;
	private static final String STYLESHEET = "resources/stylesheets/singlemultiplierbox_layout.css";

	private ChoiceBox<String> myStatRef;
	private TextField myMultiplier;
	private TextField myStatName;

	/**
	 * Creates a new empty SingleMultiplierBox
	 */
	public SingleMultiplierBox(){
		initSingleMultiplierBox();
	}
	
	/**
	 * Creates a SingleMultiplierBox from an existing StatsSingleMultiplier
	 */
	public SingleMultiplierBox(StatsSingleMultiplier ssm) {
		initSingleMultiplierBox();
		setSingleMultiplier(ssm);
	}
	
	private void initSingleMultiplierBox() {
		getStylesheets().add(STYLESHEET);

		Label x = new Label("x");
		myStatRef = new ChoiceBox<String>();
		myStatRef.setPrefWidth(MULTIPLIER_BOX_WIDTH);
		myStatRef.getItems().addAll("actor", "receiver", "constant");

		myMultiplier = new TextField();
		myStatName = new TextField();

		myMultiplier.setPrefWidth(MULTIPLIER_BOX_WIDTH);
		myStatName.setPrefWidth(STATS_BOX_WIDTH);

		myMultiplier.setPromptText("Multiplier");
		myStatName.setPromptText("Reference stat or constant");

		getChildren().addAll(myMultiplier, x, myStatRef, myStatName);
	}

	/**
	 * Gets the StatsSingleMultiplier associated with this SingleMultiplierBox
	 * (connects this view to the data)
	 * @return StatsSingleMultiplier - explains how to modify
	 * the stat
	 */
	public StatsSingleMultiplier getSingleMultipler() {
		//TODO: error checking to make sure this is a double
		double multiplier = 0;
		if (!myMultiplier.getText().equals("")) {
			multiplier = Double.parseDouble(myMultiplier.getText());
		}
		String statRef = myStatRef.getValue();
		String stat = myStatName.getText();
		return new StatsSingleMultiplier(multiplier, statRef, stat);
	}
	
	
	/**
	 * Takes in a StatsSingleMultipler and repopulates the textfields
	 * in view
	 * @param ssm - StatsSingleMultiplier to view
	 */
	private void setSingleMultiplier(StatsSingleMultiplier ssm) {
		myMultiplier.setText(""+ssm.getMultiplier());
		myStatRef.setValue(ssm.getStatRef());
		myStatName.setText(ssm.getStatName());
	}
}
