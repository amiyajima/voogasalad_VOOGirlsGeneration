package fxml_main;

import gamedata.gamecomponents.Patch;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import authoring_environment.UIspecs;

/**
 * @author Martin Tamayo
 * 
 */
public class PatchViewer extends Pane {

	private static final int HEIGHT = 150;
	private static final int WIDTH = 150;
	private static final String VIEWER_TITLE = "Patch Viewer";
	private static final String ID_LABEL = "Unique ID: ";
	private static final String NAME_LABEL = "Name: ";
	private static final Insets MARGINS = new Insets(20, WIDTH / 5, 20, WIDTH / 5 - 10);
	private static final String LABEL_CSS = "-fx-font-size: 14pt;";

	private String myID;
	private String myName;
	private String myImageLocation;
	
	public PatchViewer (Patch patch) {
		myID = patch.getID();
		myName = patch.getName();
		myImageLocation = patch.getImageLocation();
		setHeight(HEIGHT);
		setWidth(WIDTH);
		initialize();
	}

	protected void initialize () {

		VBox box = new VBox();
		box.setPadding(MARGINS);
		box.setSpacing(10);

		HBox labelBox = new HBox();
		Label eventsLabel = new Label(VIEWER_TITLE);
		eventsLabel.setStyle(LABEL_CSS);
		labelBox.getChildren().add(eventsLabel);

		HBox ids = new HBox();
		ids.setPadding(UIspecs.allPadding);
		ids.setSpacing(5);

		HBox names = new HBox();
		names.setPadding(UIspecs.allPadding);
		names.setSpacing(5);

		HBox images = new HBox();
		images.setPadding(UIspecs.allPadding);
		images.setSpacing(5);

		Label idLabel = new Label(ID_LABEL + myID);
		idLabel.setPadding(UIspecs.topRightPadding);
		ids.getChildren().addAll(idLabel);

		Label nameLabel = new Label(NAME_LABEL + myName);
		nameLabel.setPadding(UIspecs.topRightPadding);
		names.getChildren().addAll(nameLabel);
		
		initImageDisplay(images);
		
		box.getChildren().addAll(labelBox, ids, names, images);

		getChildren().add(box);
	}

	private void initImageDisplay (HBox images) {
		ImageView icon = setImageView();
		icon.setFitHeight(40);
		icon.setFitWidth(40);
		images.getChildren().addAll(icon);
	}

	private ImageView setImageView() {
		if(myImageLocation.startsWith("/")){
			return new ImageView(new Image(getClass().getResourceAsStream(myImageLocation)));
		}
		else{
			return new ImageView(new Image(myImageLocation));
		}
	}
}
