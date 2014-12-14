package fxml_main;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ErrorPopUp extends Stage {
    public ErrorPopUp(String errorMessage) {
        TextArea myText = new TextArea(errorMessage);
        myText.setEditable(false);
        Scene myScene = new Scene(myText);
        this.setScene(myScene);
        this.setTitle("Error");
    }

}
