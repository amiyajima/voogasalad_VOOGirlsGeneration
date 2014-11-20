package gameengine.tests;

import java.util.ArrayList;
import java.util.List;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.SquareGrid;
import gamedata.goals.Goal;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gameengine.player.Player;
import org.junit.Test;
import com.google.gson.Gson;
import static org.junit.Assert.*;


public class GameBuilderTester {

    @Test
    public void testGsonRead () {
        List<Player> myPlayers = new ArrayList<Player>();
        Player myPlayer1 = new Player();
        Player myPlayer2 = new Player();
        myPlayers.add(myPlayer1);
        myPlayers.add(myPlayer2);

        Grid grid1 = new SquareGrid();
        Rule rule1 = new MoveCountRule(3);
        List<Level> myLevels = new ArrayList<Level>();
        List<Rule> myRules = new ArrayList<Rule>();
        List<Goal> myGoals = new ArrayList<Goal>();
        myRules.add(rule1);
        Level level1 = new Level(grid1, myGoals, myRules);
        Level level2 = new Level(grid1, myGoals, myRules);
        myLevels.add(level1);
        myLevels.add(level2);

        Game myGame = new Game(myPlayers, myLevels);

        Gson gson = new Gson();
        String json = gson.toJson(myGame);
        System.out.println(json);
        
        
        Game obj2 = gson.fromJson(json, Game.class);
        assertTrue(myGame.equals(obj2));
    }

}
