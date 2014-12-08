package fxml_main;

import gamedata.gamecomponents.Piece;

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
import authoring.data.ActionData;
import authoring.data.PieceTypeData;
import authoring_environment.GUIGrid;

public class PieceController extends GridComponentAbstCtrl<Piece> {
	
	private PieceTypeData myPieceTypes;
	private ActionData myActionData;
	
    public PieceController (VBox vbox, ScrollPane propertiesSPane, GUIGridReference gridRef, 
    		PieceTypeData pieceTypes, ActionData actions) {
    	super(vbox, propertiesSPane, gridRef);
    	myActionData = actions;
    	myPieceTypes = pieceTypes;
    }

    @Override
    protected void initGlobalNewBtn (Button newBtn) {
    	newBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				Consumer<Piece> okLambda = (Piece piece) -> {
					myPieceTypes.add(piece);
					addEntry(piece);
				};
				myPropertiesSPane.setContent(new PieceTypeEditor(okLambda, myPieceTypes, myActionData));
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
            	EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
					@Override
					public void handle (MouseEvent e) {
						GUIGrid grid = myGridReference.getGrid();
						Point2D coor = grid.findClickedCoordinate(e.getX(), e.getY());
						grid.removePieceAtCoordinate(coor);
					}
				};
				myGridReference.getGrid().paneSetOnMousePressed(clickHandler);
            }
        });
    }

	@Override
	protected HBox makeEntryBox(Piece entry) {
		HBox hb = new HBox();
		Label name = new Label(entry.toString());
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
						Point2D coor = grid.findClickedCoordinate(e.getX(), e.getY());
						grid.addPiece(entry, coor);
					}
				};
				myGridReference.getGrid().paneSetOnMousePressed(clickHandler);
			}
		});
		hb.getChildren().addAll(img, name);
		return hb;
	}

	@Override
	protected void initEntryEditBtn(Piece entry, Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent e) {
				Consumer<Piece> okLambda = (Piece piece) -> {
					HBox entryBox = makeCompleteEntryBox(piece);
					
					HBox entryHolderBox = myEntryMap.get(entry);
					entryHolderBox.getChildren().clear();
					entryHolderBox.getChildren().add(entryBox);
					myEntryMap.put(piece, entryHolderBox);

					myPieceTypes.replace(entry, piece);
				};
				myPropertiesSPane.setContent(new PieceTypeEditor(okLambda, entry, myActionData));
			}
		});
	}

	@Override
	protected void initEntryDelBtn(Piece entry, Button delBtn) {
		delBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				myPieceTypes.remove(entry);
				myVBox.getChildren().remove(myEntryMap.get(entry));
			}
		});
	}
}
