package tests;

import gamedata.JSON.JSONManager;
import gamedata.action.Action;
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
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gamedata.stats.Stats;
import gameengine.movement.Movement;

import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import authoring.data.ActionData;
import authoring.data.PatchInstanceData;
import authoring.data.PieceInstanceData;
import authoring_environment.GUIGrid;


/**
 * Used to test JSON writer and reader
 * 
 * @author annamiyajima, Rica Zhang
 *
 */
public class JSONTesterMain {
    public static void testJSONwrite (String saveTo) {
        JSONManager myJSONmanager = new JSONManager();
        TestGameCreator jb = new TestGameCreator();

        // POINT
        Point2D.Double point = new Point2D.Double(0, 0);
        Point2D.Double point2 = new Point2D.Double(3,3);
                
        // GAME
        Game g = jb.createNewGame();

        // LEVEL AND LEVEL COMPONENTS
        List<Level> levels = g.getLevels();
        Level l = g.getCurrentLevel();
        GUIGrid grid = l.getGrid();
        List<Event> events = l.getEvents();

        // EVENT COMPONENTS
        Event e = events.get(0);
        List<Condition> conditions = e.getConditions();
        Condition c = conditions.get(0);
        List<GlobalAction> globalActions = e.getGlobalActions();
        GlobalAction gl = globalActions.get(0);

        // PIECE and PIECE COMPONENTS -- DONE
        Piece piece = grid.getPiece(point); 
        PieceInstanceData multiplePieces = new PieceInstanceData();
        multiplePieces.add(piece);

        // PIECE COMPONENTS
        List<Action> actionList = piece.getActions(); 
        Action a = actionList.get(0); 
        ActionData actionData = new ActionData(actionList); 
        Movement m = piece.getMovement(); //INCLUDED IN ACTIONS
        Stats stats = piece.getStats(); //WHY IS THIS TRANSIENT?
        Inventory inventory = piece.getInventory(); 
        
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


        // PATCH AND COMPONENTS -- DONE
        //Patch patch = grid.getPatch(point); // WORKS
        //Patch patch2 = grid.getPatch(point2);
        //PatchData multiplePatches = grid.getPatchData(); // WORKS
        GlobalAction gl2 = new DeletePieceAtLocation(new Point2D.Double(0, 0));
        myJSONmanager.writeToJSON(g, saveTo);
    }

    public static void testJSONload (String loadFrom) {
        System.out.println("\n\nLoading...");
        JSONManager jsonManager = new JSONManager();
        try {
            jsonManager.readFromJSONFile(loadFrom);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        String link = "src/resources/json/THEGAME.json";
        //String localLink = "C:\\Users\\Rica\\Desktop\\Rica-GamePlayer.json";
        testJSONwrite(link);
        testJSONload(link);
    }
}
