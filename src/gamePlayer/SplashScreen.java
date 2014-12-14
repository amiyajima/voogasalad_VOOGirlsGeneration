package gamePlayer;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Splash screen
 * @author Rica
 *
 */
public class SplashScreen extends Stage {
    private static final String DUVALL_DANCES = "/resources/images/duvall_gif.gif"; 
    private Scene myScene;
    private GridPane myGrid;
    private VBox myV;
    
    public SplashScreen() {
        myGrid = new GridPane();
        myV = new VBox();
        for (int i = 0; i < 3; i++) {
            HBox box = new HBox();
            for (int j = 0; j < 15; j++) {
                ImageView myImageView = new ImageView(new Image(getClass().getResourceAsStream(DUVALL_DANCES)));
                box.getChildren().add(myImageView);
            }
            myV.getChildren().add(box);
        }
        myGrid.getChildren().add(myV);
        myScene = new Scene(myGrid);
        this.setScene(myScene);
//        this.setFullScreen(true);
    }
    
    public void close() {
        
    }
}
