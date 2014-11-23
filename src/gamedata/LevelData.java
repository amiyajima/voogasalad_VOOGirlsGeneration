package gamedata;

import gamedata.gamecomponents.Level;
import java.util.List;

/**
 * Wrapper for Level data in JSON for parsing
 * @author Rica
 *
 */
public class LevelData {
    private List<Level> myLevels;
    
    public LevelData(List<Level> levels) {
        myLevels = levels;
    }
    
    public List<Level> getLevels() {
        return myLevels;
    }

}
