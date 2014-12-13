package authoring.data;

public class GamePropertiesData {

	private int myNumPlayers;
	private String myGridShape;
	
	public GamePropertiesData(int numPlayers, String gridShape){
		myNumPlayers = numPlayers;
		myGridShape = gridShape;
	}
	
	public int getNumPlayers(){
		return myNumPlayers;
	}
	
	public String getGridShape(){
		return myGridShape;
	}
	
//	public void setNumPlayers(int numPlayers){
//		myNumPlayers = numPlayers;
//	}
//	
//	public void setGridShape(String gridShape){
//		myGridShape = gridShape;
//	}
	
}
