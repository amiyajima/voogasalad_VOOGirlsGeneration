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
    public static void testJSONwrite () {
        String saveTo = "src/resources/json/Patch-Rica.json";
        JSONManager myJSONmanager = new JSONManager();
        TestGameCreator jb = new TestGameCreator();

        // POINT
        Point2D point = new Point2D.Double(0, 0);
        Point2D point2 = new Point2D.Double(3,3);
                
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
        System.out.println("Piece is: " + piece);
        PieceInstanceData multiplePieces = new PieceInstanceData();
        multiplePieces.add(piece);

        // PIECE COMPONENTS
        List<Action> actionList = piece.getActions(); 
        Action a = actionList.get(0); 
        ActionData actionData = new ActionData(actionList); 
        Movement m = piece.getMovement(); //INCLUDED IN ACTIONS
        Stats stats = piece.getStats(); //WHY IS THIS TRANSIENT?
        Inventory inventory = piece.getInventory(); 

        // PATCH AND COMPONENTS -- DONE
        //Patch patch = grid.getPatch(point); // WORKS
        //Patch patch2 = grid.getPatch(point2);
        //PatchData multiplePatches = grid.getPatchData(); // WORKS

        myJSONmanager.writeToJSON(piece, "src/resources/json/Rica-Piece.json");
        //myJSONmanager.writeToJSON(g, "C:\\Users\\Rica\\Desktop\\Rica-GamePlayer.json");        
    }

    public static void testJSONload () {
        System.out.println("\n\n Loading...");
        JSONManager jsonManager = new JSONManager();
        try {
            jsonManager.readFromJSONFile("src/resources/json/Rica-Piece.json");
            //jsonManager.readFromJSONFile("C:\\Users\\Rica\\Desktop\\Rica-GamePlayer.json");        

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        testJSONwrite();
        testJSONload();
    }
}
