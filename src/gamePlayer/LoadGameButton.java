package gamePlayer;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import GameEngine.GameModel;

public class LoadGameButton extends GameButton{
    
    private FileChooser myFileChooser;
    private File myFile;
    
    public static final String LOAD_GAME_TEXT = "Load game";

    public LoadGameButton (GameModel model, Stage stage) {
        super(model, stage, LOAD_GAME_TEXT);
       myFileChooser = new FileChooser();
    }

    @Override
    protected void onClickAction () {
        
        myFileChooser.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
        myFile = myFileChooser.showOpenDialog(getStage());
        if(myFile != null){
            getModel().loadGame(myFile);
        }
        
    }
    
    

}
