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
import authoring.data.PatchData;
import authoring.data.PatchTypeData;
import authoring_environment.GUIGrid;

public class PatchController extends GridComponentAbstCtrl<Patch> {

	private PatchTypeData myPatchTypes;
	private PatchData myPatches;
	
	public PatchController (VBox vbox, ScrollPane propertiesSPane, GUIGrid currGrid) {
		super(vbox, propertiesSPane, currGrid);
		//TODO: myPatchTypes needs to be changed..data..
		myPatchTypes = new PatchTypeData();
		//TODO: this is just for testing..
		currGrid = new GUIGrid(10, 10, 2, "Square Grid");
		myPatches = currGrid.getPatches();
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
	protected void initEntryEditBtn (Patch entry, Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent e) {
				Consumer<Patch> okLambda = (Patch patch) -> {
					//TODO: Use observables to make all the pieces and
					// patches in the grid change to fit the updated patch
				    HBox entryBox = myEntryMap.get(entry);
				    HBox imgNameBox = myIndivEntMap.get(entryBox);
				    
				    entryBox.getChildren().remove(imgNameBox);
				    HBox newImgNameBox = makeEntryBox(patch);	
				    
				    entryBox.getChildren().add(newImgNameBox);
				    myIndivEntMap.replace(entryBox, newImgNameBox);
				    
				    myPatchTypes.replace(entry, patch);
				    myPatches.update(myPatchTypes, entry);
				};
				myPropertiesSPane.setContent(new PatchTypeEditor(okLambda,entry));
			}
		});
	}

	@Override
	protected void initEntryDelBtn (Patch entry, Button delBtn) {

		delBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle (ActionEvent event) {
				myPatchTypes.remove(entry);
				myPatches.update(myPatchTypes, entry);
				myVBox.getChildren().remove(myEntryMap.get(entry));
			}
		});
	}
}