package authoring.data;

import gamedata.gamecomponents.Level;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LevelData implements AuthoringData<Level> {
	
	private List<Level> myLevels;
	
	 /**
     * Constructor for new LevelData,
     * initializes empty list of LevelData.
     */
    public LevelData() {
        myLevels = new LinkedList<Level>();
    }
	
	@Override
	public void add(Level p) {
		myLevels.add(p);
	}

	@Override
	public boolean canAdd(Level element) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void remove(Level p) {
		myLevels.remove(p);
	}

	@Override
	public void replace(Level origEl, Level newEl) {
		remove(origEl);
		add(newEl);
	}

	@Override
	public List<Level> getData() {
		return Collections.unmodifiableList(myLevels);
	}
}
