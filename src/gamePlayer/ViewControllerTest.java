package gamePlayer;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javafx.stage.Stage;
import gamedata.gamecomponents.Piece;
import gamedata.stats.Stats;
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
        Piece piece = new Piece(null, null, null, stats, null, 0, null);
        controller.updateStats(piece);
        assertTrue(((Text) controller.statsPane.getChildren().get(0)).getText().equals("HP:  100.0"));
        assertTrue(((Text) controller.statsPane.getChildren().get(1)).getText().equals("MP:  50.0"));
    }

}
