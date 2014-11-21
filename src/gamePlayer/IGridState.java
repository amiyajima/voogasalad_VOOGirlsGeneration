package gamePlayer;

import gamedata.gamecomponents.Piece;

/**
 *  This class represents the state of the game grid.
 *  Allows different grid states to have different on-click behavior
 */
public interface IGridState {

    public void onClick(Piece piece);
}
