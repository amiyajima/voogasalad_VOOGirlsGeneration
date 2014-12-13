package gamedata.action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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
		myTotalLogicList = new LinkedList<TotalLogicBox>();
		Button newOpBtn = makeNewOpButton();
		getChildren().addAll(newOpBtn);
		for (StatsTotalLogic stl : stlList) {
			addTotalLogicBox(stl);
		}
	}

	private Button makeNewOpButton() {
		Button newBtn = new Button("Add new operation");
		newBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				addTotalLogicBox(new StatsTotalLogic());
			}
		});
		return newBtn;
	}

	private void addTotalLogicBox(StatsTotalLogic stl) {
		HBox deletableHBox = new HBox();
		TotalLogicBox stlBox = new TotalLogicBox(stl);
		Button delBtn = makeDeleteButton(deletableHBox,stlBox);
		myTotalLogicList.add(stlBox);
		deletableHBox.getChildren().addAll(delBtn, stlBox);
		getChildren().add(deletableHBox);
	}
	
	private Button makeDeleteButton(HBox deletableHBox, TotalLogicBox stlBox) {
		Button delBtn = new Button("-");
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myTotalLogicList.remove(stlBox);
				getChildren().remove(deletableHBox);
			}
		});
		return delBtn;
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
