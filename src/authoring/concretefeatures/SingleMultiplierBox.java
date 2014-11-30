package authoring.concretefeatures;

import gamedata.action.StatsSingleMultiplier;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * GUI element used in ActionCreator. This allows a user to specify a single "operation"
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
	private TextField myScale;
	private TextField myStat;

	public SingleMultiplierBox(){
		getStylesheets().add(STYLESHEET);

		Label x = new Label("x");
		myStatRef = new ChoiceBox<String>();
		myStatRef.setPrefWidth(MULTIPLIER_BOX_WIDTH);
		myStatRef.getItems().addAll("actor", "receiver", "constant");

		myScale = new TextField();
		myStat = new TextField();

		myScale.setPrefWidth(MULTIPLIER_BOX_WIDTH);
		myStat.setPrefWidth(STATS_BOX_WIDTH);

		myScale.setPromptText("Multiplier");
		myStat.setPromptText("Reference stat or constant");

		getChildren().addAll(myScale, x, myStatRef, myStat);
	}

	/**
	 * Gets the StatsSingleMultiplier class from the view
	 * (connects this view to the data)
	 * @return StatsSingleMultiplier - explains how to modify
	 * the stat
	 */
	public StatsSingleMultiplier getSingleMultipler() {
		//TODO: error checking to make sure this is a double
		double multiplier = 0;
		if (!myScale.getText().equals("")) {
			multiplier = Double.parseDouble(myScale.getText());
		}
		String statRef = myStatRef.getValue();
		String stat = myStat.getText();
		return new StatsSingleMultiplier(multiplier, statRef, stat);
	}
}
