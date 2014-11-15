package gamePlayer;

import java.util.Observable;
import java.util.Observer;
import GameEngine.GameModel;
import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class ViewComponent extends Observable implements Observer{
    
    private GameModel myModel;
    private Node myNode;
    private Stage myStage;
    
    public ViewComponent(GameModel model, Stage stage){
        
        myModel= model;
        myStage = stage;
        myNode = setUpDisplay();
    }
    
    public GameModel getModel(){
        return myModel;
    }
    public Stage getStage(){
        return myStage;
    }
    
    
    // by default, do nothing
    @Override
    public void update (Observable o, Object obj){
        
    }
    
    protected abstract Node setUpDisplay();
    
    public Node getNode(){
        return myNode;
    }
    

}
