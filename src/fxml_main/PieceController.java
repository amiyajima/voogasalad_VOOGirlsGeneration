package fxml_main;

import gamedata.gamecomponents.Piece;
import authoring.concretefeatures.LibraryUnitEditor;
import authoring.data.ActionData;
import authoring.data.PieceTypeData;
import authoring_environment.ShapeGrid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PieceController extends GridComponentAbstCtrl<Piece> {
	
	private PieceTypeData myPieceTypes;
	private ActionData myActionData;
	
    public PieceController (VBox vbox, ScrollPane propertiesSPane, ShapeGrid currGrid, ActionData actions) {
    	super(vbox, propertiesSPane, currGrid);
    	myPieceTypes = new PieceTypeData();
    	myActionData = actions;
    }

    @Override
    protected void initGlobalNewBtn (Button newBtn) {
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("HI NEW BUTTONFORPIECE HI");
            }
        });
    }

    @Override
    protected void initGlobalEditBtn (Button editBtn) {
    	editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("HI EDIT BUTTONFORPIECE HI");
            }
        });
    }

    @Override
    protected void initGlobalDelBtn (Button delBtn) {
    	delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("HI DELETE BUTTONFORPIECE HI");
            }
        });
    }

	@Override
	protected HBox makeEntryBox(Piece entry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initEntryEditBtn(Piece entry, Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myPropertiesSPane.setContent(new LibraryUnitEditor(entry, myActionData));
			}
		});
	}

	@Override
	protected void initEntryDelBtn(Piece entry, Button delBtn) {
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Delete all instances of this piece!");
			}
		});
	}
}