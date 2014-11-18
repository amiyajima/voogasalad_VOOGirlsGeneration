package voogasalad_VOOGirlsGeneration;

import gamedata.gamecomponents.Piece;

public class SelectState implements GridState {
    private ViewController myController;

    public SelectState (ViewController controller) {
        myController = controller;
    }

    @Override
    public void onClick (Piece piece) {
        myController.updateStats(piece);
        myController.setActivePiece(piece);
        myController.updateActions(piece);
    }

}
