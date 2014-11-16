package gamePlayer;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class KeyboardControl {
    private Circle myActiveActor;
    
    
    public void controlActiveActor (Grid grid, Scene gameScene) {
        findActiveActor(grid);
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent key) {
                if (key.getCode() == KeyCode.A) {
                    System.out.println("YES A");
                }
                
                if (key.getCode() == KeyCode.B) {
                    System.out.println("YES B");
                }
                
            }
        });
    }
    
    public void findActiveActor(Grid grid){
        myActiveActor = new Circle(100, 100, 50);
        myActiveActor.setFill(Color.BLUE);
    }
    
}
