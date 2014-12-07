package authoring_environment;

import javafx.scene.control.Tab;

/** 
 * For now this exists solely to make the Grid more accessible. Otherwise it would be 
 * within a GridView within a BorderPane within this Tab.
 * 
 * I imagine LevelTab might have more uses in the future.
 * @author Mike Zhu
 *
 */

/*
 * TODO: FIND MORE USES FOR THIS CLASS
 * TODO: MAKE IT ACCEPT WHATEVER THE SUPERCLASS OF SANDYGRID WILL BE
 * TOOD: LIKE SERIOUSLY IT HAS NOTHING BUT A GETTER AND A SETTER
 * TODO: WHY IS JAVAFX SO ANNOYING SOMETIMES?!?!
 */
public class LevelTab extends Tab{
	private SuperGrid myGrid;
	
	public void setGrid(SuperGrid grid){
		myGrid = grid;
	}
	
	public SuperGrid getGrid(){
		return myGrid;
	}
}
