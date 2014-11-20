package authoring.concretefeatures;

import gamedata.gamecomponents.Piece;
import authoring.abstractfeatures.LibraryEntry;
import authoring_environment.LibraryView;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class UnitEntry extends LibraryEntry {
	
	private Piece myUnit;
	
	public UnitEntry(ImageView image, Hyperlink link, Piece unit){
		this.getChildren().addAll(image, link);
		myUnit = unit;
		
		this.setStyle("-fx-cursor: hand");
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent m){
				LibraryView.currentlySelectedUnit = myUnit;
				LibraryView.unitSelected = true;
				LibraryView.reset = false;
			}
		});
	}
	
	public Piece getUnit(){
		return myUnit;
	}
}