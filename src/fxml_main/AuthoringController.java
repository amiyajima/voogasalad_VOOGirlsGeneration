package fxml_main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import authoring_environment.ShapeGrid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class AuthoringController implements Initializable {

	@FXML
	private ScrollPane myPropertiesSPane;
	@FXML
	private VBox myPiecesVBox;
	@FXML
	private VBox myPatchesVBox;
	@FXML
	private VBox myActionsVBox;
	@FXML
	private ScrollPane myGridSPane;
	
	private ShapeGrid myCurrentGrid;
	private PieceController myPieceController;
	private PatchController myPatchController;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		myPieceController = new PieceController(myPiecesVBox, myPropertiesSPane, myCurrentGrid);
	    myPatchController = new PatchController(myPatchesVBox, myPropertiesSPane, myCurrentGrid);
	}
}