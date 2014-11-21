package gameengine.engine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.SquareGrid;
import gamedata.gamecomponents.SquarePatch;
import gamedata.goals.Goal;
import gamedata.goals.PieceOnPatchGoal;
import gamedata.goals.PlayerPiecesRemovedGoal;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gameengine.player.Player;


/**
 * Main method used to test Game/Player/Level/Rule/Grid etc. interaction on the
 * back end
 * 
 * @author annamiyajima
 *
 */
public class VoogaMain {
    public static void main (String[] args) {
        System.out.println("main is running");

        List<Player> myPlayers = new ArrayList<Player>();
        Player myPlayer1 = new Player();
        Player myPlayer2 = new Player();
        myPlayers.add(myPlayer1);
        myPlayers.add(myPlayer2);

        Grid grid1 = new SquareGrid();
        //System.out.println(grid1);
        Rule rule1 = new MoveCountRule(3);
        List<Level> myLevels = new ArrayList<Level>();
        List<Rule> myRules = new ArrayList<Rule>();
        List<Goal> myGoals = new ArrayList<Goal>();

        // testing if subclasses of goal abstract class is initialized properly
        Goal goal1 = new PlayerPiecesRemovedGoal(myPlayer2);
        myGoals.add(goal1);
        //System.out.println(myGoals);
        // this adds myID:0 to my goals

        Goal goal2 = new PlayerPiecesRemovedGoal(myPlayer1);
        myGoals.add(goal2);

        myRules.add(rule1);
        Level level1 = new Level(grid1, myGoals, myRules);
        Level level2 = new Level(grid1, myGoals, myRules);
        myLevels.add(level1);
        myLevels.add(level2);

        Game myGame = new Game(myPlayers, myLevels);
        GameBuilder builder = new GameBuilder();

        //GameDataWrapper w = new GameDataWrapper(myGame);
        //builder.writeToJSON(myGame, "test");

        Player myPlayer3 = new Player();
        myPlayers.add(myPlayer3);
        
        Game returnedGame = new Game(myPlayers, myLevels);
        //System.out.println(returnedGame);
        
        try {
            myPlayer3 = builder.readFromJSONFile("./src/resources/test.json");
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
