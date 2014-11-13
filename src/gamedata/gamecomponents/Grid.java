package gamedata.gamecomponents;


import java.util.HashMap;
import java.util.Map;

public abstract class Grid {
        private int myRow;
        private int myCol;

        //int[][] = coordinate
        private Map<int[][], Piece> myPieces;
        private Map<int[][], Patch> myPatches;

        public Grid(int height, int width) {
            height = myRow;
            width = myCol;
            myPieces = new HashMap<int[][], Piece>();
            
        }

        public void setPiece(Piece piece, int[][] coord){
            myPieces.put(coord, piece);
            
        }
        
        public void removePiece(int[][] coord){
            Piece piece = myPieces.get(coord);
            myPieces.remove(coord, piece);          
        }
        
        public void setPatch(Patch patch, int[][] coord){
            myPatches.put(coord, patch);
            
        }
        
        public void removePatch(int[][] coord){
            Patch patch = myPatches.get(coord);
            myPatches.remove(coord, patch);          
        }
        
        public void draw(){
            
        }
        
        public int getWidth(){
            return myCol;
        }
        
        public int getHeight(){
            return myRow;
        }
}
