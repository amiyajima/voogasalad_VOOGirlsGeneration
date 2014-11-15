package gamePlayer;

import java.util.List;
import java.util.Observable;
import javafx.stage.Stage;
import GameEngine.GameModel;

public class ControlPanel extends ScrollingPane {
    
    public static final int CONTROLPANEL_HEIGHT = 100;
    public static final int CONTROLPANEL_WIDTH = 200;
    
    private List<ControlLabel> myControls;

    public ControlPanel (GameModel model, Stage stage) {
        super(model, stage, CONTROLPANEL_WIDTH, CONTROLPANEL_HEIGHT);
        addDefaultControl();
    }
    
    private void updateLabels(){
        getContainer().getChildren().clear();
        addDefaultControl();
        // calling model API for the available actions the actor can possible do
    }
    
    private void addDefaultControl(){
       // getContainer().getChildren().addAll()
        
    }
    
    

    @Override
    public void update (Observable o, Object arg) {
        if(o instanceof Actor){
            updateLabels();
        }
        
    }
    

}
