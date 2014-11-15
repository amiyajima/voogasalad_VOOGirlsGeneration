package gamePlayer;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import GameEngine.GameModel;

public abstract class ScrollingPane extends ViewComponent{

    
    public static final String SCROLLING_STYLE = "scrollPane";
    private VBox myVBox;
    private int myWidth;
    private int myHeight;
    
    
    public ScrollingPane (GameModel model, Stage stage, int width,
                          int height) {
        super(model, stage);
        myWidth = width;
        myHeight = height;
    }
    
    @Override
    protected Node setUpDisplay(){
        
        ScrollPane sp = new ScrollPane();
        sp.getStyleClass().add(SCROLLING_STYLE);
        sp.setVmax(Integer.MAX_VALUE);
        sp.setPrefSize(myWidth, myHeight);
        myVBox = new VBox();
        sp.setContent(myVBox);
        
        return sp;
        
        
    }
    
    public VBox getContainer(){
        return myVBox;
    }
    
    

}
