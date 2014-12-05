package gamedata.events;

import gamedata.events.GlobalAction;
import java.util.List;


/**
 * Events are action-reaction pairs that allow users to define complex game rules.
 * Events contain a list of Conditions and Actions. Conditions observe game state, and
 * when all Conditions evaluate to true, the list of Actions are run. Through composition,
 * the behavior of any Event object is highly customizable.
 * 
 * Conditions and Actions are implemented in code, rather than through the authoring
 * environment ("hard-coded"). By allowing users to chain together any number
 * of Conditions and Actions, we provide huge flexibility in determining game rules.
 * 
 * @author Mike Zhu
 *
 */
public class Event {
    List<Condition> myConditions;
    List<GlobalAction> myGlobalActions;

    public Event (List<Condition> conditions, List<GlobalAction> actions) {
        myConditions = conditions;
        myGlobalActions = actions;
    }

    /**
     * Method called by an external Event manager during each "event running" cycle
     * (can be at the end of a user action, end of turn, end of level, etc.).
     * 
     * The list of Conditions is evaluated, and if all return true, the list of Actions
     * is run in order.
     */
    public void runEvent () {
        boolean conditionsFulfilled = true;

        for (Condition c : myConditions) {
            if (!c.evaluate()) {
                conditionsFulfilled = false;
            }
        }

        if (conditionsFulfilled) {
            for (GlobalAction a : myGlobalActions) {
                // TODO: Find a way to run actions where the ACTOR is the game (god?)
                // a.doBehavior(actor, receivers);
            }
        }

    }
}
