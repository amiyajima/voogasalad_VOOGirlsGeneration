package authoring_environment;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
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
	
	private List<SuperGrid> myGrids;
	private int myCurrentTabIdx;
	
	public WorkspaceView() {
		myGrids = new ArrayList<>();
	}
	
	/**
	 * Adds a new tab to the workspace
	 * and gives it a new name based on the
	 * number of tabs that have been made
	 * @param tab
	 */
	
	/*
	 * TODO: This only works when you close the most recent workspace (rightmost one). 
	 * Closing any other workspace does not automatically select the next one (the 
	 * workspaces ahead of the closed one shift left one - for some goddamn reason
	 * JavaFX does not detect this as a change in index. Actually what the hell.
	 * 
	 * Also when you close the last tab, then make a new one. Creating that new tab is
	 * not recognized as a selection switch. 
	 * 
	 * @Mike Zhu
	 */
	
	public void addNextTab(Tab tab, String name) {
		
		tab.setText(name);	
		
		super.getTabs().add(tab);
		getSelectionModel().select(tab);
		
		tab.setOnClosed(new EventHandler<Event>() {
        	@Override
			public void handle(Event closed) {
          		int idx = getSelectionModel().getSelectedIndex();


            	if(idx>=0){
        			myGrids.remove(idx);
        		}
            	if(idx==-1){
            		myGrids.remove(0);
            	}
			}
        });
	}
	
	public SuperGrid getActiveGrid(){
		SingleSelectionModel<Tab> selectionModel = getSelectionModel();
		int idx = selectionModel.getSelectedIndex();
		
		if(idx==-1 || idx>=myGrids.size()){
			return myGrids.get(0);
		}
		return myGrids.get(idx);
	}
	
	public void addGrid(SuperGrid superGrid){
		myGrids.add(superGrid);
	}
}
