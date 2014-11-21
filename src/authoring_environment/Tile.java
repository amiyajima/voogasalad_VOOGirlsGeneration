package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tile extends Pane{
	
	private static final Image HIDDEN_DEFAULT_IMAGE = new Image("authoring/concretefeatures/images/images.jpeg");
	
	private int mySize;
	private Rectangle myDefault;
	private Patch myTerrain;
	private Piece myUnit;
	private Rectangle selected;
	public Rectangle surfaceImage;
	private ImageView terrainImage;
	private ImageView unitImage;
	private boolean mySelected;
	private int myX;
	private int myY;
	
	public Tile(int x,int y,int size){

		mySize = size;
		mySelected = false;
		myDefault = new Rectangle(mySize, mySize);
		surfaceImage = new Rectangle(mySize, mySize);
		surfaceImage.setVisible(false);
		selected = new Rectangle(mySize, mySize);
		selected.setFill(Color.web("#0000FF",0.3));
		selected.setVisible(false);
		
		unitImage = new ImageView(HIDDEN_DEFAULT_IMAGE);
		terrainImage = new ImageView(HIDDEN_DEFAULT_IMAGE);
		unitImage.setFitHeight(mySize);
		unitImage.setFitWidth(mySize);
		terrainImage.setFitHeight(mySize);
		terrainImage.setFitWidth(mySize);
		unitImage.setVisible(false);
		terrainImage.setVisible(false);
		
		if(((x%2==0) && (y%2==0)) || ((x%2==1) && (y%2==1))){
			myDefault.setFill(Color.WHITE);
		}
		else{
			myDefault.setFill(Color.WHITESMOKE);
		}
		
		this.setLayoutX(x*mySize);
		this.setLayoutY(y*mySize);
		this.getChildren().addAll(myDefault, terrainImage, unitImage,surfaceImage, selected);
		setActionEvent();

	}
	
	private void setActionEvent() {
		this.setStyle("-fx-cursor: hand");
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent m){
				if(LibraryView.reset){
					if(LibraryView.unitSelected){
						myUnit = null;
						unitImage.setVisible(false);;
					}
					else{
						myTerrain = null;
						terrainImage.setVisible(false);;
					}
				}
				else{
					if(LibraryView.unitSelected){
						myUnit = LibraryView.currentlySelectedUnit;
						unitImage.setImage(myUnit.getImageView().getImage());
						unitImage.setVisible(true);
					}
					else{
						myTerrain = LibraryView.currentlySelectedTerrain;
						terrainImage.setImage(myTerrain.getImageView().getImage());
						terrainImage.setVisible(true);
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
//		this.getChildren().add(surfaceImage);
		surfaceImage.setVisible(true);

	}
}