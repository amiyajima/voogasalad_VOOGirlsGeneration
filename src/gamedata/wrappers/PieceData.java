package gamedata.wrappers;

import java.util.List;

/**
 * Data object representing a list of piece data objects.
 * 
 * Used in JSON deserialization
 * @author annamiyajima
 *
 */
public class PieceData {
        private List<PieceDataIndividual> myPieces;

        public PieceData (List<PieceDataIndividual> pieces) {
            myPieces = pieces;
        }

        public List<PieceDataIndividual> getPieces () {
            return myPieces;
        }
        
        public String toString(){
            return myPieces.toString();
        }
    
}
