package authoring.concretefeatures;

import authoring.abstractfeatures.PopupWindow;

public class EventEditor extends PopupWindow{

	public static final int HEIGHT = 400;
	public static final int WIDTH = 400;
	public static final String NAME = "Event Editor";
	public static final int BUTTON_SPACING = 20;
	
	private String event;
	
	public EventEditor(String s){
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		event = s;
		
		initialize();
	}
	
	@Override
	protected void initialize() {
		
	}

}
