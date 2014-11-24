package authoring_environment;

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
	
	public WorkspaceView() {
		myTabCounter = 1;
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
	

	
}
