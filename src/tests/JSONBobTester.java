package tests;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
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
import gamedata.goals.Goal;
import gamedata.goals.PlayerPiecesRemovedGoal;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import gameengine.player.Player;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * JSON Write Tester
 * 
 * @author Rica Zhang :D 
 *
 */
public class JSONBobTester {
    private static String DEFAULT_DUVALL = "/resources/images/rcd.png";
    private static String DEFAULT_BUNNY = "/resources/images/bbybunny.jpeg";
    private static String DEFAULT_LAND = "/resources/images/Land.jpeg";

    public JSONBobTester () {

    }

    /**
     * Create a new game to test
     * 
     * @return a new default game
     */
    public Game createNewGame () {
        System.out.println("Bob Tester: Create new game");
        List<Player> myPlayers = new ArrayList<Player>();
        Player myPlayer1 = new Player(12345);
        Player myPlayer2 = new Player(54321);
        // myPlayers.add(myPlayer1);
        myPlayers.add(myPlayer2);

        Grid grid = createNewGrid();

        List<Rule> myRules = new ArrayList<Rule>();
        MoveCountRule rule1 = new MoveCountRule(3);
        MoveCountRule rule2 = new MoveCountRule(5);
        // myRules.add(rule1);
        myRules.add(rule2);

        List<Goal> myGoals = new ArrayList<Goal>();
        Goal goal1 = new PlayerPiecesRemovedGoal(myPlayer2);
        myGoals.add(goal1);
        //Goal goal2 = new PlayerPiecesRemovedGoal(myPlayer1);
        //myGoals.add(goal2);

        List<Level> myLevels = new ArrayList<Level>();
        Level level1 = new Level(grid, myGoals, myRules);
        Level level2 = new Level(grid, myGoals, myRules);
        myLevels.add(level1);
        myLevels.add(level2);

        Piece piece = createNewPiece(new Point2D.Double(3, 3));
        Patch patch = createNewPatch(new Point2D.Double(3, 3));

        Game myGame = new Game(myPlayers, myLevels);
        return myGame;
    }

    public Grid createNewGrid () {
        Grid grid1 = new SquareGrid();
        for (int x = 0; x < grid1.getColumn(); x++) {
            for (int y = 0; y < grid1.getRow(); y++) {
                Patch patch = createNewPatch(new Point2D.Double(x, y));
                grid1.setPatch(patch.getLoc(), patch);
                //if((x==grid1.getColumn()/2) && (y==grid1.getRow()/2)){
                	   Piece piece = createNewPiece(new Point2D.Double(x, y));
                       grid1.setPiece(piece.getLoc(), piece);
                //}
            }
        }
        System.out.println("Bob Tester: Patches filled: " + grid1.getAllPatches().size());
        System.out.println("Bob Tester: Pieces filled: " + grid1.getAllPieces().size());
        System.out.println("Grid created: " + grid1.toString());
        return grid1;
    }

    public Piece createNewPiece (Point2D p) {
        Point2D p1 = new Point2D.Double(1, 1);
        Point2D p4 = new Point2D.Double(0, 1);
        Point2D p5 = new Point2D.Double(-1, 0);
        
        Point2D p2 = new Point2D.Double(2, 2);
        Point2D p3 = new Point2D.Double(3, 3);

        List<Point2D> pl1 = new ArrayList<Point2D>();
        pl1.add(new Point2D.Double(-1,0));
        pl1.add(new Point2D.Double(1,0));
        pl1.add(new Point2D.Double(0,1));
        pl1.add(new Point2D.Double(0,-1));

        List<Point2D> pl2 = new ArrayList<Point2D>();
        pl2.add(p1);
        pl2.add(p3);

        List<Point2D> pl3 = new ArrayList<Point2D>();
        pl3.add(p2);
        pl3.add(p3);

        List<Movement> movements = new ArrayList<Movement>();

        //movements.add(createNewMovement(pl1));
        //movements.add(createNewMovement(pl3));

        List<Action> actions = new ArrayList<Action>();
        actions.add(createNewAction(pl1, pl2));
        actions.add(createNewAction(pl2, pl3));
        actions.add(createNewMovement(pl1));

        Stats s = new Stats();
        s.add("health", 20);
        Inventory i = new Inventory();

        Random r = new Random();
        
        int randomInt = r.nextInt(50);
        
        Piece piece = null;
        if (randomInt % 2 == 1) {
            piece = new Piece("Duvall", DEFAULT_DUVALL, movements, actions, s, p, 1, i);
        }
        else {
            piece = new Piece("Bunny", DEFAULT_BUNNY, movements, actions, s, p, 1, i);
        }
        return piece;
    }

    public Patch createNewPatch (Point2D p) {
        Patch patch = new Patch("", DEFAULT_LAND, p);
        return patch;
    }

    public Movement createNewMovement (List<Point2D> pl2) {
    	Movement m1 = new Movement(pl2);
    	return m1;
     }

    public Action createNewAction (List<Point2D> pl1, List<Point2D> pl2) {
        StatsSingleMultiplier ssm1 = new StatsSingleMultiplier(0, "actor", "health");
        List<StatsSingleMultiplier> ssmList = new ArrayList<StatsSingleMultiplier>();
        ssmList.add(ssm1);

        List<StatsTotalLogic> stlList = new ArrayList<StatsTotalLogic>();
        StatsTotalLogic s1 = new StatsTotalLogic("actor", "health", ssmList);
        stlList.add(s1);

        ActionConclusion ac = new ReceiverToInventoryConclusion();

        Action a1 = new ConcreteAction("kill", pl1, pl2, stlList, ac);
        return a1;
    }
}
