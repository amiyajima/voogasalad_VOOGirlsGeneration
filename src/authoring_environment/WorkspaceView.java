package authoring_environment;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Workspace TabPane for having multiple
 * workspaces in the authoring environment
 * 
 * @author Jennie Ju
 *
 */
public class WorkspaceView extends TabPane {
	private int myTabCounter;
	
	private List<SandyGrid> myGrids;
	
	public WorkspaceView() {
		myTabCounter = 1;
		myGrids = new ArrayList<>();
	}
	
	/**
	 * Adds a new tab to the workspace
	 * and gives it a new name based on the
	 * number of tabs that have been made
	 * @param tab
	 */
	public void addNextTab(Tab tab) {
		tab.setText("Workspace " + myTabCounter);
		super.getTabs().add(tab);
		getSelectionModel().select(tab);
		myTabCounter++;
	}
	
	public void addGrid(SandyGrid grid){
		myGrids.add(grid);
	}
	
	public SandyGrid getActiveGrid(){
		SingleSelectionModel<Tab> selectionModel = getSelectionModel();

		for(int i=0; i<getTabs().size(); i++){
			if(selectionModel.isSelected(i)){
				return myGrids.get(i);
			}
		}
		return myGrids.get(0);
	}
}
