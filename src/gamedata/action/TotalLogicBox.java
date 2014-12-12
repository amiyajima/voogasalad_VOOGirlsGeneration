package gamedata.action;

import java.util.LinkedList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TotalLogicBox extends VBox {

	public static final int MULTIPLIER_BOX_WIDTH = 75;
	public static final int STATS_BOX_WIDTH = 120;
	private static final String STYLESHEET = "resources/stylesheets/singlemultiplierbox_layout.css";

	private ChoiceBox<String> myStatsRefChoice;
	private TextField myStatName;
	private VBox mySingleMultiplierVBox;
	private List<SingleMultiplierBox> mySingleMultiplierList;

	public TotalLogicBox(StatsTotalLogic stl) {
		getStylesheets().add(STYLESHEET);
		myStatsRefChoice = new ChoiceBox<String>();
		myStatsRefChoice.setPrefWidth(MULTIPLIER_BOX_WIDTH);

		myStatName = new TextField();
		myStatName.setPrefWidth(STATS_BOX_WIDTH);

		initStatsModifier(stl);
		// Operations
		mySingleMultiplierVBox = new VBox();
		initSingleMultiplierVBox(stl.getMultiplierLogic());
	}

	/**
	 * Returns the StatsTotalLogic associated with this TotalLogicBox
	 * as shown in the view
	 * @return StatsTotalLogic associated with this TotalLogicBox
	 */
	public StatsTotalLogic getStatsLogic () {
		String target = myStatsRefChoice.getValue();
		String stat = myStatName.getText();
		List<StatsSingleMultiplier> multiplierLogic = getSingleMultipliers(mySingleMultiplierList);
		return new StatsTotalLogic(target, stat, multiplierLogic);
	}

	/**
	 * Returns the List of StatsSingleMultipliers from the TotalLogicBox view
	 * @param smbList - List of SingleMultiplierBoxes representing the view of
	 * StatsSingleMultipliers
	 * @return List of StatsSingleMultipliers for use with Actions
	 */
	private List<StatsSingleMultiplier> getSingleMultipliers (List<SingleMultiplierBox> smbList) {
		List<StatsSingleMultiplier> ssmList = new LinkedList<StatsSingleMultiplier>();
		for (SingleMultiplierBox smb : smbList) {
			ssmList.add(smb.getSingleMultipler());
		}
		return ssmList;
	}

	/**
	 * Initializes the StatsTotalLogic associated with this TotalLogicBox
	 * @param stl
	 */
	private void initStatsModifier (StatsTotalLogic stl) {
		Label targetLabel = new Label("Action target");

		myStatsRefChoice.getItems().addAll("actor", "receiver");
		myStatsRefChoice.setValue(stl.getStatRef());
		// Particular stat modified
		myStatName.setText(stl.getStatName());
		myStatName.setPromptText("Stat to be modified");
		HBox targetAndStatHBox = new HBox();
		targetAndStatHBox.getChildren().addAll(myStatsRefChoice, myStatName);
		//        targetAndStatHBox.setSpacing(10);

		getChildren().addAll(targetLabel, targetAndStatHBox);
	}
	
	private void initSingleMultiplierVBox(List<StatsSingleMultiplier> ssmList) {
		//        Label operationsLabel = new Label("Operations to be performed");
		for (StatsSingleMultiplier ssm : ssmList) {
			addSingleMultiplierBox(ssm);
		}

		Button newMultiplierBtn = new Button("+");
		initNewButton(newMultiplierBtn);
		//        mySingleMultiplierVBox.setSpacing(10);
		mySingleMultiplierVBox.getChildren().addAll(newMultiplierBtn);

	}

	private void initNewButton(Button newBtn) {
		newBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				addSingleMultiplierBox(new StatsSingleMultiplier());
			}
		});
	}

	private void addSingleMultiplierBox(StatsSingleMultiplier ssm) {
		HBox deletableSsmHBox = new HBox();
		SingleMultiplierBox ssmBox = new SingleMultiplierBox(ssm);
		Button delSsmBtn = makeDeleteButton(deletableSsmHBox, ssmBox);
		deletableSsmHBox.getChildren().addAll(ssmBox,delSsmBtn);

		mySingleMultiplierList.add(ssmBox);
		mySingleMultiplierVBox.getChildren().add(ssmBox);
	}
	
	private Button makeDeleteButton(HBox deletableSsmHBox, SingleMultiplierBox ssmBox) {
		Button delBtn = new Button("-");
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				mySingleMultiplierList.remove(ssmBox);
				mySingleMultiplierVBox.getChildren().remove(deletableSsmHBox);
			}
		});
		return delBtn;
	}
}
