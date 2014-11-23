package gamedata.wrappers;

import gamedata.gamecomponents.Level;
import java.util.List;

/**
 * Wrapper for Level data in JSON for parsing
 * @author Rica
 *
 */
public class LevelData {
    private List<LevelDataIndividual> myLevels;
    
    public LevelData(List<LevelDataIndividual> levels) {
        myLevels = levels;
        System.out.println("My levels initiated: " + levels.get(0).toString());
    }
    
    public List<LevelDataIndividual> getLevels() {
        System.out.println("Levels get called: " + myLevels.get(0).toString());
        return myLevels;
    }

}
