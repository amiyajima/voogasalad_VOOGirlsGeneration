package gamePlayer;

import java.util.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import GameEngine.GameModel;

public class ScorePane extends ScrollingPane{
    
    public static final int SCOREPANE_HEIGHT = 150;
    public static final int SCOREPANE_WIDTH = 200;
    public static final String PLAYER_TEXT = "Player: ";
    public static final String SCORE_TEXT = "Score: ";
    
    

    public ScorePane (GameModel model, Stage stage, GameDisplay display) {
        super(model, stage, display, SCOREPANE_WIDTH, SCOREPANE_HEIGHT);
        updateDisplay();
        
        
    }
    
    private void updateDisplay(){
        
        double s1 = getModel().getScore();
        Text t = new Text (PLAYER_TEXT + s1);
        getContainer().getChildren().add(t);
  
    }
    
    @Override
    public void update (Observable o, Object arg) {
        
        if(o instanceof ToggleScoreLabel){
            
            System.out.println("notified!");
            getNode().setVisible(!getNode().isVisible());
            
        }
        if(o instanceof GameModel){
            updateDisplay();
        }
    }
    
    
    

    

}
