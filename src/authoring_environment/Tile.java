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
	private Rectangle terrain;
	private Rectangle unit;
	private Rectangle selected;
	public Rectangle surfaceImage;
	private boolean mySelected;

	
	public Tile(int x,int y,int size){
		mySelected = false;
		mySize=size;
		terrain=new Rectangle(mySize,mySize);
		unit=new Rectangle(mySize,mySize);
		surfaceImage=new Rectangle(mySize,mySize);
		surfaceImage.setVisible(false);
		selected=new Rectangle(mySize,mySize);
		selected.setFill(Color.web("#0000FF",0.3));
		selected.setVisible(false);
		
		if(((x%2==0) && (y%2==0)) || ((x%2==1) && (y%2==1))){
			myDefault = Color.WHITE;
		}
		else{
			myDefault = Color.WHITESMOKE;
		}
		
		terrain.setFill(myDefault);
		unit.setVisible(false);
		this.setLayoutX(x*mySize);
		this.setLayoutY(y*mySize);
		this.getChildren().addAll(terrain, unit,selected,surfaceImage);
		setActionEvent();
//		switchSelected();
	}
	
	private void setActionEvent() {
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

	public void switchSelected(){
		if (selected.isVisible()){
			selected.setVisible(false);
			mySelected = false;
		}
		else { 
			selected.setVisible(true);				
			mySelected = true;
		}
	}
	
	public boolean getSelected(){
		return mySelected;
	}
	
	public void addSurfaceImage(Image image){
		surfaceImage.setFill(new ImagePattern(image));
	}
}