package tests;

import gamedata.JSON.JSONManager;
import gamedata.action.Action;
import gamedata.events.Condition;
import gamedata.events.Event;
import gamedata.events.GlobalAction;
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
import java.util.List;
import authoring.data.PatchData;
import authoring.data.PieceData;
import authoring_environment.GUIGrid;


/**
 * Used to test JSON writer and reader
 * 
 * @author annamiyajima, Rica Zhang
 *
 */
public class VoogaMain {
    public static void testJSONwrite () {
        System.out.println("this should print");
        String saveTo = "src/resources/json/Patch.json";
        // String saveTo = "src/resources/json/RicaSample.json";
        JSONManager myJSONmanager = new JSONManager();
        JSONBobTester jb = new JSONBobTester();

        // POINT
        Point2D point = new Point2D.Double(0, 0);
        
        //GAME
        Game g = jb.createNewGame();
        
        //LEVEL AND LEVEL COMPONENTS
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

        // PIECE AND COMPONENTS
        Piece piece = grid.getPiece(point);
        PieceData multiplePieces = new PieceData();
        multiplePieces.add(piece);
        
        List<Action> actions = piece.getActions();
        Action a = actions.get(0);
        Movement m = piece.getMovement();
        Stats stats = piece.getStats();
        Inventory inventory = piece.getInventory();

        //PATCH AND COMPONENTS
        Patch patch = grid.getPatch(point);
        PatchData multiplePatches = new PatchData();
        multiplePatches.add(patch);

        // System.out.println("things still work");
        // myJSONmanager.writeToJSON(jb.createNewGame(), saveTo);
        // myJSONmanager.writeToJSON(jb.createSuperGrid(), saveTo);
        myJSONmanager.writeToJSON(multiplePatches, "src/resources/json/MultiplePatches.json");
    }

    public static void testJSONload () {
        JSONManager jsonManager = new JSONManager();
        try {
            jsonManager.readFromJSONFile("src/resources/json/MultiplePatches.json");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        System.out.println("main is running");
        // testJSONwrite();
        testJSONload();
    }
}
