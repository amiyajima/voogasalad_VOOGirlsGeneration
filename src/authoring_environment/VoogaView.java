package authoring_environment;

import authoring.data.ActionData;
import authoring.data.PatchTypeData;
import authoring.data.PieceTypeData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
        private final String CREATOR_TITLE = "Creator";
        private final int HEIGHT = 600;
        private final int WIDTH = 1000;
        private final int WIDTH2 = 350;
        private final int WINDOW_PADDING = 10;	
        private ActionData myActions = new ActionData();
        
	public VoogaView(){
		PieceTypeData pieceTypeData = new PieceTypeData();
		PatchTypeData patchTypeData = new PatchTypeData();
	
		myLibraryView = new LibraryView(pieceTypeData,patchTypeData);
		myWorkspaceView = new WorkspaceView();
		myMenuView = new MenuView(myWorkspaceView, myLibraryView, myActions);
		
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
	              
		initializeCreator();
	}
	
	private void initializeCreator() {
	    CreatorEditor creatorEditor = new CreatorEditor(myLibraryView, myActions);
            Scene creatorScene = new Scene(new Group(), WIDTH2, HEIGHT);
            Group creatorRoot = (Group) creatorScene.getRoot();
            creatorRoot.getChildren().add(creatorEditor);
            Stage creatorStage = new Stage();
            creatorStage.setTitle(CREATOR_TITLE);
            creatorStage.setX(WIDTH + WINDOW_PADDING);
            creatorStage.setY(0);
            creatorStage.setScene(creatorScene);
            creatorStage.show();        
	}
}