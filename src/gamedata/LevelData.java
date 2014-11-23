package gamedata;

import gamedata.gamecomponents.Level;
import java.util.List;

public class LevelData {
    private List<Level> myLevels;
    
    public LevelData(List<Level> levels) {
        myLevels = levels;
    }
    
    public List<Level> getLevels() {
        return myLevels;
    }

}
