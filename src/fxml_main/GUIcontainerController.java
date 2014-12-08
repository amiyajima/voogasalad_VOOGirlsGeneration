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
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIcontainerController implements Initializable{

	private GamePropertiesData myGamePropertiesData;
	private BorderPane rootLayout;
	private Scene myScene;

	@FXML
	private MenuItem gameProperties;
	
	@FXML
	private Tab testauthor;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myGamePropertiesData=new GamePropertiesData();
		LoadAuthoring();
	}

	private void LoadAuthoring(){		
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIcontainerController.class.getResource("Voogirls_Authoring.fxml"));
            rootLayout = (BorderPane) loader.load();

            AuthoringController authorController = loader.getController();

            authorController.initData(myGamePropertiesData);
            
//            rootLayout = (BorderPane) loader.load();
//    		myScene = new Scene(rootLayout);
    		testauthor.setContent(rootLayout);

        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	
	@FXML
	private void showGamePropertiesWindow(){
			GamePropertiesEditor gamePptEditor=new GamePropertiesEditor(myGamePropertiesData);
	}
	

}
