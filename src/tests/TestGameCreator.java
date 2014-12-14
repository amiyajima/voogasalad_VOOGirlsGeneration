package tests;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;
import gamedata.action.conclusions.ReceiverToInventoryConclusion;
import gamedata.events.Condition;
import gamedata.events.Event;
import gamedata.events.GlobalAction;
import gamedata.events.conditions.IsDead;
import gamedata.events.globalaction.DeletePieceAtLocation;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.goals.Goal;
import gamedata.goals.PlayerPiecesRemovedGoal;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import gameengine.player.HumanPlayer;
import gameengine.player.Player;
import gameengine.player.SimpleAIPlayer;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import authoring_environment.GUIGrid;
import authoring_environment.SuperGrid;

/**
 * Creates a test game and various game components
 * @author Rica
 *
 */
public class TestGameCreator {

	private static String DEFAULT_DUVALL = "/resources/images/rcd.png";
	private static String DEFAULT_BUNNY = "/resources/images/bbybunny.jpeg";
	private static String DEFAULT_RICA = "/resources/images/Rica.png";
	private static String DEFAULT_LAND = "/resources/images/Land.jpeg";
	private static int RANDOMIZE = 0;
	private static int DUVALL = 1;
	private static int BUNNY = 2;
	private static int RICA = 3;

	public TestGameCreator() {

	}

	// TODO: remove, used for testing purposes
	public GUIGrid createGUIGrid() {
		GUIGrid test = new GUIGrid(2, 2, 5, "square");
		return test;
	}

	// TODO: remove, used for testing purposes
	public SuperGrid createSuperGrid() {
		SuperGrid grid = new SuperGrid(2, 2, 5, "square");
		return grid;
	}

	/**
	 * Create a new game to test
	 * 
	 * @return a new default game
	 */
	public Game createNewGame() {
		System.out.println("TCG: Create new game");
		List<Player> myPlayers = new ArrayList<Player>();
		Player myPlayer1 = new HumanPlayer(1);
		Player myPlayer2 = new SimpleAIPlayer(2);
		myPlayers.add(myPlayer1);
		myPlayers.add(myPlayer2);

		GUIGrid gridLevel1 = createNewGrid();
		GUIGrid gridLevel2 = createNewGrid();

		List<Event> myEvents = new ArrayList<Event>();

		Event e1 = new Event("Garbage Collection Event");
		Condition c = new IsDead("health");
		GlobalAction gl = new DeletePieceAtLocation(new Point2D.Double(0, 0));
		e1.getConditions().add(c);
		e1.getGlobalActions().add(gl);
		myEvents.add(e1);

		List<Level> myLevels = new ArrayList<Level>();
		Level level1 = new Level(gridLevel1, myEvents, "Level 1", false);
		Level level2 = new Level(gridLevel2, myEvents, "Level 2", true);
		myLevels.add(level1);
		myLevels.add(level2);

		Game myGame = new Game(2, myLevels, myLevels.get(0));
		myGame.addPlayers(myPlayers);
		System.out.println("TGC: Game Created " + myGame);
		return myGame;
	}

	public GUIGrid createNewGrid() {
		GUIGrid grid1 = new GUIGrid(5, 5, 75, "Square Grid");

		Piece randomTemplate = createNewPiece(grid1, new Point2D.Double(0, 0),
				0);
		Piece duvallTemplate = createNewPiece(grid1, new Point2D.Double(0, 0),
				1);
		Piece bunnyTemplate = createNewPiece(grid1, new Point2D.Double(0, 0), 2);
		Piece ricaTemplate = createNewPiece(grid1, new Point2D.Double(0, 0), 3);
		Patch templPatch = createNewPatch(new Point2D.Double(0, 0));

		for (int x = 0; x < grid1.getNumCols(); x++) {
			for (int y = 0; y < grid1.getNumRows(); y++) {
				Piece actual;
				if (x == y) {
					actual = new Piece(ricaTemplate, new Point2D.Double(x, y));
				} else {
					actual = new Piece(randomTemplate, new Point2D.Double(x, y));
				}
				if (x == 0) {
					actual.setPlayerID(2);
				} else {
					actual.setPlayerID(1);
				}
				grid1.addPieceAtLoc(actual, new Point2D.Double(x, y));
				grid1.addPatchAtLoc(templPatch, new Point2D.Double(x, y));
			}
		}
		return grid1;
	}

	public Piece createNewPiece(GUIGrid g, Point2D.Double p, int type) {
		Point2D.Double p1 = new Point2D.Double(1, 1);
		Point2D.Double p4 = new Point2D.Double(1, 0);
		Point2D.Double p5 = new Point2D.Double(-1, 0);

		Point2D.Double p2 = new Point2D.Double(2, 2);
		Point2D.Double p3 = new Point2D.Double(3, 3);

		List<Point2D.Double> pl1 = new ArrayList<Point2D.Double>();
		pl1.add(new Point2D.Double(-1, 0));
		pl1.add(new Point2D.Double(1, 0));
		pl1.add(new Point2D.Double(0, 1));
		pl1.add(new Point2D.Double(0, -1));

		List<Point2D.Double> pl2 = new ArrayList<Point2D.Double>();
		pl2.add(p4);

		List<Point2D.Double> pl3 = new ArrayList<Point2D.Double>();
		pl3.add(p2);
		pl3.add(p3);

		List<Point2D.Double> pl4 = new ArrayList<Point2D.Double>();
		pl4.add(p5);
		pl4.add(p3);
		
		List<List<Point2D.Double>> paths = new ArrayList<List<Point2D.Double>>();
		paths.add(pl3);
		Movement move = new Movement(pl1);

		List<Action> actions = new ArrayList<Action>();
		actions.add(createNewAction(pl2, pl4));
		actions.add(createNewAction(pl1, pl3));
		//actions.add(move);

		Stats s = new Stats();
		s.add("health", 20);
		Inventory i = new Inventory();

		Random r = new Random();

		int randomInt = r.nextInt(50);

		Piece piece = null;
		if (type == 0) {
			if (randomInt % 2 == 1) {
				piece = new Piece("ID", "Duvall", DEFAULT_DUVALL, move, actions, s,
						p, 1, false, false);
			} else {
				piece = new Piece("ID", "Bunny", DEFAULT_BUNNY, move, actions, s, p,
						1, false, false);
			}
		} else if (type == 1) {
			piece = new Piece("Duvall_ID", "Duvall", DEFAULT_DUVALL, move, actions,
					s, p, 1, false, false);
		} else if (type == 2) {
			piece = new Piece("Bunny_ID", "Bunny", DEFAULT_BUNNY, move, actions, s,
					p, 1, false, false);
		} else {
			piece = new Piece("Rica_ID", "Rica", DEFAULT_RICA, move, actions, s, p,
					1, false, false);
		}
		return piece;
	}
	
	/*
	public Inventory createNewInventory(Piece item) {
	     Inventory i = new Inventory();
	     i.addItem(item);
	     return i;

	}
	*/

	public Patch createNewPatch(Point2D.Double p) {
		Patch patch = new Patch("ID", "land", DEFAULT_LAND, p);
		return patch;
	}

	public Movement createNewMovement() {
	    Point2D.Double p2 = new Point2D.Double(2, 2);
	    Point2D.Double p3 = new Point2D.Double(3, 3);

            List<Point2D.Double> pl1 = new ArrayList<Point2D.Double>();
            pl1.add(new Point2D.Double(-1, 0));
            pl1.add(new Point2D.Double(1, 0));
            pl1.add(new Point2D.Double(0, 1));
            pl1.add(new Point2D.Double(0, -1));

            List<Point2D.Double> pl3 = new ArrayList<Point2D.Double>();
            pl3.add(p2);
            pl3.add(p3);

            List<List<Point2D.Double>> paths = new ArrayList<List<Point2D.Double>>();
            paths.add(pl3);
            Movement move = new Movement(pl1);
            return move;
	}

	public Action createNewAction(List<Point2D.Double> pl1, List<Point2D.Double> pl2) {
		List<StatsTotalLogic> stlList = new ArrayList<StatsTotalLogic>();
		stlList.add(createNewStatsTotalLogic());
		ActionConclusion ac = new ReceiverToInventoryConclusion();
		Action a1 = new ConcreteAction("kill", pl1, pl2, stlList, ac);
		return a1;
	}
	
	public StatsSingleMultiplier createStatsSingleMultiplier() {
               return new StatsSingleMultiplier(10, "actor", "health");
	}
	
	public StatsTotalLogic createNewStatsTotalLogic() {
	   StatsSingleMultiplier ssm1 = new StatsSingleMultiplier(0, "actor", "health");
	   StatsSingleMultiplier ssm2 = new StatsSingleMultiplier(20, "actor", "health");
	   List<StatsSingleMultiplier> ssmList = new ArrayList<StatsSingleMultiplier>();
           ssmList.add(ssm1);
           ssmList.add(ssm2);
           
           List<StatsTotalLogic> stlList = new ArrayList<StatsTotalLogic>();
           StatsTotalLogic s1 = new StatsTotalLogic("actor", "health", ssmList);
           stlList.add(s1);

           return s1;
	}
	
	public Event createNewEvent() {
	    Event e1 = new Event("Garbage Collection Event");
            Condition c = new IsDead("health");
            GlobalAction gl = new DeletePieceAtLocation(new Point2D.Double(0, 0));
            e1.getConditions().add(c);
            e1.getGlobalActions().add(gl);
            return e1;
	}


}
