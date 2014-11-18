package voogasalad_VOOGirlsGeneration;

public class ApplyState implements GridState{

    private ViewController myController;
    
    public ApplyState(ViewController controller){
        myController = controller;
    }

    @Override
    public void onClick() {
        GameGrid grid = myController.getGrid();
        grid.getChildren().forEach(node-> node.setOnMouseClicked(event->onMouseClick()));
        
    }
    
    private void onMouseClick(){
        
    }

}
