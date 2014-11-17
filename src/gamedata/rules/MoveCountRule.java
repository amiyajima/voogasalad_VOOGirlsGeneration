package gamedata.rules;

public class MoveCountRule extends Rule {

    private int myNumMoves;

    public MoveCountRule (int moves) {
        myNumMoves = moves;
    }

    @Override
    public boolean conditionsMet (int turnCount) {
        return (turnCount < myNumMoves);
    }

}
