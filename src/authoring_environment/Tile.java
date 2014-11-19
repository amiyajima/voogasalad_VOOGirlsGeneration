package authoring_environment;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tile extends Pane{
	private int mySize;
	private Color myDefault;

	public Tile(int x,int y,int size){
		mySize=size;
		Rectangle terrain=new Rectangle(mySize,mySize);
		Rectangle unit=new Rectangle(mySize,mySize);
		if(((x%2==0) && (y%2==0)) || ((x%2==1) && (y%2==1))){
			myDefault = Color.RED;
		}
		else{
			myDefault = Color.BLACK;
		}
		terrain.setFill(myDefault);
		unit.setVisible(false);
		this.setLayoutX(x*mySize);
		this.setLayoutY(y*mySize);
		this.getChildren().addAll(terrain, unit);
		setActionEvent(terrain, unit);
	}
	
	private void setActionEvent(Rectangle terrain, Rectangle unit) {
		this.setStyle("-fx-cursor: hand");
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent m){
				if(LibraryView.reset){
					if(LibraryView.unitSelected){
						unit.setFill(myDefault);
						unit.setVisible(false);
					}
					else{
						terrain.setFill(myDefault);
					}
				}
				else{
					Image image = LibraryView.currentlySelectedImage.getImage();
					if(LibraryView.unitSelected){
						unit.setFill(new ImagePattern(image));
						unit.setVisible(true);
					}
					else{
						terrain.setFill(new ImagePattern(image));
					}
				}
			}
		});
	}
	
	public int getSize(){
		return mySize;
	}
	
	public void setSize(int newSize){
		mySize=newSize;
	}
}