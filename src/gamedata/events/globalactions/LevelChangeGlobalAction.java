package gamedata.events.globalactions;

import java.util.function.Consumer;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Level;

/**
 * Currently implemented for levelChange globalAction
 * @author annamiyajima
 *
 */
public class LevelChangeGlobalAction implements GlobalAction{

    private Level myLevel;
    
    public LevelChangeGlobalAction( Level l){
        myLevel = l;
    }
    
    public void performAction(){
        //lambda called by level when conditions are met
        Consumer<Game> c = (Game g) -> {g.setCurrentLevel(myLevel);};
    }
}
