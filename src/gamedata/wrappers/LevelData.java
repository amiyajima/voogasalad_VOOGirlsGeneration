package gamedata.wrappers;

import gamedata.gamecomponents.Level;
import java.util.List;


/**
 * Wrapper for LevelData in JSON for parsing
 * 
 * @author Rica
 *
 */
public class LevelData {
    private List<LevelDataIndividual> myLevels;

    public LevelData (List<LevelDataIndividual> levels) {
        myLevels = levels;
    }

    public List<LevelDataIndividual> getLevels () {
        return myLevels;
    }

    public String toString () {
        return "LevelData: toString " + myLevels.toString();
    }

}
