package gamedata.action;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ActionStatsEditor extends VBox {
	private List<TotalLogicBox> myTotalLogicList;
	
	/**
	 * Constructor for an ActionStatsEditor.
	 * Populates the editor based on an existing list of
	 * StatsTotalLogics
	 * @param stlList
	 */
	public ActionStatsEditor(List<StatsTotalLogic> stlList) {
		Button newOpBtn = makeNewOpButton();
		for (StatsTotalLogic stl : stlList) {
			addTotalLogicBox(stl);
		}
		
		getChildren().addAll(newOpBtn);
	}

	private Button makeNewOpButton() {
		Button newBtn = new Button("Add new operation");
		newBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				getChildren().remove(newBtn);
				addTotalLogicBox(new StatsTotalLogic());
				getChildren().add(newBtn);
			}
		});
		return newBtn;
	}

	private void addTotalLogicBox(StatsTotalLogic stl) {
		TotalLogicBox stlBox = new TotalLogicBox(stl);
		myTotalLogicList.add(stlBox);
		getChildren().add(stlBox);
	}
	
	/**
	 * Returns all the StatsTotalLogics that are currently viewed
	 * as TotalLogicBoxes in thie ActionStatsEditor
	 * @return List of StatsTotalLogic
	 */
	public List<StatsTotalLogic> getStatsTotalLogics() {
		List<StatsTotalLogic> stlList = new ArrayList<StatsTotalLogic>();
		for (TotalLogicBox stlBox : myTotalLogicList) {
			stlList.add(stlBox.getStatsLogic());
		}
		return stlList;
	}
	
}
