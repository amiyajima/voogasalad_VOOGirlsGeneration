package fxml_main;

import gamedata.gamecomponents.Patch;
import java.awt.geom.Point2D;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.PatchTypeData;
import authoring_environment.GUIGrid;

public class PatchController extends GridComponentAbstCtrl<Patch> {

	private PatchTypeData myPatchTypes;

	public PatchController (VBox vbox, ScrollPane propertiesSPane, GUIGridReference gridRef,
			PatchTypeData patchTypes) {
		super(vbox, propertiesSPane, gridRef);
		myPatchTypes = patchTypes;
	}

	@Override
	protected void initGlobalNewBtn (Button newBtn) {
		newBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				Consumer<Patch> okLambda = (Patch patch) -> {
					myPatchTypes.add(patch);
					addEntry(patch);
				};
				myPropertiesSPane.setContent(new PatchTypeEditor(okLambda, myPatchTypes));
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
				EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
					@Override
					public void handle (MouseEvent e) {
						GUIGrid grid = myGridReference.getGrid();
						Point2D coor = grid.calculateClicked(e.getX(), e.getY());
						grid.removePatch(coor);
					}
				};
				myGridReference.getGrid().paneSetOnMouseEvent(clickHandler);
			}
		});
	}

	@Override
	protected HBox makeEntryBox (Patch entry) {
		HBox hb = new HBox();
		Label name = new Label(entry.getName());
		name.setTranslateY(7.5);
		ImageView img = entry.getImageView();
		img.setFitHeight(40);
		img.setFitWidth(40);
		hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent event) {
				EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
					@Override
					public void handle (MouseEvent e) {
						GUIGrid grid = myGridReference.getGrid();
						Point2D coor = grid.calculateClicked(e.getX(), e.getY());
						grid.addPatch(entry, coor);
					}
				};
				myGridReference.getGrid().paneSetOnMouseEvent(clickHandler);
			}
		});
		hb.getChildren().addAll(img, name);
		return hb;
	}

	@Override
	protected void initEntryEditBtn (Patch entry, Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent e) {
				Consumer<Patch> okLambda = (Patch patch) -> {
					HBox entryBox = makeCompleteEntryBox(patch);

					HBox entryHolderBox = myEntryMap.get(entry);
					entryHolderBox.getChildren().clear();
					entryHolderBox.getChildren().add(entryBox);

					myPatchTypes.replace(entry, patch);
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
				myVBox.getChildren().remove(myEntryMap.get(entry));
			}
		});
	}
}
