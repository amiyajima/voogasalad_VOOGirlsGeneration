package authoring.data;

import gamedata.gamecomponents.Level;

import java.util.List;

public class LevelData implements AuthoringData<Level> {
	
	private List<Level> myLevels;
	
	@Override
	public void add(Level p) {
		myLevels.add(p);
	}

	@Override
	public void remove(Level p) {
		myLevels.remove(p);
	}

	@Override
	public void replace(Level origEl, Level newEl) {
		newEl.getGrid().getRow();
		newEl.getGrid().getCol();
		// TODO: FINISH WRITING CODE
	}

	@Override
	public List<Level> getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
