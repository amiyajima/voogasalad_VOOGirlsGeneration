package utilities.leapMotion;

import java.awt.Event;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.Type;
import gamePlayer.ViewController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;


public class LeapUIController extends Application {

    public static final String LEAP_UI= "leapUI.fxml";
    public static final String MOUSE_OPTIONS = "/src/utilities/leapMotion/mouseControl";
    public static final String MOUSE_KEY = "mouse";
    public static final String CLICK_KEY = "click";


    @FXML
    private VBox gesturesVBox;

    @FXML
    private ComboBox ClickDropDown;
    @FXML 
    private ComboBox MouseDropDown;
    @FXML
    private BorderPane myRoot;
    private Map<String, String> myControls;
    private Label myCurrentLabel;

    private Stage myStage;


    @Override
    public void start (Stage stage) throws Exception {

        myStage = stage;
        FXMLLoader myloader = new FXMLLoader(getClass().getResource(LEAP_UI));


        myloader.setController(this);

        myloader.load();

        Scene scene = new Scene(myRoot);


        myControls= new HashMap<String, String>();
        setUpDropDown();
        setUpGesture();
       

        stage.setScene(scene);
        stage.show();

    }

    protected void setUpGesture(){
        Type t = (Type) ClickDropDown.valueProperty().getValue();

        gesturesVBox.getChildren().forEach(hbox-> {

           
            Label l = (Label) ((HBox)hbox).getChildren().get(0);
            if(! t.toString().equals(l.getText())){


                hbox.setVisible(true);

                hbox.setOnMouseClicked(event->{ 



                    TextField tf = new TextField();
                    tf.setPromptText("> enter the Key");
                    if(((HBox)hbox).getChildren().size()<2){
                        ((HBox)hbox).getChildren().add(tf);
                        tf.setOnKeyPressed(ke->{
                            if(ke.getCode().equals(KeyCode.ENTER)){
                                tf.getParent().requestFocus();
                                myCurrentLabel = l;
                                mapKey(tf);
                            }
                        });
                    }


                });
            }
            else{
                hbox.setVisible(false);
            }
        });
    }


    private void mapKey(TextField tf){
        if(tf.getText().length()==1){
            myControls.put(myCurrentLabel.getText(), tf.getText());
            // System.out.println(myControls.entrySet().toArray().toString());
        }
        else{
            tf.clear();

            tf.setPromptText(">enter single key");
            tf.getParent().requestFocus();
        }

    }


    /**
     * to set mouse tracking of leap motion
     * allows for dynamic UI dropdown based on the current available options
     */
    protected void setUpDropDown(){
        List<File> options = new ArrayList<File>();

        File files = new File(System.getProperty("user.dir")+ MOUSE_OPTIONS);
        for(File f: files.listFiles()){
            if(f.getName().endsWith(".java")){
                options.add(f);
            }
        }
        options.forEach(file->{
            Text l=new Text();
            //TODO: fix this
            l.setText(file.getName().substring(0, file.getName().length()-5));
            MouseDropDown.getItems().add(l);
            //l.setOnMouseClicked(event->myControls.put(MOUSE_KEY, l.getText()));

        });
        MouseDropDown.valueProperty().setValue(MouseDropDown.getItems().get(0));
        ClickDropDown.getItems().addAll(Gesture.Type.values());
        ClickDropDown.valueProperty().setValue(Gesture.Type.TYPE_SCREEN_TAP);
        ClickDropDown.valueProperty().addListener(event->{setUpGesture();
                                                            onSelect();});

     //   MouseDropDown.valueProperty().setValue(MouseDropDown.getItems().get(0));
        MouseDropDown.valueProperty().addListener(event->onSelect());
        //        
        //        System.out.println(((Label)(MouseDropDown.getSelectionModel().getSelectedItem())).getText());
        //        myControls.put(MOUSE_KEY, ((Label)(MouseDropDown.getSelectionModel().getSelectedItem())).getText());

    }

    private void onSelect(){
//        Label l = ((Label)MouseDropDown.valueProperty().getValue());
        Type t = (Type) ClickDropDown.valueProperty().getValue();
//        myControls.put(MOUSE_KEY, l.getText());
        myControls.put(t.toString(), ""+InputEvent.BUTTON1_DOWN_MASK);
    }

    private void disableGesture(){
        Type t = (Type) ClickDropDown.valueProperty().getValue();
        System.out.println(t.toString());
        gesturesVBox.getChildren().forEach(hbox->{
            String gesture = ((Label) ((HBox)hbox).getChildren().get(0)).getText();
            System.out.println(gesture);

            if(t.toString().equals(gesture)){
                hbox.removeEventHandler(MouseEvent.MOUSE_CLICKED, hbox.getOnMouseClicked());
            }
        });
    }
    @FXML
    private void okayAction() throws IOException{
        
        Label l = ((Label)MouseDropDown.valueProperty().getValue());
        myControls.put(MOUSE_KEY, l.getText());
        PropertyStorage storage = new PropertyStorage(myControls);
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Properties", "*.properties"));
        File f = fc.showSaveDialog(myStage);
        storage.save(f);
        myStage.close();

    }


    public static void main(String[] args){
        launch();
    }

}
