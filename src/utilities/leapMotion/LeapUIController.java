package utilities.leapMotion;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import gamePlayer.ViewController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LeapUIController extends Application {

    public static final String LEAP_UI= "leapUI.fxml";
    public static final String MOUSE_OPTIONS = "/src/utilities/leapMotion/mouseControl";

    @FXML
    protected HBox screenTap;
    @FXML 
    private HBox circle;

    @FXML
    private HBox mySwipe;

    @FXML
    private HBox myCustomerized;
    
    @FXML 
    private ComboBox MouseDropDown;
    @FXML
    private BorderPane myRoot;


    @Override
    public void start (Stage stage) throws Exception {
  
        FXMLLoader myloader = new FXMLLoader(getClass().getResource(LEAP_UI));

     
        myloader.setController(this);

        myloader.load();

        Scene scene = new Scene(myRoot);
        setUpDropDown();
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
    
    /**
     * to set mouse tracking of leap motion
     * allows for dynamic UI dropdown based on the current available options
     */
//    @FXML
//    private void updateOption(){
//        System.out.println("updating");
//        List<File> options = new ArrayList<File>();
//        
//        File files = new File(System.getProperty("user.dir")+ MOUSE_OPTIONS);
//        for(File f: files.listFiles()){
//            if(f.getName().endsWith(".java")){
//                options.add(f);
//            }
//        }
//        
//        options.forEach(file->{
//            MenuItem l = new MenuItem();
////            Class<?> c = null;
////            try {
////                c = Class.forName("utilities.leapMotion.mouseControl."+ file.getName().substring(0, file.getName().length()-5));
////                Field[] fields = c.getDeclaredFields();
////                System.out.println(fields[0].toString());
////            }
////            catch (Exception e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            }
//            l.setText(file.getName().substring(0, file.getName().length()-5));
//            l.setOnAction(event->{MouseDropDown.getItems().add(l);
//                MouseDropDown.setValue(l); });
//            
////            
//            
//        });
//        
//    }
    
    protected void setUpDropDown(){
        List<File> options = new ArrayList<File>();
        
        File files = new File(System.getProperty("user.dir")+ MOUSE_OPTIONS);
        for(File f: files.listFiles()){
            if(f.getName().endsWith(".java")){
                options.add(f);
            }
        }
        options.forEach(file->{
            Label l=new Label();
            l.setText(file.getName().substring(0, file.getName().length()-5));
            MouseDropDown.getItems().add(l);
            l.setOnMouseClicked(event->MouseDropDown.setValue(l));
                 
        });

        
    }

    
    public static void main(String[] args){
        launch();
    }

}
