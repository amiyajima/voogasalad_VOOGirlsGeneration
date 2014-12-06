package gamePlayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import com.leapmotion.leap.Controller;
import java.awt.AWTException;
import java.awt.Robot;

public class LeapExample extends Application { 
    private SimpleLeapListener listener = new SimpleLeapListener();
    private Controller leapController = new Controller();

    private AnchorPane root = new AnchorPane();
    Robot robot;
    private Circle circle=new Circle(50,Color.DEEPSKYBLUE);

    @Override
    public void start(Stage primaryStage) throws AWTException {
        robot = new Robot();

        leapController.addListener(listener);        
//        circle.setLayoutX(circle.getRadius());
//        circle.setLayoutY(circle.getRadius());
//        root.getChildren().add(circle);
        final Scene scene = new Scene(root, 800, 600); 

        //        ChangeListener cl = new ChangeListener<Point2D>()

        listener.pointProperty().addListener(new ChangeListener<Point2D>(){

            @Override
            public void changed (ObservableValue<? extends Point2D> arg0, Point2D t, Point2D t1) {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        Point2D d=root.sceneToLocal(t1.getX()-scene.getX()-scene.getWindow().getX(),
                                                    t1.getY()-scene.getY()-scene.getWindow().getY());
                        double dx=d.getX(), dy=d.getY();
                        if(dx>=0d && dx<=root.getWidth()-2d*circle.getRadius() && 
                                dy>=0d && dy<=root.getHeight()-2d*circle.getRadius()){
//                            circle.setTranslateX(dx);
//                            circle.setTranslateY(dy);   
                            robot.mouseMove((int)dx, (int)dy);
                            
                        }
                    }
                });

            }

        });



        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void stop(){
        leapController.removeListener(listener);

    }
    
    public static void main(String[] args){
        launch();
    }
}


