package gamedata;

import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ActorRemovalConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.ReceiverRemovalConclusion;
import gamedata.action.ReceiverToInventoryConclusion;
import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.gamecomponents.SquareGrid;
import gamedata.gamecomponents.SquarePatch;
import gamedata.goals.Goal;
import gamedata.goals.PieceOnPatchGoal;
import gamedata.goals.PlayerPiecesRemovedGoal;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
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
        // System.out.println(grid1);
        Rule rule1 = new MoveCountRule(3);
        List<Level> myLevels = new ArrayList<Level>();
        List<Rule> myRules = new ArrayList<Rule>();
        List<Goal> myGoals = new ArrayList<Goal>();

        // testing if subclasses of goal abstract class is initialized properly
        Goal goal1 = new PlayerPiecesRemovedGoal(myPlayer2);
        myGoals.add(goal1);
        // System.out.println(myGoals);
        // this adds myID:0 to my goals

        Goal goal2 = new PlayerPiecesRemovedGoal(myPlayer1);
        myGoals.add(goal2);

        myRules.add(rule1);
        Level level1 = new Level(grid1, myGoals, myRules);
        Level level2 = new Level(grid1, myGoals, myRules);
        myLevels.add(level1);
        myLevels.add(level2);

        // random point2ds to use
        Point2D p1 = new Point2D.Double(1, 1);
        Point2D p2 = new Point2D.Double(2, 2);
        Point2D p3 = new Point2D.Double(3, 3);

        List<Point2D> pl1 = new ArrayList<Point2D>();
        pl1.add(p1);
        pl1.add(p2);

        List<Point2D> pl2 = new ArrayList<Point2D>();
        pl1.add(p1);
        pl1.add(p3);

        List<Point2D> pl3 = new ArrayList<Point2D>();
        pl1.add(p2);
        pl1.add(p3);

        List<Movement> movements = new ArrayList<Movement>();
        Movement m1 = new Movement(pl1);
        Movement m2 = new Movement(pl2);
        movements.add(m1);
        movements.add(m2);

        StatsSingleMultiplier ssm1 = new StatsSingleMultiplier(0, "actor", "health");
        List<StatsSingleMultiplier> ssmList = new ArrayList<StatsSingleMultiplier>();
        ssmList.add(ssm1);

        List<StatsTotalLogic> stlList = new ArrayList<StatsTotalLogic>();
        StatsTotalLogic s1 = new StatsTotalLogic("actor", "health", ssmList);
        stlList.add(s1);

        ActionConclusion ac = new ReceiverToInventoryConclusion();
        List<Action> actions = new ArrayList<Action>();
        Action a1 = new ConcreteAction("kill", pl3, pl2, stlList, ac);

        Stats s = new Stats();

        Inventory i = new Inventory();

        Piece piece1 =
                new Piece("/resources/images/bbybunny.jpeg", movements, actions, s, p2, 0,
                          0, 0, i);
        Piece piece2 =
                new Piece("/resources/images/rcd.png", movements, actions, s, p3, 0, 0,
                          0, i);

        grid1.setPiece(piece1, p1);
        grid1.setPiece(piece2, p2);

        Game myGame = new Game(myPlayers, myLevels);
        JSONManager builder = new JSONManager();

        builder.writeToJSON(myGame, "test");
        /*
         * Player myPlayer3 = new Player();
         * myPlayers.add(myPlayer3);
         * 
         * Game returnedGame = new Game(myPlayers, myLevels);
         * //System.out.println(returnedGame);
         * 
         * try {
         * myPlayer3 = builder.readFromJSONFile("./src/resources/test.json");
         * }
         * catch (FileNotFoundException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         */
    }
}
