package fxml_main;

import gamedata.action.Action;
import gameengine.player.Player;

import java.util.List;
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
public class ActionController extends GridComponentAbstCtrl<Action> {
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
		// TODO: Need to not hard-code square, have it passed through the constructor
		// as maybe a gridshapeproperty (new class?)
		newBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				Consumer<Action> okLambda = (Action action) -> {
					myActionData.add(action);
					System.out.println(myActionData.getActionIDs().get(0));
					System.out.println("Created Action");
					System.out.println(action.getActionRange().toString());
					addEntry(action);
				};
				myPropertiesSPane.setContent(new ActionEditor(okLambda, myGridShape));
			}
		});
	}

	@Override
	protected void initGlobalEditBtn (Button editBtn) {
		// do nothing
	}


	@Override
	protected void initGlobalDelBtn (Button delBtn) {
		// do nothing
	}

	@Override
	protected void initEntryEditBtn (Action entry, Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent click) {
				Consumer<Action> okLambda = (Action action) -> {

					myVBox.getChildren().remove(entry);

					HBox entryHolderBox = myEntryMap.get(entry);
					entryHolderBox.getChildren().clear();

					myEntryMap.get(entry);

				};

				List<Player> playersRO = null;

				myPropertiesSPane.setContent(new ActionEditor(okLambda, entry, myGridShape));

			}

		});
	}

	@Override
	protected HBox makeEntryBox (Action entry) {
		HBox hb = new HBox();
		Label name = new Label(entry.toString());
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
	protected void initEntryDelBtn (Action entry, Button delBtn) {
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myVBox.getChildren().remove(myEntryMap.get(entry));
			}
		});
	}
}
