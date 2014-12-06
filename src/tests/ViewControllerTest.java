package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.stage.Stage;
import gamePlayer.ViewController;
import gamedata.action.Action;
import gamedata.action.ConcreteAction;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import javafx.scene.text.Text;

import org.junit.Test;



public class ViewControllerTest {

    
    @Test
    public void updateStatsTest () {
        ViewController controller = new ViewController(new Stage());
        Map <String,Double> map = new HashMap<String,Double>();
        map.put("HP", 100.0);
        map.put("MP", 50.0);
        Stats stats = new Stats(map);
        Piece piece = new Piece("Test Piece", null, null, null, stats, new Point2D.Double(0,0), 0, null);
        controller.updateStats(piece);
        assertEquals("HP:  100.0",((Text) controller.statsPane.getChildren().get(0)).getText());
        assertEquals("MP:  50.0",((Text) controller.statsPane.getChildren().get(1)).getText());
    }
    @Test
    public void findPositionTest () {
        ViewController controller = new ViewController(new Stage ());
        Point2D point = controller.findPosition (450.0,120.0);
        assertEquals(5,point.getX(),0.001);
        assertEquals(2,point.getY(),0.001);
        
    }
    @Test 
    public void updateActionsTest () {
        ViewController controller = new ViewController(new Stage());
        Action move = new ConcreteAction("move", null, null, null, null);
        Action attack = new ConcreteAction("attack", null, null, null, null);
        Action build = new ConcreteAction("build", null, null, null, null);
        List<Action> actionList = new ArrayList<Action>();
        actionList.add(move);actionList.add(attack);actionList.add(build);
        Piece piece = new Piece("Test Piece", null, null, null, null, new Point2D.Double(0,0), 0, null);
        controller.updateActions(piece);
        assertEquals ("move", ((Text) controller.controlPane.getChildren().get(0)).getText());
        assertEquals ("attack", ((Text) controller.controlPane.getChildren().get(1)).getText());
        assertEquals ("build", ((Text) controller.controlPane.getChildren().get(2)).getText());
        
    }
    @Test
    public void bindActionTest(){
        ViewController controller = new ViewController(new Stage());
        Action move = new ConcreteAction("move", null, null, null, null);
        controller.bindAction(move);
        assertEquals("move",controller.getActiveAction().toString());
        
    }
    
 

}
