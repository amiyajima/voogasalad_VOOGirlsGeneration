package fxml_main;

import java.net.URL;
import java.util.ResourceBundle;

import authoring.createedit.GamePropertiesEditor;
import authoring.data.GamePropertiesData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class GUIcontainerController implements Initializable{

	private GamePropertiesData myGamePropertiesData;

	@FXML
	private MenuItem gameProperties;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myGamePropertiesData=new GamePropertiesData();

	}
	
	
	@FXML
	private void showGamePropertiesWindow(){
			GamePropertiesEditor gamePptEditor=new GamePropertiesEditor(myGamePropertiesData);
	}
	


}
