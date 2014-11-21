package authoring.concretefeatures.menus;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.SquareGrid;
import gamedata.goals.Goal;
import gamedata.goals.PlayerPiecesRemovedGoal;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gameengine.engine.GameBuilder;
import gameengine.player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Tester for JSON file writer
 * @author Rica
 *
 */
public class JSONBobTester {
    public JSONBobTester() {
        createNewGame();
    }

    /**
     * Create a new game to test
     * @return
     */
    public Game createNewGame () {
        System.out.println("JSONBobTester is running");

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

        // testing if subclasses of goal abstract class is initialized properly
        Goal goal1 = new PlayerPiecesRemovedGoal(myPlayer2);
        myGoals.add(goal1);

        Goal goal2 = new PlayerPiecesRemovedGoal(myPlayer1);
        myGoals.add(goal2);

        myRules.add(rule1);
        Level level1 = new Level(grid1, myGoals, myRules);
        Level level2 = new Level(grid1, myGoals, myRules);
        myLevels.add(level1);
        myLevels.add(level2);
        
        Player myPlayer3 = new Player();
        myPlayers.add(myPlayer3);

        Game myGame = new Game(myPlayers, myLevels);
        return myGame;
        
    }

}
