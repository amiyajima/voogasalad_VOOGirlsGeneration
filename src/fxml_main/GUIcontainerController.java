package fxml_main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import authoring.createedit.GamePropertiesEditor;
import authoring.data.GamePropertiesData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

public class GUIcontainerController implements Initializable{

	private GamePropertiesData myGamePropertiesData;

	@FXML
	private MenuItem gameProperties;
	
	@FXML
	private Tab author1;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myGamePropertiesData=new GamePropertiesData();
//		try {
//			makeNewTab();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
	}


	private void makeNewTab() throws IOException {
		author1=new Tab();
		Parent root=null;
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Voogirls_Authoring.fxml"));
		root = loader.load();
        author1.setContent(root);
	}
	
	
	@FXML
	private void showGamePropertiesWindow(){
			GamePropertiesEditor gamePptEditor=new GamePropertiesEditor(myGamePropertiesData);
	}
	

}
