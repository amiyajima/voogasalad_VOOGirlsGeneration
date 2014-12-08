package fxml_main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import authoring.createedit.GamePropertiesEditor;
import authoring.data.GamePropertiesData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GUIcontainerController implements Initializable{

	private GamePropertiesData myGamePropertiesData;
	private BorderPane rootLayout;

	@FXML
	private MenuItem gameProperties;
	
	@FXML
	private Tab author1;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myGamePropertiesData=new GamePropertiesData();
		makeNewTab();
		rootLayout.setStyle("-fx-background-color: blue;");
		author1=new Tab("TEST");
        author1.setContent(rootLayout);
       
	}


	private void makeNewTab(){
		
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIcontainerController.class.getResource("Voogirls_Authoring.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout.
            rootLayout.setStyle("-fx-background-color: blue;");            
//            author1.setContent(rootLayout);
           
//            author1.setContent(new Circle(5));
//            author1.setStyle("-fx-background-color: #333333;");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	
	@FXML
	private void showGamePropertiesWindow(){
			GamePropertiesEditor gamePptEditor=new GamePropertiesEditor(myGamePropertiesData);
	}
	

}
