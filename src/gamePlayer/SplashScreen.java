package gamePlayer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

/**
 * Splash screen
 * @author Rica
 *
 */
public class SplashScreen {
    private static final String DUVALL_DANCES = "/resources/images/duvall_gif.gif"; 
    private Scene myScene;
    private ScrollPane myPane;
    private ImageView myImageView;
    
    public SplashScreen() {
        myImageView = new ImageView(new Image(getClass().getResourceAsStream(DUVALL_DANCES)));
        myScene = new Scene(new Group(myImageView));

    }
    
    public Scene getScene() {
        return myScene;
    }

}
