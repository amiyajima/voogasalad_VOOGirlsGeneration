package authoring_environment;

import authoring.data.PatchTypeData;
import authoring.data.PieceTypeData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

/**
 * The GUI contains all the parts in authoring environment. It sets the size 
 * and the position of the parts in the GUI.
 * 
 * @author huangmengen
 */
public class VoogaView extends BorderPane {

	private MenuView myMenuView;

	private WorkspaceView myWorkspaceView;
	private LibraryView myLibraryView;
	
	public VoogaView(){
		PieceTypeData pieceTypeData = new PieceTypeData();
		PatchTypeData patchTypeData = new PatchTypeData();
	
		myLibraryView = new LibraryView(pieceTypeData,patchTypeData);
		myWorkspaceView = new WorkspaceView();
		myMenuView = new MenuView(myWorkspaceView, myLibraryView);
		
		/**
		 * Associate the LibraryView with the currently selected Tab
		 */
		myWorkspaceView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

			@Override
			public void changed(ObservableValue<? extends Tab> arg0, Tab arg1, Tab arg2) {
				ShapeGrid activeGrid = myWorkspaceView.getActiveGrid();
		        myLibraryView.associateGrid(activeGrid);
		    }

		}); 
		
		setTop(myMenuView);	
		setLeft(myLibraryView);
		setRight(myWorkspaceView);
	}
}