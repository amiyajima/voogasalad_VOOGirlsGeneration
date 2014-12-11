package tests;

import java.io.BufferedReader;
import com.google.gson.Gson;
import gamedata.goals.Goal;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gamedata.wrappers.GoalData;
import gamedata.wrappers.GridData;
import gamedata.wrappers.LevelData;
import gamedata.wrappers.LevelDataIndividual;
import gamedata.wrappers.PatchData;
import gamedata.wrappers.PatchDataIndividual;
import gamedata.wrappers.PieceDataIndividual;
import gamedata.wrappers.PlayerData;
import gamedata.wrappers.PlayerDataIndividual;
import gamedata.wrappers.RuleData;
import gameengine.player.Player;

/**
 * JSON Read Tester
 * @author Anna Miyajima, Rica Zhang
 *
 */
public class JSONParseTester {
    
    public void testRead(Gson myGson, BufferedReader br) {
        // need to add {myLevels: before and } after list
        LevelData myLevels = myGson.fromJson(br, LevelData.class);
        System.out.println(myLevels.getLevels().get(0));
        // level data exists and contains levels. goals and rules within the level is empty.
        
        //works using singleLevel.json
        LevelDataIndividual mySingleLevel = myGson.fromJson(br, LevelDataIndividual.class);
        System.out.println(mySingleLevel);

        // works using Grid.json
        GridData gridData = myGson.fromJson(br, GridData.class);
        System.out.println(gridData);

        PlayerDataIndividual playerData = myGson.fromJson(br, PlayerDataIndividual.class);
        System.out.println(playerData);

        // works using SinglePatch.json
        PatchDataIndividual patchData = myGson.fromJson(br, PatchDataIndividual.class);
        System.out.println(patchData.getMyTypeID());

        // works using SinglePiece.json
        PieceDataIndividual pieceData = myGson.fromJson(br, PieceDataIndividual.class);
        System.out.println(pieceData.getMyTypeID());
        
        // map of patches
        PatchData myPatches = myGson.fromJson(br, PatchData.class);
        System.out.println(myPatches);

        // goal data is created, but contains no goals. does not work if it is called after rule
        // data
//        GoalData myGoals = myGson.fromJson(br, GoalData.class);
//        System.out.println(myGoals.toString());

        // ruledata exists but contains no rules. does not work if it is called after goal data
//        RuleData ruledata = myGson.fromJson(br, RuleData.class);
//        System.out.println(ruledata);
        // issue = how to represent "properties" and put it in the IndividualRuleData super class
        // same results whether rule data uses RuleDataIndividual or MoveCountRuleData. seems like
        // the constructor within isn't called.

        // when tested with SingleRule.json, creates a single MoveCountRule
        Rule rule = myGson.fromJson(br, MoveCountRule.class);
        System.out.println(rule.toString());

        // when tested with the SingleGoal json, creates a PlayerPiecesRemovedGoal
//        Goal g = myGson.fromJson(br, Goal.class);
//        System.out.println(g.toString());

        PlayerData myPlayers = myGson.fromJson(br, PlayerData.class);
        System.out.println(myPlayers.getPlayers().get(0).getID());
        System.out.println(myPlayers.getPlayers().get(1).getID());

        Player player = myGson.fromJson(br, Player.class);
        System.out.println(player.toString());

    }

}
