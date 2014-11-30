package gamedata.rules;

import gamedata.gamecomponents.Piece;

/**
 * A rule that checks if a piece can attack another piece
 * For example, a piece that belongs to one player cannot attack another piece
 * that belongs to the same player
 * 
 * TODO: DEPRECIATED? Move into actions.
 *
 */
public class CanAttackRule extends Rule {

    public CanAttackRule () {

    }

    @Override
    public boolean conditionsMet (int x) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * @param a piece that attacks
     * @param b piece that's attacked
     * @return
     */
    public boolean conditionsMet (Piece a, Piece b){
        return (a.getPlayerID() != b.getPlayerID());
        
    }

}
