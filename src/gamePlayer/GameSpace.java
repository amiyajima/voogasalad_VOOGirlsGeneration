package gamePlayer;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import GameEngine.GameModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GameSpace{
    

    private GameView myView;
    private GameDisplay myGameDisplay;
    private GameModel myGameModel;
    
    private BorderPane myPane;
    private ViewComponentFactory myFactory;
    private List<String> myComponents;
    
    public GameSpace(Stage stage, List<String> componentsLocation){
        
        myView = new GameView(stage);
        myGameModel = new GameModel();
        myGameDisplay = new GameDisplay();
        myComponents = componentsLocation;
        
        myFactory= new ViewComponentFactory(stage, myGameModel, myGameDisplay);
        setupGamespace();
    
    }
    
    private void setupGamespace(){
        
        myPane.setCenter(myGameDisplay.getNode());
        
        for (String s: myComponents){
            ViewComponent component = myFactory.makeComponent(s);
            if(component instanceof ToggleScoreLabel){
                myFactory.setObserver(component, new ArrayList<>(Arrays.asList("scorePane")));
                
            }
            addComponent(component);
        }
        
        
        
    }
    
    private void addComponent(ViewComponent component){
        
    }


}
