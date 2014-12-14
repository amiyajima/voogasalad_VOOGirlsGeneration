package fxml_main;

import gamedata.action.ConcreteAction;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.ActionData;


/**
 * @author annamiyajima
 *
 */
public class ActionController extends GridComponentAbstCtrl<ConcreteAction> {
	private ActionData myActionData;
	private String myGridShape;

	protected ActionController (VBox vbox, ScrollPane propertiesSPane,
			GUIGridReference gridRef, ActionData actionData, String gridShape) {
		super(vbox, propertiesSPane, gridRef);
		myActionData = actionData;
		myGridShape = gridShape;
	}

	@Override
	protected void initGlobalNewBtn (Button newBtn) {
		newBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				Consumer<ConcreteAction> okLambda = (ConcreteAction action) -> {
					myActionData.add(action);
					addEntry(action);
				};
				myPropertiesSPane.setContent(new ActionEditor(okLambda, myGridShape));
			}
		});
	}

	@Override
	protected void initGlobalEditBtn (Button editBtn) {
		editBtn.setVisible(false);
		// do nothing
	}


	@Override
	protected void initGlobalDelBtn (Button delBtn) {
		delBtn.setVisible(false);
		// do nothing
	}

	@Override
	protected void initEntryEditBtn (ConcreteAction entry, Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				Consumer<ConcreteAction> okLambda = (ConcreteAction action) -> {
					HBox entryBox = makeCompleteEntryBox(action);
					HBox entryHolderBox = myEntryMap.get(entry);
					entryHolderBox.getChildren().clear();
					entryHolderBox.getChildren().add(entryBox);
					myEntryMap.put(action, entryHolderBox);
					myActionData.replace(entry, action);
				};
				myPropertiesSPane.setContent(new ActionEditor(okLambda, entry, myGridShape));
			}

		});
	}

	@Override
	protected HBox makeEntryBox (ConcreteAction entry) {
		HBox hb = new HBox();
		Label name = new Label(entry.getName());
		name.setTranslateY(7.5);
		hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent e) {
				myPropertiesSPane.setContent(new ActionViewer(entry));
			}

		});
		hb.getChildren().addAll(name);
		return hb;
	}

	@Override
	protected void initEntryDelBtn (ConcreteAction entry, Button delBtn) {
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myActionData.remove(entry);
				myVBox.getChildren().remove(myEntryMap.get(entry));
			}
		});
	}
}
