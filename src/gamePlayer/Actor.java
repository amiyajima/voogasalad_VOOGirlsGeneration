package gamePlayer;

import java.util.function.Consumer;
import com.sun.istack.internal.NotNull;
import javafx.scene.image.Image;

public interface Actor {
    
    public void setActorImage(Image image);
    
    public Image getCurrentImage();
    
    public void performOnClick();
    
    public void performOnKey();
    
    public void setPosition(Position pos);
    
    public void actOnGrid(@NotNull Consumer<Grid> function);
    
//    public abstract double getHP();
//    
//    public void setHP(double d);
//    
//    public abstract int getNumTurns();
//    
//    public void setNumTurns(int i);
//    
    public void setSelected(boolean isSelected);
//    
//    public double getScore();
//    
//    public void setScore(double d);
    

}
