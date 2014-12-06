package fxml_main;

import gamedata.gamecomponents.Patch;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.PatchTypeData;
import authoring_environment.GUIGrid;

public class PatchController extends GridComponentAbstCtrl<Patch> {

	private PatchTypeData myPatchTypes;

	public PatchController (VBox vbox, ScrollPane propertiesSPane, GUIGrid currGrid) {
		super(vbox, propertiesSPane, currGrid);
		myPatchTypes = new PatchTypeData();
	}

	@Override
	protected void initGlobalNewBtn (Button newBtn) {
		newBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				Consumer<Patch> okLambda = (Patch patch) -> {
					addEntry(patch);
				};
				myPropertiesSPane.setContent(new PatchTypeEditor(okLambda));
			}
		});
	}

	@Override
	protected void initGlobalEditBtn (Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				System.out.println("HI EDIT BUTTONFORPATCH HI");
			}
		});
	}

	@Override
	protected void initGlobalDelBtn (Button delBtn) {
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				System.out.println("HI DELETE BUTTONFORPATCH HI");
			}
		});
	}

	@Override
	protected HBox makeEntryBox (Patch entry) {
		myPatchTypes.add(entry);
		HBox hb = new HBox();
		Label name = new Label(entry.getName());
		name.setTranslateY(7.5);
		ImageView img = entry.getImageView();
		img.setFitHeight(40);
		img.setFitWidth(40);
		hb.getChildren().addAll(img, name);
		return hb;
	}

	@Override
	protected void initEntryEditBtn(Patch entry, Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent e) {
				Consumer<Patch> okLambda = (Patch patch) -> {
					//TODO: Use observables to make all the pieces and
					// patches in the grid change to fit the updated patch
				};
				myPropertiesSPane.setContent(new PatchTypeEditor(okLambda, entry));
			}
		});
	}

	@Override
	protected void initEntryDelBtn (Patch entry, Button delBtn) {
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myPatchTypes.remove(entry);
				HBox entryBox = myEntryMap.get(entry);
				myVBox.getChildren().remove(entryBox);
			}
		});
	}
}