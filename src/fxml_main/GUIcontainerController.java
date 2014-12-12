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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIcontainerController implements Initializable{

	private GamePropertiesData myGamePropertiesData;
	private BorderPane rootLayout;
	private Scene myScene;
	private int myTabCount=0;


	@FXML
	private MenuItem gameProperties;
	
	@FXML
	private MenuItem newAuthor;
	
//	@FXML
//	private Tab testauthor;
	
	@FXML
	private TabPane displayedTabs;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myGamePropertiesData=new GamePropertiesData();
//		BackgroundFill backgroundFill = 
//                new BackgroundFill(null, null, null);
//		displayedTabs.setBackground(new Background(backgroundFill));
//		LoadNewAuthoring();
		
     		displayedTabs=new TabPane();
        	 Tab tab=new Tab("test");
             tab.setContent(rootLayout);
             displayedTabs.getTabs().add(tab);
             displayedTabs.getSelectionModel().select(tab);
             System.out.println(displayedTabs.getTabs().size());


         
	}

	private void LoadNewAuthoring(){		
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIcontainerController.class.getResource("Voogirls_Authoring.fxml"));
            rootLayout = (BorderPane) loader.load();

            AuthoringController authorController = loader.getController();

            authorController.initData(myGamePropertiesData);
            
//          rootLayout = (BorderPane) loader.load();
//    		myScene = new Scene(rootLayout);
            
//            tab.setText("First Tab");
            
//            testauthor.setContent(rootLayout);
            
    		Tab tab=new Tab("Authoring"+ myTabCount++);
			tab.setStyle("-fx-background-color: blue;");
            tab.setContent(rootLayout);
            displayedTabs.getTabs().add(tab);
            displayedTabs.getSelectionModel().select(tab);
            System.out.println(displayedTabs.getTabs().size());


        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	@FXML
	private void showGamePropertiesWindow(){
			GamePropertiesEditor gamePptEditor=new GamePropertiesEditor(myGamePropertiesData);
	}
	

}
