package tests;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.ReceiverToInventoryConclusion;
import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;
import gamedata.events.Event;
import gamedata.gamecomponents.Game;
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
import gameengine.player.HumanPlayer;
import gameengine.player.Player;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import authoring_environment.GUIGrid;
import authoring_environment.SuperGrid;


/**
 * JSON Write Tester
 * 
 * @author Rica Zhang, Anna Miyajima
 *
 */
public class JSONBobTester {
    private static String DEFAULT_DUVALL = "/resources/images/rcd.png";
    private static String DEFAULT_BUNNY = "/resources/images/bbybunny.jpeg";
    private static String DEFAULT_LAND = "/resources/images/Land.jpeg";

    public JSONBobTester () {

    }

    // TODO: remove, used for testing purposes
    public GUIGrid createGUIGrid () {
        GUIGrid test = new GUIGrid(2, 2, 5, "square");
        return test;
    }

    // TODO: remove, used for testing purposes
    public SuperGrid createSuperGrid () {
        SuperGrid grid = new SuperGrid(2, 2, 5, "square");
        return grid;
    }

    /**
     * Create a new game to test
     * 
     * @return a new default game
     */
    public Game createNewGame () {
        System.out.println("Bob Tester: Create new game");
        List<Player> myPlayers = new ArrayList<Player>();

        Player myPlayer1 = new HumanPlayer(12345);
        Player myPlayer2 = new HumanPlayer(54321);
        // myPlayers.add(myPlayer1);
        myPlayers.add(myPlayer2);

        GUIGrid grid = createNewGrid();

        /*
         * List<Rule> myRules = new ArrayList<Rule>();
         * MoveCountRule rule1 = new MoveCountRule(3);
         * MoveCountRule rule2 = new MoveCountRule(5);
         * // myRules.add(rule1);
         * myRules.add(rule2);
         * 
         * List<Goal> myGoals = new ArrayList<Goal>();
         * Goal goal1 = new PlayerPiecesRemovedGoal(myPlayer2);
         * myGoals.add(goal1);
         * // Goal goal2 = new PlayerPiecesRemovedGoal(myPlayer1);
         * // myGoals.add(goal2);
         */

        List<Event> myEvents = new ArrayList<Event>();

        List<Level> myLevels = new ArrayList<Level>();
        Level level1 = new Level(grid, myEvents, "Default ID", false);
        Level level2 = new Level(grid, myEvents, "Default ID", true);
        myLevels.add(level1);
        myLevels.add(level2);

        Piece piece = createNewPiece(grid, new Point2D.Double(3, 3));
        Patch patch = createNewPatch(new Point2D.Double(3, 3));

        Game myGame = new Game(1, myLevels, myLevels.get(0));
        myGame.addPlayer(myPlayer1);
        System.out.println(myGame);
        return myGame;
    }

    public GUIGrid createNewGrid () {
        GUIGrid grid1 = new GUIGrid(5, 5, 75, "Square Grid");

        Piece templ = createNewPiece(grid1, new Point2D.Double(0, 0));
        System.out.println(templ.getImageLocation());
        Patch templPatch = createNewPatch(new Point2D.Double(0, 0));
        System.out.println(templPatch.getImageLocation());

        for (int x = 0; x < grid1.getCol(); x++) {
            for (int y = 0; y < grid1.getRow(); y++) {
                grid1.addPiece(templ, new Point2D.Double(x, y));
                grid1.addPatch(templPatch, new Point2D.Double(x, y));
            }
        }

        System.out.println("Bob Tester: Patches filled: " + grid1.getPatches().getData().size());
        System.out.println("Bob Tester: Pieces filled: " + grid1.getPieces().getData().size());
        System.out.println("Grid created: " + grid1.toString());
        return grid1;
    }

    public Piece createNewPiece (GUIGrid g, Point2D p) {
        Point2D p1 = new Point2D.Double(1, 1);
        Point2D p4 = new Point2D.Double(0, 1);
        Point2D p5 = new Point2D.Double(-1, 0);

        Point2D p2 = new Point2D.Double(2, 2);
        Point2D p3 = new Point2D.Double(3, 3);

        List<Point2D> pl1 = new ArrayList<Point2D>();
        pl1.add(new Point2D.Double(-1, 0));
        pl1.add(new Point2D.Double(1, 0));
        pl1.add(new Point2D.Double(0, 1));
        pl1.add(new Point2D.Double(0, -1));

        List<Point2D> pl2 = new ArrayList<Point2D>();
        pl2.add(p1);
        pl2.add(p3);

        List<Point2D> pl3 = new ArrayList<Point2D>();
        pl3.add(p2);
        pl3.add(p3);

        Movement move = new Movement(g, pl1);

        List<Action> actions = new ArrayList<Action>();
        actions.add(createNewAction(pl1, pl2));
        actions.add(createNewAction(pl2, pl3));

        Stats s = new Stats();
        s.add("health", 20);
        Inventory i = new Inventory();

        Random r = new Random();

        int randomInt = r.nextInt(50);

        Piece piece = null;
        if (randomInt % 2 == 1) {
            piece = new Piece("ID", "Duvall", DEFAULT_DUVALL, move, actions, s, p, 1, i);
        }
        else {
            piece = new Piece("ID", "Bunny", DEFAULT_BUNNY, move, actions, s, p, 1, i);
        }
        return piece;
    }

    public Patch createNewPatch (Point2D p) {
        Patch patch = new Patch("ID", "land", DEFAULT_LAND, p);
        return patch;
    }

    public Movement createNewMovement (GUIGrid g, List<Point2D> pl2) {
        Movement m1 = new Movement(g, pl2);
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
