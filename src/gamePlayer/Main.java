package gamePlayer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import utilities.leapMotion.LeapMotionListener;
import com.leapmotion.leap.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Method for Game Player
 * @author 
 *
 */
public class Main extends Application{

    @Override
    public void start (Stage arg0) throws Exception {

        new ViewController(arg0);
        LeapMotionListener listener = new LeapMotionListener();

        Controller leapController = new Controller();
        listener.onConnect(leapController);

        ScheduledExecutorService leapPollingThread = Executors
                .newSingleThreadScheduledExecutor();
        leapPollingThread.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                listener.onFrame(leapController);
            }

        }, 10, (int) (1000d / 60d), TimeUnit.MILLISECONDS);

    }



    public static void main(String[] args){
        launch(args);
    }

}