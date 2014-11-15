package gamePlayer;

import java.util.Observable;
import java.util.function.Consumer;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class SingleActor extends Observable implements Actor{
    
    
    private double myHP;
    private Grid myGrid;
    private int myNumTurns;
    private Position myPosition;
    private ImageView myImage;
    private double myScore;
    
    
    public SingleActor(double HP, Image image, Grid grid, Position position){

        myImage = new ImageView(image);
        myGrid = grid;
        myPosition = position;

    }

    @Override
    public void setActorImage (Image image) {
        myImage = new ImageView(image);
        
    }

    @Override
    public Image getCurrentImage () {
        return myImage.getImage();
    }

//    @Override
//    public double getHP () {
//        return myHP;
//    }


    @Override
    public void setSelected (boolean isSelected) {
        
        if(isSelected){
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(5.0);
            dropShadow.setOffsetX(5.0);
            dropShadow.setOffsetY(5.0);
            dropShadow.setColor(Color.NAVY);
            myImage.setEffect(dropShadow);
        }
        else{
            myImage.setEffect(null);
        }
        
        //notify the controlPane to update display to show only available actions
        setChanged();
        notifyObservers();
        
        
    }
    
    


    @Override
    public void performOnClick () {
        actOnGrid(grid->grid.showPossibleMoves(this));
        
    }

    @Override
    public void performOnKey () {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void actOnGrid (Consumer<Grid> function) {
       function.accept(myGrid);
        
    }

    @Override
    public void setPosition (Position pos) {
        
        
    }



}
