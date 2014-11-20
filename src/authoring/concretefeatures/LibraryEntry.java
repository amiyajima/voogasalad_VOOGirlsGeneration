package authoring.concretefeatures;

import authoring_environment.LibraryView;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class LibraryEntry extends HBox {
	
	public LibraryEntry(ImageView image, Hyperlink link, boolean unit){
		this.getChildren().addAll(image, link);
		
		this.setStyle("-fx-cursor: hand");
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent m){
				LibraryView.currentlySelectedImage = image;
				LibraryView.unitSelected = unit;
				LibraryView.reset = false;
			}
		});
	}
}