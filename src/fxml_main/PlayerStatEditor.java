package fxml_main;

import gamedata.stats.Stats;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring.concretefeatures.StatsTotalEditor;
import authoring.data.PlayerStatData;

public class PlayerStatEditor extends PopupWindow {
	private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 420;
    private static final int TEXTFIELD_WIDTH = 200;
    private static final String STYLESHEET = "/resources/stylesheets/slategray_layout.css";
	
	private PlayerStatData myPlayerStatData;
	private Stats myStats;

	public PlayerStatEditor(PlayerStatData psd) {
		myPlayerStatData = psd;
		myStats = myPlayerStatData.getStats();
		initialize();
	}

	@Override
	protected void initialize() {
		VBox mainBox = new VBox();

		Label statsLabel = new Label("Player Stats Editor:");
		Button statsBtn = new Button("Set Stats...");
		statsBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StatsTotalEditor statsEditor = 
						new StatsTotalEditor(myPlayerStatData.getStats());
				statsEditor.show();
			}
		});

		Label scoreLabel = new Label("Stat Used as Score:");
		TextField scoreStatField = new TextField(myPlayerStatData.getScoreStat());
		scoreStatField.setMaxWidth(TEXTFIELD_WIDTH);

		Button okBtn = new Button("OK");
		okBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String scoreStat = scoreStatField.getText();
				if (myStats.contains(scoreStat)) {
					myPlayerStatData.setScoreStat(scoreStat);
				} else {
					System.out.println("Stat " + scoreStat + " does not exist.");
				}
				close();
			}
		});

		mainBox.getChildren().addAll(statsLabel, statsBtn,
				scoreLabel, scoreStatField, okBtn);
		Scene scene = new Scene(mainBox,WINDOW_WIDTH,WINDOW_HEIGHT);
		scene.getStylesheets().add(STYLESHEET);
		setScene(scene);
	}
}
