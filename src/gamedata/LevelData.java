package gamedata;

import gamedata.gamecomponents.Level;
import java.util.List;

/**
 * Wrapper for Level data in JSON for parsing
 * @author Rica
 *
 */
public class LevelData {
    private List<IndividualLevelData> myLevels;
    
    public LevelData(List<IndividualLevelData> levels) {
        myLevels = levels;
        System.out.println("My levels initiated: " + levels.get(0).toString());
    }
    
    public List<IndividualLevelData> getLevels() {
        System.out.println("Levels get called: " + myLevels.get(0).toString());
        return myLevels;
    }

}
