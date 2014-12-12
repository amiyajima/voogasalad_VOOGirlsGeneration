package gamedata.wrappers;

import gamedata.gamecomponents.Level;
import java.util.ArrayList;
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
    
    /**
     * Returns a list of levels parsed from the data
     * @return
     */
    public List<Level> getLevelsFromData() {
        List<Level> myLevelsFromData = new ArrayList<Level>();
        for (LevelDataIndividual ldi : myLevels) {
           // myLevelsFromData.add(ldi.getLevel());
        }
        return myLevelsFromData;
    }

    public String toString () {
        return "LevelData: toString " + myLevels.toString();
    }

}
