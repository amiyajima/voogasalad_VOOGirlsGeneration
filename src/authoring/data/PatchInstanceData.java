package authoring.data;

import gamedata.gamecomponents.GridComponent;
import gamedata.gamecomponents.Patch;

/**
 * Class for storing the patches created by the user
 * Stored in the authoring environment
 * 
 * @author Sandy Lee
 */
public class PatchInstanceData extends GridComponentInstanceData {
    
	@Override
	public void replace(GridComponent origEl, GridComponent newEl) {
		remove(origEl);
		add((GridComponent)(new Patch((Patch)newEl, origEl.getLoc())));
	}
}
