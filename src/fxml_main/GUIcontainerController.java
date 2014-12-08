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
	private BorderPane testauthor;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myGamePropertiesData=new GamePropertiesData();
		LoadAuthoring();
//		testauthor=new BorderPane();
//		testauthor.setStyle("-fx-background-color: blue;");
//		testauthor.setMinSize(100, 100);
//		testauthor.setMaxSize(100, 100);
//		testauthor.setPrefSize(100, 100);
//
//		
//		System.out.println(testauthor.isVisible());
//		System.out.println(testauthor.getWidth());
//		System.out.println(testauthor.getHeight());

//		testauthor=rootLayout;
//		author1=new Tab("TEST");
//        author1.setContent(rootLayout);
	}

	private void LoadAuthoring(){		
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUIcontainerController.class.getResource("Voogirls_Authoring.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout.
//            rootLayout.setStyle("-fx-background-color: blue;");            
//            author1.setContent(rootLayout);
           
//            author1.setContent(new Circle(5));
//            author1.setStyle("-fx-background-color: #333333;");
            
              
//            Stage actionLogicStage = new Stage();
//    		actionLogicStage.setTitle("!!!");
//    		actionLogicStage.initModality(Modality.WINDOW_MODAL);
    		myScene = new Scene(rootLayout);
    		testauthor.setCenter(rootLayout);
//    		actionLogicStage.setScene(myScene);
//    		actionLogicStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	
	@FXML
	private void showGamePropertiesWindow(){
			GamePropertiesEditor gamePptEditor=new GamePropertiesEditor(myGamePropertiesData);
	}
	

}
