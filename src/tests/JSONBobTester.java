package tests;

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
 * Tester for JSON file writer
 * 
 * @author Rica
 *
 */
public class JSONBobTester {
    public JSONBobTester () {
        createNewGame();
    }

    /**
     * Create a new game to test
     * 
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

        List<Rule> myRules = new ArrayList<Rule>();
        Rule rule1 = new MoveCountRule(3);
        myRules.add(rule1);

        List<Goal> myGoals = new ArrayList<Goal>();
        Goal goal1 = new PlayerPiecesRemovedGoal(myPlayer2);
        myGoals.add(goal1);
        Goal goal2 = new PlayerPiecesRemovedGoal(myPlayer1);
        myGoals.add(goal2);

        List<Level> myLevels = new ArrayList<Level>();
        Level level1 = new Level(grid1, myGoals, myRules);
        Level level2 = new Level(grid1, myGoals, myRules);
        myLevels.add(level1);
        myLevels.add(level2);

        Game myGame = new Game(myPlayers, myLevels);
        return myGame;

    }

    public Piece createNewPiece () {
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
        actions.add(a1);

        Stats s = new Stats();
        Inventory i = new Inventory();

        Piece piece = new Piece("/resources/images/rcd.png",
                                movements, actions, s, p3, 5, 6, 7, i);

        return piece;
    }

    public Grid createNewGrid () {
        Grid grid1 = new SquareGrid();
        return grid1;
    }

    public Patch createNewPatch () {
        Point2D p1 = new Point2D.Double(1, 1);
        Patch patch = new SquarePatch(3, "/resources/images/rcd.png", p1);
        return patch;
    }

    public Movement createNewMovement () {
        Point2D p1 = new Point2D.Double(1, 1);
        Point2D p2 = new Point2D.Double(2, 2);
        Point2D p3 = new Point2D.Double(3, 3);

        List<Point2D> pl1 = new ArrayList<Point2D>();
        pl1.add(p1);
        pl1.add(p2);

        List<Point2D> pl2 = new ArrayList<Point2D>();
        pl1.add(p1);
        pl1.add(p3);

        Movement m1 = new Movement(pl1, pl2);
        return m1;
    }

    public Action createNewAction () {
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

        StatsSingleMultiplier ssm1 = new StatsSingleMultiplier(0, "actor", "health");
        List<StatsSingleMultiplier> ssmList = new ArrayList<StatsSingleMultiplier>();
        ssmList.add(ssm1);

        List<StatsTotalLogic> stlList = new ArrayList<StatsTotalLogic>();
        StatsTotalLogic s1 = new StatsTotalLogic("actor", "health", ssmList);
        stlList.add(s1);

        ActionConclusion ac = new ReceiverToInventoryConclusion();

        Action a1 = new ConcreteAction("kill", pl3, pl2, stlList, ac);
        return a1;
    }

}
