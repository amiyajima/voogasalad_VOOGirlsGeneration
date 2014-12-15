package gamePlayer;

import java.util.List;

import fxml_main.ErrorPopUp;
import gamedata.gamecomponents.Game;
import gameengine.player.Player;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//This entire file is part of my masterpiece.
//Jesse Ling
public class PlayerEditor {

	ViewController myViewController;

	public PlayerEditor(ViewController vc) {
		myViewController = vc;
	}

	/**
	 * Starts screen to choose the type of players
	 */
	protected void editGUIPlayers(Game myGame) {
		Game myModel = myGame;
		Stage stage = new Stage();
		stage.setScene(myViewController.getPlayerScene());
		stage.show();
		for (Player p : myModel.getPlayers()) {
			Label playerLabel = new Label("Player: " + p.toString());
			playerLabel
					.setOnMouseClicked(event -> editSpecificPlayer(p.getID()));
			myViewController.getPlayersList().getChildren().add(playerLabel);
		}
		myViewController
				.getPlayerTypeCombo()
				.getItems()
				.addAll(myViewController.HUMAN_PLAYER,
						myViewController.SIMPLE_AI_PLAYER);
		myViewController.getPlayerTypeCombo().setValue(
				myViewController.HUMAN_PLAYER);
		myViewController.setGameButton();
	}

	protected void editPlayers(Game myGame) {
		try {
			Game myModel = myGame;
			Stage stage = new Stage();
			stage.setScene(myViewController.getPlayerScene());
			stage.show();
			for (Player p : myModel.getPlayers()) {
				Label playerLabel = new Label("Player: " + p.toString());
				playerLabel.setOnMouseClicked(event -> editSpecificPlayer(p
						.getID()));
				myViewController.getPlayersList().getChildren()
						.add(playerLabel);
			}
			myViewController
					.getPlayerTypeCombo()
					.getItems()
					.addAll(myViewController.HUMAN_PLAYER,
							myViewController.SIMPLE_AI_PLAYER);
			myViewController.getPlayerTypeCombo().setValue(
					myViewController.HUMAN_PLAYER);
			myViewController.setGameButton();
		} catch (Exception e) {

		}

	}

	protected void editSpecificPlayer(int playerID) {
		myViewController.setPlayerName(playerID);
		myViewController.getPlayerButton().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				myViewController.getGame().replacePlayer(playerID,
						myViewController.getPlayerTypeCombo().getValue());
			}

		});
	}

	protected void removePreviousPlayer(int playerID) {
		List<Player> myPlayerList = myViewController.getPlayerList();
		int removeIndex = -1;
		for (int i = 0; i < myPlayerList.size(); i++) {
			if (myPlayerList.get(i).getID() == playerID) {
				removeIndex = i;
			}
		}
		if (removeIndex > -1)
			myPlayerList.remove(removeIndex);
	}

}
