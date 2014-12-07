package fxml_main;

import gamedata.gamecomponents.Piece;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.data.ActionData;
import authoring.data.PieceTypeData;

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
					addEntry(piece);
				};
				myPropertiesSPane.setContent(new PieceTypeEditor(okLambda, myActionData));
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
		myPieceTypes.add(entry);
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
	protected void initEntryEditBtn(Piece entry, Button editBtn) {
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent e) {
				Consumer<Piece> okLambda = (Piece piece) -> {
					//TODO: Use observables to make all the pieces and
					// patches in the grid change to fit the updated patch
					HBox entryBox = myEntryMap.get(entry);
				    HBox imgNameBox = myIndivEntMap.get(entryBox);
				    
				    entryBox.getChildren().remove(imgNameBox);
				    HBox newImgNameBox = makeEntryBox(piece);	
				    
				    entryBox.getChildren().add(newImgNameBox);
				    myIndivEntMap.replace(entryBox, newImgNameBox);
				    
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
				HBox entryBox = myEntryMap.get(entry);
				myVBox.getChildren().remove(entryBox);
			}
		});
	}
}