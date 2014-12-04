/**
 * Sample Skeleton for "simple.fxml" Controller Class
 * Use copy/paste to copy paste this code into your favorite IDE
 **/

package fxml_example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class FXMLExampleController implements Initializable {

	//Below is the model
	private FXMLExampleModel myModel;
	
	@FXML //  fx:id="myButton"
	private Button myButton; // Value injected by FXMLLoader

	public FXMLExampleController(){
		
	}

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		assert myButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";
	}
	
    @FXML
    void TestIndependentActions(ActionEvent event){
    	System.out.println("THIS WAS A TEST!");
    }
}