package tests;

import java.util.ArrayList;
import java.util.List;
import gamedata.JSONManager;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.SquareGrid;
import gamedata.goals.Goal;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gameengine.engine.GameBuilder;
import gameengine.player.Player;
import org.junit.Test;
import com.google.gson.Gson;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Currently using VoogaMain class in gameengine to test instead
 * @author annamiyajima
 *
 */
public class GameBuilderTester {

/*
>>>>>>> 4c3c9cba372ef06197d2b75eecdc25e825d6bbf3:src/tests/GameBuilderTester.java
    private Game myGame;
    private GameBuilder myGameBuilder;
    private List<Player> myPlayers;
    
    @Before
    public void setUp () {
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
        myGame = new Game(myPlayers, myLevels);
        myGameBuilder = new GameBuilder();
        System.out.println(myGame);
    }

    @Test
    public void testGsonRead () {
<<<<<<< HEAD:src/gameengine/tests/GameBuilderTester.java
        Gson gson = new Gson();
        String json = gson.toJson(myGame);
        System.out.println(json);
        Game obj2 = gson.fromJson(json, Game.class);
        assertNotNull(obj2);
=======
        readFromJsonFile();
>>>>>>> 4c3c9cba372ef06197d2b75eecdc25e825d6bbf3:src/tests/GameBuilderTester.java
    }

    @Test
    public void testWriteToJson () {
        myGameBuilder.writePlayersToJSONFile(myPlayers);
<<<<<<< HEAD:src/gameengine/tests/GameBuilderTester.java
    }

    @Test
    public void testReadFromJsonFile () {

=======
>>>>>>> 4c3c9cba372ef06197d2b75eecdc25e825d6bbf3:src/tests/GameBuilderTester.java
    }

    @Test
    public void testReadFromJsonFile () {

    }
*/
}
