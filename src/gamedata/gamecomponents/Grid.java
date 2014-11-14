package gamedata.gamecomponents;


import java.util.HashMap;
import java.util.Map;

public abstract class Grid {
        private int myRow;
        private int myColumn;

        private Map<Coordinate, Patch> myPatches;
        private Map<Coordinate, Piece> myPieces;

        public Grid(int row, int column) {
            row = myRow;
            column = myColumn;
            
            myPatches =  new HashMap<Coordinate, Patch>();
            myPieces = new HashMap<Coordinate, Piece>();
            
            setGrid();
            
        }

        //set up grid by initializing patches on it
        private void setGrid () {
            for (int x = 0; x < myColumn; x++) {
                for (int y = 0; y < myRow; y++) {
                    Patch patch = new SquarePatch();
                    myPatches.put(new Coordinate(x, y), patch);
                }
            }
            
        }

        public void setPiece(Piece piece, Coordinate coord){
            myPieces.put(coord, piece);
            
        }
        
        public void removePiece(Coordinate coord){
            Piece piece = myPieces.get(coord);
            myPieces.remove(coord, piece);          
        }
        
        public void setPatch(Patch patch, Coordinate coord){
            myPatches.put(coord, patch);
            
        }
        
        public void removePatch(Coordinate coord){
            Patch patch = myPatches.get(coord);
            myPatches.remove(coord, patch);          
        }
        
        public void draw(){
            
        }
        
        public int getColumn(){
            return myColumn;
        }
        
        public int getRow(){
            return myRow;
        }
}
