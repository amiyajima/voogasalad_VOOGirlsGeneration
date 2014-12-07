package utilities;

import java.io.IOException;
import gamePlayer.ViewController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LeapUIController extends Application {

    public static final String LEAP_UI= "./leapUI.fxml";

    @FXML
    private HBox myScreenTap;
    @FXML 
    private HBox myCircle;

    @FXML
    private HBox mySwipe;

    @FXML
    private HBox myCustomerized;
    private BorderPane myRoot;


    @Override
    public void start (Stage stage) throws Exception {

        myRoot = FXMLLoader.load(getClass().getResource(LEAP_UI));
        
        //loadFXML(LEAP_UI,  myRoot);
        Scene scene = new Scene(myRoot);
        stage.setScene(scene);
        stage.show();

    }

//    public void loadFXML(String url, Node n) {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
//        fxmlLoader.setController(this);
//        fxmlLoader.setRoot(n);
//        try {
//            fxmlLoader.load();
//
//        } catch (IOException exception) {
//
//            throw new RuntimeException(exception);
//        }
//
//    }
    public static void main(String[] args){
        launch();
    }

}
