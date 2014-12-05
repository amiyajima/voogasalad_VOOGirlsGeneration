package fxml_main;

import gamedata.gamecomponents.Patch;
import authoring.data.PatchTypeData;
import authoring_environment.ShapeGrid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PatchController extends GridComponentAbstCtrl<Patch> {
	
	private PatchTypeData myPatchTypes;
	
    public PatchController (VBox vbox, ScrollPane propertiesSPane, ShapeGrid currGrid) {
    	super(vbox, propertiesSPane, currGrid);
    	myPatchTypes = new PatchTypeData();
    }

    @Override
    protected void initGlobalNewBtn (Button newBtn) {
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("HI NEW BUTTONFORPATCH HI");
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
	protected HBox makeEntryBox(Patch entry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initEntryEditBtn(Patch entry, Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
	}

	@Override
	protected void initEntryDelBtn(Patch entry, Button delBtn) {
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Delete all instances of this patch!");
			}
		});
	}
}