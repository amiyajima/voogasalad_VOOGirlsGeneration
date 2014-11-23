package authoring.concretefeatures;

import gamedata.gamecomponents.Piece;
import authoring.abstractfeatures.LibraryEntry;
import authoring_environment.LibraryView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * @author Martin Tamayo
 * 
 * An HBox containing data for a unit piece. UnitEntries are
 * added to the LibraryView in the game authoring environment.
 */
public class UnitEntry extends LibraryEntry {
	
	private Piece myUnit;
	
	/**
	 * Constructs the UnitEntry, which is displayed as an HBox in the
	 * LibraryView in the game authoring environment. This class also
	 * contains the Piece class for the unit.
	 * 
	 * @param image : ImageView of the image of the unit.
	 * @param link : Name of unit. Links to UnitEditor.
	 * @param unit : Actual Piece class for the unit.
	 */
	public UnitEntry(Piece unit, ImageView image, Label name, Button editButton, Button delButton){
		this.getChildren().addAll(delButton, editButton, image, name);
		myUnit = unit;
		
		this.setStyle("-fx-cursor: hand");
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent m){
				LibraryView.currentlySelectedUnit = myUnit;
				LibraryView.doNothing = false;
				LibraryView.unitSelected = true;
				LibraryView.reset = false;
			}
		});
	}
	
	/**
	 * Method to retrieve the Piece class contained within this UnitEntry class.
	 * 
	 * @return Piece associated with the UnitEntry.
	 */
	public Piece getUnit(){
		return myUnit;
	}
}