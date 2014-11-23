package authoring.data;

import java.util.LinkedList;
import java.util.List;

import authoring.datawrappers.GridPatchWrapper;

/**
 * @author Martin Tamayo
 *
 */
public class AllPatchWrappers implements AuthoringData<GridPatchWrapper>{
	
	private List<GridPatchWrapper> allPatches;
	
	/**
     * Constructor for AllPatchWrappers,
     * initializes empty list of GridPatchWrappers.
     */
    public AllPatchWrappers() {
        allPatches = new LinkedList<GridPatchWrapper>();
    }

	@Override
	public void add(GridPatchWrapper patchWrapper) {
		allPatches.add(patchWrapper);
	}

	@Override
	public void remove(GridPatchWrapper patchWrapper) {
		allPatches.remove(patchWrapper);
	}

	@Override
	public void clear() {
		allPatches.clear();
	}
}